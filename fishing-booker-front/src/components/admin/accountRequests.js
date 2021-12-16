import { useEffect, useState } from 'react';
import '../../css/usersProfile.css';
import deleteImg from '../../images/trash.png';
import editImg from '../../images/pencil.png'
import addImg from '../../images/plus.png'
import axios from 'axios';
const AccountRequest = () => {
    const SERVER_URL = process.env.REACT_APP_API;
    const [requests, setRequests] = useState([]);
    const headers = {'Content-Type' : 'application/json',
                     'Authorization' : `Bearer ${localStorage.jwtToken}`}
    useEffect(() => {
        axios.get(SERVER_URL + "/requests/getAll", { headers: headers})
        .then(response => {
            setRequests(response.data);
        });
    }, [SERVER_URL])

    const acceptRequest = (request) => {
        const headers = {'Content-Type' : 'application/json',
                     'Authorization' : `Bearer ${localStorage.jwtToken}`}
        axios.put(SERVER_URL + "/requests/approve", request, { headers: headers})
        .then(
            window.location.reload()
        );
    }

    const rejectRequest = (request) => {
        const headers = {'Content-Type' : 'application/json',
                     'Authorization' : `Bearer ${localStorage.jwtToken}`}
        axios.put(SERVER_URL + "/requests/reject",request, { headers: headers})
        .then(
            window.location.reload()
        );
    }

    return(
        <div className="container-home">

                <ol>
                {requests.map((request) => (
                    <li className="withBorder" key={request.id}>
                        <span className="list">{request.id}.</span> <p>username: {request.userId}</p> <p>{request.registrationReason}</p>
                        <button onClick={() => acceptRequest(request)} className="accept-request">Accept</button>
                        <button onClick={() => rejectRequest(request)} className="reject-request">Reject</button>
                    </li>
                ))}
                </ol>

        </div>
    );
}
export default AccountRequest;

/*{requests.map((request) => (
    <div className="title">{request.registrationReason}</div>
))}*/