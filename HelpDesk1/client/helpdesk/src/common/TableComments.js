import React from "react";
import Button from "./Button";

export default class TableComments extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
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
            <tr>
              <th>{}</th>
              <th></th>
              <th>{}</th>
            </tr>
          </tbody>
        </table>

        <div className="row">
          <div className="col-6 d-grid gap-2">
            <textarea
              maxLength="500"
              name
              onChange
            ></textarea>
          </div>

          <div className="col-2 mt-4">
            <Button lable="Add Comment" onClick></Button>
          </div>
        </div>
      </div>
    );
  }
}
