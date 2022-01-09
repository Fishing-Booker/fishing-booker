import React, { useEffect, useState } from 'react'
import { Link, BrowserRouter as Router, Route, Switch } from "react-router-dom";
import { withRouter } from 'react-router-dom';
import '../../css/addingForm.css'
import Modal from 'react-modal';
import axios from 'axios';

const AddLodgeReservationByOwner = ({modalIsOpen, setModalIsOpen, entityId}) => {

    const SERVER_URL = process.env.REACT_APP_API; 

    const [startDate, setStartDate] = useState("");
    const [endDate, setEndDate] = useState("");
    const [clientId, setClientId] = useState("");

    const [user, setUser] = useState("");

    useEffect(() => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
            .then(response => {
                setUser(response.data);
            })
    }, [])

    const newReservation = {
        owner: user.id,
        startDate,
        endDate,
        clientId,
        entityId
    }


    const addReservation= () => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        console.log(entityId);
        axios.post(SERVER_URL + "/reservations/addReservation", newReservation, {headers: headers})
          .then(response => {
            setModalIsOpen(false)
            window.location.reload();
        });
    }

   return (
       <div>
            <Modal className="fullscreen" isOpen={modalIsOpen}
            shouldCloseOnEsc={true}
            onRequestClose={() => setModalIsOpen(false)} >
                <div id="addLodgeReservation" className="adding-wrapper">
                    <div className="right">
                        <div className="info">
                            <h3>ADD NEW RESERVATION</h3>
                            <div className="info_data">
                                <div className="data">
                                    <h4>Lodge:</h4>
                                    <select>
                                        <option>Lodge1</option>
                                        <option>Lodge2</option>
                                    </select>
                                </div>
                                <div className="data">
                                    <h4>Reservation start:</h4>
                                    <input type="datetime-local" required onChange={(e) => {setStartDate(e.target.value)}}  value={startDate}/>
                                </div>
                                <div className="data">
                                    <h4>Reservation end:</h4>
                                    <input type="datetime-local" required onChange={(e) => {setEndDate(e.target.value)}}  value={endDate}/>
                                </div>
                                <div className="data">
                                    <h4>Number of persons:</h4>
                                    <input type="number" min="1" step="1" value="10" />
                                </div>
                                <div className="data">
                                    <h4>Additional services:</h4>
                                    <input type="text" />
                                </div>
                                <div className="data">
                                    <h4>Price:</h4>
                                    <input type="text"/>
                                </div>
                                <button onClick={() => addReservation()}>
                                    Add
                                </button>
                            </div> <br/> <br/>
                        </div>
                    </div>
                </div>
            </Modal>
        </div>
   )
    
}

export default AddLodgeReservationByOwner;