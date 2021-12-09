import React, { Component } from 'react'
import { useState } from 'react';
import { Link, BrowserRouter as Router, Route, Switch } from "react-router-dom";
import { withRouter } from 'react-router-dom';
import '../css/adminsProfile.css'
import AddLodgeReservationPeriod from './addLodgeReservationPeriod';

const LodgeReservationCalendar = () => {
        
    const [addPeriod, setAddPeriod] = useState(false);

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
                        <Link to="#addLodgeReservationPeriod" onClick={() => setAddPeriod(true)}>
                            <button className="new-period-btn">
                                Define new reservation period
                            </button>
                        </Link><br/><br/>
                    </div> <br/> <br/>

                </div>
            </div>
            <AddLodgeReservationPeriod modalIsOpen={addPeriod} setModalIsOpen={setAddPeriod} />
        </div>
    )

}


export default LodgeReservationCalendar;