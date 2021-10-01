import React from "react";
import Title from "../common/Title.js";
import Button from "../common/Button.js";

class LoginView extends React.Component {
  constructor(props) {
    super(props);
    this.state = {};
  }
  render() {
    return (
      <div className="container overflow-hidden">
        <br></br>

        <div className="row ">
          <div className="col-3"></div>
          <div className="col-6 ">
            <div className="m-5">
              <Title title="Login to the Help Desk"></Title>
            </div>
          </div>
          <div className="col-3"></div>
        </div>

        <div className="row">
          <div className="col-5"></div>
          <div className="col-4">
            <p className="color-red">
              {this.props.loginRequired &&
                "Please fill out the required field."}
            </p>
          </div>
          <div className="col-3"></div>
        </div>

        <div className="row mb-4">
          <div className="col-3"></div>
          <div className="col-2 text-right">
            <lable>User Name:</lable>
          </div>
          <div className="col-4 d-grid gap-2 ">
            <input
              type="email"
              name="login"
              placeholder="Email"
              onChange={this.props.onHandleChange}
            ></input>
          </div>
          <div className="col-3"></div>
        </div>

        <div className="row">
          <div className="col-5"></div>
          <div className="col-4">
            <p className="color-red">
              {this.props.passwordRequired &&
                "Please fill out the required field."}
            </p>
          </div>
          <div className="col-3"></div>
        </div>

        <div className="row mb-4">
          <div className="col-3"></div>
          <div className="col-2 text-right">
            <lable>Password:</lable>
          </div>
          <div className="col-4 d-grid gap-2 ">
            <input
              type="password"
              name="password"
              placeholder="Enter password"
              onChange={this.props.onHandleChange}
            ></input>
          </div>
          <div className="col-3"></div>
        </div>

        <div className="row">
          <div className="col-3"></div>
          <div className="col-6">
            <p className="color-red text-center">
              {this.props.enterInvalid &&
              "Please make sure you are using a valid email or password"}
            </p>
          </div>
          <div className="col-3"></div>
        </div>

        <div className="row">
          <div className="col-7"></div>
          <div className="col-2">
            <Button
              lable="Enter"
              onClick={this.props.signin}
              className="btn-md"
            ></Button>
          </div>
          <div className="col-3"></div>
        </div>
      </div>
    );
  }
}
export default LoginView;
