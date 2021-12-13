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
                        <Route path="/register/"><Homepage/><RegistrationType/></Route>
                        <Route path="/registrationForm/:registrationType"><Homepage/><RegistrationForm/></Route>
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
                        <Route path="/lodgePricelist/:lodgeId"><LodgePriceList/></Route>
                        <Route exact path="/myProfile"><AdminsProfile/></Route>
                        <Route exact path="/changePassword"><ChangePassword/></Route>
                        <Route exact path="/addLodge"><AddLodgeForm/></Route>
                        <Route exact path="/deleteLodge"><DeleteLogdeForm/></Route>
                        <Route exact path="/editLodge"><EditLogdeForm/></Route>
                        <Route exact path="/lodgeActions"><LodgeActions/></Route>
                        <Route exact path="/addLodgeAction"><AddLodgeActionFrom/></Route>
                        <Route exact path="/lodgeReservations"><LodgeReservations/></Route>
                        <Route exact path="/addLodgeReservation"><AddLodgeReservationByOwner/></Route>
                        <Route exact path="/clientProfile"><ClientProfile/></Route>
                        <Route exact path="/addLodgeReservationPeriod"><AddLodgeReservationPeriod/></Route>
                        <Route exact path="/lodgeReservationCalendar"><LodgeReservationCalendar/></Route>
                    </Switch>

                )}
                
	        </div>
        </div>
        </Router>
    )
}

export default FrontPage;

