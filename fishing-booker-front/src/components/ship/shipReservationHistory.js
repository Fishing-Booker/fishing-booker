import React from 'react'
import { useState, useEffect } from 'react';
import { Link, useParams} from "react-router-dom";
import '../../css/usersProfile.css'
import axios from 'axios';
import { format } from 'date-fns';
import { useToasts } from "react-toast-notifications";
import commentImg from '../../images/comment.png';
import ClientProfile from '../clientProfile';
import AddShipReport from './addShipReport';

const ShipReservationHistory = () => {
    const SERVER_URL = process.env.REACT_APP_API; 
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
                axios.get(SERVER_URL + '/reservations/getPastOwnerEntitiesReservations/' + user.id, {headers: headers})
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


    const addReport = (id) => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + "/reports/checkHasReservationReport/" + id, {headers: headers})
        .then(response => {
            if(response.data == true){
                addToast("You already made report for this reservation.", { appearance: "error" });
            } else {
                setReservationId(id);
                setAddReportForm(true);
            }
        })

    }

    const allReservations = reservations.length ? (
        reservations.map(reservation => {
            return(
                <li className="table-row" key={reservation.reservationId}>
                    <div className="col col-1-action" >{reservation.entityName}</div>
                    <div className="col col-2-action" >{reservation.startDate}</div>
                    <div className="col col-3-action" >{reservation.endDate}</div>
                    <div className="col col-4-action" onClick={() => showClientForm(reservation.clientUsername)}>{reservation.clientUsername}</div>
                    <div className="col col-5-action" >
                        <img className='info-img' src={commentImg} onClick={() => addReport(reservation.reservationId)}/>
                    </div>
                </li>
            )
        })
    ) : (
        <div>There is no past reservations...</div>
    );

    return (
        <div className="wrapper">
            <div className="reservations-right">
                <div className="info">
                    <h3>RESERVATION HISTORY</h3>
                    <div className="container-table-reservations">
                        <ul className="responsive-table">
                            <li className="table-header">
                            <div className="col col-1-action">Ship</div>
                            <div className="col col-2-action">Reservation start</div>
                            <div className="col col-3-action">Reservation end</div>
                            <div className="col col-4-action">Client</div>
                            <div className="col col-5-action">Report</div>
                            </li>
                            {allReservations}
                        </ul>
                    </div>
                </div>
            </div>

            <AddShipReport modalIsOpen={addReportForm} setModalIsOpen={setAddReportForm} reservationId={reservationId}/>
            <ClientProfile modalIsOpen={clientModal} setModalIsOpen={setClientModal} clientUsername={client} />
        </div>
    )
}
export default ShipReservationHistory;