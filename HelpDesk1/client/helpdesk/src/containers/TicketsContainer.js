import React from "react";
import axios from "axios";
import TicketsView from "../view/TicketView";
import history from "../history";
import Logout from "../component/Logout";

export default class TicketsContainer extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      tickets: [],
      btnActive: true,
      action: [],
      user: JSON.parse(localStorage.User),
      inputValue: "",
      colorName: "",
    };

    this.toTicketNew = this.toTicketNew.bind(this);
    this.onClickBtn = this.onClickBtn.bind(this);
    this.goToOverview = this.goToOverview.bind(this);
    this.onChangeAction = this.onChangeAction.bind(this);
    this.onChangeState = this.onChangeState.bind(this);
    this.onAction = this.onAction.bind(this);
    this.onMyTickets = this.onMyTickets.bind(this);
    this.onSort = this.onSort.bind(this);
    this.onChangeInput = this.onChangeInput.bind(this);
    this.onChangeTh = this.onChangeTh.bind(this);
    this.onShowTickets = this.onShowTickets.bind(this);
  }
  onChangeInput(e) {
    const validInput =/^[-"'`~.\w#!$%@^&*+=,:;?/<>|()[\]{}]+$/;
    if(validInput.test(e.target.value) || e.target.value===""){
    this.setState({ inputValue: e.target.value });
    }
  }

  onChangeTh(e) {
    if(e.target.id!==this.state.colorName){
      this.setState({ inputValue: "" });
    }
    switch (e.target.id) {
      case "id":
        this.setState({ colorName: "id" });
        break;
      case "name":
        this.setState({ colorName: "name" });
        break;
      case "desiredResolutionDate":
        this.setState({ colorName: "desiredResolutionDate" });
        break;
      case "urgency":
        this.setState({ colorName: "urgency" });
        break;
      case "state":
        this.setState({ colorName: "state" });
        break;
    }
  }

  toTicketNew() {
    history.push("/create");
  }

  goToOverview(e) {
    history.push({
      pathname: "/overview",
      state: e.target.value,
    });
  }

  componentDidMount() {
    axios
      .get(
        "http://localhost:8099/HelpDesk/tickets",
        JSON.parse(localStorage.AuthHeader)
      )
      .then((resp) => {
        this.setState({ tickets: resp.data });
      });
  }

  onClickBtn(e) {
    e.target.id === "btnAllT"
      ? this.setState({ btnActive: true })
      : this.setState({ btnActive: false });
    axios
      .get(
        "http://localhost:8099/HelpDesk/tickets",
        JSON.parse(localStorage.AuthHeader)
      )
      .then((resp) => {
        this.setState({ tickets: resp.data });
      });
  }

  onChangeAction(ticket) {
    var result = [];
    switch (this.state.user.role) {
      case "EMPLOYEE":
        if (ticket.state === "DRAFT" || ticket.state === "DECLINED") {
          result = ["Submit", "Cancel"];
        }
        break;
      case "MANAGER":
        if (ticket.owner.id === this.state.user.id) {
          if (ticket.state === "DRAFT" || ticket.state === "DECLINED") {
            result = ["Submit", "Cancel"];
          }
        } else if (ticket.state === "NEW") {
          result = ["Approve", "Decline", "Cancel"];
        }
        break;
      case "ENGINEER":
        if (ticket.state === "APPROVED") {
          result = ["Assing to Me", "Cancel"];
        }
        if (ticket.state === "IN_PROGRESS") {
          result = ["Done"];
        }
        break;
    }
    return result;
  }

  onAction(data) {
    var result = "";
    switch (data) {
      case "Submit":
        result = "NEW";
        break;
      case "Approve":
        result = "APPROVED";
        break;
      case "Decline":
        result = "DECLINED";
        break;
      case "Cancel":
        result = "CANCELED";
        break;
      case "Assing to Me":
        result = "IN_PROGRESS";
        break;
      case "Done":
        result = "DONE";
        break;
    }

    return result;
  }

  onChangeState(e) {
    var status = this.onAction(e.target.value);
    var ticketId = e.target.name;
    var ticket = this.state.tickets.filter((t) => t.id == ticketId)[0];
    ticket.state = status;
    var newTickets = this.state.tickets.filter((t) => t.id != ticketId);
    this.setState({ tickets: [...newTickets, ticket] });

    axios
      .put(
        "http://localhost:8099/HelpDesk/tickets/" + ticketId + "/" + status,
        null,
        JSON.parse(localStorage.AuthHeader)
      )
      .then((responce) => {})
      .catch((error) => {});
  }

  onMyTickets() {
    var result = [];
    switch (this.state.user.role) {
      case "EMPLOYEE":
        result = this.state.tickets;
        break;
      case "MANAGER":
        result = this.state.tickets.filter(
          (t) =>
            t.owner.id === this.state.user.id ||
            (t.approver
              ? t.approver.id === this.state.user.id && t.state === "APPROVED"
              : false)
        );
        break;
      case "ENGINEER":
        result = this.state.tickets.filter((t) =>
          t.assignee ? t.assignee.id === this.state.user.id : false
        );
        break;
    }
    return result;
  }
  onShowTickets(){
  let result=[];
    if(this.state.btnActive){
      result = this.state.tickets;
    }else{
      result = this.onMyTickets();
    }
    if(this.state.colorName && this.state.inputValue){
      switch (this.state.colorName) {
        case "id":
          result = result.filter((t)=>String(t.id).includes(this.state.inputValue));
          break;
        case "name":
          result = result.filter((t)=>t.name.includes(this.state.inputValue));
          break;
        case "desiredResolutionDate":
          result = result.filter((t)=>t.desiredResolutionDate.includes(this.state.inputValue));
          break;
        case "urgency":
          result = result.filter((t)=>t.urgency.includes(this.state.inputValue.toUpperCase()));
          break;
        case "state":
          result = result.filter((t)=>t.state.includes(this.state.inputValue.toUpperCase()));
          break;
      }
    }
    return result;
  }

  render() {
    var btnClassPrimary = "btn-primary";
    var btnClassDefault = "bnt-default";
    return (
      <div>
        <Logout />
        <TicketsView
          user={this.state.user}
          tickets={this.onShowTickets()}
          btnAllTClass={
            this.state.btnActive ? btnClassPrimary : btnClassDefault
          }
          btnMyTClass={this.state.btnActive ? btnClassDefault : btnClassPrimary}
          onClickBtn={this.onClickBtn}
          onClickBtnMyT={this.onClickBtnMyT}
          toTicketNew={this.toTicketNew}
          goToOverview={this.goToOverview}
          onChangeState={this.onChangeState}
          onChangeAction={this.onChangeAction ? this.onChangeAction : []}
          onSort={this.onSort}
          onChangeInput={this.onChangeInput}
          onChangeTh={this.onChangeTh}
          colorName={this.state.colorName}
          inputValue={this.state.inputValue}
        ></TicketsView>
      </div>
    );
  }

  onSort(e) {
    let order = ["LOW", "AVERAGE", "HIGH", "CRITICAL"];
    let ticketsSort = this.state.tickets;
    switch (e.target.id) {
      case "idUp":
        ticketsSort.sort((a, b) => a.id - b.id);
        break;
      case "idDown":
        ticketsSort.sort((a, b) => b.id - a.id);
        break;
      case "nameUp":
        ticketsSort.sort((a, b) => a.name.localeCompare(b.name));
        break;
      case "nameDown":
        ticketsSort.sort((a, b) => b.name.localeCompare(a.name));
        break;
      case "dateUp":
        ticketsSort.sort(
          (a, b) =>
            new Date(a.desiredResolutionDate) -
            new Date(b.desiredResolutionDate)
        );
        break;
      case "dateDown":
        ticketsSort.sort(
          (a, b) =>
            new Date(b.desiredResolutionDate) -
            new Date(a.desiredResolutionDate)
        );
        break;
      case "UrgencyUp":
        ticketsSort.sort(
          (a, b) => order.indexOf(b.urgency) - order.indexOf(a.urgency)
        );
        break;
      case "UrgencyDown":
        ticketsSort.sort(
          (a, b) => order.indexOf(a.urgency) - order.indexOf(b.urgency)
        );
        break;
      case "StatusUp":
        ticketsSort.sort((a, b) => a.state.localeCompare(b.state));
        break;
      case "StatusDown":
        ticketsSort.sort((a, b) => b.state.localeCompare(a.state));
        break;
    }
    this.setState({ tickets: ticketsSort });
  }
}
