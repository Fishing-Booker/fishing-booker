import Entities from "./entities";
import { useState } from "react";
import RegistrationForm from "./registrationForm";
import { Link, BrowserRouter as Router, Route, Switch } from "react-router-dom";
import LoginForm from "./loginForm";
import Navbar from "./navbar";
import React from 'react'
import RegistrationType from "./registrationType";
import AdminsProfile from "./adminsProfile";
import ChangePassword from "./changePassword";

const FrontPage = () => {

    const [isLogged, setIsLogged] = useState(true);

    return (
        <Router>
        <div>
            <div className="container">
                <Navbar/>
                {!isLogged ? (
                <div className="row">
                    <div className="col">
                        <h1> Fishing booker </h1>
                        <p className="main-description"> Book your next fishing trip! </p>
                        <button className="explore-btn" type="button">Explore</button>
                    </div>
                    <Switch>
                        <Route exact path="/"><Entities/></Route>
                        <Route path="/register"><RegistrationType/></Route>
                        <Route path="/registrationForm"><RegistrationForm/></Route>
                        <Route path="/login"><LoginForm/></Route>
                    </Switch>
                </div>
                ) : (
                    <Switch>
                        <Route exact path="/myProfile"><AdminsProfile/></Route>
                        <Route exact path="/changePassword"><ChangePassword/></Route>
                    </Switch>
                )}
	        </div> 
        </div>
        </Router>
    )
}

export default FrontPage;

