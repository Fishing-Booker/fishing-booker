import React, { Component } from 'react'
import { useState, useEffect } from 'react';
import { Link, useParams} from "react-router-dom";
import { withRouter } from 'react-router-dom';
import '../../css/usersProfile.css'
import axios from 'axios';
import { format } from 'date-fns';
import infoImg from '../../images/info.png';

const ShipOwnerReservations = () => {

    const SERVER_URL = process.env.REACT_APP_API; 

    const [reservations, setReservations] = useState([]);

    const [user, setUser] = useState([]);

    useEffect(() => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
            .then(response => {
                setUser(response.data);

                axios.get(SERVER_URL + "/actions/entityActions/", {headers: headers})
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
                <li class="table-row" key={reservation.id}>
                    <div class="col col-1-action" >{reservation.startDate}</div>
                    <div class="col col-2-action" >{reservation.endDate}</div>
                    <div class="col col-3-action" >{reservation.price}</div>
                    <div class="col col-4-action" >{reservation.client}</div>
                    <div class="col col-5-action" >
                        <img className='info-img' src={infoImg} />
                    </div>
                </li>
            )
        })
    ) : (
        <div></div>
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
                    <h3>OWNER RESERVATIONS</h3><br/>
                    <div class="container-table">
                        <ul class="responsive-table">
                            <li class="table-header">
                            <div class="col col-1-action">Start date</div>
                            <div class="col col-2-action">End date</div>
                            <div class="col col-3-action">Price</div>
                            <div class="col col-4-action">Client</div>
                            <div class="col col-5-action">Info</div>
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