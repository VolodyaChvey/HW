import React from "react";
import Button from "../common/Button";

export default class LogoutView extends React.Component{
    constructor(props){
        super(props);
    }

    render(){
        return (
            <div className="container">
                <div className="mt-5"></div>
                <div className="row">
                    <div className="col-10"></div>
                    <div className="col-2">
                       <Button
                       lable="Log out"
                       className="btn btn-success"
                       onClick={this.props.toLogin}
                       ></Button>
                    </div>
                </div>
            </div>
        )
    }
}