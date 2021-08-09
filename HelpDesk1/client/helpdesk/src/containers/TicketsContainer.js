import React from 'react';
import axios from 'axios';
import TicketsView from '../view/TicketView';

export default class TicketsContainer extends React.Component{
    constructor(props){
        super(props)
        this.state={
            tickets:''
        }
        
        this.toTicketNew=this.toTicketNew.bind(this)
      
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



render(){
    
    return <TicketsView tickets={this.state.tickets}></TicketsView>
}

}