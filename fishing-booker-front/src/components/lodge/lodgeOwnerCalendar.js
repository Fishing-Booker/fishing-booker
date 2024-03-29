import React, { Component } from 'react';
import { useState, useEffect } from 'react';
import '../../css/usersProfile.css';
import Calendar from 'react-awesome-calendar';
import axios from 'axios';
import { format } from 'date-fns';

const LodgeOwnerCalendar = () => {
    const [periods, setPeriods] = useState([]);
    const SERVER_URL = process.env.REACT_APP_API; 
    const [user, setUser] = useState("");

    const colors = ['#fd3153', '#1ccb9e', '#3694DF', '#00008B', '#8B008B', '#FF8C00', '#00CED1', '#DAA520', '#98FF79','#95D7FA', '#F095FA', '#FB4889']

    useEffect(() => {
        setPeriods([])
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
            .then(r => {
            setUser(r.data);
            console.log(r.data);

            axios.get(SERVER_URL + "/reservations/getOwnerReservations/" + r.data.id, {headers: headers})
                .then(res => {
                    var resDates = res.data;
                    for(let d of resDates) {
                        d.startDate = format(d.startDate, 'yyyy-MM-dd kk:mm');
                        d.endDate = format(d.endDate, 'yyyy-MM-dd kk:mm');
                    }

                    console.log(resDates)

                    var allPeriods = []
                    for(let d of resDates){
                        if(d.reservationType === "regularReservation") {
                            var event = {
                                color: '#FB8C48',
                                from: d.startDate,
                                to: d.endDate,
                                title: "Reservation #" + d.reservationId + " for "+ d.entityName + ": client - " + d.clientName + " " + d.clientSurname + ", service type - " +  d.regularService
                            }
                            allPeriods.push(event);
                        } else {
                            var event = {
                                color: '#97FB48',
                                from: d.startDate,
                                to: d.endDate,
                                title: "Action #" + d.reservationId + " for "+ d.entityName + ": client - " + d.clientName + " " + d.clientSurname + ", service type - " +  d.regularService
                            }
                            allPeriods.push(event);
                        }
                    }

                    setPeriods(allPeriods);

                    axios.get(SERVER_URL + "/periods/entityPeriods/" + 4, { headers: headers })
                    .then(response => {
                        var periods = response.data;
                        for(let p of periods) {
                            p.startDate = format(p.startDate, 'yyyy-MM-dd kk:mm');
                            p.endDate = format(p.endDate, 'yyyy-MM-dd kk:mm');
                        }

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

                    })

                });
    })
    }, [])

    return(
        <div className="calendar-wrapper">
            <Calendar 
                events={periods}
            />
        </div>
        
    )
}
export default LodgeOwnerCalendar;