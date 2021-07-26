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

    onAuth() {



        
    }
    render() {
        return <LoginView onHandleChange={this.onHandleChange} onAuth={this.onAuth} />
    }
}
export default LoginContainer