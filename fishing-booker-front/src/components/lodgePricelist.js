import React from 'react'
import { Link, BrowserRouter as Router, Route, Switch } from "react-router-dom";
import '../css/lodgePricelist.css';

const LodgePriceList = () => {
    return (
        <div className="wrapper">
            <div className="left">
                <h4>Lodge name</h4><br/>
                <Link className="sidebar-link" to={"/lodgeImages/"}>Images</Link><br/><br/>
                    <Link className="sidebar-link" to={"/lodgeRules/"}>Rules</Link><br/><br/>
                    <Link className="sidebar-link" to="/lodgePricelist">Pricelist</Link><br/><br/>
                    <Link className="sidebar-link" to="/lodgeActions">Actions</Link><br/><br/>
                    <Link className="sidebar-link" to="/lodgeReservationCalendar">Reservation calendar</Link><br/><br/>
            </div>
            <div className="right">
                <div className="info">
                    <h3>LODGE PRICELIST</h3>
                    <div className="info_data">

                    <div className="container-table">
                        <ul className="responsive-table">
                            <li className="table-header">
                            <div className="col col-1">Service Name</div>
                            <div className="col col-3">Price</div>
                            <div className="col col-4">Service Type</div>
                            </li>
                            <li className="table-row">
                            <div className="col col-1" >Lodge1</div>
                            <div className="col col-3" >$350</div>
                            <div className="col col-4" >Regular</div>
                            </li>
                            <li className="table-row">
                            <div className="col col-1" >Lodge2</div>
                            <div className="col col-3" >$220</div>
                            <div className="col col-4" >Regular</div>
                            </li>
                            <li className="table-row">
                            <div className="col col-1" >TV</div>
                            <div className="col col-3" >$341</div>
                            <div className="col col-4" >Additional</div>
                            </li>
                            <li className="table-row">
                            <div className="col col-1" >Lodge3</div>
                            <div className="col col-3" >$115</div>
                            <div className="col col-4" >Regular</div>
                            </li>
                        </ul>
                        </div>

                    </div> <br/> <br/>
                    <button className="edit-profile-btn" >Save changes</button>
                </div>
            </div>
        </div>
    )
}

export default LodgePriceList;