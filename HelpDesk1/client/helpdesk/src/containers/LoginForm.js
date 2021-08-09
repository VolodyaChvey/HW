import React from 'react';
import axios from 'axios';

 
export default class LoginForm extends React.Component{
    constructor(props){
        super(props)
        this.state={
            login: '',
            password: ''
        }
        this.handleSubmit=this.handleSubmit.bind(this);
        this.onLoginChange=this.onLoginChange.bind(this);
        this.onPasswordChange=this.onPasswordChange.bind(this);
        this.onHandleChange=this.onHandleChange.bind(this);
    }
    createHeader(){
        let authValue = btoa (this.state.login + ':' + this.state.password)
        return{headers: {'Authorization':'Basic ' + authValue}}
    }

    handleSubmit(e){
        e.preventDefault();
        
            console.log(this.createHeader())
            //this.singin()
            this.loginIsValid()
    }
    onHandleChange(event) {
        this.setState({[event.target.name]: event.target.value})
        this.ontest()
    }
    ontest(){
        console.log(this.state.login,this.state.password)
    }
    onLoginChange(e){
        var val=e.target.value;
        this.setState({login: val})
        
      
    }
    loginIsValid(){
        var re =/^[^@|\.].+[^@|\.]$/
        var loginValid =re.test(this.state.login)
      re=/^.*(?=.*[@{1}]).*$/
       // loginValid=this.state.login.length<=100
        //re=/\p{L}/
        loginValid=re.test(this.state.login)
        console.log(loginValid)
        re=/^[^@|\.].+[^@|\.]$/g
        console.log(re.test(this.state.login))
    }
    singin(){
        axios.get('http://localhost:8099/HelpDesk/users/current', this.createHeader())
        .then((responce)=>{
            localStorage.setItem('AuthHeader',JSON.stringify(this.createHeader()))
            localStorage.setItem('User',JSON.stringify(responce.data));
           console.log(localStorage.getItem)
        }).catch(error =>{
            this.setState({
                display:'block'
            })
        }
        )
    }
    onPasswordChange(e){
        var val=e.target.value;
        this.setState({password: val})
    }
    render(){
        return (
            <form onSubmit={this.handleSubmit}>
                <p>
                    <label>Email:</label>
                    <input type='text' value={this.state.login} 
                        onChange={this.onHandleChange} name='login'/>
                </p>
                <p>
                    <label>Password:</label>
                    <input type='password' value={this.state.password} 
                        onChange={this.onHandleChange} name='password'/>
                </p>
                <button type='submit'>Отправить</button>
            </form>
        );
    }
}