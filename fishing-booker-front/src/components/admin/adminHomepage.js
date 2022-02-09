import { useEffect, useState } from 'react';
import '../../css/usersProfile.css';
import axios from 'axios';
import { Link } from 'react-router-dom';
import AdminFirstLogin from './adminFirstLogin';
import AddLoyaltyProgramme from './addLoyaltyProgramme';

const AdminHomepage = () => {
    const SERVER_URL = process.env.REACT_APP_API;
    const [role, setRole] = useState("");
    const [firstLogin, setFirstLogin] = useState(false);
    const [addLoyalty, setAddLoyalty] = useState(false);
    useEffect(() => {
        const headers = {'Content-Type' : 'application/json',
                             'Authorization' : `Bearer ${localStorage.jwtToken}`}
        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers})
            .then(response => {
                var user = response.data;
                console.log(user);
                axios.get(SERVER_URL + `/users/getRole/${user.id}`, {headers:headers})
                .then(response => {
                    setRole(response.data);;
                    if(response.data === "ROLE_ADMIN" && user.firstLogin===true) {
                        setFirstLogin(true);
                    }
                });
            });
    }, [SERVER_URL])
    return(
        <div>
            <div className="container-home1">
                <div className="title">
                    LOYALTY PROGRAMME
                    <button className="add-loyalty-btn" onClick={(e) => {setAddLoyalty(true)}}>+</button>
                </div>
                <ol>
                    <li className="withBorder">
                        <p>username: </p> <p></p>
                        <button className="response-request">Response</button>
                    </li>
                </ol>
            </div>
            <AddLoyaltyProgramme modalIsOpen={addLoyalty} setModalIsOpen={setAddLoyalty}/>
            <AdminFirstLogin modalIsOpen={firstLogin} setModalIsOpen={setFirstLogin}/>
        </div>
    )
}
export default AdminHomepage;