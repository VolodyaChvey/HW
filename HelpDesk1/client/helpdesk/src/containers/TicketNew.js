import React from "react";
import TicketNewView from "../view/TicketNewView";
import axios from "axios";
import history from "../history";

export default class TicketNew extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      state: null,
      category: null,
      name: null,
      urgency: null,
      desiredResolutionDate: null,
      discription: null,
      comment: null,
    };
    this.toTicketList = this.toTicketList.bind(this);
    this.onSave = this.onSave.bind(this);
    this.onHandleChange = this.onHandleChange.bind(this);
  }
  onHandleChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }
  toTicketList() {
    window.location.href = "/tickets";
  }

  onSave(e) {
    var ticket=this.state;
    ticket.state=e.target.value;
    axios
      .post(
        "http://localhost:8099/HelpDesk/tickets",
        ticket,
        JSON.parse(localStorage.AuthHeader)
      )
      .then((responce) => {
        if(this.state.text){
          axios
          .post(
            "http://localhost:8099/HelpDesk/tickets/"+ responce.data.id +"/comments",
            {
              text: this.state.text,
              ticketId: responce.data.id
            },
            JSON.parse(localStorage.AuthHeader)
          )
          .then((responce) => {})
        }
       history.push("/tickets");
      })
      .catch((error) => {});
  }
  render() {
    return (
      <div>
        <TicketNewView
          toTicketList={this.toTicketList}
          onSave={this.onSave}
          onHandleChange={this.onHandleChange}
        ></TicketNewView>
      </div>
    );
  }
}
