import { useEffect, useState } from 'react';
import '../../css/usersProfile.css';
import axios from 'axios';
import { Link } from 'react-router-dom';

const UserList = () => {

    const SERVER_URL = process.env.REACT_APP_API;
    const [users, setUsers] = useState([]);
    const [userRoles, setUserRoles] = useState([]);
    useEffect(() => {
        const headers = {'Content-Type' : 'application/json',
                             'Authorization' : `Bearer ${localStorage.jwtToken}`}
        axios.get(SERVER_URL + "/users/getUserList", { headers: headers})
        .then(response => {
            setUsers(response.data);
            console.log(response.data);
        });
    }, [SERVER_URL])


    return(
        <div className="container-home">
            <div className="title">
                USER LIST
            </div>
            <ol>
            {users.map((user) => (
                <li className="withBorder" key={user.id}>
                    <span className="list">{user.serialNumber}.</span> 
                    <p><b>name: </b> {user.name}</p>
                    <p><b>surname: </b> {user.surname}</p>
                    <p><b>username: </b> {user.username}</p> 
                    <p><b>email:</b> {user.email}</p>
                    {user.role==="ROLE_DEFADMIN" && <p><b>ADMIN</b> </p>}
                    {user.role==="ROLE_ADMIN" && <p><b>ADMIN</b> </p>}
                    {user.role==="ROLE_CLIENT" && <p><b>CLIENT</b></p>}
                    {user.role==="ROLE_INSTRUCTOR" && <p><b>INSTRUCTOR</b></p>}
                    {user.role==="ROLE_SHIPOWNER" && <p><b>SHIP OWNER</b></p>}
                    {user.role==="ROLE_LODGEOWNER" && <p><b>LODGE OWNER</b></p>}
                    <Link to={`/userInfo/${user.id}`}>
                        <button className="user-details">Info</button>
                    </Link>
                </li>
            ))}
            </ol>

        </div>
    );

}
export default UserList;