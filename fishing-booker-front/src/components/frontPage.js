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
import Homepage from "./homepage";
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
                    
                    <Switch>
                        <Route exact path="/"><Homepage/><Entities/></Route>
                        <Route path="/register"><Homepage/><RegistrationType/></Route>
                        <Route path="/registrationForm"><Homepage/><RegistrationForm/></Route>
                        <Route path="/login"><Homepage/><LoginForm/></Route>
                        
                    </Switch>
                </div>
                    
                ) : (

                    <Switch>
                        <Route exact path="/"><LodgeOwnerHomePage/></Route>
                        <Route path="/profile"><UserProfilPage/></Route>
                        <Route path="/lodge/:lodgeId"><LodgeProfile/></Route>
                        <Route path="/lodgeImages/:lodgeId"><LodgeImages/></Route>
                        <Route path="/lodgeRules/:lodgeId"><LodgeRules/></Route>
                        <Route path="/lodgePricelist"><LodgePriceList/></Route>
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

