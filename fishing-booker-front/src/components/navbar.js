import { Link } from "react-router-dom";
import { useState } from "react";
import React from 'react'

const Navbar = () => {

    const [visible, setVisible] = useState(true);
    const [userRole, setUserRole] = useState("instructor");

    return (
            <div className="navbar">
                <nav>
                    <ul>
                        {!visible && <li><Link to="/"> HOME</Link></li>}
                        {!visible ? <li><Link to="/register">REGISTER </Link></li> : null}
                        {!visible && <li><Link to="/login">LOG IN</Link></li>}
                        {userRole=="instructor" && <li><Link to="/homepage">HOMEPAGE</Link></li>}
                        {visible && <li><Link to="/myProfile">PROFILE</Link></li>}
                        {userRole=="instructor" && <li><Link to="/homepage">ADVENTURES</Link></li>}
                        {userRole=="instructor" && <li><Link to="/reservations">RESERVATIONS</Link></li>}
                        {userRole=="instructor" && <li><Link to="/reservations">MY CALENDAR</Link></li>}
                        {visible && <li><Link to="/">LOG OUT</Link></li>}
                    </ul>
                </nav>
            </div>
    )
}

export default Navbar;