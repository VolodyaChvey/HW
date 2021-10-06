import React from "react";
import FeedbackView from "../view/FeedbackView";
import history from "../history";
import Logout from "../component/Logout";

export default class Feedback extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
        
      id: this.props.location.state.id,
      name: this.props.location.state.name,
      rate: this.props.location.state.rate,
      text: this.props.location.state.text,
    };

    this.toTicketOverview = this.toTicketOverview.bind(this);
  }

  toTicketOverview() {
    history.push({
      pathname: "/overview",
      state: this.state.id,
    });
  }

  render() {
    return (
      <div>
        <Logout/>
        <FeedbackView
          id={this.state.id}
          name={this.state.name}
          rate={this.state.rate}
          text={this.state.text}
          toTicketOverview={this.toTicketOverview}
        ></FeedbackView>
      </div>
    );
  }
}
