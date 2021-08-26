import React from 'react'

class LabelInput extends React.Component {
    constructor(props) {
        super(props)
        this.state = {}
    }

    render() {
        const {lable, name, type = 'text', placeholder, onChange } = this.props
        return (
        <div className='row m-3'>
            <div className='col-lg-4 mb-3 text-right'>
                <label className=''>{lable}</label>
            </div>
            <div className='col-lg-8 mb-3 '>
                 <input name={name} type={type} placeholder={placeholder} onChange={onChange}
                    className='w-100'  />
            </div>
            
        </div>
        )}

}
export default LabelInput