import React, { Component, useEffect } from 'react';
import { useState } from 'react';
import { Link, useParams} from "react-router-dom";
import '../../css/usersProfile.css'
import Calendar from 'react-awesome-calendar';
import axios from 'axios';
import AddShipReservationPeriod from './addShipreservationPeriod';
import { format } from 'date-fns';
import ShipReservationPeriods from './shipReservationPeriods';


const ShipReservationCalendar = () => {

    const {shipId} = useParams();

    const SERVER_URL = process.env.REACT_APP_API; 
        
    const [addPeriod, setAddPeriod] = useState(false);

    const [showPeriods, setShowPeriods] = useState(false);

    const [periods, setPeriods] = useState([]);

    const [user, setUser] = useState([]);

    useEffect(() => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
            .then(response => {
                var owner = response.data;
                setUser(response.data);
             

        axios.get(SERVER_URL + "/periods/allFreePeriods/" + owner.id + "/" + shipId, { headers: headers })
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

            axios.get(SERVER_URL + '/reservations/getEntityReservations/' + shipId, {headers: headers})
            .then(res => {
                var resDates = res.data;
                    for(let d of resDates) {
                        d.startDate = format(d.startDate, 'yyyy-MM-dd kk:mm');
                        d.endDate = format(d.endDate, 'yyyy-MM-dd kk:mm');
                    }

                    for(let d of resDates){
                        if(d.reservationType === "regular reservation") {
                            var event = {
                                color: '#FB8C48',
                                from: d.startDate,
                                to: d.endDate,
                                title: "Reservation #" + d.reservationId + " for "+ d.entityName + ": client - " + d.clientUsername + ", service type - " +  d.regularService
                            }
                            allPeriods.push(event);
                        } else {
                            var event = {
                                color: '#97FB48',
                                from: d.startDate,
                                to: d.endDate,
                                title: "Action #" + d.reservationId + " for "+ d.entityName + ": client - " + d.clientUsername + ", service type - " +  d.regularService
                            }
                            allPeriods.push(event);
                        }
                    }

                    setPeriods(allPeriods);
            })

        })

    })
    }, [])


    return (
        <div className="wrapper">
            <div className="left">
                <h4>SHIP PROFILE</h4><br/>
                <Link className="sidebar-link" to={"/shipNavEq/" + shipId}>Navigation equipment</Link><br/><br/>
                <Link className="sidebar-link" to={"/shipFishEq/" + shipId}>Fishing equipment</Link><br/><br/>
                <Link className="sidebar-link" to={"/shipImages/" + shipId}>Images</Link><br/><br/>
                <Link className="sidebar-link" to={"/shipRules/" + shipId}>Rules</Link><br/><br/>
                <Link className="sidebar-link" to={"/shipPricelist/" + shipId}>Pricelist</Link><br/><br/>
                <Link className="sidebar-link" to={"/shipActions/" + shipId}>Actions</Link><br/><br/>
                <Link className="sidebar-link" to={"/shipReservationCalendar/" + shipId}>Reservation calendar</Link><br/><br/>
            </div>
            <div className="right">
                <div className="info">
                    <h3>SHIP RESERVATION CALENDAR</h3>
                    <div className="info_data">
                        <button className="new-period-btn" onClick={() => setShowPeriods(true)}>
                            Edit reservation periods
                        </button>

                        <div style={{'background-color': '#2f5e3d'}}>
                            <Calendar events={periods}/>
                        </div>
                    </div> <br/> <br/>
                </div>
            </div>
            <AddShipReservationPeriod modalIsOpen={addPeriod} setModalIsOpen={setAddPeriod} entityId={shipId}/>
            <ShipReservationPeriods modalIsOpen={showPeriods} setModalIsOpen={setShowPeriods} entityId={shipId} />
        </div>
    )

}


export default ShipReservationCalendar;