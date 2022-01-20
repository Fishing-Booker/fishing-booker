import React, { Component, useEffect } from 'react';
import { useState } from 'react';
import { Link, useParams} from "react-router-dom";
import '../../css/usersProfile.css'
import AddLodgeReservationPeriod from './addLodgeReservationPeriod';
import Calendar from 'react-awesome-calendar';
import axios from 'axios';
import LodgeReservationPeriods from './lodgeReservationPeriods';

const LodgeReservationCalendar = () => {

    const {lodgeId} = useParams();

    const SERVER_URL = process.env.REACT_APP_API; 
        
    const [addPeriod, setAddPeriod] = useState(false);
    const [addReservation, setAddReservation] = useState(false);

    const [showPeriods, setShowPeriods] = useState(false);

    useEffect(() => {
        /*const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + "/periods/freePeriods/" + lodgeId, {headers: headers})
          .then(response => {
              console.log(response.data);
          });*/
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
                <h4>LODGE PROFILE</h4><br/>
                <Link className="sidebar-link" to={"/lodgeImages/" + lodgeId}>Images</Link><br/><br/>
                <Link className="sidebar-link" to={"/lodgeRules/" + lodgeId}>Rules</Link><br/><br/>
                <Link className="sidebar-link" to={"/lodgePricelist/" + lodgeId}>Pricelist</Link><br/><br/>
                <Link className="sidebar-link" to={"/lodgeActions/" + lodgeId}>Actions</Link><br/><br/>
                <Link className="sidebar-link" to={"/lodgeReservationCalendar/" + lodgeId}>Reservation calendar</Link><br/><br/>
            </div>
            <div className="right">
                <div className="info">
                    <h3>LODGE RESERVATION CALENDAR</h3>
                    <div className="info_data">
                        <button className="new-period-btn" onClick={() => setShowPeriods(true)}>
                            Edit reservation periods
                        </button>

                        <Calendar events={events}/>
                    </div> <br/> <br/>
                </div>
            </div>

            <AddLodgeReservationPeriod modalIsOpen={addPeriod} setModalIsOpen={setAddPeriod} entityId={lodgeId}/>
            <LodgeReservationPeriods modalIsOpen={showPeriods} setModalIsOpen={setShowPeriods} entityId={lodgeId} />
        </div>
    )

}


export default LodgeReservationCalendar;