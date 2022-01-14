import React, { Component, useEffect } from 'react';
import { useState } from 'react';
import { Link, useParams} from "react-router-dom";
import '../../css/usersProfile.css'
import Calendar from 'react-awesome-calendar';
import axios from 'axios';
import AddShipReservationPeriod from './addShipReservationPeriod';

const ShipReservationCalendar = () => {

    const {shipId} = useParams();

    const SERVER_URL = process.env.REACT_APP_API; 
        
    const [addPeriod, setAddPeriod] = useState(false);
    const [addReservation, setAddReservation] = useState(false);

    useEffect(() => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + "/periods/freePeriods/" + shipId, {headers: headers})
          .then(response => {
              console.log(response.data);
          });
    }, [])

    const events = [{
        id: 1,
        color: '#fd3153',
        from: '2021-12-02T00:00:00+00:00',
        to: '2021-12-04T19:00:00+00:00',
        title: 'This is an event'
    }, {
        id: 2,
        color: '#1ccb9e',
        from: '2021-12-03T00:00:00+00:00',
        to: '2021-12-06T19:00:00+00:00',
        title: 'This is another event'
    }, {
        id: 3,
        color: '#3694DF',
        from: '2019-05-05T13:00:00+00:00',
        to: '2019-05-05T20:00:00+00:00',
        title: 'This is also another event'
    }];

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
                        <button className="new-period-btn" onClick={() => setAddPeriod(true)}>
                            New reservation period
                        </button>

                        <Calendar events={events}/>
                    </div> <br/> <br/>
                </div>
            </div>

            <AddShipReservationPeriod modalIsOpen={addPeriod} setModalIsOpen={setAddPeriod} entityId={shipId}/>
        </div>
    )

}


export default ShipReservationCalendar;