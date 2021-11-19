import { Link } from "react-router-dom";
import { useState } from "react";
import React from 'react'

const Navbar = () => {

    const [visible, setVisible] = useState(false);

    return (
            <div className="navbar">
                <nav>
                    <ul>
                        <li><Link to="/"> HOME</Link></li>
                        {!visible ? <li><Link to="/register">REGISTER </Link></li> : null}
                        <li><Link to="/profile"> MY PROFILE</Link></li>
                        <li><Link to="/login">LOG IN</Link></li>
                    </ul>
                </nav>
            </div>
    )
}

export default Navbar;