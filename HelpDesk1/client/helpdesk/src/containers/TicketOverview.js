import React from "react";
import TicketOverviewView from "../view/TicketOverviewView";
import axios from "axios";
import history from "../history";
import { saveAs } from "file-saver";
import Logout from "../component/Logout";

export default class TicketOverview extends React.Component {
  constructor(props) {
    super(props);
    let index=0;
    if(!this.props.location.state){
      index=this.props.location.pathname.substring(10)
    }else{
      index=this.props.location.state
    }
    this.state = {
      id: index,
      ticket: {},
      feedback: {},
      history: [],
      comments: [],
      attachments:[],
      btnActiv: true,
      user: JSON.parse(localStorage.User),
      allShowComments: false,
      allShowHistory: false,
    };
    this.onClickBtn = this.onClickBtn.bind(this);
    this.toEdit = this.toEdit.bind(this);
    this.goToTickets = this.goToTickets.bind(this);
    this.onAddComment = this.onAddComment.bind(this);
    this.onHandleChange = this.onHandleChange.bind(this);
    this.onDownLoad = this.onDownLoad.bind(this);
    this.toLeaveFeedback = this.toLeaveFeedback.bind(this);
    this.isEmpty = this.isEmpty.bind(this);
    this.toComments = this.toComments.bind(this);
    this.onAllShowComments = this.onAllShowComments.bind(this);
    this.toHistories = this.toHistories.bind(this);
    this.onAllShowHistories = this.onAllShowHistories.bind(this);
    this.onRemove = this.onRemove.bind(this);
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
  
  componentDidMount() {
    let id;
    if(this.state.id){
      id=this.state.id;
    }else{
      id=this.props.location.pathname.substring(10)
    }
   

    axios
      .get(
        "http://localhost:8099/HelpDesk/tickets/" + this.state.id,
        JSON.parse(localStorage.AuthHeader)
      )
      .then((resp) => {
        this.setState({ticket: resp.data });
      });
      axios
      .get(
        "http://localhost:8099/HelpDesk/tickets/" + this.state.id +"/history",
        JSON.parse(localStorage.AuthHeader)
      )
      .then((resp) => {
        this.setState({history: resp.data });
      });
      axios
      .get(
        "http://localhost:8099/HelpDesk/tickets/" + this.state.id +"/comments",
        JSON.parse(localStorage.AuthHeader)
      )
      .then((resp) => {
        this.setState({comments: resp.data });
      });
      axios
      .get(
        "http://localhost:8099/HelpDesk/tickets/" + this.state.id +"/attachments",
        JSON.parse(localStorage.AuthHeader)
      )
      .then((resp) => {
        this.setState({attachments: resp.data})
      });
      axios
      .get(
        "http://localhost:8099/HelpDesk/tickets/" + this.state.id +"/feedback",
        JSON.parse(localStorage.AuthHeader)
      )
      .then((resp) => {
        this.setState({feedback: resp.data})
      });
  }

  toEdit() {
    history.push({
      pathname: "/edit",
      state: this.state.id,
    });
  }

  isEmpty(obj){
    for(let key in obj){
      return false;
    }
    return true;
  }

  toLeaveFeedback(){
    if(this.isEmpty(this.state.feedback)){
      history.push({
        pathname:"/LeaveFeedback",
        state:{
          id:this.state.id,
          name:this.state.ticket.name,
        }
      });
    }else{
      history.push({
        pathname:"/Feedback",
        state:{
          id:this.state.id,
          name:this.state.ticket.name,
          rate:this.state.feedback.rate,
          text:this.state.feedback.text,
        }
      });
    }
  }

  goToTickets() {
    history.push("/tickets");
  }

  onClickBtn(e) {
    e.target.value === "history"
      ? this.setState({ btnActiv: true })
      : this.setState({ btnActiv: false });
  }
  onAddComment(){
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
      this.setState({comments:[responce.data, ...this.state.comments]});
      this.setState({text:""});
    })
  }

  onHandleChange(e) {
    e.preventDefault();
    if (this.onValid(e.target)) {
      this.setState({ [e.target.name]: e.target.value });
    }
  }

  onDownLoad(e){
    e.preventDefault();
    var id=e.target.id
    var name=e.target.name
    let url ='http://localhost:8099/HelpDesk/attachments/'+id;
    fetch(url,JSON.parse(localStorage.AuthHeader))
    .then((responce)=>{
      if(responce.ok){
        responce.blob().then((blob)=>{
          saveAs(blob,name);
        })
      };
    })
  }

  onRemove(e){
    e.preventDefault();
    var id = e.target.id;
    let url ='http://localhost:8099/HelpDesk/attachments/'+id;
    axios
    .delete(url,JSON.parse(localStorage.AuthHeader))
    .then((resp)=>{
      if(resp.status===204){
        this.setState({attachments:this.state.attachments.filter((a)=>
          a.id!=id)})
      }
    })
  }

  onAllShowComments(){
    this.setState({allShowComments: !this.state.allShowComments}) 
  }

  toComments(){
    if(!this.state.comments){
      return [];
    }
    if(this.state.allShowComments){
      return this.state.comments;
    }else{
      return this.state.comments.slice(0,5);
    }
  }

  onAllShowHistories(){
    this.setState({allShowHistory:!this.state.allShowHistory})
  }

  toHistories(){
    if(!this.state.history){
      return [];
    }
    if(this.state.allShowHistory){
      return this.state.history
    }else{
      return this.state.history.slice(0,5)
    }
  }

  render() {
    console.log(this.state.feedback)
    var btnClassPrimary = " btn-primary";
    var btnClassDefault = " btn-default";
    return (
      <div>
        <Logout/>
        <TicketOverviewView
          showFeedback={!this.isEmpty(this.state.feedback)||
            ((this.state.ticket.owner&& (this.state.ticket.owner.id===this.state.user.id))&&(this.state.ticket.state==="DONE"))}
          user={this.state.user}
          history={this.toHistories()}
          comments={this.toComments()}
          onAllShowComments={this.onAllShowComments}
          onAllShowHistories={this.onAllShowHistories}
          attachments={this.state.attachments ? this.state.attachments : []}
          ticket={this.state.ticket ? this.state.ticket : {}}
          addComment={this.onAddComment}
          onClickBtn={this.onClickBtn}
          onHandleChange={this.onHandleChange}
          onDownLoad={this.onDownLoad}
          onRemove={this.onRemove}
          ActiveHistory={this.state.btnActiv}
          classBtnHistory={
            this.state.btnActiv ? btnClassPrimary : btnClassDefault
          }
          classBtnComments={
            this.state.btnActiv ? btnClassDefault : btnClassPrimary
          }
          toEdit={this.toEdit}
          goToTickets={this.goToTickets}
          toLeaveFeedback={this.toLeaveFeedback}
          text={this.state.text}
          lableComments={this.state.allShowComments?"Show the latest":"Show All"}
          lableHistories={this.state. allShowHistory?"Show the latest":"Show All"}
        ></TicketOverviewView>
      </div>
    );
  }
}
