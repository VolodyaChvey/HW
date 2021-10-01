import React from "react";

export default class TableHistory extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <div className="conteiner">
        <h4 onClick={this.props.onAllShowHistories} className="color-info">
          {this.props.lableHistories}
        </h4>
        <table className="table table-bordered table-responsive table-hover">
          <thead>
            <tr className="table-active">
              <th>Data</th>
              <th>User</th>
              <th>Action</th>
              <th>Description</th>
            </tr>
          </thead>
          <tbody>
            {this.props.history.map((h, i) => (
              <tr>
                <th>
                  {new Date(h.date).toLocaleString("en-US", {
                    year: "numeric",
                    month: "short",
                    day: "numeric",
                    hour: "2-digit",
                    minute: "2-digit",
                    second: "2-digit",
                    hour12: false,
                  })}
                </th>
                <th>{h.userDto.firstName + " " + h.userDto.lastName} </th>
                <th>{h.action}</th>
                <th>{h.description}</th>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    );
  }
}
