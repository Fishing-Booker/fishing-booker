import React, { Component, useEffect } from 'react';
import { useState } from 'react';
import { Link, useParams} from "react-router-dom";
import '../../css/usersProfile.css'
import Calendar from 'react-awesome-calendar';
import axios from 'axios';
import AddOwnerReservationPeriod from './addOwnerReservationPeriod';
import { format } from 'date-fns';

const ShipOwnerReservationCalendar = () => {

    const SERVER_URL = process.env.REACT_APP_API; 

    const [user, setUser] = useState([]);
        
    const [addPeriod, setAddPeriod] = useState(false);
    const [addReservation, setAddReservation] = useState(false);

    const [periods, setPeriods] = useState([]);

    useEffect(() => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
            .then(response => {
                var owner = response.data;
                setUser(response.data);
             

        axios.get(SERVER_URL + "/ownerPeriods/allFreeOwnerPeriods/" + owner.id, { headers: headers })
        .then(response => {
            console.log(response.data);
            var periods = response.data;
            for(let p of periods) {
                p.startDate = format(p.startDate, 'yyyy-MM-dd kk:mm');
                p.endDate = format(p.endDate, 'yyyy-MM-dd kk:mm');
            }

            var allPeriods = [];
            for(let p of periods){
                var event = {
                    color: '#FF0000',
                    from: p.startDate,
                    to: p.endDate,
                    title: "Free period #"
                }
                allPeriods.push(event);
            }

            setPeriods(allPeriods);

            axios.get(SERVER_URL + "/reservations/shipOwnerReservations/" + owner.id, {headers: headers})
                    .then(response => {
                        var reservations = response.data;
                        for(let res of reservations){
                            res.startDate = format(res.startDate, 'dd.MM.yyyy. kk:mm');
                            res.endDate = format(res.endDate, 'dd.MM.yyyy. kk:mm');
                        }
                        for(let r of reservations){
                            if(r.reservationType === "regular reservation") {
                                var event = {
                                    color: '#FB8C48',
                                    from: r.startDate,
                                    to: r.endDate,
                                    title: "Reservation #" + r.reservationId + " for "+ r.entityName + ": client - " + r.clientUsername + ", service type - " +  r.regularService
                                }
                                allPeriods.push(event);
                            } else {
                                var event = {
                                    color: '#97FB48',
                                    from: r.startDate,
                                    to: r.endDate,
                                    title: "Action #" + r.reservationId + " for "+ r.entityName + ": client - " + r.clientUsername + ", service type - " +  r.regularService
                                }
                                allPeriods.push(event);
                            }
                        }
                        setPeriods(allPeriods);
                    });
            

            })
        })
    }, [])

    

    return (
        <div className="wrapper">
            <div className="left">
                <h4>OWNER RESERVATIONS</h4><br/>
                <Link className="sidebar-link" to={"/shipOwnerReservationCalendar"}>Calendar</Link><br/><br/>
                <Link className="sidebar-link" to={"/shipOwnerReservations"}>Reservation history</Link><br/><br/>
            </div>
            <div className="right">
                <div className="info">
                    <h3>SHIP OWNER RESERVATION CALENDAR</h3>
                    <div className="info_data">
                        <button className="new-period-btn" onClick={() => setAddPeriod(true)}>
                            Edit reservation periods
                        </button>

                        <div style={{'background-color': '#2f5e3d'}}>
                            <Calendar events={periods}/>
                        </div>
                    </div> <br/> <br/>
                </div>
            </div>

            <AddOwnerReservationPeriod modalIsOpen={addPeriod} setModalIsOpen={setAddPeriod} />
        </div>
    )

}


export default ShipOwnerReservationCalendar;