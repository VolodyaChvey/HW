import React from "react";
import EditTicketView from "../view/EditTicketView";
import axios from "axios";
import history from "../history";

export default class EditTicket extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      id: this.props.location.state,
    };

    this.onHandleChange = this.onHandleChange.bind(this);
    this.goToOverview = this.goToOverview.bind(this);
    this.onUpdate = this.onUpdate.bind(this);
    this.onChangeState = this.onChangeState.bind(this);
  }
  onChangeState(data) {
    for (var key in data) {
      this.setState({ [key]: data[key] });
    }
  }
  onHandleChange(e) {
    
    this.setState({ [e.target.name]: e.target.value });
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
        "http://localhost:8099/HelpDesk/tickets/" + this.state.id,
        JSON.parse(localStorage.AuthHeader)
      )
      .then((resp) => {
        this.onChangeState(resp.data);
      });
  }
  onUpdate(e) {
    var status = e.target.value;
    console.log(this.state);
    axios
      .put(
        "http://localhost:8099/HelpDesk/tickets/" + this.state.id,
        {
          id: this.state.id,
          desiredResolutionDate: this.state.desiredResolutionDate,
          name: this.state.name,
          description: this.state.description,
          state: status,
          createdOn: this.state.createdOn,
          owner: this.state.owner,
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
            "http://localhost:8099/HelpDesk/tickets/"+ this.state.id +"/comments",
            {
              text: this.state.text,
              ticketId: this.state.id
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
        <EditTicketView
           id={this.state.id}
           onHandleChange={this.onHandleChange}
           onUpdate={this.onUpdate}
           goToOverview={this.goToOverview}
        ></EditTicketView>
      </div>
    );
  }
}
