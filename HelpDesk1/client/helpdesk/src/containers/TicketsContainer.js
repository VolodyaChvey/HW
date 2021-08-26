import React from "react";
import axios from "axios";
import TicketsView from "../view/TicketView";
import history from "../history";

export default class TicketsContainer extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      tickets: [],
      ticketsPrint:[],
      btnActive: true,
      action: [],
      user:JSON.parse(localStorage.User),
    };

    this.toTicketNew = this.toTicketNew.bind(this);
    this.onClickBtnAllT = this.onClickBtnAllT.bind(this);
    this.onClickBtnMyT = this.onClickBtnMyT.bind(this);
    this.goToOverview = this.goToOverview.bind(this);
    this.onChangeAction = this.onChangeAction.bind(this);
    this.onChangeState = this.onChangeState.bind(this);
    this.onAction = this.onAction.bind(this);
    this.onMyTickets = this.onMyTickets.bind(this);
    this.onSortIdUp = this.onSortIdUp.bind(this);
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
        "http://localhost:8099/HelpDesk/tickets/all",
        JSON.parse(localStorage.AuthHeader)
      )
      .then((resp) => {
        this.setState({ tickets: resp.data });
        this.setState({ticketsPrint:this.state.tickets})
      });
  }

  onClickBtnAllT() {
    this.setState({ btnActive: true });
    this.setState({ticketsPrint:this.state.tickets})
  }

  onClickBtnMyT() {
    this.setState({ btnActive: false });
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
    var ticket = this.state.tickets.filter(t=>t.id==ticketId)[0];
    ticket.state = status;
    var newTickets = this.state.tickets.filter(t=>t.id!=ticketId);
    this.setState({tickets:[...newTickets,ticket]})
   
    axios
      .put(
        "http://localhost:8099/HelpDesk/tickets/" + ticketId + "/" + status,
        null,
        JSON.parse(localStorage.AuthHeader)
      )
      .then((responce) => {
      
      })
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
            t.owner.id === this.state.user.id
            ||(t.approver?(t.approver.id === this.state.user.id && t.state === "APPROVED"):false)
        );
        break;
      case "ENGINEER":
        result = this.state.tickets.filter((t) =>
         (t.assignee?(t.assignee.id === this.state.user.id):false));
        break;
    }
   
    return result;
  }

  render() {
    var btnClassPrimary = "btn-primary";
    var btnClassDefault = "bnt-default";
    return (
      <TicketsView
        user={this.state.user}
        tickets={this.state.btnActive ? this.state.tickets : this.onMyTickets()}
        btnAllTClass={this.state.btnActive ? btnClassPrimary : btnClassDefault}
        btnMyTClass={this.state.btnActive ? btnClassDefault : btnClassPrimary}
        onClickBtnAllT={this.onClickBtnAllT}
        onClickBtnMyT={this.onClickBtnMyT}
        toTicketNew={this.toTicketNew}
        goToOverview={this.goToOverview}
        onChangeState={this.onChangeState}
        onChangeAction={this.onChangeAction ? this.onChangeAction : []}
        onSortIdUp={this.onSortIdUp}
      ></TicketsView>
    );
  }

  onSortIdUp(e){
    console.log(e.target.id)
    this.state.tickets.sort((a,b) => a.id - b.id)
    this.setState({tickets:this.state.tickets})
  }
}
