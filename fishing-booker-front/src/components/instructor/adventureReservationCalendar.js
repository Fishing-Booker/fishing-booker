import React, { Component } from 'react';
import { useState } from 'react';
import { Link, BrowserRouter as Router, Route, Switch } from "react-router-dom";
import '../../css/usersProfile.css';
import Calendar from 'react-awesome-calendar';
import {useParams }from 'react-router';

const AdventureReservationCalendar = () => {
    const [addPeriod, setAddPeriod] = useState(false);
    const {adventureId} = useParams();

    const events = [{
        id: 1,
        color: '#fd3153',
        from: '2022-01-02T00:00:00+00:00',
        to: '2022-01-04T19:00:00+00:00',
        title: 'This is an event'
    }, {
        id: 2,
        color: '#1ccb9e',
        from: '2022-01-03T00:00:00+00:00',
        to: '2022-01-06T19:00:00+00:00',
        title: 'This is another event'
    }, {
        id: 3,
        color: '#3694DF',
        from: '2022-01-05T13:00:00+00:00',
        to: '2022-01-05T20:00:00+00:00',
        title: 'This is also another event'
    }];

    return (
        <div className="wrapper">
            <div className="left">
                <h4>ADVENTURE PROFILE</h4><br/>
                <Link className="sidebar-link" to={"/adventureImages/" + adventureId}>Images</Link><br/><br/>
                <Link className="sidebar-link" to={"/adventureRules/" + adventureId}>Rules</Link><br/><br/>
                <Link className="sidebar-link" to={"/adventurePricelist/" + adventureId}>Pricelist</Link><br/><br/>
                <Link className="sidebar-link" to={"/adventureActions/" + adventureId}>Actions</Link><br/><br/>
                <Link className="sidebar-link" to={"/adventureReservationCalendar/" + adventureId}>Reservation calendar</Link><br/><br/>
            </div>
            <div className="right">
                <div className="info">
                    <h3>ADVENTURE RESERVATION CALENDAR</h3>
                    <div className="info-data">
                        <button className="new-period-btn" onClick={() => setAddPeriod(true)}>
                            Define new reservation period
                        </button>
                        <Calendar 
                            events={events}
                        />
                    </div>
                </div>
            </div>
            
        </div>
    );

}
export default AdventureReservationCalendar;