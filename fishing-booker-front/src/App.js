import { Component } from 'react';
import './App.css';
import FrontPage from './components/frontPage';
import { ToastProvider } from "react-toast-notifications";
import React from 'react';

class App extends Component {
  render() {
    return (
      <ToastProvider autoDismiss={true}>
        <div>
          <FrontPage/>
        </div>
      </ToastProvider>
    )
  }
}

export default App;
