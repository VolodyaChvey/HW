import React from 'react';
import Title from '../common/Title.js'
import Button from '../common/Button.js'
import LabelInput from '../common/LabelInput.js'

class LoginView extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
        }
    }
    render() {
        const { onHandleChange, onAuth } = this.props
        return <div className='container overflow-hidden'>
            <br></br>
            <div className='row '>
                <div className='col-3'></div>
                <div className='col-6 '>
                    <div className='m-5'>
                        <Title title='Login to the Help Desk'></Title>
                    </div>               
                <form className=''>
                    <br></br>
                    <LabelInput lable='User Name:' name='login' type='email'
                        placeholder='Email' onChange={onHandleChange}></LabelInput>
                    <LabelInput lable='Password:' name='password' type='password'
                        placeholder='Enter password' onChange={onHandleChange}></LabelInput>
                        <div className='row'>
                            <div className='col-8'></div>
                            <div className='col-4 mr-1'>
                                <Button lable='Enter' type="submit" onClick={onAuth}  className='btn-md'></Button>
                            </div>
                            <div className='col-'></div>
                        </div>
                
                    </form>
                </div>
                <div className='col-3'></div>
                
            </div>
        </div>
    }

}
export default LoginView