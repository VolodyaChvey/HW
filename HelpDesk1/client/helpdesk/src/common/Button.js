import React from "react";

class Button extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
           
        }
    }

  render() {
    return (
      <div className="d-grid gap-2 ">
        <button
          type={this.props.type}
          className={this.props.className}
          value={this.props.value}
          name={this.props.name}
          onClick={this.props.onClick}
        >
          {this.props.lable}
        </button>
      </div>
    );
  }
}
export default Button;

