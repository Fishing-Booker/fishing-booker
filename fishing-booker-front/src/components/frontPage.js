import Entities from "./entities";
import { useState, useEffect } from "react";
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
import AddLodgeForm from "./addLodgeForm";
import DeleteLogdeForm from "./deleteLodgeForm";
import EditLogdeForm from "./editLodgeForm";
import LodgeActions from "./lodgeActions";
import AddLodgeActionFrom from "./addLodgeActionForm";
import LodgeReservations from "./lodgeReservations";
import AddLodgeReservationByOwner from "./addLodgeReservationByOwner";
import ClientProfile from "./clientProfile";
import AddLodgeReservationPeriod from "./addLodgeReservationPeriod";
import LodgeReservationCalendar from "./lodgeReservationCalendar";
import Verification from "./registration/verification";


const FrontPage = () => {

    const [isLogged, setIsLogged] = useState(false);

    useEffect(() => {
        let token = localStorage.getItem('jwtToken');
        if (token != null) {
            setIsLogged(true)
        } else {
            setIsLogged(false)
        }
    })

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
                        <Route path="/verify/:code?"><Verification/></Route>
                        
                    </Switch>
                </div>
                    
                ) : (

                    <Switch>
                        <Route exact path="/"><LodgeOwnerHomePage/></Route>
                        <Route path="/profile"><UserProfilPage/></Route>
                        <Route path="/lodge/:lodgeId"><LodgeProfile/></Route>
                        <Route path="/lodgeImages"><LodgeImages/></Route>
                        <Route path="/lodgeRules"><LodgeRules/></Route>
                        <Route path="/lodgePricelist"><LodgePriceList/></Route>
                        <Route path="/myProfile"><AdminsProfile/></Route>
                        <Route path="/changePassword"><ChangePassword/></Route>
                        <Route path="/addLodge"><AddLodgeForm/></Route>
                        <Route path="/deleteLodge"><DeleteLogdeForm/></Route>
                        <Route path="/editLodge"><EditLogdeForm/></Route>
                        <Route path="/lodgeActions"><LodgeActions/></Route>
                        <Route path="/addLodgeAction"><AddLodgeActionFrom/></Route>
                        <Route path="/lodgeReservations"><LodgeReservations/></Route>
                        <Route path="/addLodgeReservation"><AddLodgeReservationByOwner/></Route>
                        <Route path="/clientProfile"><ClientProfile/></Route>
                        <Route path="/addLodgeReservationPeriod"><AddLodgeReservationPeriod/></Route>
                        <Route path="/lodgeReservationCalendar"><LodgeReservationCalendar/></Route>
                    </Switch>

                )}
                
	        </div>
        </div>
        </Router>
    )
}

export default FrontPage;

