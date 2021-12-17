import React, { useState } from 'react'
import { Link, BrowserRouter as Router, Route, Switch } from "react-router-dom";
import '../../css/usersProfile.css';
import Modal from 'react-modal'
import { useEffect } from 'react';
import axios from 'axios';

const RequestResponseForm = ({modalIsOpen, setModalIsOpen, request}) => {
    const [response, setResponse] = useState();
    const responseDTO = {
        requestId : request.id,
        userUsername : request.userId,
        response
    }
    const SERVER_URL = process.env.REACT_APP_API;

    const acceptRequest = (request) => {
        if (responseDTO.response === "") return;
        const headers = {'Content-Type' : 'application/json',
                         'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.put(SERVER_URL + "/deleteRequests/approve", responseDTO, { headers: headers})
        .then(
            window.location.reload()
        );

        setResponse("");
    }

    const rejectRequest = (request) => {
        if (responseDTO.response === "") return;
        const headers = {'Content-Type' : 'application/json',
                         'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.put(SERVER_URL + "/deleteRequests/reject", responseDTO, { headers: headers})
        .then(
            window.location.reload()
        );

        setResponse("");
    }

    return(
        <div>
            <Modal className="fullscreen" isOpen={modalIsOpen}
            shouldCloseOnEsc={true}
            onRequestClose={() => setModalIsOpen(false)}
            ariaHideApp={false}>
                <div id="addLodge" className="adding-wrapper">
                    <div className="right">
                        <div className="info">
                            <h3>REQUEST RESPONSE</h3>
                            <div className="info_data">
                                <div className="data">
                                    <h4>Reason:</h4>
                                    <textarea value={response} onChange={(e) => setResponse(e.target.value)} required></textarea>
                                </div>
                                <Link to="/deleteRequests" onClick={() => setModalIsOpen(false)} >
                                <button onClick={() => acceptRequest(request)} className="accept-request">Accept</button>
                                </Link>
                                <Link to="/deleteRequests" onClick={() => setModalIsOpen(false)} >
                                <button onClick={() => rejectRequest(request)} className="reject-request">Reject</button>
                                </Link>
                            </div>
                        </div> 
                    </div>
                </div>
            </Modal>
        </div>
    )

}
export default RequestResponseForm;