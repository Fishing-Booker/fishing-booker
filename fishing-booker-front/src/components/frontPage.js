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
import axios from "axios";


const FrontPage = () => {

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
                             'Authorization' : Bearer `${localStorage.jwtToken}`}
            console.log(headers)
            axios.get("http://localhost:8080/users/getLoggedUser", { headers: headers})
            .then(response => {
                var user = response.data;
                axios.get(`http://localhost:8080/users/getRole/${user.id}`, {headers:headers})
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

