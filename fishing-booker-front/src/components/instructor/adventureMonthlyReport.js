import React, { Component } from 'react';
import Chart from 'chart.js/auto';
import { CategoryScale } from "chart.js";
import { useState, useEffect } from 'react';
import '../../css/usersProfile.css'
import axios from 'axios';
import { Bar } from "react-chartjs-2";

const AdventureMonthlyReport = () => {

    const SERVER_URL = process.env.REACT_APP_API; 

    const [monthlyReservations, setMonthlyReservations] = useState([]);
    const [year, setYear] = useState(new Date().getFullYear());
    const [user, setUser] = useState([]);

    useEffect(() => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
            .then(response => {
                setUser(response.data);
                var user = response.data;

                axios.get(SERVER_URL + "/reservationReports/monthly/" + year + "/" + user.id, {headers: headers})
                    .then(response => {
                        setMonthlyReservations(response.data);
                    });
            })

        
    }, [year])

    return (
        <div >
            <h2 >
                Number of month reservations for year:
                <input className="search-box" type="number" placeholder='Enter year for report' value={year} onChange={(e) => setYear(e.target.value)} />
            </h2>
            <Bar style={{marginLeft:'5%', marginBottom: '10%'}}
                data={{
                    // Name of the variables on x-axies for each bar
                    labels: ["January", "February", "March", "April","May","June","July", "August", "September", "October","November","December"],
                    datasets: [
                    {
                        // Label for bars
                        label: "number of reservations",
                        // Data or value of your each variable
                        data: monthlyReservations,
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

export default AdventureMonthlyReport;