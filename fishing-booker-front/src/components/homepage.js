import { Link } from "react-router-dom"
import { useState, useEffect } from "react";
import axios from "axios";

const Homepage = () => {
    const SERVER_URL = process.env.REACT_APP_API;
    const [isFirstDay, setIsFirstDay] = useState(false);
    const [isLogged, setIsLogged] = useState(false);
    const [userRole, setUserRole] = useState("");
    const [user, setUser] = useState();

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
                    if (response.data === 'ROLE_CLIENT') {
                        var today = new Date();
                        const day = today.getDate();
                        if (day === 3) {
                            axios.put(SERVER_URL + "/penalties", pomUser.id, { headers: headers })
                                .then(response => {console.log(response.status); setIsFirstDay(true)});
                        }
                    }
                });
            });
        }
    }, [isLogged, isFirstDay])

    return (
        <div>
            <div className="col">
                <h1> Fishing booker </h1>
                <p className="main-description"> Book your next fishing trip! </p>
                <button className="explore-btn" type="button"><Link className="explore-link" to ="/explore">Explore</Link> </button>
            </div>
        </div>
    )
}

export default Homepage;