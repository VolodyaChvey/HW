import React from "react"
import TicketOverviewView from "../view/TicketOverviewView"
import axios from "axios"

export default class TicketOverview extends React.Component{
    constructor(props){
        super(props)
        this.state={
            id:6,
            ticket:null,
            btnActiv: true
        }
        this.onClickBtn=this.onClickBtn.bind(this);
        this.toEdit=this.toEdit.bind(this);
    }
    componentDidMount(){
        axios.get('http://localhost:8099/HelpDesk/tickets/' + this.state.id, JSON.parse(localStorage.AuthHeader))
        .then((resp)=>{
            this.setState({ticket:resp.data})
            console.log(this.state.ticket)
        }
    
        )
    }
    toEdit(){
        window.location.href='/ticket-edit'
    }

    onClickBtn(e){
        (e.target.value==='history')?this.setState({btnActiv: true}):this.setState({btnActiv: false})
    }

    render(){
        var btnClassPrimary='btn-primary';
        var btnClassDefault='btn-default';
        return(
            <TicketOverviewView
                ticket={this.state.ticket?this.state.ticket:{}}
                onClickBtn={this.onClickBtn}
                ActiveHistory={this.state.btnActiv}
                classBtnHistory={this.state.btnActiv?btnClassPrimary:btnClassDefault}
                classBtnComments={this.state.btnActiv?btnClassDefault:btnClassPrimary}
                toEdit={this.toEdit}
            ></TicketOverviewView>
        )
    }
}