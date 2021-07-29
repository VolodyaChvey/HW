import React from 'react';
import LoginView from '../view/LoginView';

class LoginContainer extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            username: '', password: ''
        }
        this.onHandleChange = this.onHandleChange.bind()
    }
    onHandleChange(event) {
        this.setState({ [event.target.name]: event.target.value })
    }
    createHeader(){
        let authValue = btoa (this.state.login + ':' + this.state.password)
        return{headers: {'Authorization':'Basic ' + authValue}}
    }
    getHeaderValue(){
        return 'Basic '+ btoa(this.state.login + ':' + this.state.password)
    }
    singin(e){
        e.preventDefault
        axios.get('http://localhost:8099/HelpDesk/users/current', this.createHeader()).then((responce)=>{
            localStorage.setItem('AuthHeader',JSON.stringify(this.createHeader()))
            localStorage.setItem('User',JSON.stringify(response.data));
           
        }).catch(error =>{
            this.setState({
                display:'block'
            })
        }
        )
    }

    onAuth() {

        

        
    }
    render() {
        return <LoginView onHandleChange={this.onHandleChange} onAuth={this.onAuth} />
    }
}
export default LoginContainer