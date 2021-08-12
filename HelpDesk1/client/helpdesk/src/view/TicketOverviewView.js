import React from "react";

import Title from "../common/Title";
import { Button } from "react-bootstrap";
import { Table } from "react-bootstrap";

export default class TicketOverviewView extends React.Component {
  constructor(props) {
    super(props);
  }
  onlkj() {
    // <Button lable="Ticket List" className="btn-success"></Button>
  }
  render() {
    return (
      <div className="container">
        <div className="row">
          <Button className="btn btn-success btn-block">Butoon</Button>
        </div>
        <Button className="btn btn-primary btn-block btn-lg">button</Button>
        <Table className="table table-hover table-bordered">
          <thead>
            <tr className="table-active">
              <th>
                ID<i className="fas fa-sort" ></i>
                <button className="fas fa-sort"></button>
              </th>
              <th>
                Name<i className="fas fa-sort" ></i>
              </th>
              <th>
                Desired Date
                <i className="fas fa-sort" ></i>
              </th>
              <th>
                Urgency
                <i className="fas fa-sort" ></i>
              </th>
              <th>
                Status
                <i class="fas fa-sort" ></i>
              </th>
              <th>Action</th>
            </tr>
          </thead>
        </Table>

        <div className="row">
          <div className="col-2"></div>
          <div className="col-4">
            <Title title="Ticket ( ) - "></Title>
          </div>
          <div className="col-"></div>
        </div>
        <div className="row">
          <div className="col-"></div>
          <div className="col-"></div>
          <div className="col-"></div>
        </div>
      </div>
    );
  }
}
