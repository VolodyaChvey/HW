import React from "react";

export default class LabelInputTicket extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      class: this.props.className,
    };
  }
  render() {
    return (
      <div className="row my-3">
        <div className="col-3"></div>
        <div className="col-2">
          <h6 className="text-center">
            {this.props.label}
            <span className="text-danger">*</span>
          </h6>
        </div>
        <div className="col-4 d-grid gap-2">
          <input
            type="text"
            onChange={this.props.onChange}
            name={this.props.name}
            value={this.props.value}
          ></input>
        </div>
        <div className="col-3"></div>
      </div>
    );
  }
}
