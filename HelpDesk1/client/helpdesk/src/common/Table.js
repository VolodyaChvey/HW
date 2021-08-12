import React from "react";

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
              <th>ID
                <i className='fas fa-sort'></i>
              </th>
              <th>Name</th>
              <th>Desired Date</th>
              <th>Urgency</th>
              <th>Status</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {this.props.tickets.map((t) => (
              <tr>
                <th>{t.id}</th>
                <th>{t.name}</th>
                <th>
                  {t.desiredResolutionDate &&
                    new Date(t.desiredResolutionDate).toLocaleDateString('en-GB')}
                </th>
                <th>{t.urgency}</th>
                <th>{t.state}</th>
                <th></th>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    );
  }
}
