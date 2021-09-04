import React from "react";
import TicketOverviewView from "../view/TicketOverviewView";
import axios from "axios";
import history from "../history";
import { saveAs } from "file-saver";

export default class TicketOverview extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      id: this.props.location.state,
      ticket: {},
      history: [],
      comments: [],
      attachments:[],
      btnActiv: true,
      user: JSON.parse(localStorage.User)
    };
    this.onClickBtn = this.onClickBtn.bind(this);
    this.toEdit = this.toEdit.bind(this);
    this.goToTickets = this.goToTickets.bind(this);
    this.onAddComment = this.onAddComment.bind(this);
    this.onHandleChange = this.onHandleChange.bind(this);
    this.onDownLoad = this.onDownLoad.bind(this);
    this.toLeaveFeedback = this.toLeaveFeedback.bind(this);
  }
  
  componentDidMount() {
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
  }

  toEdit() {
    history.push({
      pathname: "/edit",
      state: this.state.id,
    });
  }
  toLeaveFeedback(){
    history.push({
      pathname:"/leaveFeedback",
      state:{
        id:this.state.id,
        name:this.state.ticket.name
      }
    });
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
      this.setState({comments:[...this.state.comments,responce.data]})
    })
  }

  onHandleChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }

  onDownLoad(e){
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

  render() {
    var btnClassPrimary = "btn-primary";
    var btnClassDefault = "btn-default";
    return (
      <TicketOverviewView
        user={this.state.user}
        history={this.state.history ? this.state.history : []}
        comments={this.state.comments ? this.state.comments : []}
        attachments={this.state.attachments ? this.state.attachments : []}
        ticket={this.state.ticket ? this.state.ticket : {}}
        addComment={this.onAddComment}
        onClickBtn={this.onClickBtn}
        onHandleChange={this.onHandleChange}
        onDownLoad={this.onDownLoad}
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
      ></TicketOverviewView>
    );
  }
}
