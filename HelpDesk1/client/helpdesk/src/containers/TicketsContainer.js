import React from 'react';
import axios from 'axios';
import TicketsView from '../view/TicketView';
import history from '../history';


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
      this.goToOverview=this.goToOverview.bind(this)

    }
toTicketNew(){
    history.push('/create')
 
}
goToOverview(e){
    history.push({
        pathname:"/overview",
        state:e.target.value
    })
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
    goToOverview={this.goToOverview}></TicketsView>
}

}