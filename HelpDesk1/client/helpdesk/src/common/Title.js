import React from 'react'

class Title extends React.Component {
    constructor(props) {
        super(props)
        this.state = {}
    }

    render(){
        return <div className="text-center m-3">
            <h3 >{this.props.title}</h3>
        </div>
    }

}
export default Title