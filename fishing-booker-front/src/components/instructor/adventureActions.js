import React, { Component } from 'react'
import { useState } from 'react';
import { Link, BrowserRouter as Router, Route, Switch } from "react-router-dom";
import {useParams }from 'react-router';
import AddAdventureActionForm from "./addAdventureActionForm";
import '../../css/usersProfile.css'


const AdventureActions = () => {
    const [addAction, setAddAction] = useState(false);
    const {adventureId} = useParams();

    return (
        <div className="wrapper">
            <div className="left">
                <h4>ADVENTURE PROFILE</h4><br/>
                <Link className="sidebar-link" to={"/adventureImages/"}>Images</Link><br/><br/>
                <Link className="sidebar-link" to={"/adventureRules/"}>Rules</Link><br/><br/>
                <Link className="sidebar-link" to={"/adventurePricelist/" + adventureId}>Pricelist</Link><br/><br/>
                <Link className="sidebar-link" to={"/adventureActions/" + adventureId}>Actions</Link><br/><br/>
                <Link className="sidebar-link" to={"/adventureReservationCalendar/" + adventureId}>Reservation calendar</Link><br/><br/>
            </div>
            <div className="right">
                <div className="info">
                    <h3>ACTIONS</h3>
                    <button className="new-action-btn" onClick={() => setAddAction(true)}>
                        Create new action
                    </button><br/><br/>
                    <div className="container-table">
                        <ul className="responsive-table">
                            <li className="table-header">
                            <div className="col col-1">Action start</div>
                            <div className="col col-3">Action end</div>
                            <div className="col col-4">Price</div>
                            </li>
                            <li className="table-row">
                            <div className="col col-1" >20.11.2021.</div>
                            <div className="col col-3" >30.11.2021.</div>
                            <div className="col col-4" >$350</div>
                            </li>
                            <li className="table-row">
                            <div className="col col-1" >20.11.2021.</div>
                            <div className="col col-3" >30.11.2021.</div>
                            <div className="col col-4" >$220</div>
                            </li>
                            <li className="table-row">
                            <div className="col col-1" >20.11.2021.</div>
                            <div className="col col-3" >30.11.2021.</div>
                            <div className="col col-4" >$341</div>
                            </li>
                            <li className="table-row">
                            <div className="col col-1" >20.11.2021.</div>
                            <div className="col col-3" >30.11.2021.</div>
                            <div className="col col-4" >$115</div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <AddAdventureActionForm modalIsOpen={addAction} setModalIsOpen={setAddAction}/>
        </div>
    )
}
export default AdventureActions;