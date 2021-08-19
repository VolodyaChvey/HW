import React from "react";
import Button from "./Button";

export default class TableComments extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    console.log(this.props.comments)
    return (
      <div className="container">
        <h4 color="info">Show All</h4>
        <table className="table table-bordered table-responsive table-hover">
          <thead>
            <tr className="table-active">
              <th>Data</th>
              <th>User</th>
              <th>Comments</th>
            </tr>
          </thead>
          <tbody>
            {this.props.comments.map((c, i) => (
              <tr>
                <th>
                  {new Date(c.date).toLocaleString("en-US", {
                    year: "numeric",
                    month: "short",
                    day: "numeric",
                    hour: "2-digit",
                    minute: "2-digit",
                    second: "2-digit",
                    hour12: false,
                  })}
                </th>
                <th>{c.userDto.firstName + " " + c.userDto.lastName} </th>
                <th>{c.text}</th>
              </tr>
            ))}
          </tbody>
        </table>

        <div className="row">
          <div className="col-6 d-grid gap-2">
            <textarea maxLength="500" name="text" onChange></textarea>
          </div>

          <div className="col-2 mt-4">
            <Button lable="Add Comment" onClick={this.props.addComment}></Button>
          </div>
        </div>
      </div>
    );
  }
}
