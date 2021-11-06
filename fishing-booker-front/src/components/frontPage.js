import Entities from "./entities";
import { useState } from "react";
import RegistrationForm from "./registrationForm";
import { Link, BrowserRouter as Router, Route, Switch } from "react-router-dom";
import LoginForm from "./loginForm";

const FrontPage = () => {

    return (
        <Router>
        <div>
            <div className="container">
                <div className="navbar">
                    <nav>
                        <ul>
                            <li><Link to="/"> HOME</Link></li>
                            <li><Link to="/register">REGISTER </Link></li>
                            <li><Link to="/login">LOG IN</Link></li>
                        </ul>
                    </nav>
                </div>

                <div className="row">
                    <div className="col">
                        <h1> Fishing booker </h1>
                        <p className="main-description"> Book your next fishing trip! </p>
                        <button className="explore-btn" type="button">Explore</button>
                    </div>
                    <Switch>
                        <Route exact path="/"><Entities/></Route>
                        <Route path="/register"><RegistrationForm/></Route>
                        <Route path="/login"><LoginForm/></Route>
                    </Switch>
                </div>
	        </div>
        </div>
        </Router>
    )
}

export default FrontPage;

