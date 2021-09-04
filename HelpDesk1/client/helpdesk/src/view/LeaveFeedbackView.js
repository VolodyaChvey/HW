import React from "react";
import { ButtonGroup } from "react-bootstrap";
import Button from "../common/Button";

export default class LeaveFeedbackView extends React.Component {
  constructor(props) {
    super(props);
  }
  render() {
    return (
      <div className="container">
        <div className="row mt-5">
          <div className="col- "></div>
          <div className="col- "></div>
          <div className="col- "></div>
        </div>

        <div className="row">
          <div className="col-2">
            <Button
              lable="Back"
              className="btn btn-success"
              onClick={this.props.toTicketOverview}
            ></Button>
          </div>
          <div className="col-8">
            <h2>
              Ticket ({this.props.id}) - {this.props.name}
            </h2>
          </div>
          <div className="col- "></div>
        </div>

        <div className="row my-5">
          <div className="col- "></div>
          <div className="col- "></div>
          <div className="col- "></div>
        </div>

        <div className="row">
          <div className="col-2"></div>
          <div className="col-8">
              <h5>Please, rate your satisfaction with the solution:</h5>
          </div>
          <div className="col- "></div>
        </div>

        <div className="row">
          <div className="col-2"></div>
          <div className="col-8">
          <ButtonGroup vertical>
          <i className="far fa-star h1"></i>

            <span className="">1</span>
          <i className="fas fa-star"></i>
          </ButtonGroup>
          </div>
          <div className="col- "></div>
        </div>

        <div className="row">
          <div className="col-2"></div>
          <div className="col-8 d-grid gap-2">
            <textarea>

            </textarea>
          </div>
          <div className="col- "></div>
        </div>

        <div className="row">
          <div className="col-2"></div>
          <div className="col-8"></div>
          <div className="col-2">
          <Button
              lable="Submit"
              className="btn btn-success"
              onClick
            ></Button>
          </div>
        </div>
      </div>
    );
  }
}
