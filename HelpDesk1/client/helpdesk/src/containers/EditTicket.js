import React from 'react'
import EditTicketView from '../view/EditTicketView'
import axios from "axios";

export default class EditTicket extends React.Component{
    constructor(props){
        super(props)
        this.state={
          id:2
        }
       
       
          
    this.onHandleChange = this.onHandleChange.bind(this);
    this.toTicketOverview = this.toTicketOverview.bind(this);
    this.onUpdate = this.onUpdate.bind(this); 
          this.onChangeState= this.onChangeState.bind(this);
    }
    onChangeState(data){
       for(var key in data){
         this.setState({[key]:data[key]})
       }
       console.log(this.state)
      

    }
    onHandleChange(e) {
      console.log(e.target.name)
      this.setState({[e.target.name]: e.target.value });
    }
    toTicketOverview(){

    }
    componentDidMount(){
      axios.get('http://localhost:8099/HelpDesk/tickets/' + this.state.id,
         JSON.parse(localStorage.AuthHeader))
      .then((resp)=>{
       
        this.onChangeState(resp.data)
      }
  
      )
    }
    onUpdate(e){
        var status = e.target.value;
        console.log(this.state)
        axios
        .put(
          "http://localhost:8099/HelpDesk/tickets/"+this.state.id,
          {
            id:this.state.id,
            desiredResolutionDate: this.state.desiredResolutionDate,
            name: this.state.name,
            description: this.state.description,
            state: status,
            createdOn:this.state.createdOn,
            owner:this.state.owner,
            category: this.state.category,
            comment: this.state.comment,
            urgency: this.state.urgency,
          },
          JSON.parse(localStorage.AuthHeader)
        )
        .then((responce) => {
          

          window.location.href = "/tickets";
        })
        .catch((error) => {});

    }

    render(){
   
        return(
            <div>
                <EditTicketView 
                    id={this.state.id}
                    onHandleChange={this.onHandleChange}
                    onUpdate={this.onUpdate}
                    toTicketOverview={this.toTicketOverview}
                ></EditTicketView>
            </div>
        )
    }
}