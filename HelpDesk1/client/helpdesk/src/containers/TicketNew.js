import React from "react";
import TicketNewView from "../view/TicketNewView";
import axios from "axios";

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
    var status = e.target.value;
    this.setState({ state: status });
    axios
      .post(
        "http://localhost:8099/HelpDesk/tickets",
        {
          desiredResolutionDate: this.state.desiredResolutionDate,
          name: this.state.name,
          description: this.state.discription,
          state: status,
          category: this.state.category,
          comment: this.state.comment,
          urgency: this.state.urgency,
        },
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
          .then((responce) => {
          })
        }
        window.location.href = "/tickets";
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
