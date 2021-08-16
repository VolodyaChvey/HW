import React from 'react';
import axios from 'axios';
import TicketsView from '../view/TicketView';


export default class TicketsContainer extends React.Component{
    constructor(props){
        super(props)
        this.state={
            tickets:[],
 
            btnActive:true
        }
        
      this.toTicketNew=this.toTicketNew.bind(this)
      this.onClickBtnAllT=this.onClickBtnAllT.bind(this)
      this.onClickBtnMyT=this.onClickBtnMyT.bind(this)
      this.toHistory=this.toHistory.bind(this)

    }
toTicketNew(){
  window.location.href='ticket/new'
 
}
toHistory(e){
    var con=e.target.value
    console.log(con)
}

componentDidMount(){
    axios.get('http://localhost:8099/HelpDesk/tickets/all', JSON.parse(localStorage.AuthHeader))
    .then((resp)=>{
        this.setState({tickets:resp.data})
    }

    )
}
onClickBtnAllT(){
this.setState({btnActive:true})
 axios.get('http://localhost:8099/HelpDesk/tickets/all', JSON.parse(localStorage.AuthHeader))
 .then((resp)=>{
     this.setState({tickets:resp.data})
 })
}
onClickBtnMyT(){
    axios.get('http://localhost:8099/HelpDesk/tickets/my',JSON.parse(localStorage.AuthHeader))
    .then((resp)=>{
        this.setState({tickets:resp.data})
    })
    this.setState({btnActive:false})
}

render(){
    var btnClassPrimary='btn-primary'
    var btnClassDefault='bnt-default'
    return <TicketsView tickets={this.state.tickets}
    btnAllTClass={this.state.btnActive?btnClassPrimary:btnClassDefault}
    btnMyTClass={this.state.btnActive?btnClassDefault:btnClassPrimary}
    onClickBtnAllT={this.onClickBtnAllT}
    onClickBtnMyT={this.onClickBtnMyT}
    toTicketNew={this.toTicketNew}
    toHistory={this.toHistory}></TicketsView>
}

}