import React, { Component } from 'react';
import { useState, useEffect } from 'react';
import { Link, BrowserRouter as Router, Route, Switch } from "react-router-dom";
import '../../css/usersProfile.css';
import Calendar from 'react-awesome-calendar';
import {useParams }from 'react-router'
import AddInstructorsAvailablePeriod from "./addInstructorsAvailablePeriod"
import axios from 'axios';

const InstructorsCalendar = () => {
    const [addPeriod, setAddPeriod] = useState(false);
    const {adventureId} = useParams();
    const [periods, setPeriods] = useState([]);
    const SERVER_URL = process.env.REACT_APP_API; 

    const colors = ['#fd3153', '#1ccb9e', '#3694DF', '#00008B', '#8B008B', '#FF8C00', '#00CED1', '#DAA520']

    useEffect(() => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + "/ownerPeriods/getOwnerReservationPeriods", {headers: headers})
          .then(response => {
            setPeriods(response.data);
            console.log(response.data)
        });
    }, [])

    const events = [{
        color: colors[Math.floor(Math.random()*colors.length)],
        from: '2022-01-02T00:00:00',
        to: '2022-01-04T19:00:00',
        title: 'FREE'
    }, {
        color: '#1ccb9e',
        from: '2022-01-03T00:00:00+00:00',
        to: '2022-01-06T19:00:00+00:00',
        title: 'This is another event'
    }, {
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