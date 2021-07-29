import React from 'react'

class LableInput extends React.Component {
    constructor(props) {
        super(props)
        this.state = {}
    }

    render() {
        const {lable, name, type = 'text', placeholder, onChange } = this.props
        return <div className='labelInput'>
            <span>{lable}</span>
            <input name={name} type={type} placeholder={placeholder} onChange={onChange} />
        </div>
    }

}
export default LableInput