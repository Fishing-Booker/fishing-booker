import React from 'react'
import { Link } from "react-router-dom";
import { withRouter } from 'react-router-dom';
import '../css/addingForm.css'
import Modal from 'react-modal';
import { useState, useEffect } from 'react';
import axios from 'axios';

const ClientProfile = ({modalIsOpen, setModalIsOpen, clientUsername}) => {

    const SERVER_URL = process.env.REACT_APP_API; 

    const [client, setClient] = useState([]);

    useEffect(() => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + "/users/user/" + clientUsername, { headers: headers })
            .then(response => {
                setClient(response.data);
            })
    }, [clientUsername])

   return (
       <div>
            <Modal className="fullscreen" isOpen={modalIsOpen}
            shouldCloseOnEsc={true}
            onRequestClose={() => setModalIsOpen(false)} >
            <div id="clientProfile" className="adding-wrapper">
                <div className="right">
                    <div className="info">
                        <h3>CLIENT NAME</h3>
                        <div className="info_data">
                            <div className="client-data">
                                <h4>Name:</h4>
                                {client.name} {client.surname}
                            </div>
                            <div className="client-data">
                                <h4>Username:</h4>
                                {clientUsername}
                            </div>
                            <div className="client-data">
                                <h4>Email:</h4>
                                {client.email}
                            </div>
                            <div className="client-data">
                                <h4>Phone number:</h4>
                                {client.phoneNumber}
                            </div>
                            <div className="client-data">
                                <h4>Address:</h4>
                                {client.address}, {client.city}, {client.country}
                            </div>
                            <Link to="/lodgeReservations" onClick={() => setModalIsOpen(false)}>
                                <button className="client-btn" >
                                    Done
                                </button>
                            </Link>
                        </div> <br/> <br/>
                    </div>
                </div>
            </div>
            </Modal>
        </div>
   )
    
}

export default ClientProfile;