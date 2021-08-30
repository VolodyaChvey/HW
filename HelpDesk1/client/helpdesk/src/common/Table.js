import React from "react";
import Action from "../common/Action";
import Button from "./Button";
import '../css/table_cell.css'

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
                <div className='cell_colider'>
                <i id="idUp" className="fas fa-caret-up" onClick={this.props.onSort}/> <br/>
                <i id="idDown" className="fas fa-caret-down" onClick={this.props.onSort}></i>
                </div>
              </th>
              <th>
                Name &nbsp;
                <div className='cell_colider'>
                <i  id="nameUp" className="fas fa-caret-up" onClick={this.props.onSort}></i> <br/>
                <i  id="nameDown" className="fas fa-caret-down" onClick={this.props.onSort}></i>
                </div>
              </th>
              <th>
                Desired Date &nbsp;
                <div className='cell_colider'>
                <i  id="dateUp" className="fas fa-caret-up" onClick={this.props.onSort}></i> <br/>
                <i  id="dateDown" className="fas fa-caret-down" onClick={this.props.onSort}></i>
                </div>
              </th>
              <th>
                Urgency &nbsp;
                <div className='cell_colider'>
                <i  id="UrgencyUp" className="fas fa-caret-up" onClick={this.props.onSort}></i> <br/>
                <i  id="UrgencyDown" className="fas fa-caret-down" onClick={this.props.onSort}></i>
                </div>
              </th>
              <th>
                Status &nbsp;
                <div className='cell_colider'>
                <i  id="StatusUp" className="fas fa-caret-up" onClick={this.props.onSort}></i> <br/>
                <i  id="StatusDown" className="fas fa-caret-down" onClick={this.props.onSort}></i>
                </div>
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
