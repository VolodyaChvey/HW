import React from "react";
import EditTicketView from "../view/EditTicketView";
import axios from "axios";
import history from "../history";

export default class EditTicket extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      id: this.props.location.state,
      attachments: [],
    };

    this.onHandleChange = this.onHandleChange.bind(this);
    this.goToOverview = this.goToOverview.bind(this);
    this.onUpdate = this.onUpdate.bind(this);
    this.onChangeState = this.onChangeState.bind(this);
    this.onHandleChangeAttachment = this.onHandleChangeAttachment.bind(this);
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
    var ticket = this.state;
    ticket.state = e.target.value;
    axios
      .put(
        "http://localhost:8099/HelpDesk/tickets/" + this.state.id,
        ticket,
        JSON.parse(localStorage.AuthHeader)
      )
      .then((responce) => {
        if (this.state.text) {
          axios
            .post(
              "http://localhost:8099/HelpDesk/tickets/" +
                this.state.id +
                "/comments",
              {
                text: this.state.text,
                ticketId: this.state.id,
              },
              JSON.parse(localStorage.AuthHeader)
            )
            .then((responce) => {});
        }
        if(this.state.attachments.length!=0){
          var formData= new FormData();
          for(let i of this.state.attachments){
            formData.append("files",i.blob,i.name)
          }
          axios
          .post(
            "http://localhost:8099/HelpDesk/tickets/" +
              this.state.id +
              "/attachments",
            formData,
            JSON.parse(localStorage.AuthHeader)
          )
          .then((responce) => {});
        }

        history.push("/tickets");
      })
      .catch((error) => {});
  }
  onHandleChangeAttachment(e) {
    let files = e.target.files;
    for (let i = 0; i < files.length; i++) {
      let reader = new FileReader();
      reader.onloadend = () => {
        this.setState({
          attachments: [
            ...this.state.attachments,
            {
              name: files[i].name,
              blob: new Blob([reader.result], { type: files[i].type }),
            },
          ],
        });
      };
      reader.readAsBinaryString(files[i]);
    }
  }

  render() {
    return (
      <div>
        <EditTicketView
          id={this.state.id}
          description={this.state.description}
          name={this.state.name}
          onHandleChange={this.onHandleChange}
          onUpdate={this.onUpdate}
          goToOverview={this.goToOverview}
          onHandleChangeAttachment={this.onHandleChangeAttachment}
        ></EditTicketView>
      </div>
    );
  }
}
