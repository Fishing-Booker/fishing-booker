import { useEffect, useState } from 'react';
import '../../css/usersProfile.css';
import axios from 'axios';
import RequestResponseForm from './requestResponse';
const DeleteRequest = () => {
    const SERVER_URL = process.env.REACT_APP_API;
    const [requests, setRequests] = useState([]);
    const [addResponse, setAddResponse] = useState(false);
    const [modalRequest, setModalRequest] = useState("");
    const headers = {'Content-Type' : 'application/json',
                     'Authorization' : `Bearer ${localStorage.jwtToken}`}
    useEffect(() => {
        axios.get(SERVER_URL + "/deleteRequests/getAll", { headers: headers})
        .then(response => {
            setRequests(response.data);
        });
    }, [SERVER_URL])

    const respondToRequest = (request) => {
        setAddResponse(true);
        setModalRequest(request);
    }

    return(
        <div className="container-home">
            <div className="title">
                REQUESTS FOR DELETING ACCOUNT
            </div>

                <ol>
                {requests.map((request) => (
                    <li className="withBorder" key={request.id}>
                        <span className="list">{request.id}.</span> <p>username: {request.userId}</p> <p>{request.deleteReason}</p>
                        <button onClick={() => respondToRequest(request)} className="response-request">Response</button>
                    </li>
                ))}
                </ol>
                <RequestResponseForm modalIsOpen={addResponse} setModalIsOpen={setAddResponse} request={modalRequest}/>

        </div>
    );
}
export default DeleteRequest;