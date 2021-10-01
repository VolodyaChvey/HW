import React from "react";
import TicketNewView from "../view/TicketNewView";
import Logout from "../component/Logout";
import axios from "axios";
import history from "../history";

export default class TicketNew extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      category: null,
      name: null,
      urgency: null,
      desiredResolutionDate: null,
      description: null,
      attachments: [],
      notification: "",
    };
    this.toTicketList = this.toTicketList.bind(this);
    this.onSave = this.onSave.bind(this);
    this.onHandleChange = this.onHandleChange.bind(this);
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

  onHandleChange(e) {
    if (this.onValid(e.target)) {
      this.setState({ [e.target.name]: e.target.value });
    }
  }

  toTicketList() {
    history.push("/tickets");
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

  onSave(e) {
    if (!(this.state.category && this.state.name && this.state.urgency)) {
      return;
    }
    var ticket = {};
    ticket.state = e.target.value;
    ticket.category = this.state.category;
    ticket.name = this.state.name;
    ticket.urgency = this.state.urgency;
    ticket.desiredResolutionDate = this.state.desiredResolutionDate;
    ticket.description = this.state.description;
    var formData = new FormData();
    formData.append("ticketDto", JSON.stringify(ticket));
    for (let i of this.state.attachments) {
      formData.append("files", i.blob, i.name);
    }

    axios
      .post(
        "http://localhost:8099/HelpDesk/tickets",
        formData,
        JSON.parse(localStorage.AuthHeader)
      )
      .then((responce) => {
        if (this.state.text) {
          axios
            .post(
              "http://localhost:8099/HelpDesk/tickets/" +
                responce.data.id +
                "/comments",
              {
                text: this.state.text,
                ticketId: responce.data.id,
              },
              JSON.parse(localStorage.AuthHeader)
            )
            .then((responce) => {});
        }
        history.push("/tickets");
      })
      .catch((error) => {});
  }

  render() {
    return (
      <div>
        <Logout />
        <TicketNewView
          notification={this.state.notification}
          toTicketList={this.toTicketList}
          onSave={this.onSave}
          onHandleChange={this.onHandleChange}
          onHandleChangeAttachment={this.onHandleChangeAttachment}
        ></TicketNewView>
      </div>
    );
  }
}
