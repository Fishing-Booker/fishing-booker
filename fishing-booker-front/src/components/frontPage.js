import Entities from "./entities";
import { useState } from "react";
import RegistrationForm from "./registrationForm";
import { Link, BrowserRouter as Router, Route, Switch } from "react-router-dom";
import LoginForm from "./loginForm";
import Navbar from "./navbar";
import React from 'react'
import RegistrationType from "./registrationType";
import UserProfilPage from "./userProfilePage";
import LodgeOwnerHomePage from "./lodgeOwnerHomePage";
import LodgeProfile from "./lodgeProfile";
import LodgeImages from "./lodgeImages";
import LodgeRules from "./lodgeRules";
import LodgePriceList from "./lodgePricelist";


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
                        <Route exact path="/"><LodgeOwnerHomePage/></Route>
                        <Route path="/profile"><UserProfilPage/></Route>
                        <Route path="/lodge"><LodgeProfile/></Route>
                        <Route path="/lodgeImages"><LodgeImages/></Route>
                        <Route path="/lodgeRules"><LodgeRules/></Route>
                        <Route path="/lodgePricelist"><LodgePriceList/></Route>
                    </Switch>

                )}
                
	        </div>
        </div>
        </Router>
    )
}

export default FrontPage;

