import React from "react";
import Button from "../common/Button";

export default class FeedbackView extends React.Component {
  constructor(props) {
    super(props);
  }
  render() {
    console.log(this.props.text)
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
            <h5>Feedback:</h5>
          </div>
          <div className="col- "></div>
        </div>

        <div className="row">
          <div className="col-2"></div>
          <div className="col-8 row">
              {[...Array(5)].map((n, i) => (
                <div className="col-1">
                  <i id={i+1}
                    className={
                      i === (this.props.rate-1)
                        ? "far fa-star h1 yellow"
                        : "far fa-star h1"
                    }
                  ></i>
                  <p className="text-center">{i+1}</p>
                </div>
              ))}
          </div>
          <div className="col- "></div>
        </div>

        <div className="row">
          <div className="col-2"></div>
          <div className="col-8 d-grid gap-2">
            <textarea
              defaultValue={this.props.text}
              readonly
            ></textarea>
          </div>
          <div className="col- "></div>
        </div>

        <div className="row">
          <div className="col- "></div>
          <div className="col- "></div>
          <div className="col- "></div>
        </div>
      </div>
    );
  }
}
