import React from 'react';
import Title from '../common/Title.js'
import Button from '../common/Button.js'
import LableInput from '../common/LableInput.js'

class LoginView extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
        }
    }
    render() {
        const { onChange, onAuth } = this.props
        return <div className='loginView'>
            <Title title='Login to the Help Desk'></Title>
            <form action='' method='GET'>
            <LableInput lable='login' name='login' type='email' placeholder='Email'></LableInput>
            <LableInput lable='Password' name='password' type='password' placeholder='Enter password'></LableInput>
            <Button lable='login' onClick={onAuth}></Button>
            </form>
        </div>
    }

}
export default LoginView