import { useEffect, useState } from 'react';
import '../../css/usersProfile.css';
import axios from 'axios';

const UserList = () => {

    const SERVER_URL = process.env.REACT_APP_API;
    const [users, setUsers] = useState([]);
    useEffect(() => {
        const headers = {'Content-Type' : 'application/json',
                             'Authorization' : `Bearer ${localStorage.jwtToken}`}
        axios.get(SERVER_URL + "/users/all", { headers: headers})
        .then(response => {
            setUsers(response.data);
            console.log(response.data);
        });
    }, [SERVER_URL])


    return(
        <div className="container-home">
            <div className="title">
                ADMIN LIST
            </div>
                <ol>
                {users.map((admin) => (
                    <li className="withBorder" key={admin.id}>
                        <span className="list">{admin.id}.</span> <p>username: {admin.username}</p> <p>{admin.role}</p>
                        <button className="accept-request">Accept</button>
                        <button className="reject-request">Reject</button>
                    </li>
                ))}
                </ol>

        </div>
    );

}
export default UserList;