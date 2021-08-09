import React from 'react'
import Button from '../common/Button'
import Title from '../common/Title'



export default class TicketNewView extends React.Component{
    constructor(props){
        super(props)
    }
    render(){
        return (
        <div className='container'>
            <div className='row'>
                <div className='col-2 mt-4'>
                    <Button lable='Ticket List' className='btn-success'></Button>
                </div>
                <div className='col-4'>
                    <Title title='Create new Ticket'></Title>
                </div>
                <div className='col-6'></div>
            </div>
            <div className='row my-3'>
                <div className='col-3'></div>
                <div className='col-2'>
                    <h6 className='text-center'>Category</h6>
                </div>
                <div className='col-4 d-grid gap-2'>
                    <select className='from-select '>
                        <option value='1'>Application & Services</option>
                        <option value='2'>Benefits & Paper Work</option>
                        <option value='3'>Hardware & Software</option>
                        <option value='4'>People Management</option>
                        <option value='5'>Security & Access</option>
                        <option value='6'>Workplaces & Facilities</option>
                    </select>
                </div>
                <div className='col-3'></div>
            </div>
            <div className='row my-3'>
                <div className='col-3'></div>
                <div className='col-2'>
                    <h6 className='text-center'>Name</h6>
                </div>
                <div className='col-4 d-grid gap-2'>
                    <input type='text'></input>
                </div>
                <div className='col-3'></div>
            </div>
            <div className='row my-3'>
                <div className='col-3'></div>
                <div className='col-2'>
                    <h6 className='text-center'>Discription</h6>
                </div>
                <div className='col-5 d-grid gap-2'>
                    <textarea></textarea>
                </div>
                <div className='col-2'></div>
            </div>
            <div className='row my-3'>
                <div className='col-3'></div>
                <div className='col-2'>
                    <h6 className='text-center'>Urgency</h6>
                </div>
                <div className='col-4 d-grid gap-2'>
                    <select className='from-select '>
                        <option value='1'>Critical</option>
                        <option value='2'>High</option>
                        <option value='3'>Medium</option>
                        <option value='4'>Low</option>
                    </select>
                </div>
                <div className='col-3'></div>
            </div>
        </div>)
    }
}