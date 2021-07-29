import React from 'react';
import { BrowserRouter as Router, Route } from 'react-router-dom'
import LoginContainer from './containers/LoginContainer';
import { Switch } from 'react-router';


function App() {
  return (
  <div className='wrapper'>
      <Router>
        <Switch>
          <Route exact path='/login' component={LoginContainer} />
        </Switch>
      </Router>
  </div>
  );
}

export default App;
