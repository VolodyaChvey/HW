import React from 'react';
import axios from 'axios';
import TicketsView from '../view/TicketView';

export default class TicketsContainer extends React.Component{
    constructor(props){
        super(props)
        this.state={
            tickets:[],
            btnClassPrimary:'btn-primary',
            btnClassDefault:' ',
            btnActive:true
        }
        this.toTicketNew=this.toTicketNew.bind(this)
      this.onClickBtnAllT=this.onClickBtnAllT.bind(this)
      this.onClickBtnMyT=this.onClickBtnMyT.bind(this)
      
    }
toTicketNew(){
    window.location.href='ticket/new'
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
 console.log(this.state.btnActive)
}
onClickBtnMyT(){
    this.setState({btnActive:false})
    console.log(this.state.btnActive)
    axios.get('http://localhost:8099/HelpDesk/tickets/my',JSON.parse(localStorage.AuthHeader))
    .then((resp)=>{
        this.setState({tickets:resp.data})
    })
}



render(){
    
    return <TicketsView tickets={this.state.tickets} 
    btnAllTClass={this.state.btnActive?this.state.btnClassPrimary:this.state.btnClassDefault} 
    btnMyTClass={this.state.btnActive?this.state.btnClassDefault:this.state.btnClassPrimary} 
    onClickBtnAllT={this.onClickBtnAllT}
    onClickBtnMyT={this.onClickBtnMyT}
    toTicketNew={this.toTicketNew}></TicketsView>
}

}