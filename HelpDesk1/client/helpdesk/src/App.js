import React from 'react';
import { Route, Switch } from 'react-router-dom';
import LoginContainer from './containers/LoginContainer';
import 'bootstrap/dist/css/bootstrap.min.css';
import TicketsContainer from './containers/TicketsContainer';
import TicketNew from './containers/TicketNew';
import TicketOverview from './containers/TicketOverview';
import EditTicket from './containers/EditTicket';
import LeaveFeedback from './containers/LeaveFeedback';


function App() {
  return (
  <div>
      
        <Switch>
          <Route exact path='/' component={LoginContainer} />
          <Route  path='/tickets' component={TicketsContainer} />
          <Route  path='/create' component={TicketNew} />
          <Route  path='/overview' component={TicketOverview }/>
          <Route  path='/edit' component={EditTicket}/>
          <Route  path='/leaveFeedback' component={LeaveFeedback}/>
        </Switch>
     
  </div>
  );
}

export default App;
