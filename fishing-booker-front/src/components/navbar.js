import { Link, useHistory } from "react-router-dom";
import { useState, useEffect } from "react";
import React from 'react'
import axios from "axios";

const Navbar = () => {

    const [isLogged, setIsLogged] = useState(false);
    const [userRole, setUserRole] = useState("");
    const [user, setUser] = useState();
    const history = useHistory();
    const SERVER_URL = process.env.REACT_APP_API;

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
                var pomUser = response.data;
                setUser(pomUser);
                axios.get(SERVER_URL + `/users/getRole/${pomUser.id}`, {headers:headers})
                .then(response => {
                    setUserRole(response.data);
                });
            });
        }
    }, [isLogged])

    const logOut = e => {
        localStorage.removeItem('jwtToken');
        history.push('/')
        window.location.reload()
    }

    return (
            <div className="navbar">
                <nav>
                    <ul>
                        <li><Link to="/"> HOME</Link></li>
                        {isLogged ? (<li><Link to="/profile">MY PROFILE</Link></li>) : null}
                        {!isLogged ? (<li><Link to="/register">REGISTER </Link></li>) : null}
                        {!isLogged ? (<li><Link to="/login">LOG IN</Link></li>) : null}
                        {userRole==="ROLE_INSTRUCTOR" && <li><Link to={`/reservations/${user.id}`}>RESERVATIONS</Link></li>}
                        {userRole==="ROLE_INSTRUCTOR" && <li><Link to={`/instructorsCalendar`}>MY CALENDAR</Link></li>}
                        {userRole==="lodgeOwner" && <li><Link to="/lodgeReservations">RESERVATION HISTORY</Link></li>}
                        {(userRole==="ROLE_ADMIN" || userRole==="ROLE_DEFADMIN") && <li><Link to="/accountRequests">ACCOUNT REQUESTS</Link></li>}
                        {(userRole==="ROLE_ADMIN" || userRole==="ROLE_DEFADMIN") && <li><Link to="/deleteRequests">DELETE REQUESTS</Link></li>}
                        {userRole === "ROLE_CLIENT" && <li><Link to="/client-reservations">RESERVATIONS</Link></li>}
                        {userRole === "ROLE_CLIENT" && <li><Link to="/reservation-history">RESERVATION HISTORY</Link></li>}
                        {isLogged ? (<li><Link to="/logout" onClick={logOut}>LOG OUT</Link></li>) : null}
                    </ul>
                </nav>
            </div>
    )
}

export default Navbar;