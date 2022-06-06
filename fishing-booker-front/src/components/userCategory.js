import axios from "axios";
import { useEffect, useState } from "react";
import { useToasts } from "react-toast-notifications";
import { Link, useHistory } from "react-router-dom";

const UserCategory = () => {

    const SERVER_URL = process.env.REACT_APP_API;
    const { addToast } = useToasts();

    const [user, setUser] = useState([]);
    const [role, setRole] = useState("");

    useEffect(() => {
        const headers = {'Content-Type': 'application/json',
                         'Authorization': `Bearer ${localStorage.jwtToken}`}
        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
            .then(response => {
                console.log("OKISSSSS")
                setUser(response.data);
                var user = response.data
                axios.get(SERVER_URL + `/users/getRole/${user.id}`, {headers:headers})
                .then(response => {
                    setRole(response.data);
                });
            })

    }, [])

    return (
        <div className="wrapper">
            <div className="left">
                <h4>{user.name} {user.surname}</h4>
                <p>{user.role}</p><br/>
                <Link to={`/changePassword/${user.id}`}>Change password</Link> <br/><br/>
                {(role !== "ROLE_ADMIN" || role !== "ROLE_DEFADMIN") && <Link to={`/deleteAccount/${user.id}`}>Delete your account</Link>}
            </div>
            <div className="right">
                <div className="info">
                    <h3>Category</h3>
                    <div className="info_data">
                    OKI
                    </div>
                </div>
            </div>
        </div>
    );

}

export default UserCategory;