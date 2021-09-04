import React from "react";
import TicketNewView from "../view/TicketNewView";
import axios from "axios";
import history from "../history";
import { saveAs } from "file-saver";

export default class TicketNew extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      state: null,
      category: null,
      name: null,
      urgency: null,
      desiredResolutionDate: null,
      description: null,
      comment: null,
      attachments:[],
    };
    this.toTicketList = this.toTicketList.bind(this);
    this.onSave = this.onSave.bind(this);
    this.onHandleChange = this.onHandleChange.bind(this);
    this.onHandleChangeAttachment = this.onHandleChangeAttachment.bind(this);
  }
  
  onHandleChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }

  toTicketList() {
   history.push("/tickets");
  } 

  onHandleChangeAttachment(e){
    //console.log(e.target.files)
    let files = e.target.files
    for (let i = 0; i < files.length; i++) {
        let reader = new FileReader();
        reader.onloadend = () => {
            this.setState({
                attachments: [...this.state.attachments, {
                    name: files[i].name,
                    blob: new Blob([reader.result],{type:files[i].type}),
                }]
            });    
          }
        reader.readAsArrayBuffer(files[i]);     
    }
  }

  onSave(e) {
    var ticket={};
    ticket.state=e.target.value;
    ticket.category=this.state.category;
    ticket.name=this.state.name;
    ticket.urgency=this.state.urgency;
    ticket.desiredResolutionDate=this.state.desiredResolutionDate;
    ticket.description=this.state.description;
    ticket.comment=this.state.comment;
    var formData= new FormData();
    formData.append("ticketDto",JSON.stringify(ticket));
    for(let i of this.state.attachments){
      formData.append("files",i.blob,i.name)
     // saveAs(i.blob,i.name)
    }

   axios
      .post(
        "http://localhost:8099/HelpDesk/tickets",
        formData,
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
          onHandleChangeAttachment={this.onHandleChangeAttachment}
        ></TicketNewView>
      </div>
    );
  }
}
