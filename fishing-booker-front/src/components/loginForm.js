import './../css/registration.css';
import { Link } from "react-router-dom";
import React from 'react'

const LoginForm = () => {
    return (
        <div className="container-log">
        <div className="title">Log In</div>
        <div className="content">
          <form action="#">
            <div className="user-details">
              <div className="input-box">
                <span className="details">Email</span>
                <input type="text" placeholder="Enter your email" required/>
              </div>
              <div className="input-box">
                <span className="details">Password</span>
                <input type="text" placeholder="Enter your password" required/>
              </div>
            </div>
            <p className="reg-message">Don't have an account? <Link className="link" to="/register">Register</Link></p>
            <div className="button">
              <input type="submit" value="Log in"/>
            </div>
          </form>
        </div>
      </div>
    )
}

export default LoginForm;

