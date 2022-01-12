import React from 'react'
import { useState, useEffect } from 'react';
import { Link, useParams} from "react-router-dom";
import '../../css/usersProfile.css'
import AddLodgeReservationByOwner from './addLodgeReservationByOwner';
import ClientProfile from '../clientProfile';
import axios from 'axios';
import { format } from 'date-fns';
import { useToasts } from "react-toast-notifications";
import commentImg from '../../images/comment.png';
import AddLodgeReport from './addLodgeReport';

const LodgeReservations = () => {

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

    const addReport = (id) => {
        setReservationId(id);
        setAddReportForm(true);
    }

    const allReservations = reservations.length ? (
        reservations.map(reservation => {
            return(
                <li class="table-row" key={reservation.reservationId}>
                    <div class="col col-1-action" >{reservation.entityName}</div>
                    <div class="col col-2-action" >{reservation.startDate}</div>
                    <div class="col col-3-action" >{reservation.endDate}</div>
                    <div class="col col-4-action" onClick={() => showClientForm(reservation.clientUsername)}>{reservation.clientUsername}</div>
                    <div class="col col-5-action" >
                        <img className='info-img' src={commentImg} onClick={() => addReport(reservation.reservationId)}/>
                    </div>
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
                            <div class="col col-1-action">Lodge</div>
                            <div class="col col-2-action">Reservation start</div>
                            <div class="col col-3-action">Reservation end</div>
                            <div class="col col-4-action">Client</div>
                            <div class="col col-5-action">Report</div>
                            </li>
                            {allReservations}
                        </ul>
                    </div>
                </div>
            </div>

            <AddLodgeReport modalIsOpen={addReportForm} setModalIsOpen={setAddReportForm} reservationId={reservationId}/>
            <AddLodgeReservationByOwner modalIsOpen={addReservationForm} setModalIsOpen={setAddReservationForm} />
            <ClientProfile modalIsOpen={clientModal} setModalIsOpen={setClientModal} clientUsername={client} />
        </div>
    )
    
}

export default LodgeReservations;