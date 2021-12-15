import '../../css/registration.css';
import { Link, useHistory } from "react-router-dom";
import React from 'react'
import { useState } from "react";
import axios from 'axios';

const LoginForm = () => {
  const SERVER_URL = process.env.REACT_APP_API;
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const history = useHistory();

  const credentials = {
    username,
    password
  }

  const handleLogin = e => {
    e.preventDefault();
    axios.post(SERVER_URL + "/auth/login", credentials)
         .then(response => {
           let token = response.data.accessToken;
           console.log(token);
           localStorage.setItem('jwtToken', token);
           history.push('/')
           window.location.reload()
         })
  }


    return (
        <div className="container-log">
        <div className="title">Log In</div>
        <div className="content">
          <form action="#">
            <div className="user-details">
              <div className="input-box">
                <span className="details">Username</span>
                <input type="text" placeholder="Enter your username" required value={username} onChange={(e) => setUsername(e.target.value)}/>
              </div>
              <div className="input-box">
                <span className="details">Password</span>
                <input type="text" placeholder="Enter your password" required value={password} onChange={(e) => setPassword(e.target.value)}/>
              </div>
            </div>
            <p className="reg-message">Don't have an account? <Link className="link" to="/register">Register</Link></p>
            <div className="button">
              <input type="submit" value="Log in" onClick={handleLogin}/>
            </div>
          </form>
        </div>
      </div>
    )
}

export default LoginForm;

