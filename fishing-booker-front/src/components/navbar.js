import { Link, useHistory } from "react-router-dom";
import { useState, useEffect } from "react";
import React from 'react'

const Navbar = () => {

    const [isLogged, setIsLogged] = useState(false);
    const [userRole, setUserRole] = useState("lodgeOwner");
    const history = useHistory();

    useEffect(() => {
        let token = localStorage.getItem('jwtToken');
        if (token != null) {
            setIsLogged(true)
        } else {
            setIsLogged(false)
        }
    })

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
                        {isLogged ? (<li><Link to="/myProfile">MY PROFILE</Link></li>) : null}
                        {!isLogged ? (<li><Link to="/register">REGISTER </Link></li>) : null}
                        {!isLogged ? (<li><Link to="/login">LOG IN</Link></li>) : null}
                        {userRole=="instructor" && <li><Link to="/homepage">HOMEPAGE</Link></li>}
                        {userRole=="instructor" && <li><Link to="/homepage">ADVENTURES</Link></li>}
                        {userRole=="instructor" && <li><Link to="/reservations">RESERVATIONS</Link></li>}
                        {userRole=="instructor" && <li><Link to="/reservations">MY CALENDAR</Link></li>}
                        {userRole=="lodgeOwner" && <li><Link to="/lodgeReservations">RESERVATION HISTORY</Link></li>}
                        {isLogged ? (<li><Link to="/logout" onClick={logOut}>LOG OUT</Link></li>) : null}
                    </ul>
                </nav>
            </div>
    )
}

export default Navbar;