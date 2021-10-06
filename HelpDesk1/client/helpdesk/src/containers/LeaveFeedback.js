import React from "react";
import LeaveFeedbackView from "../view/LeaveFeedbackView";
import history from "../history";
import axios from "axios";
import Logout from "../component/Logout";

export default class LeaveFeedback extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      id: this.props.location.state.id,
      name: this.props.location.state.name,
      rate: 0,
      text: null,
    };
    this.toTicketOverview = this.toTicketOverview.bind(this);
    this.handleStarClick = this.handleStarClick.bind(this);
    this.onHandleChange = this.onHandleChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
    this.onValid = this.onValid.bind(this);
  }

  onValid(data) {
    let result = false;
    const isText = /^[-\s"'`~.\w#!$%@^&*+=,:;?/<>|()[\]{}]{1,500}$/;
    switch (data.name) {
      case "text":
        result = isText.test(data.value);
        break;
      default:
        result = true;
    }
    return result;
  }

  toTicketOverview() {
    history.push({
      pathname: "/overview",
      state: this.state.id,
    });
  }
  handleStarClick(e) {
    e.preventDefault();
    this.setState({ rate: e.target.id });
  }
  onHandleChange(e) {
    e.preventDefault();
    if (this.onValid(e.target)) {
      this.setState({ [e.target.name]: e.target.value });
    }
  }
  onSubmit() {
   if (this.state.rate > 0) {
      axios
        .post(
          "http://localhost:8099/HelpDesk/tickets/" +
            this.state.id +
            "/feedback",
          {
            rate: this.state.rate,
            text: this.state.text,
            ticketId: this.state.id,
          },
          JSON.parse(localStorage.AuthHeader)
        )
        .then((responce) => {
          history.push({
            pathname: "/overview",
            state: this.state.id,
          });
        })
        .catch((error)=>{});
      }
  }

  render() {
    return (
      <div>
        <Logout/>
        <LeaveFeedbackView
          id={this.state.id}
          name={this.state.name}
          rate={this.state.rate}
          toTicketOverview={this.toTicketOverview}
          handleStarClick={this.handleStarClick}
          onHandleChange={this.onHandleChange}
          onSubmit={this.onSubmit}
        ></LeaveFeedbackView>
      </div>
    );
  }
}
