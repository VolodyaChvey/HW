import React from "react";

export default class LabelTextarea extends React.Component {
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
          <h6 className="text-center">{this.props.lable}</h6>
        </div>
        <div className="col-5 d-grid gap-2">
          <textarea
            maxLength="500"
            name={this.props.name}
            onChange={this.props.onChange}
          ></textarea>
        </div>
        <div className="col-2"></div>
      </div>
    );
  }
}
