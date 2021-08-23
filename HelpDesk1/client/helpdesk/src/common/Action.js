import React from "react";
import Button from "../common/Button";
import { ButtonGroup } from "react-bootstrap";
import { Dropdown } from "react-bootstrap";
import { DropdownButton } from "react-bootstrap";
export default class Action extends React.Component {
  constructor(props) {
    super(props);
  }
  render() {
    return (
      <div >
<ButtonGroup className="d-grid gap-2 ">
  <DropdownButton as={ButtonGroup}  variant="success" id="bg-nested-dropdown"
       title={this.props.changeAction.length===0?
       "No action":"Action"} >
      {this.props.changeAction.map((a,i)=>(
        <Dropdown.Item eventKey={i}>
        <Button
         lable={a} 
         onClick={this.props.onChangeState}
         value={a}
         name={this.props.name}
        ></Button>
      </Dropdown.Item>
      ))}
  </DropdownButton>
</ButtonGroup>

      </div>
    );
  }
}
