import Entities from "./entities";
import { useState, useEffect } from "react";
import RegistrationForm from "./registration/registrationForm";
import { Link, BrowserRouter as Router, Route, Switch } from "react-router-dom";
import LoginForm from "./registration/loginForm";
import Navbar from "./navbar";
import React from 'react'
import RegistrationType from "./registration/registrationType";
import UserProfilPage from "./userProfilePage";
import LodgeOwnerHomePage from "./lodge/lodgeOwnerHomePage";
import LodgeProfile from "./lodge/lodgeProfile";
import LodgeImages from "./lodge/lodgeImages";
import LodgeRules from "./lodge/lodgeRules";
import LodgePriceList from "./lodge/lodgePricelist";
import Homepage from "./homepage";
import AdminsProfile from "./adminsProfile";
import ChangePassword from "./changePassword";
import AddLodgeForm from "./lodge/addLodgeForm";
import DeleteLogdeForm from "./lodge/deleteLodgeForm";
import EditLogdeForm from "./lodge/editLodgeForm";
import LodgeActions from "./lodge/lodgeActions";
import AddLodgeActionFrom from "./lodge/addLodgeActionForm";
import LodgeReservations from "./lodge/lodgeReservations";
import AddLodgeReservationByOwner from "./lodge/addLodgeReservationByOwner";
import ClientProfile from "./clientProfile";
import AddLodgeReservationPeriod from "./lodge/addLodgeReservationPeriod";
import LodgeReservationCalendar from "./lodge/lodgeReservationCalendar";
import Verification from "./registration/verification";
import axios from "axios";


const FrontPage = () => {
    const SERVER_URL = process.env.REACT_APP_API; 
    const [isLogged, setIsLogged] = useState(false);
    const [role, setRole] = useState("ROLE_INSTRUCTOR")
    

    useEffect(() => {
        let token = localStorage.getItem('jwtToken');
        if (token != null) {
            setIsLogged(true)
        } else {
            setIsLogged(false)
        }

        if(isLogged == true) {
            const headers = {'Content-Type' : 'application/json',
                             'Authorization' : `Bearer ${localStorage.jwtToken}`}
            console.log(headers)
            axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers})
            .then(response => {
                var user = response.data;
                axios.get(SERVER_URL + `/users/getRole/${user.id}`, {headers:headers})
                .then(response => {
                    setRole(response.data);
                });
            });
        }
    })

    return (
        <Router>
        <div>
            <div className="container">
                <Navbar/>
                {!isLogged &&
                    <div className="row">
                    <Switch>
                        <Route exact path="/"><Homepage/><Entities/></Route>
                        <Route path="/register/"><Homepage/><RegistrationType/></Route>
                        <Route path="/registrationForm/:registrationType"><Homepage/><RegistrationForm/></Route>
                        <Route path="/login"><Homepage/><LoginForm/></Route>
                        <Route path="/verify/:code?"><Verification/></Route>
                        
                    </Switch>
                </div> }
                    
                { isLogged && role == "ROLE_LODGEOWNER" &&

                    <Switch>
                        <Route exact path="/"><LodgeOwnerHomePage/></Route>
                        <Route path="/profile"><UserProfilPage/></Route>
                        <Route path="/lodge/:lodgeId"><LodgeProfile/></Route>
                        <Route path="/lodgeImages/:lodgeId"><LodgeImages/></Route>
                        <Route path="/lodgeRules/:lodgeId"><LodgeRules/></Route>
                        <Route path="/lodgePricelist/:lodgeId"><LodgePriceList/></Route>
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

                }

                {(role=="ROLE_ADMIN" || role == "ROLE_DEFADMIN") && 
                    <Switch>
                        <Route exact path="/"></Route>
                        <Route exact path="/myProfile"><AdminsProfile/></Route>
                        <Route exact path="/changePassword"><ChangePassword/></Route>
                    </Switch>
                }

                {role=="ROLE_INSTRUCTOR" && 
                    <Switch>
                        <Route exact path="/"></Route>
                        <Route exact path="/myProfile"><AdminsProfile/></Route>
                        <Route exact path="/changePassword"><ChangePassword/></Route>
                    </Switch>
                }

                
                
	        </div>
        </div>
        </Router>
    )
}

export default FrontPage;

