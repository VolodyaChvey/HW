import React from "react";
import TicketNewView from "../view/TicketNewView";
import axios from "axios";

export default class TicketNew extends React.Component {
  constructor(props) {
    super(props);
    this.state={
      category: null,
      name:null,
      urgency:null,
      desiredResolutionDate:null,
      discription:null,
      comment:null
    }
    this.toTicketList = this.toTicketList.bind(this);
    this.onSave = this.onSave.bind(this);
    this.onHandleChange=this.onHandleChange.bind(this);
    this.onDraft=this.onDraft.bind(this)
    this.onNew=this.onNew.bind(this)
    this.onUrgency=this.onUrgency.bind(this)
  }
  onHandleChange(e){
    this.setState({[e.target.name]:e.target.value})
  }
  toTicketList() {
    window.location.href = "/tickets";
  }
  onDraft(){
    this.onSave("draft")

  }
  onNew(){
  this.onSave('new')
  }

  onUrgency(){
 var val=this.state.urgency;
  var u;
   switch(val){
      case "1": u="critical"; break
      case "2": u="high"; break
      case "3": u="average"; break
      case "4": u="low"; break
    }
    this.setState({urgency: u})
    return u
  }

  onSave(status) {
    axios
      .post(
        "http://localhost:8099/HelpDesk/tickets",
        {
          desiredResolutionDate:this.state.desiredResolutionDate,
          name: this.state.name,
          description: this.state.discription,
          state: status,
          category:this.state.category,
          comment: this.state.comment,
          urgency:this.onUrgency()
        },
        JSON.parse(localStorage.AuthHeader)
      )
      .then((responce) => {

     window.location.href='/tickets'
      })
      .catch((error) => {

      });
  }
  render() {
    return (
      <TicketNewView
        toTicketList={this.toTicketList}
        onDraft={this.onDraft}
        onNew={this.onNew}
        onHandleChange={this.onHandleChange}
      ></TicketNewView>
    );
  }
}