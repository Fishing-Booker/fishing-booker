import React from 'react'
import { Link, BrowserRouter as Router, Route, Switch } from "react-router-dom";
import '../css/userProfile.css'

const LodgeProfile = () => {
    return (
        <div class="wrapper">
            <div class="left">
                <h4>Lodge name</h4><br/>
                <Link className="sidebar-link" to="/lodgeImages" >Images</Link><br/><br/>
                <Link className="sidebar-link" to="/lodgeRules">Rules</Link><br/><br/>
                <Link className="sidebar-link" to="/lodgePricelist">Pricelist</Link><br/><br/>
                <a href="">Reservation calendar</a><br/><br/>
                <a href="">Actions for reservations</a><br/><br/>
                <a href="">Reservations reports</a><br/><br/>
            </div>
            <div class="right">
                <div class="info">
                    <h3>LODGE NAME</h3>
                    <div class="info_data">
                        <div class="data">
                            <h4>Address</h4>
                            <input  value="Lodge name"/>
                        </div>
                        <div class="data">
                            <h4>Bedrooms</h4>
                            <input   value="Number of rooms and beds in them"/>
                        </div>
                        <div class="data">
                            <h4>Additional services</h4>
                            <input  value="List of services"/>
                        </div>
                        <div class="data">
                            <h4>Description</h4>
                            <textarea   value="Lodge description"/>
                        </div>
                    </div> <br/> <br/>
                    <button className="edit-profile-btn" >Save changes</button>
                </div>
            </div>
        </div>
    )
}

export default LodgeProfile;