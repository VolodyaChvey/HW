import React from "react";
import Button from "../common/Button";
import TableComments from "../common/TableComments";
import TableHistory from "../common/TableHistory";

export default class TicketOverviewView extends React.Component {
  constructor(props) {
    super(props);
    this.onCategory=this.onCategory.bind(this)
  }

  onCategory(data){
    var result="not found";
    switch(data){
      case 1: result="Application & Services"; break;
      case 2: result="Benefits & Paper Work"; break;
      case 3: result="Hardware & Software"; break;
      case 4: result="People Management"; break;
      case 5: result="Security & Access"; break;
      case 6: result="Workplaces & Facilities"; break; 
    }
    return result;
  }

  render() {
    
    return (
      <div className="container">
        <div className="row mt-5">
          <div className="col- "> </div>
          <div className="col-"></div>
          <div className="col-"> </div>
        </div>

        <div className="row">
        <div className="col-2 ">
            <Button
              lable="Ticket List"
              className="btn-success my-2"
              onClick={this.props.goToTickets}
            ></Button>
          </div>

          <div className="col-8">
            <h2>
              Ticket ({this.props.ticket.id}) - {this.props.ticket.name}
            </h2>
          </div>

          <div className="col-2">
            {(this.props.ticket.state==="DRAFT"&&(this.props.user.role==="MANAGER"||this.props.user.role==="EMPLOYEE"))
            &&<Button
              lable="Edit"
              className="btn-success my-4"
              onClick={this.props.toEdit}
            ></Button>}
          </div>
        </div>

        <div className="row">
          <div className="col-3 text-right" style={{ textAlign: "right" }}>
            <p style={{ textAlign: "right" }}>Created on:</p>
          </div>
          <div className="col-1 "> </div>
          <div className="col-2">{new Date(this.props.ticket.createdOn).toLocaleDateString('en-GB')}</div>

          <div className="col-4"></div>
          <div className="col-2">
            <Button lable="Leave Fedback" className="btn-success"></Button>
          </div>
        </div>

        <div className="row">
          <div className="col-3">
            <p className="" style={{ textAlign: "right" }}>
              Status:
            </p>
          </div>
          <div className="col-1"></div>
          <div className="col-2"> {this.props.ticket.state}</div>
          <div className="col-2">
            <p style={{ textAlign: "right" }}>Category:</p>
          </div>
          <div className="col-2">{this.onCategory(this.props.ticket.category)}</div>
          <div className="col-2"> </div>
        </div>

        <div className="row">
          <div className="col-3"> Urgency: </div>
          <div className="col-1"></div>
          <div className="col-2"> {this.props.ticket.urgency}</div>
          <div className="col-6"> </div>
        </div>

        <div className="row">
          <div className="col-3"> Desired resolution date: </div>
          <div className="col-1"></div>
          <div className="col-2">
            {this.props.ticket.desiredResolutionDate&&
            new Date(this.props.ticket.desiredResolutionDate).toLocaleDateString('en-GB')}
          </div>
          <div className="col-6"> </div>
        </div>

        <div className="row">
          <div className="col-3"> Owner: </div>
          <div className="col-1"></div>
          <div className="col-2">
            {this.props.ticket.owner &&
              this.props.ticket.owner.firstName +
                " " +
                this.props.ticket.owner.lastName}
          </div>
          <div className="col-6"> </div>
        </div>

        <div className="row">
          <div className="col-3"> Approver: </div>
          <div className="col-1"></div>
          <div className="col-2">
            {this.props.ticket.approver &&
              this.props.ticket.approver.firstName +
                " " +
                this.props.ticket.approver.lastName}
          </div>
          <div className="col-6"> </div>
        </div>

        <div className="row">
          <div className="col-3"> Assignee: </div>
          <div className="col-1"></div>
          <div className="col-2">
            {this.props.ticket.assignee &&
              this.props.ticket.assignee.firstName +
                " " +
                this.props.ticket.assignee.lastName}
          </div>
          <div className="col-6"> </div>
        </div>

        <div className="row">
          <div className="col-3"> Attachments: </div>
          <div className="col-1"></div>
          <div className="col-8"> {this.props.attachments.map((a,i)=>(
            <div className="row">
              <div className="col">
              {a.name} 
              </div>
              <div className='col'>
                
              <Button lable='Download' name={a.id} onClick={this.props.onDownLoad}></Button>
              </div>
            </div>
          ))}</div>
          <div className="col"> </div>
        </div>

        <div className="row">
          <div className="col-3"> Description: </div>
          <div className="col-1"></div>
          <div className="col-5 d-grid gap-2">
            <textarea
              maxLength="500"
              name={this.props.name}
              onChange={this.props.onChange}
              defaultValue={this.props.ticket.description}
            >
              
            </textarea>
          </div>
          <div className="col-"> </div>
        </div>

        <div className="row mt-5 mb-3">
          <div className="col-6 ">
            <Button
              lable="History"
              value="history"
              className={this.props.classBtnHistory}
              onClick={this.props.onClickBtn}
            ></Button>
          </div>
          <div className="col-6">
            <Button
              lable="Comments"
              className={this.props.classBtnComments}
              onClick={this.props.onClickBtn}
            ></Button>
          </div>
        </div>
        {this.props.ActiveHistory ? (
          <TableHistory
             history={this.props.history}
          ></TableHistory>
        ) : (
          <TableComments
              comments={this.props.comments}
              addComment={this.props.addComment}
              onHandleChange={this.props.onHandleChange}
          ></TableComments>
        )}
      </div>
    );
  }
}
