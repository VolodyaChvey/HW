import React from 'react'
import {InputGroup} from "react-bootstrap"
class Button extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            class: this.props.className
        }



    }

    render() {
        return (<div className='d-grid gap-2 ' >
            <button type={this.props.type} className={this.state.class} onClick={this.props.onClick}>{this.props.lable}</button>
        </div>
        )
    }


}
export default Button