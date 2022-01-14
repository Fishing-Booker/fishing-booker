import React, { Component, useEffect } from 'react';
import { useState } from 'react';
import { Link, useParams} from "react-router-dom";
import '../../css/usersProfile.css'
import Calendar from 'react-awesome-calendar';
import axios from 'axios';

const ShipOwnerReservationCalendar = () => {

    const SERVER_URL = process.env.REACT_APP_API; 

    const [user, setUser]
        
    const [addPeriod, setAddPeriod] = useState(false);
    const [addReservation, setAddReservation] = useState(false);

    useEffect(() => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
            .then(response => {
                setUser(response.data);


            })
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
            <div className="right">
                <div className="info">
                    <h3>SHIP OWNER RESERVATION CALENDAR</h3>
                    <div className="info_data">
                        <button className="new-period-btn" onClick={() => setAddPeriod(true)}>
                            New reservation period
                        </button>

                        <Calendar events={events}/>
                    </div> <br/> <br/>
                </div>
            </div>

        </div>
    )

}


export default ShipOwnerReservationCalendar;