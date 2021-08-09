import React from 'react'

export default class Table extends React.Component{
constructor(props){
    super(props)
    this.state={
        tickets:''
    }
    
    this.onPaintTable=this.onPaintTable.bind(this)
}

onPaintTable(props){
    this.setState({tickets:()=>this.props.tickets})
    console.log(this.props.tickets)
    if(this.state.tickets){
        console.log(this.state.tickets)
        for(let t of this.state.tickets){
            console.log(t.id)
        }
        /*this.props.tickets.map(t=>{
            return(
                <tr>
                    <th>{t.id}</th>
                    <th>{t.name}</th>
                    <th>{t.desiredResolutionDate}</th>
                    <th>{t.urgency}</th>
                    <th>{t.state}</th>
                    <th></th>
                </tr>
            )
        })*/
    }
}

        render(){
            console.log(this.state.tickets)
            return(
            <div className='container'>
                <table className='table table-bordered table-responsive'>
                    <thead >
                        <tr className='table-active'>
                            <th >ID</th>
                            <th>Name</th>
                            <th>Desired Date</th>
                            <th>Urgency</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                    {this.onPaintTable}
                    </tbody>
                    
                </table>
            </div>
            )
        }
}