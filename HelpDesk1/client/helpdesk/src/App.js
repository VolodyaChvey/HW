import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import LoginContainer from './containers/LoginContainer';
import LoginForm from './containers/LoginForm';
import 'bootstrap/dist/css/bootstrap.min.css';
import TicketsContainer from './containers/TicketsContainer';
import TicketNew from './containers/TicketNew';


function App() {
  return (
  <div>
      <Router>
        <Switch>
          <Route exact path='/' component={LoginContainer} />
          <Route  path='/tickets' component={TicketsContainer} />
          <Route  path='/ticket/new' component={TicketNew} />
        </Switch>
      </Router>
  </div>
  );
}

export default App;
