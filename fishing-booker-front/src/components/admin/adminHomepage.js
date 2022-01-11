import { useEffect, useState } from 'react';
import '../../css/usersProfile.css';
import axios from 'axios';
import { Link } from 'react-router-dom';
import AdminFirstLogin from './adminFirstLogin';

const AdminHomepage = () => {
    const SERVER_URL = process.env.REACT_APP_API;
    const [role, setRole] = useState("");
    const [firstLogin, setFirstLogin] = useState(false);
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
            <AdminFirstLogin modalIsOpen={firstLogin} setModalIsOpen={setFirstLogin}/>
        </div>
    )
}
export default AdminHomepage;