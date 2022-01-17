import React, { Component } from 'react'
import { useState, useEffect } from 'react';
import { Link, useParams} from "react-router-dom";
import { withRouter } from 'react-router-dom';
import '../../css/usersProfile.css'
import axios from 'axios';
import { format } from 'date-fns';
import infoImg from '../../images/info.png';
import commentImg from '../../images/comment.png';

const ShipOwnerReservations = () => {

    const SERVER_URL = process.env.REACT_APP_API; 

    const [reservations, setReservations] = useState([]);

    const [user, setUser] = useState([]);

    useEffect(() => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
            .then(response => {
                setUser(response.data);
                var user = response.data;

                axios.get(SERVER_URL + "/reservations/shipOwnerReservations/" + user.id, {headers: headers})
                    .then(response => {
                        var actions = response.data;
                        for(let action of actions){
                            action.startDate = format(action.startDate, 'dd.MM.yyyy. kk:mm');
                            action.endDate = format(action.endDate, 'dd.MM.yyyy. kk:mm');
                        }
                        setReservations(actions);
                    });
            })

        
    }, [])

    const allReservations = reservations.length ? (
        reservations.map(reservation => {
            return(
                <li class="table-row" key={reservation.reservationId}>
                    <div class="col col-1-action" >{reservation.entityName}</div>
                    <div class="col col-2-action" >{reservation.startDate}</div>
                    <div class="col col-3-action" >{reservation.endDate}</div>
                    <div class="col col-4-action" >{reservation.clientUsername}</div>
                </li>
            )
        })
    ) : (
        <div>Your lodge still does not have reservations....</div>
    );
        
    return (
        <div className="wrapper">
            <div className="left">
                <h4>OWNER RESERVATIONS</h4><br/>
                <Link className="sidebar-link" to={"/shipOwnerReservationCalendar"}>Calendar</Link><br/><br/>
                <Link className="sidebar-link" to={"/shipOwnerReservations"}>Reservation history</Link><br/><br/>
            </div>
            <div className="right">
            <div className="info">
                    <h3>MY RESERVATIONS</h3>
                    <div class="container-table-reservations"><br/>
                        <ul class="responsive-table">
                            <li class="table-header">
                            <div class="col col-1-action">Ship</div>
                            <div class="col col-2-action">Reservation start</div>
                            <div class="col col-3-action">Reservation end</div>
                            <div class="col col-4-action">Client</div>
                            </li>
                            {allReservations}
                        </ul>
                    </div>
                </div>
            </div>

        </div>
    )

}

export default ShipOwnerReservations;