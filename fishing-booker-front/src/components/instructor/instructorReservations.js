import React from 'react'
import { useState, useEffect } from 'react';
import '../../css/usersProfile.css'
import ClientProfile from '../clientProfile';
import axios from 'axios';
import { format } from 'date-fns';
import { useToasts } from "react-toast-notifications";
import AddAventureReservation from './addAdventureReservation';

const InstructorReservations = () => {
    const SERVER_URL = process.env.REACT_APP_API; 

    const [addReservationForm, setAddReservationForm] = useState(false);
    const [clientModal, setClientModal] = useState(false);
    const [addReportForm, setAddReportForm] = useState(false);

    const [reservationId, setReservationId] = useState("");

    const [user, setUser] = useState([]);

    const [reservations, setReservations] = useState([]);

    const [client, setClient] = useState("");

    const { addToast } = useToasts();

    useEffect(() => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
            .then(response => {
                setUser(response.data);
                var user = response.data;
                axios.get(SERVER_URL + '/reservations/getFutureOwnerEntitiesReservations/' + user.id, {headers: headers})
                .then(response => {
                    console.log(response.data)
                    var reservations = response.data;
                    for(let r of reservations){
                        r.startDate = format(r.startDate, 'dd.MM.yyyy. kk:mm');
                        r.endDate = format(r.endDate, 'dd.MM.yyyy. kk:mm');
                    }
                    setReservations(reservations);

            })
            })
        
    }, [])

    const showClientForm = (username) => {
        setClient(username);
        setClientModal(true);
    }

    const addReservation= () => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`};

        axios.get(SERVER_URL + "/reservations/checkActiveReservations/" + user.id, {headers: headers})
        .then(response => {
            var currentReservations = response.data;
            console.log(currentReservations);
            if(currentReservations == true){
                setAddReservationForm(true);
            } else {
                addToast("There isn't any active reservation.", { appearance: "error" });
                setAddReservationForm(true);
            }
        })
    }

    const addReport = (id) => {
        setReservationId(id);
        setAddReportForm(true);
    }

    const allReservations = reservations.length ? (
        reservations.map(reservation => {
            return(
                <li className="table-row" key={reservation.reservationId}>
                    <div className="col col-1-action" >{reservation.entityName}</div>
                    <div className="col col-2-action" >{reservation.startDate}</div>
                    <div className="col col-3-action" >{reservation.endDate}</div>
                    <div className="col col-4-action" onClick={() => showClientForm(reservation.clientUsername)}>{reservation.clientUsername}</div>
                </li>
            )
        })
    ) : (
        <div>Your lodge still does not have reservations....</div>
    );

    return (
        <div className="wrapper">
            <div className="reservations-right">
                <div className="info">
                    <h3>RESERVATIONS</h3>
                    <button className="new-reservation-btn" onClick={() => addReservation()}>
                        Create new reservation
                    </button><br/><br/>
                    <div className="container-table-reservations">
                        <ul className="responsive-table">
                            <li className="table-header">
                            <div className="col col-1-action">Adventure</div>
                            <div className="col col-2-action">Reservation start</div>
                            <div className="col col-3-action">Reservation end</div>
                            <div className="col col-4-action">Client</div>
                            </li>
                            {allReservations}
                        </ul>
                    </div>
                </div>
            </div>
            <ClientProfile modalIsOpen={clientModal} setModalIsOpen={setClientModal} clientUsername={client} />
            <AddAventureReservation modalIsOpen={addReservationForm} setModalIsOpen={setAddReservationForm}/>
        </div>
    )

}
export default InstructorReservations;