import React, { Component } from 'react';
import logo from './where_next_logo.png';
import './App.css';

class App extends Component {
  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <p>
            WhereNext <code>...coding in progress...</code> will be here :)
          </p>
        </header>
      </div>
    );
  }
}

export default App;
