import React from 'react'
import { useState, useEffect } from 'react';
import { Link, useParams} from "react-router-dom";
import '../../css/usersProfile.css'
import AddLodgeReservationByOwner from './addLodgeReservationByOwner';
import ClientProfile from '../clientProfile';
import axios from 'axios';
import { format } from 'date-fns';
import { useToasts } from "react-toast-notifications";

const LodgeReservations = () => {

    const SERVER_URL = process.env.REACT_APP_API; 

    const [addReservationForm, setAddReservationForm] = useState(false);
    const [clientModal, setClientModal] = useState(false);

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
                axios.get(SERVER_URL + '/reservations/getOwnerEntitiesReservations/' + user.id, {headers: headers})
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
            }
        })
    }

    const allReservations = reservations.length ? (
        reservations.map(reservation => {
            return(
                <li class="table-row" >
                    <div class="col col-1" >{reservation.entityName}</div>
                    <div class="col col-2" >{reservation.startDate}</div>
                    <div class="col col-3" >{reservation.endDate}</div>
                    <div class="col col-4" onClick={() => showClientForm(reservation.clientUsername)}>{reservation.clientUsername}</div>
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
                    <div class="container-table-reservations">
                        <ul class="responsive-table">
                            <li class="table-header">
                            <div class="col col-1">Lodge</div>
                            <div class="col col-2">Reservation start</div>
                            <div class="col col-3">Reservation end</div>
                            <div class="col col-4">Client</div>
                            </li>
                            {allReservations}
                        </ul>
                    </div>
                </div>
            </div>

            <AddLodgeReservationByOwner modalIsOpen={addReservationForm} setModalIsOpen={setAddReservationForm} />
            <ClientProfile modalIsOpen={clientModal} setModalIsOpen={setClientModal} clientUsername={client} />
        </div>
    )
    
}

export default LodgeReservations;