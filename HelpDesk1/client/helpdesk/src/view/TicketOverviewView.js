import React from "react";

import Title from "../common/Title";
import { Button } from "react-bootstrap";


export default class TicketOverviewView extends React.Component {
  constructor(props) {
    super(props);
  }
 
  render() {
    return (
      <div className="container">
        <div className="row">
          <div className="col- "> </div>
          <div className="col-"></div>
          <div className="col-"> </div>
        </div>


        <div className="row"></div>



        <div className="row">
          <div className="col-2">
            <Button className="btn btn-success btn-block">Ticket List</Button>
          </div>
          <div className="col-7">
            <Title title="Ticket ( ) - "></Title>
          </div>
          <div className="col-3">
            <Button className='btn btn-success btn-block'>Edit</Button>
          </div>
        </div>
        <div className="row">
          <div className="col-5">
            <label className="text-right">Created on:</label>
          </div>
          <div className="col-4">
            <label></label>
          </div>
          <div className="col-3">
            <Button className="btn btn-success btn-block">Leave Fedback</Button>
          </div>
        </div>
        <div className="row">
          <div className="col- "> </div>
          <div className="col-">

          </div>
          <div className="col-"> </div>
        </div>
      </div>
    );
  }
}
