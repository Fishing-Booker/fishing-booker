import { Component } from 'react';
import './App.css';
import FrontPage from './components/frontPage';
import { ToastProvider } from "react-toast-notifications";
import { Provider } from "react-redux";
import { store } from "./actions/store";
import React from 'react'

import LodgeOwnerHomePage from './components/lodgeOwnerHomePage'

class App extends Component {
  render() {
    return (
      <Provider store={store}>
        <ToastProvider autoDismiss={true}>
          <div>
            <LodgeOwnerHomePage/>
          </div>
        </ToastProvider>
      </Provider>
    )
  }
}

export default App;
