import React from "react";
import LoginView from "../view/LoginView";
import axios from "axios";
import history from "../history";

class LoginContainer extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      login: "",
      password: "",
      loginRequired: false,
      passwordRequired: false,
      enterInvalid:false,
    };
    this.onHandleChange = this.onHandleChange.bind(this);
    this.signin = this.signin.bind(this);
    this.createHeader = this.createHeader.bind(this);
    this.onCheckEmail = this.onCheckEmail.bind(this);
  }

  onCheckEmail(){
      if(!this.state.login){
          return "Please fill out the required field.";
      }
  }

  onHandleChange(event) {
    event.preventDefault();
    this.setState({ [event.target.name]: event.target.value });
    this.setState({enterInvalid:false});
    if(event.target.name==="login"){
      this.setState({loginRequired: false})
    }
    if(event.target.name==="password"){
      this.setState({passwordRequired: false})
    }
  }

  createHeader() {
    let authValue = btoa(this.state.login + ":" + this.state.password);
    return { headers: { Authorization: "Basic " + authValue } };
  }

  getHeaderValue() {
    return "Basic " + btoa(this.state.login + ":" + this.state.password);
  }

  signin(e) {
    e.preventDefault();
    if(this.onAuth()){
    axios
      .get("http://localhost:8099/HelpDesk/users/current", this.createHeader())
      .then((responce) => {
        localStorage.setItem("AuthHeader", JSON.stringify(this.createHeader()));
        localStorage.setItem("User", JSON.stringify(responce.data));
        history.push("/tickets");
      })
      .catch((error) => {
        this.setState({enterInvalid: true});
      });
    }else{
      this.setState({enterInvalid: true});
    }
  }

  onAuth() {
    if(!this.state.login){
        this.setState({loginRequired:true});
        return false;
    }else{
      this.setState({loginRequired:false})
    }
    if(!this.state.password){
      this.setState({passwordRequired:true});
      return false;
    }else{
      this.setState({passwordRequired:false})
    }
    let email =[...this.state.login];
    let validLogin =  
     email.indexOf("@") > 0 &&
      email.indexOf(".") > 0 &&
      email.indexOf("@") < email.length - 1 &&
      email.indexOf(".") < email.length - 1 &&
      email.length <= 100;
    const isPassword = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[-"'`~.#!$%@^&*_+=,:;?/<>|()[\]{}])[-"'`~.\w#!$%@^&*+=,:;?/<>|()[\]{}]{6,20}$/;
    return validLogin && isPassword.test(this.state.password);
  }

  render() {
    return (
      <div>
        <LoginView onHandleChange={this.onHandleChange} 
         signin={this.signin}
         loginRequired={this.state.loginRequired}
         passwordRequired={this.state.passwordRequired}
         enterInvalid={this.state.enterInvalid} />
      </div>
    );
  }
}
export default LoginContainer;
