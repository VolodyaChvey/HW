import React from "react";
import Button from "../common/Button";
import LabelTextarea from "../common/LabelTextarea";
import LabelInputTicket from "../common/LabelInputTicket";

export default class EditTicketView extends React.Component {
  constructor(props) {
    super(props);
    this.state={
      arrayCategory:[
        {value:"1",
          text:"Application & Services",},
        { value:"2",
          text:"Benefits & Paper Work",},
        {value:"3",
          text:"Hardware & Software",},
        {value:"4",
          text:"People Management",},
        {value:"5",
         text:"Security & Access",},
        {value:"6",
        text:"Workplaces & Facilities"}],
      arrayUrgency:[
        {value:"critical",
        text:"Critical",},
        {value:"high",
        text:"High",},
        {value:"average",
        text:"Medium",},
        {value:"low",
        text:"Low"}],
    }
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
            <h2 style={{ textAlign: "center" }}>
              Edit Ticket ({this.props.id})
            </h2>
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
              {this.state.arrayCategory.map((c,i)=>(
                <option value={c.value} label={c.text}
                 selected={c.value===String(this.props.category)}/>
              ))}
            </select>
          </div>
          <div className="col-3"></div>
        </div>

        <LabelInputTicket
          label="Name"
          name="name"
          value={this.props.name}
          onChange={this.props.onHandleChange}
        ></LabelInputTicket>
        <LabelTextarea
          lable="Description"
          name="description"
          onChange={this.props.onHandleChange}
          value={this.props.description}
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
              className="from-select"
              name="urgency"
              onChange={this.props.onHandleChange}
            >
             {this.state.arrayUrgency.map((u,i)=>(
                <option value={u.value} label={u.text} 
                selected={u.value.toUpperCase()===this.props.urgency}/>
              ))}
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
              value={this.props.desiredResolutionDate}
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
            <label class="btn btn-outline-secondary">
              Browse
              <input
                type="file"
                multiple
                id="input"
                accept="application/pdf,application/msword,image/png,image/jpeg,image/pjpeg,
                application/vnd.openxmlformats-officedocument.wordprocessingml.document"
                hidden
                onChange={this.props.onHandleChangeAttachment}
              ></input>
            </label>
          </div>
          <div className="col-5 color-red">
          {this.props.notification}
          </div>
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
