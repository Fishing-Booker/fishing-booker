import React from 'react'
import { Link, BrowserRouter as Router, Route, Switch } from "react-router-dom";
import '../css/lodgePricelist.css';

const LodgePriceList = () => {
    return (
        <div class="wrapper">
            <div class="left">
                <h4>Lodge name</h4><br/>
                <Link className="sidebar-link" to="/lodgeImages" >Images</Link><br/><br/>
                <Link className="sidebar-link" to="/lodgeRules">Rules</Link><br/><br/>
                <a href="">Pricelist</a><br/><br/>
                <a href="">Reservation calendar</a><br/><br/>
                <a href="">Actions for reservations</a><br/><br/>
                <a href="">Reservations reports</a><br/><br/>
            </div>
            <div class="right">
                <div class="info">
                    <h3>LODGE PRICELIST</h3>
                    <div class="info_data">

                    <div class="container-table">
                        <ul class="responsive-table">
                            <li class="table-header">
                            <div class="col col-1">Service Name</div>
                            <div class="col col-3">Price</div>
                            <div class="col col-4">Service Type</div>
                            </li>
                            <li class="table-row">
                            <div class="col col-1" >Lodge1</div>
                            <div class="col col-3" >$350</div>
                            <div class="col col-4" >Regular</div>
                            </li>
                            <li class="table-row">
                            <div class="col col-1" >Lodge2</div>
                            <div class="col col-3" >$220</div>
                            <div class="col col-4" >Regular</div>
                            </li>
                            <li class="table-row">
                            <div class="col col-1" >TV</div>
                            <div class="col col-3" >$341</div>
                            <div class="col col-4" >Additional</div>
                            </li>
                            <li class="table-row">
                            <div class="col col-1" >Lodge3</div>
                            <div class="col col-3" >$115</div>
                            <div class="col col-4" >Regular</div>
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