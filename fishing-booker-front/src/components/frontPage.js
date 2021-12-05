import Entities from "./entities";
import { useState } from "react";
import RegistrationForm from "./registrationForm";
import { Link, BrowserRouter as Router, Route, Switch } from "react-router-dom";
import LoginForm from "./loginForm";
import Navbar from "./navbar";
import React from 'react'
import RegistrationType from "./registrationType";
import UserProfilPage from "./userProfilePage";
import Homepage from "./homepage";


const FrontPage = () => {

    return (
        <Router>
        <div>
            <div className="container">
                <Navbar/>
                <div className="row">
                    <Switch>
                        <Route exact path="/"><Homepage/><Entities/></Route>
                        <Route path="/register"><Homepage/><RegistrationForm/></Route>
                        <Route path="/login"><Homepage/><LoginForm/></Route>
                        <Route path="/profile"><UserProfilPage/></Route>
                    </Switch>
                </div>
	        </div>
        </div>
        </Router>
    )
}

export default FrontPage;

