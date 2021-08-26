import React from "react";
import Action from "../common/Action";
import Button from "./Button";

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
                ID &nbsp;
                <i id="1" className="fas fa-caret-up" onClick={this.props.onSortIdUp}></i>
                <i className="fas fa-caret-down"></i>
              </th>
              <th>
                Name &nbsp;
                <i className="fas fa-caret-up"></i>
                <i className="fas fa-caret-down"></i>
              </th>
              <th>
                Desired Date &nbsp;
                <i className="fas fa-caret-up"></i>
                <i className="fas fa-caret-down"></i>
              </th>
              <th>
                Urgency &nbsp;
                <i className="fas fa-caret-up"></i>
                <i className="fas fa-caret-down"></i>
              </th>
              <th>
                Status &nbsp;
                <i className="fas fa-sort"></i>
                <i className="fas fa-caret-down"></i>
              </th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {this.props.tickets.map((t, i) => (
              <tr>
                <th>{t.id}</th>
                <th>
                  <Button
                    className="btn btn-link"
                    value={t.id}
                    onClick={this.props.goToOverview}
                    lable={t.name}
                  ></Button>
                </th>
                <th>
                  {t.desiredResolutionDate &&
                    new Date(t.desiredResolutionDate).toLocaleDateString(
                      "en-GB"
                    )}
                </th>
                <th>{t.urgency}</th>
                <th>{t.state}</th>
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
