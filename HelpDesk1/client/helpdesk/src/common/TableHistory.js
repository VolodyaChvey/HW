import React from "react";

export default class TableHistory extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <div className="conteiner">
        <h4 color='info'>Show All</h4>
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
            <tr>
              <th>{}</th>
              <th></th>
              <th>{}</th>
              <th>{}</th>
            </tr>
          </tbody>
        </table>
      </div>
    );
  }
}
