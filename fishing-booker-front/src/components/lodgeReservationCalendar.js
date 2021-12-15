import React, { Component } from 'react';
import { useState } from 'react';
import { Link, BrowserRouter as Router, Route, Switch } from "react-router-dom";
import { withRouter } from 'react-router-dom';
import '../css/adminsProfile.css'
import AddLodgeReservationPeriod from './addLodgeReservationPeriod';
import Calendar from 'react-awesome-calendar';

const LodgeReservationCalendar = () => {
        
    const [addPeriod, setAddPeriod] = useState(false);

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
                <Link className="sidebar-link" to={"/lodgeImages/"}>Images</Link><br/><br/>
                <Link className="sidebar-link" to={"/lodgeRules/"}>Rules</Link><br/><br/>
                <Link className="sidebar-link" to="/lodgePricelist">Pricelist</Link><br/><br/>
                <Link className="sidebar-link" to="/lodgeActions">Actions</Link><br/><br/>
                <Link className="sidebar-link" to="/lodgeReservationCalendar">Reservation calendar</Link><br/><br/>
            </div>
            <div className="right">
                <div className="info">
                    <h3>LODGE RESERVATION CALENDAR</h3>
                    <div className="info_data">
                        <Link to="#addReservationPeriod" onClick={() => setAddPeriod(true)}>
                            <button className="new-period-btn">
                                Define new reservation period
                            </button>
                        </Link>
                        <Calendar 
                            events={events}
                        />
                    </div> <br/> <br/>
                </div>
            </div>
            <AddLodgeReservationPeriod modalIsOpen={addPeriod} setModalIsOpen={setAddPeriod} />
        </div>
    )

}


export default LodgeReservationCalendar;