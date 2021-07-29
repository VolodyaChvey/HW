import React from 'react'

class Title extends React.Component {
    constructor(props) {
        super(props)
        this.state = {}
    }

    render(){
        return <div>
            <h3 className='title'>{this.props.title}</h3>
        </div>
    }

}
export default Title