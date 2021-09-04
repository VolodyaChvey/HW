import React from 'react'
import LeaveFeedbackView from '../view/LeaveFeedbackView'
import history from "../history";

export default class LeaveFeedback extends React.Component{
    constructor(props){
        super(props)
        console.log(this.props.location)
        this.state={
            id:this.props.location.state.id,
            name:this.props.location.state.name,
        }
        this.toTicketOverview = this.toTicketOverview.bind(this);
    }
    toTicketOverview(){
        history.push("/overview")
    }

    render(){
        return(
            <div>
                <LeaveFeedbackView
                    id={this.state.id}
                    name={this.state.name}
                    toTicketOverview={this.toTicketOverview}
                ></LeaveFeedbackView>
            </div>
        )
    }
}