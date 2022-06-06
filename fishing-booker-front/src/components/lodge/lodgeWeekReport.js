import React, { Component } from 'react';
import Chart from 'chart.js/auto';
import { CategoryScale } from "chart.js";
import { useState, useEffect } from 'react';
import '../../css/usersProfile.css'
import axios from 'axios';
import { Bar } from "react-chartjs-2";

const LodgeWeeklyReport = () => {

    const SERVER_URL = process.env.REACT_APP_API; 

    const [weeklylyReservations, setWeeklyReservations] = useState([]);
    const [year, setYear] = useState(new Date().getFullYear());
    const [month, setMonth] = useState(new Date().getMonth());
    const [user, setUser] = useState([]);

    useEffect(() => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
            .then(response => {
                setUser(response.data);
                var user = response.data;

                var convertedMonth = month + 1;
                axios.get(SERVER_URL + "/reservationReports/weekly/" + convertedMonth + "/" + year + "/" + user.id, {headers: headers})
                    .then(response => {
                        setWeeklyReservations(response.data);
                    });
            })

        
    }, [year, month])

    return (
        <div >
            <h2 >
                Month:
                <input className="search-box" type="number" placeholder='Enter year for report' value={month} onChange={(e) => setMonth(parseInt(e.target.value))} />
                 of year:
                <input className="search-box" type="number" placeholder='Enter year for report' value={year} onChange={(e) => setYear(e.target.value)} />
            </h2>
            <Bar style={{marginLeft:'5%', marginBottom: '10%'}}
                data={{
                    // Name of the variables on x-axies for each bar
                    labels: ["1. week", "2. week", "3. week", "4. week","5.week","6. week"],
                    datasets: [
                    {                        
                        // Label for bars
                        label: "number of reservations per week",
                        // Data or value of your each variable
                        data: weeklylyReservations,
                        // Color of each bar
                        backgroundColor: ["red" , "#9966cc", "#7FFFD4", "#b9f2ff", "#50C878", "#498877", "#9b111e", "#B4C424", "#0F52BA", "#a8c3bc", "#ffc87c", "#a3928f"],
                        // Border color of each bar
                        borderColor:  ["red", "#9966cc", "#7FFFD4", "#b9f2ff", "#50C878", "#498877", "#9b111e", "#B4C424", "#0F52BA", "#a8c3bc", "#ffc87c", "#a3928f"],
                        borderWidth: 0.5,
                    },
                    ],
                }}
                // Height of graph
                height={600}
                width={1200}
            />
        </div>
    )

}

export default LodgeWeeklyReport;