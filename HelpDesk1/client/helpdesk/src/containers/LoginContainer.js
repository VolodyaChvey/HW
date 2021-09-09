import React from 'react';
import LoginView from '../view/LoginView';
import axios from 'axios';
import history from "../history";

class LoginContainer extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            login: '',
            password: ''
        }
        this.onHandleChange = this.onHandleChange.bind(this);
        this.signin=this.signin.bind(this);
        this.createHeader=this.createHeader.bind(this);

    }
    onHandleChange(event) {
        event.preventDefault()
        this.setState({[event.target.name]: event.target.value })
      
    }
    createHeader(){
        let authValue = btoa (this.state.login + ':' + this.state.password)
        return{headers: {'Authorization':'Basic ' + authValue}}
    }
    getHeaderValue(){
        return 'Basic '+ btoa(this.state.login + ':' + this.state.password)
    }
   signin(e){
        e.preventDefault()
        this.onAuth()
        axios.get('http://localhost:8099/HelpDesk/users/current', this.createHeader())
        .then((responce)=>{
            localStorage.setItem('AuthHeader',JSON.stringify(this.createHeader()))
            localStorage.setItem('User',JSON.stringify(responce.data));
            history.push('/tickets');
        }).catch(error =>{
            this.setState({
                display:'block'
            })
        }
        )
    }
    onAuth() {
       
      var re =/^[^@|\.].+[^@|\.]$/;

      var formValid=re.test(this.state.login)//&&this.state.login.length<=100&&this.state.password>5&&this.state.password<21;
      console.log(formValid);
        if(formValid){
            
        }

    }
    render() {
        return <LoginView onHandleChange={this.onHandleChange} onAuth={this.signin} />
    }
}
export default LoginContainer