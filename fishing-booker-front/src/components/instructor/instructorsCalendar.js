import React, { Component } from 'react';
import { useState } from 'react';
import { Link, BrowserRouter as Router, Route, Switch } from "react-router-dom";
import '../../css/usersProfile.css';
import Calendar from 'react-awesome-calendar';
import {useParams }from 'react-router'
import AddInstructorsAvailablePeriod from "./addInstructorsAvailablePeriod"

const InstructorsCalendar = () => {
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

    return(
        <div className="calendar-wrapper">
            <button className="add-period-btn" onClick={() => setAddPeriod(true)}>+</button>
            <Calendar 
                events={events}
            />
            <AddInstructorsAvailablePeriod modalIsOpen={addPeriod} setModalIsOpen={setAddPeriod}/>
        </div>
        
    )
}
export default InstructorsCalendar;