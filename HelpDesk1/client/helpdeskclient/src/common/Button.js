import React from 'react'

class Button extends React.Component {
    constructor(props) {
        super(props)
        this.state = {}
    }

    render() {
        return <div>
            <button type='button' className='btn btn-block btn-lg btn-primary' onClick={this.props.onClick}>{this.props.lable}</button>
        </div>
    }


}
export default Button