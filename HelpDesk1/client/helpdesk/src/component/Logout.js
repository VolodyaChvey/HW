import React from "react";
import LogoutView from "./LogoutView"
import history from "../history";

export default class Logout extends React.Component{
    constructor(props){
        super(props);
        this.toLogin = this.toLogin.bind(this);
    }
    toLogin(){
        localStorage.clear();
        history.push("/")
    }

    render(){
        return (
            <div>
                <LogoutView
                    toLogin={this.toLogin}
                ></LogoutView>
            </div>
        )
    }
}