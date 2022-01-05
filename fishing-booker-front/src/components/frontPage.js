import Entities from "./entities";
import { useState, useEffect } from "react";
import RegistrationForm from "./registration/registrationForm";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
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
import AccountRequest from "./admin/accountRequests";
import DeleteAccount from "./deleteAccount";
import DeleteRequest from "./admin/deleteRequests";
import AdventureProfile from "./instructor/adventureProfile";
import AdventureImages from "./instructor/adventureImages";
import AdventureRules from "./instructor/adventureRules";
import AdventurePricelist from "./instructor/adventurePricelist";
import AdventureActions from "./instructor/adventureActions";
import InstructorHomepage from "./instructor/instructorHomepage";
import InstructorsCalendar from "./instructor/instructorsCalendar";
import ShipOwnerHomePage from "./ship/shipOwnerHomePage";
import AddShipForm from "./ship/addShipForm";
import Explore from "./explore";
import Adventures from "./client/adventures";
import Lodges from "./client/lodges";
import Ships from "./client/ships";
import Search from "./search";
import ShipProfile from "./ship/shipProfile";
import ShipRules from "./ship/shipRules";
import ShipNavigationEquipment from "./ship/shipNavigationEq";
import ShipFishingEquipment from "./ship/shipFishingEq";
import Reservation from "./client/reservations";
import ReservationHistory from "./client/reservationHistory";
import MakeReservation from "./client/makeReservation";


const FrontPage = () => {
    const SERVER_URL = process.env.REACT_APP_API; 
    const [isLogged, setIsLogged] = useState(false);
    const [role, setRole] = useState("")
    

    useEffect(() => {
        let token = localStorage.getItem('jwtToken');
        if (token != null) {
            setIsLogged(true)
        } else {
            setIsLogged(false)
        }

        if(isLogged === true) {
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
                        <Route exact path="/"><Homepage/></Route>
                        <Route path="/register/"><Homepage/><RegistrationType/></Route>
                        <Route path="/registrationForm/:registrationType"><Homepage/><RegistrationForm/></Route>
                        <Route path="/login"><Homepage/><LoginForm/></Route>
                        <Route path="/verify/:code?"><Verification/></Route>
                        <Route path="/explore"><Explore/></Route>
                        <div className="row-search">
                            <Route path="/adventures"><Adventures/></Route>
                            <Route path="/lodges"><Lodges/></Route>
                            <Route path="/ships"><Ships/></Route>
                        </div>
                    </Switch>
                </div> }

                {isLogged && 
                    <Switch>
                        <Route path="/deleteAccount/:id"><DeleteAccount/></Route>
                    </Switch>
                }
                    
                { isLogged && role === "ROLE_LODGEOWNER" &&

                    <Switch>
                        <Route exact path="/"><LodgeOwnerHomePage/></Route>
                        <Route path="/profile"><UserProfilPage/></Route>
                        <Route path="/lodge/:lodgeId"><LodgeProfile/></Route>
                        <Route path="/lodgeImages/:lodgeId"><LodgeImages/></Route>
                        <Route path="/lodgeRules/:lodgeId"><LodgeRules/></Route>
                        <Route path="/lodgePricelist/:lodgeId"><LodgePriceList/></Route>
                        <Route path="/changePassword/:id"><ChangePassword/></Route>
                        <Route path="/addLodge"><AddLodgeForm/></Route>
                        <Route path="/deleteLodge"><DeleteLogdeForm/></Route>
                        <Route path="/editLodge"><EditLogdeForm/></Route>
                        <Route path="/lodgeActions"><LodgeActions/></Route>
                        <Route path="/addLodgeAction"><AddLodgeActionFrom/></Route>
                        <Route path="/lodgeReservations"><LodgeReservations/></Route>
                        <Route path="/addLodgeReservation"><AddLodgeReservationByOwner/></Route>
                        <Route path="/clientProfile"><ClientProfile/></Route>
                        <Route path="/addLodgeReservationPeriod"><AddLodgeReservationPeriod/></Route>
                        <Route path="/lodgeReservationCalendar/:lodgeId"><LodgeReservationCalendar/></Route>
                    </Switch>

                }

                {isLogged && (role==="ROLE_ADMIN" || role === "ROLE_DEFADMIN") && 
                    <Switch>
                        <Route exact path="/"></Route>
                        <Route path="/profile"><UserProfilPage/></Route>
                        <Route path="/changePassword/:id"><ChangePassword/></Route>
                        <Route path="/accountRequests"><AccountRequest/></Route>
                        <Route path="/deleteRequests"><DeleteRequest/></Route>

                    </Switch>
                }

                {isLogged && role==="ROLE_INSTRUCTOR" && 
                    <Switch>
                        <Route exact path="/"><InstructorHomepage/></Route>
                        <Route path="/profile"><UserProfilPage/></Route>
                        <Route path="/changePassword/:id"><ChangePassword/></Route>
                        <Route path="/adventureProfile/:adventureId"><AdventureProfile/></Route>
                        <Route path="/adventureImages/:adventureId"><AdventureImages/></Route>
                        <Route path="/adventureRules/:adventureId"><AdventureRules/></Route>
                        <Route path="/adventurePricelist/:adventureId"><AdventurePricelist/></Route>
                        <Route path="/adventureActions/:adventureId"><AdventureActions/></Route>
                        <Route path="/instructorsCalendar"><InstructorsCalendar/></Route>

                    </Switch>
                }

                {(isLogged && role==="ROLE_CLIENT") &&
                    <div className="row">
                        <Switch>
                            <Route exact path="/"><Homepage/></Route>
                            <Route path="/profile"><UserProfilPage/></Route>
                            <Route path="/changePassword/:id"><ChangePassword/></Route>
                            <Route path="/explore"><Explore/></Route>
                            <Route path="/client-reservations"><Reservation/></Route>
                            <Route path="/reservation-history"><ReservationHistory/></Route>
                            <Route path="/make-reservation/:id"><MakeReservation/></Route>
                            <div className="row-search">
                                <Route path="/adventures"><Adventures/></Route>
                                <Route path="/lodges"><Lodges/></Route>
                                <Route path="/ships"><Ships/></Route>
                            </div>
                        </Switch>
                    </div>
                }

                {isLogged && role === "ROLE_SHIPOWNER" &&
                    <Switch>
                        <Route exact path="/"><ShipOwnerHomePage/></Route>
                        <Route path="/profile"><UserProfilPage/></Route>
                        <Route path="/changePassword/:id"><ChangePassword/></Route>
                        <Route path="/ship/:shipId"><ShipProfile/></Route>
                        <Route path="/shipRules/:shipId"><ShipRules/></Route>
                        <Route path="/shipNavEq/:shipId"><ShipNavigationEquipment/></Route>
                        <Route path="/shipFishEq/:shipId"><ShipFishingEquipment/></Route>
                    </Switch>
                }
                
	        </div>
        </div>
        </Router>
    )
}

export default FrontPage;

