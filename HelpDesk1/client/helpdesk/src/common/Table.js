import React from "react";
import Action from "../common/Action";
import Button from "./Button";
import { ButtonGroup } from "react-bootstrap";

export default class Table extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <div className="container">
        <table className="table table-bordered table-responsive table-hover">
          <thead>
            <tr className="table-active">
              <th>
                <div className="row">
                  <div className={
                      this.props.colorName === "id"
                        ? "col text-right color-info"
                        : "col text-right"}
                    id="id"
                    onClick={this.props.onChangeTh}
                  >ID
                  </div>
                  <div className="col">
                    <ButtonGroup vertical>
                      <i
                        id="idUp"
                        className="fas fa-caret-up"
                        onClick={this.props.onSort}
                      />
                      <i
                        id="idDown"
                        className="fas fa-caret-down"
                        onClick={this.props.onSort}
                      ></i>
                    </ButtonGroup>
                  </div>
                </div>
              </th>
              <th>
                <div className="row">
                  <div className={
                      this.props.colorName === "name"
                        ? "col text-right color-info"
                        : "col text-right"}
                    id="name"
                    onClick={this.props.onChangeTh}
                  > Name
                  </div>
                  <div className="col">
                    <ButtonGroup vertical>
                      <i
                        id="nameUp"
                        className="fas fa-caret-up"
                        onClick={this.props.onSort}
                      ></i>
                      <i
                        id="nameDown"
                        className="fas fa-caret-down"
                        onClick={this.props.onSort}
                      ></i>
                    </ButtonGroup>
                  </div>
                </div>
              </th>
              <th>
                <div className="row">
                  <div className={
                      this.props.colorName === "desiredResolutionDate"
                        ? "col text-right color-info"
                        : "col text-right"}
                    id="desiredResolutionDate"
                    onClick={this.props.onChangeTh}
                  >Desired Date
                  </div>
                  <div className="col">
                    <ButtonGroup vertical>
                      <i
                        id="dateUp"
                        className="fas fa-caret-up"
                        onClick={this.props.onSort}
                      ></i>
                      <i
                        id="dateDown"
                        className="fas fa-caret-down"
                        onClick={this.props.onSort}
                      ></i>
                    </ButtonGroup>
                  </div>
                </div>
              </th>
              <th>
                <div className="row">
                  <div className={
                      this.props.colorName === "urgency"
                        ? "col text-right color-info"
                        : "col text-right"}
                    id="urgency"
                    onClick={this.props.onChangeTh}
                  >Urgency 
                  </div>
                  <div className="col">
                    <ButtonGroup vertical>
                      <i
                        id="UrgencyUp"
                        className="fas fa-caret-up"
                        onClick={this.props.onSort}
                      ></i>
                      <i
                        id="UrgencyDown"
                        className="fas fa-caret-down"
                        onClick={this.props.onSort}
                      ></i>
                    </ButtonGroup>
                  </div>
                </div>
              </th>
              <th>
                <div className="row">
                  <div className={
                      this.props.colorName === "state"
                        ? "col text-right color-info"
                        : "col text-right"}
                    id="state"
                    onClick={this.props.onChangeTh}
                  >Status
                  </div>
                  <div className="col">
                    <ButtonGroup vertical>
                      <i
                        id="StatusUp"
                        className="fas fa-caret-up"
                        onClick={this.props.onSort}
                      ></i>
                      <i
                        id="StatusDown"
                        className="fas fa-caret-down"
                        onClick={this.props.onSort}
                      ></i>
                    </ButtonGroup>
                  </div>
                </div>
              </th>
              <th>
                <div className="text-center">Action</div>
              </th>
            </tr>
          </thead>
          <tbody>
            {this.props.tickets.map((t, i) => (
              <tr>
                <th className="text-center">{t.id}</th>
                <th>
                  <Button
                    className="btn btn-link"
                    value={t.id}
                    onClick={this.props.goToOverview}
                    lable={t.name}
                  ></Button>
                </th>
                <th className="text-center">
                  {t.desiredResolutionDate &&
                    new Date(t.desiredResolutionDate).toLocaleDateString(
                      "en-GB"
                    )}
                </th>
                <th className="text-center">{t.urgency}</th>
                <th className="text-center">{t.state}</th>
                <th>
                  <Action
                    name={t.id}
                    onChangeState={this.props.onChangeState}
                    changeAction={this.props.onChangeAction(t)}
                  ></Action>
                </th>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    );
  }
}
