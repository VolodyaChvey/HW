import React from "react";
import Button from "../common/Button";
import Table from "../common/Table";

export default class TicketsView extends React.Component {
  constructor(props) {
    super(props);
    this.state={
    classMyBtn:this.props.btnMyTClass,
    classAllBtn:this.props.btnAllTClass
    }
  }
  
  render() {

    return (
      <div>
        <div class="container ">
          <div className="m-5"></div>
          <div className="row">
            <div className="col-8"></div>
            <div className="col-4">
              <Button
                lable="Create New Ticket"
                className="btn-success btn-lg"
                onClick={this.props.toTicketNew}
              ></Button>
            </div>
          </div>
          <div className="m-3"></div>
          <div className="row mt-5">
            <div className="col-6 ">
              <Button lable="All Tickets" className={this.props.btnAllTClass}
               id='btnAllT' onClick={this.props.onClickBtnAllT}></Button>
            </div>
            <div className="col-6">
              <Button lable="My Tickets" className={this.props.btnMyTClass}
               id='btnMyt' onClick={this.props.onClickBtnMyT}></Button>
            </div>
          </div>
          <div className="row my-3">
            <div className="col-4 d-grid gap-2">
              <input type="text"></input>
            </div>
            <div className="col-8 "></div>
          </div>
        </div>
        <Table tickets={this.props.tickets} goToOverview={this.props.goToOverview}></Table>
      </div>
    );
  }
}
