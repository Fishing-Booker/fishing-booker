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
import LodgeReservationInfo from './lodgeReservationInfo';

const LodgeReservations = () => {

    const SERVER_URL = process.env.REACT_APP_API; 

    const [addReservationForm, setAddReservationForm] = useState(false);
    const [clientModal, setClientModal] = useState(false);
    const [addReportForm, setAddReportForm] = useState(false);

    const [reservationId, setReservationId] = useState("");

    const [user, setUser] = useState([]);

    const [reservations, setReservations] = useState([]);

    const [client, setClient] = useState("");

    const [showInfo, setShowInfo] = useState(false);
    const [reservation, setReservation] = useState([]);

    const [url, setUrl] = useState("");
    const [clientName, setClientName] = useState("");

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

    useEffect(() => {

        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        if(clientName == "free"){
            axios.get(SERVER_URL + '/reservations/searchClients?username=' + user.username + "&owner=" + user.id, {headers: headers})
                .then(response => {
                    console.log(response.data)
                    var reservations = response.data;
                    for(let r of reservations){
                        r.startDate = format(r.startDate, 'dd.MM.yyyy. kk:mm');
                        r.endDate = format(r.endDate, 'dd.MM.yyyy. kk:mm');
                    }
                    setReservations(reservations);

                })
        } else if(clientName == user.username){
            setReservations([]);
        }else {
            axios.get(url, {headers: headers})
                .then(response => {
                    console.log(response.data)
                    var reservations = response.data;
                    for(let r of reservations){
                        r.startDate = format(r.startDate, 'dd.MM.yyyy. kk:mm');
                        r.endDate = format(r.endDate, 'dd.MM.yyyy. kk:mm');
                    }
                    setReservations(reservations);

                })
        }

    }, [url])

    const showResInfo = (reservation) => {
        setReservation(reservation);
        setShowInfo(true);
    }

    const showClientForm = (username) => {
        if(username == "free"){
            addToast("This action is still free.", { appearance: "error" });
        } else {
            setClient(username);
            setClientModal(true);
        }
        
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
                <li class="table-row" key={reservation.reservationId} >
                    <div class="col col-1-reservation" onClick={() => showResInfo(reservation)}>{reservation.entityName}</div>
                    <div class="col col-2-reservation" >{reservation.startDate}</div>
                    <div class="col col-3-reservation" >{reservation.endDate}</div>
                    <div class="col col-4-reservation" onClick={() => showClientForm(reservation.clientUsername)}>{reservation.clientUsername}</div>
                    <div class="col col-5-reservation" >{reservation.reservationType}</div>
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
                    <h3>FUTURE RESERVATIONS</h3>
                    
                    <input className="search-box" type="search" placeholder="Enter client username " value={clientName} 
                        onChange={(e) => {
                            setClientName(e.target.value)
                            setUrl(SERVER_URL + '/reservations/searchClients?username=' + clientName + "&owner=" + user.id);
                            if(e.target.value === ''){
                                setUrl(SERVER_URL + '/reservations/getFutureOwnerEntitiesReservations/' + user.id);
                            }
                        }}
                    />
                    <button className="new-reservation-btn" onClick={() => addReservation()} style={{'margin-left': '50%'}}>
                        Create new reservation
                    </button>
                    <br/><br/>
                    <div class="container-table-reservations">
                        <ul class="responsive-table">
                            <li class="table-header">
                            <div class="col col-1-reservation">Lodge</div>
                            <div class="col col-2-reservation">Reservation start</div>
                            <div class="col col-3-reservation">Reservation end</div>
                            <div class="col col-4-reservation">Client</div>
                            <div class="col col-5-reservation">Type</div>
                            </li>
                            {allReservations}
                        </ul>
                    </div>
                </div>
            </div>

            <AddLodgeReservationByOwner modalIsOpen={addReservationForm} setModalIsOpen={setAddReservationForm} />
            <ClientProfile modalIsOpen={clientModal} setModalIsOpen={setClientModal} clientUsername={client} />
            <LodgeReservationInfo modalIsOpen={showInfo} setModalIsOpen={setShowInfo} reservation={reservation} />
        </div>
    )
    
}

export default LodgeReservations;