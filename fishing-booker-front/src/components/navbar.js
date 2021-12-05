import { Link } from "react-router-dom";
import { useState } from "react";
import React from 'react'

const Navbar = () => {

    const [isLogged, setIsLogged] = useState(false);
    const [userRole, setUserRole] = useState("instructor");


    return (
            <div className="navbar">
                <nav>
                    <ul>
                        <li><Link to="/"> HOME</Link></li>
                        {isLogged ? (<li><Link to="/profile">MY PROFILE</Link></li>) : null}
                        {!isLogged ? (<li><Link to="/register">REGISTER </Link></li>) : null}
                        {!isLogged ? (<li><Link to="/login">LOG IN</Link></li>) : null}
                        {isLogged ? (<li><Link to="/logout">LOG OUT</Link></li>) : null}
                        {userRole=="instructor" && <li><Link to="/homepage">HOMEPAGE</Link></li>}
                        {userRole=="instructor" && <li><Link to="/homepage">ADVENTURES</Link></li>}
                        {userRole=="instructor" && <li><Link to="/reservations">RESERVATIONS</Link></li>}
                        {userRole=="instructor" && <li><Link to="/reservations">MY CALENDAR</Link></li>}
                    </ul>
                </nav>
            </div>
    )
}

export default Navbar;