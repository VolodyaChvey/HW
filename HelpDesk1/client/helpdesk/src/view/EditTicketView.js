import React from "react";
import Button from "../common/Button";
import LabelTextarea from "../common/LabelTextarea";
import LabelInputTicket from "../common/LabelInputTicket";

export default class EditTicketView extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <div className="container">
        <div className="row my-4">
          <div className="col-2 mt-1">
          <Button
              lable="Ticket Overview"
              className="btn-success"
              onClick={this.props.goToOverview}
              value={this.props.id}
            ></Button>
          </div>
          <div className="col-4">
            <h2 style={{textAlign:'center'}}>Edit Ticket ({this.props.id})</h2>
          </div>
          <div className="col-6"></div>
        </div>
        <div className="row my-3">
          <div className="col-3"></div>
          <div className="col-2">
            <h6 className="text-center">
              Category<span className="text-danger">*</span>
            </h6>
          </div>
          <div className="col-4 d-grid gap-2">
            <select
              className="from-select "
              name="category"
              onChange={this.props.onHandleChange}
            >
              <option></option>
              <option value="1">Application & Services</option>
              <option value="2">Benefits & Paper Work</option>
              <option value="3">Hardware & Software</option>
              <option value="4">People Management</option>
              <option value="5">Security & Access</option>
              <option value="6">Workplaces & Facilities</option>
            </select>
          </div>
          <div className="col-3"></div>
        </div>
        <LabelInputTicket
          label="Name"
          name="name"
          onChange={this.props.onHandleChange}
        ></LabelInputTicket>
        <LabelTextarea
          lable="Description"
          name="description"
          onChange={this.props.onHandleChange}
        ></LabelTextarea>
        <div className="row my-3">
          <div className="col-3"></div>
          <div className="col-2">
            <h6 className="text-center">
              Urgency<span className="text-danger">*</span>
            </h6>
          </div>
          <div className="col-4 d-grid gap-2">
            <select
              className="from-select "
              name="urgency"
              onChange={this.props.onHandleChange}
            >
              <option></option>
              <option value="critical">Critical</option>
              <option value="high">High</option>
              <option value="average">Medium</option>
              <option value="low">Low</option>
            </select>
          </div>
          <div className="col-3"></div>
        </div>
        <div className="row my-3">
          <div className="col-3"></div>
          <div className="col-2">
            <h6 className="text-center">Desired resolution date</h6>
          </div>
          <div className="col-4 d-grid gap-2">
            <input
              type="date"
              name="desiredResolutionDate"
              onChange={this.props.onHandleChange}
              min={new Date().toISOString().substr(0, 10)}
            ></input>
          </div>
          <div className="col-3"></div>
        </div>
        <div className="row my-3">
          <div className="col-3"></div>
          <div className="col-2">
            <h6 className="text-center">Add attachments</h6>
          </div>
          <div className="col-2 ">
            <Button lable="Browse"></Button>
          </div>
          <div className="col-5"></div>
        </div>

        <LabelTextarea
          lable="Comment"
          name="text"
          onChange={this.props.onHandleChange}
        ></LabelTextarea>

        <div className="row mt-5">
          <div className="col-8"></div>
          <div className="col-2">
            <Button
              lable="Save as Draft"
              value="draft"
              onClick={this.props.onUpdate}
            ></Button>
          </div>
          <div className="col-2">
            <Button
              lable="Submit"
              value="new"
              onClick={this.props.onUpdate}
              className="btn-success"
            ></Button>
          </div>
        </div>
      </div>
    );
  }
}
