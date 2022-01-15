import React, { Component } from 'react';
import { useState, useEffect } from 'react';
import { Link, BrowserRouter as Router, Route, Switch } from "react-router-dom";
import '../../css/usersProfile.css';
import Calendar from 'react-awesome-calendar';
import {useParams }from 'react-router'
import AddInstructorsAvailablePeriod from "./addInstructorsAvailablePeriod"
import axios from 'axios';
import { format } from 'date-fns';

const InstructorsCalendar = () => {
    const [addPeriod, setAddPeriod] = useState(false);
    const {adventureId} = useParams();
    const [periods, setPeriods] = useState([]);
    const SERVER_URL = process.env.REACT_APP_API; 
    //const [events, setEvents] = useState([]);

    const colors = ['#fd3153', '#1ccb9e', '#3694DF', '#00008B', '#8B008B', '#FF8C00', '#00CED1', '#DAA520']

    useEffect(() => {
        setPeriods([])
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + "/ownerPeriods/getOwnerReservationPeriods", {headers: headers})
          .then(response => {
            var dates = response.data;
            console.log(response.data);
            for(let dat of dates) {
                dat.startDate = format(dat.startDate, 'yyyy-MM-dd kk:mm');
                dat.endDate = format(dat.endDate, 'yyyy-MM-dd kk:mm');
            }
            console.log(dates)

            for(let dat of dates){
                var event = {
                    color: colors[Math.floor(Math.random()*colors.length)],
                    from: dat.startDate,
                    to: dat.endDate
                }
                periods.push(event);
            }
            console.log(periods)
            setPeriods(periods);
        });
    }, [])

    return(
        <div className="calendar-wrapper">
            <button className="add-period-btn" onClick={() => setAddPeriod(true)}>+</button>
            <Calendar 
                events={periods}
            />
            <AddInstructorsAvailablePeriod modalIsOpen={addPeriod} setModalIsOpen={setAddPeriod}/>
        </div>
        
    )
}
export default InstructorsCalendar;