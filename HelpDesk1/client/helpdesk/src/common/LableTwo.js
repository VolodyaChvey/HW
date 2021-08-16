import React from 'react'

export default class LableTwo extends React.Component{
    constructor(props) {
        super(props);
      }

      render(){
          return(
              <div className="d-grid gap-2 ">
                  <span className='text-right'>{this.props.lable}</span>
                  <span>{this.props.meaning}</span>
              </div>
          )
      }
    }