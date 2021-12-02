import { Link } from "react-router-dom";
import { useState } from "react";
import React from 'react'

const Navbar = () => {

    const [visible, setVisible] = useState(true);

    return (
            <div className="navbar">
                <nav>
                    <ul>
                        <li><Link to="/"> HOME</Link></li>
                        {!visible ? <li><Link to="/register">REGISTER </Link></li> : null}
                        {!visible && <li><Link to="/login">LOG IN</Link></li>}
                        {visible && <li><Link to="/myProfile">MY PROFILE</Link></li>}
                        {visible && <li><Link to="/">LOG OUT</Link></li>}
                    </ul>
                </nav>
            </div>
    )
}

export default Navbar;