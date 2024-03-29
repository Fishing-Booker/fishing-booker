import Entities from "./entities";
import { useState, useEffect } from "react";
import RegistrationForm from "./registration/registrationForm";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import LoginForm from "./registration/loginForm";
import Navbar from "./navbar";
import React from 'react'
import RegistrationType from "./registration/registrationType";
import UserProfilPage from "./userProfilePage";
import UserCategory from "./userCategory"
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
import ShipProfile from "./ship/shipProfile";
import ShipRules from "./ship/shipRules";
import ShipNavigationEquipment from "./ship/shipNavigationEq";
import ShipFishingEquipment from "./ship/shipFishingEq";
import Reservation from "./client/reservations";
import ReservationHistory from "./client/reservationHistory";
import LodgeReservation from "./client/lodgeReservation";
import Subscriptions from "./client/subscriptions";
import ShipReservation from "./client/shipReservation";
import AdventureReservation from "./client/adventureReservation";
import UserList from "./admin/userList";
import UserInfo from "./admin/userInfo";
import AdminHomepage from "./admin/adminHomepage";
import LodgeReservationActions from "./client/lodgeReservationActions";
import ShipReservationAction from "./client/shipReservationAction";
import AdventureReservationActions from "./client/adventureReservationActions";
import ShipImages from "./ship/shipImages";
import InstructorReservations from "./instructor/instructorReservations";
import ShipPriceList from "./ship/shipPricelist";
import ReservationHistoryOwner from "./reservationHistory";
import Feedbacks from "./admin/feedbacks";
import ShipReservationCalendar from "./ship/shipReservationCalendar";
import ShipActions from "./ship/shipActions";
import ShipOwnerReservationCalendar from "./ship/shipOwnerReservationCalendar";
import ShipOwnerReservations from "./ship/shipOwnerReservations";
import ShipReservations from "./ship/shipReservations";
import LodgeOwnerCalendar from "./lodge/lodgeOwnerCalendar";
import LodgeReservationHistory from "./lodge/lodgeReservationHistory";
import ShipReservationHistory from "./ship/shipReservationHistory";
import ReservationReport from "./admin/reservationReport";
import LodgeLocation from "./lodge/lodgeLocation";
import ShipLocation from "./ship/shipLocation";
import AdventureLocation from "./instructor/adventureLocation";
import LodgeReports from "./lodge/lodgeReports";
import ShipReports from "./ship/shipReports";
import AdventureReports from "./instructor/adventureReports";

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
                        <Route path="/lodgeLocation/:lodgeId"><LodgeLocation/></Route>
                        <Route path="/lodgeRules/:lodgeId"><LodgeRules/></Route>
                        <Route path="/lodgePricelist/:lodgeId"><LodgePriceList/></Route>
                        <Route path="/changePassword/:id"><ChangePassword/></Route>
                        <Route path="/addLodge"><AddLodgeForm/></Route>
                        <Route path="/deleteLodge"><DeleteLogdeForm/></Route>
                        <Route path="/editLodge"><EditLogdeForm/></Route>
                        <Route path="/lodgeActions/:lodgeId"><LodgeActions/></Route>
                        <Route path="/addLodgeAction"><AddLodgeActionFrom/></Route>
                        <Route path="/lodgeReservations"><LodgeReservations/></Route>
                        <Route path="/addLodgeReservation"><AddLodgeReservationByOwner/></Route>
                        <Route path="/clientProfile"><ClientProfile/></Route>
                        <Route path="/addLodgeReservationPeriod"><AddLodgeReservationPeriod/></Route>
                        <Route path="/lodgeReservationCalendar/:lodgeId"><LodgeReservationCalendar/></Route>
                        <Route path="/reservationHistory"><LodgeReservationHistory/></Route>
                        <Route path="/lodgeOwnerCalendar"><LodgeOwnerCalendar/></Route>
                        <Route path="/lodgeReports"><LodgeReports/></Route>
                        <Route path="/category"><UserCategory/> </Route>
                    </Switch>

                }

                {isLogged && (role==="ROLE_ADMIN" || role === "ROLE_DEFADMIN") && 
                    <Switch>
                        <Route exact path="/"><AdminHomepage/></Route>
                        <Route path="/profile"><UserProfilPage/></Route>
                        <Route path="/changePassword/:id"><ChangePassword/></Route>
                        <Route path="/accountRequests"><AccountRequest/></Route>
                        <Route path="/deleteRequests"><DeleteRequest/></Route>
                        <Route path="/userList"><UserList/></Route>
                        <Route path="/userInfo/:userId"><UserInfo/></Route>
                        <Route path="/feedbacks"><Feedbacks/></Route>
                        <Route path="/reservationReport"><ReservationReport/></Route>
                    </Switch>
                }

                {isLogged && role==="ROLE_INSTRUCTOR" && 
                    <Switch>
                        <Route exact path="/"><InstructorHomepage/></Route>
                        <Route path="/profile"><UserProfilPage/></Route>
                        <Route path="/changePassword/:id"><ChangePassword/></Route>
                        <Route path="/adventureProfile/:adventureId"><AdventureProfile/></Route>
                        <Route path="/adventureImages/:adventureId"><AdventureImages/></Route>
                        <Route path="/adventureLocation/:adventureId"><AdventureLocation/></Route>
                        <Route path="/adventureRules/:adventureId"><AdventureRules/></Route>
                        <Route path="/adventurePricelist/:adventureId"><AdventurePricelist/></Route>
                        <Route path="/adventureActions/:adventureId"><AdventureActions/></Route>
                        <Route path="/instructorsCalendar"><InstructorsCalendar/></Route>
                        <Route path="/reservations"><InstructorReservations/></Route>
                        <Route path="/reservationHistory"><ReservationHistoryOwner/></Route>
                        <Route path="/adventureReports" ><AdventureReports/></Route>
                        <Route path="/category"><UserCategory/> </Route>
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
                            <Route path="/lodge-reservation/:id"><LodgeReservation/></Route>
                            <Route path="/subscriptions/:id"><Subscriptions/></Route>
                            <Route path="/ship-reservation/:id"><ShipReservation/></Route>
                            <Route path="/adventure-reservation/:id"><AdventureReservation/></Route>
                            <Route path="/lodge-actions/:id"><LodgeReservationActions/></Route>
                            <Route path="/ship-actions/:id"><ShipReservationAction/></Route>
                            <Route path="/adventure-actions/:id"><AdventureReservationActions/></Route>
                            <div className="row-search">
                                <Route path="/adventures"><Adventures/></Route>
                                <Route path="/lodges"><Lodges/></Route>
                                <Route path="/ships"><Ships/></Route>
                            </div>
                            <Route path="/category"><UserCategory/> </Route>
                        </Switch>
                    </div>
                }

                {isLogged && role === "ROLE_SHIPOWNER" &&
                    <Switch>
                        <Route exact path="/"><ShipOwnerHomePage/></Route>
                        <Route path="/profile"><UserProfilPage/></Route>
                        <Route path="/changePassword/:id"><ChangePassword/></Route>
                        <Route path="/ship/:shipId"><ShipProfile/></Route>
                        <Route path="/shipImages/:shipId"><ShipImages/></Route>
                        <Route path="/shipLocation/:shipId"><ShipLocation/></Route>
                        <Route path="/shipRules/:shipId"><ShipRules/></Route>
                        <Route path="/shipPricelist/:shipId"><ShipPriceList/></Route>
                        <Route path="/shipNavEq/:shipId"><ShipNavigationEquipment/></Route>
                        <Route path="/shipFishEq/:shipId"><ShipFishingEquipment/></Route>
                        <Route path="/shipReservationCalendar/:shipId"><ShipReservationCalendar/></Route>
                        <Route path="/shipActions/:shipId"><ShipActions/></Route>
                        <Route path="/shipOwnerReservationCalendar"><ShipOwnerReservationCalendar/></Route>
                        <Route path="/shipOwnerReservations"><ShipOwnerReservations/></Route>
                        <Route path="/shipReservations"><ShipReservations/></Route>
                        <Route path="/reservationHistory"><ShipReservationHistory/></Route>
                        <Route path="/shipReports"><ShipReports/></Route>
                        <Route path="/category"><UserCategory/> </Route>
                    </Switch>
                }
                
	        </div>
        </div>
        </Router>
    )
}

export default FrontPage;

