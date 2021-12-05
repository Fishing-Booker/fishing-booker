import { Link } from "react-router-dom";
import { useState } from "react";

const Navbar = () => {
    const [isLogged, setIsLogged] = useState(false);

    return (
            <div className="navbar">
                <nav>
                    <ul>
                        <li><Link to="/"> HOME</Link></li>
                        {isLogged ? (<li><Link to="/profile">MY PROFILE</Link></li>) : null}
                        {!isLogged ? (<li><Link to="/register">REGISTER </Link></li>) : null}
                        {!isLogged ? (<li><Link to="/login">LOG IN</Link></li>) : null}
                        {isLogged ? (<li><Link to="/logout">LOG OUT</Link></li>) : null}
                    </ul>
                </nav>
            </div>
    )
}

export default Navbar;