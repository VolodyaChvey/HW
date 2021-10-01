import React from "react";
import EditTicketView from "../view/EditTicketView";
import axios from "axios";
import history from "../history";
import Logout from "../component/Logout";

export default class EditTicket extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      id: this.props.location.state,
      attachments: [],
      notification: "",
    };

    this.onHandleChange = this.onHandleChange.bind(this);
    this.goToOverview = this.goToOverview.bind(this);
    this.onUpdate = this.onUpdate.bind(this);
    this.onChangeState = this.onChangeState.bind(this);
    this.onHandleChangeAttachment = this.onHandleChangeAttachment.bind(this);
    this.onValid = this.onValid.bind(this);
    this.onAttachmentValid = this.onAttachmentValid.bind(this);
  }

  onAttachmentValid(data) {
    let formats = ["pdf", "doc", "docx", "png", "jpeg", "jpg"];
    for (let i = 0; i < data.length; i++) {
      let isFormat = data[i].name.split(".").splice(-1, 1)[0];
      if (formats.indexOf(isFormat) === -1) {
        this.setState({
          notification:
            "The selected file type is not allowed. Please select a file of one of the following types: pdf, png, doc, docx, jpg, jpeg",
        });
        return false;
      }
      if (data[i].size > 5 * 1024 * 1024) {
        this.setState({
          notification:
            "The size of attached file should not be greater than 5 Mb. Please select another file.",
        });
        return false;
      }
    }
    this.setState({ notification: "" });
    return true;
  }

  onValid(data) {
    let result = false;
    const isName = /^[-\s"'`~.a-z#!$%@^&*+=_,:;?/<>|()[\]{}]{1,100}$/;
    const isDescription = /^[-\s"'`~.a-z#!$%@^&*+=_,:;?/<>|()[\]{}]{1,500}$/i;
    const isText = /^[-\s"'`~.\w#!$%@^&*+=,:;?/<>|()[\]{}]{1,500}$/;
    switch (data.name) {
      case "name":
        result = isName.test(data.value);
        break;
      case "description":
        result = isDescription.test(data.value);
        break;
      case "text":
        result = isText.test(data.value);
        break;
      default:
        result = true;
    }
    return result;
  }

  onChangeState(data) {
    for (var key in data) {
      this.setState({ [key]: data[key] });
    }
  }

  onHandleChange(e) {
    if (this.onValid(e.target)) {
      this.setState({ [e.target.name]: e.target.value });
    }
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
        if (this.state.attachments.length !== 0) {
          var formData = new FormData();
          for (let i of this.state.attachments) {
            formData.append("files", i.blob, i.name);
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
    if (this.onAttachmentValid(files)) {
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
        reader.readAsArrayBuffer(files[i]);
      }
    }
  }

  render() {
    return (
      <div>
        <Logout />
        <EditTicketView
          id={this.state.id}
          description={this.state.description}
          name={this.state.name}
          category={this.state.category}
          urgency={this.state.urgency}
          desiredResolutionDate={this.state.desiredResolutionDate}
          notification={this.state.notification}
          onHandleChange={this.onHandleChange}
          onUpdate={this.onUpdate}
          goToOverview={this.goToOverview}
          onHandleChangeAttachment={this.onHandleChangeAttachment}
        ></EditTicketView>
      </div>
    );
  }
}
