import React, { Component } from 'react';
import Chart from 'chart.js/auto';
import { CategoryScale } from "chart.js";
import { useState, useEffect } from 'react';
import { Link, useParams} from "react-router-dom";
import { withRouter } from 'react-router-dom';
import '../../css/usersProfile.css'
import axios from 'axios';
import { format } from 'date-fns';
import { Bar } from "react-chartjs-2";
import AdventureSalaryReport from './adventureSalaryReport';
import AdventureYearlyReport from './adventureYearlyReport';
import AdventureWeeklyReport from './adventureWeeklyReport';
import AdventureMonthlyReport from './adventureMonthlyReport';

const AdventureReports = () => {

    const SERVER_URL = process.env.REACT_APP_API; 

    const [reservations, setReservations] = useState([]);
    const [monthlyReservations, setMonthlyReservations] = useState([]);
    const [year, setYear] = useState(new Date().getFullYear());
    const [user, setUser] = useState([]);
    const [reportType, setReportType] = useState('yearly');

    useEffect(() => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
            .then(response => {
                setUser(response.data);
                var user = response.data;

                axios.get(SERVER_URL + "/reservationReports/yearly/" + year + "/" + user.id, {headers: headers})
                    .then(response => {
                        setMonthlyReservations(response.data);
                    });
            })

        
    }, [year])
        
    return (
        <div className="wrapper">
            <div className="left">
                <h4>REPORTS</h4><br/>
                <div className="sidebar-link" onClick={() => setReportType("yearly")} >Yearly report</div><br/><br/>
                <div className="sidebar-link" onClick={() => setReportType("monthly")} >Monthly report</div><br/><br/>
                <div className="sidebar-link" onClick={() => setReportType("weekly")} >Weekly report</div><br/><br/>
                <div className="sidebar-link" onClick={() => setReportType("salary")} >Salary report</div><br/><br/>
            </div>
            <div className="right">
            <div className="info">
                    <h3>REPORTS</h3>
                    {reportType === "yearly" && <AdventureYearlyReport />}
                    {reportType === "monthly" && <AdventureMonthlyReport />}
                    {reportType === "weekly" && <AdventureWeeklyReport />}
                    {reportType === "salary" && <AdventureSalaryReport />}
                </div>
            </div>

        </div>
    )

}

export default AdventureReports;