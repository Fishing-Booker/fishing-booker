import React from 'react'
import { Link, BrowserRouter as Router, Route, Switch } from "react-router-dom";
import '../css/lodgeProfile.css';
import lodgeImg1 from "../images/lodgeImg1.jpg";
import lodgeImg2 from "../images/lodgeImg2.png";
import lodgeImg3 from "../images/lodgeImg3.jpg";

const LodgeImages = () => {
    return (
        <div class="wrapper">
            <div class="left">
                <h4>Lodge name</h4><br/>
                <a href="">Images</a><br/><br/>
                <Link className="sidebar-link" to="/lodgeRules">Rules</Link><br/><br/>
                <Link className="sidebar-link" to="/lodgePricelist">Pricelist</Link><br/><br/>
                <a href="">Reservation calendar</a><br/><br/>
                <a href="">Actions for reservations</a><br/><br/>
                <a href="">Reservations reports</a><br/><br/>
            </div>
            <div class="right">
                <div class="info">
                    <h3>LODGE IMAGES</h3>
                    <div class="info_data-images">
                        <div className="card-image">
                            <img src={lodgeImg1} />
                        </div>
                        <div className="card-image">
                            <img src={lodgeImg2} />
                        </div>
                        <div className="card-image">
                            <img src={lodgeImg3} />
                        </div>
                        <div className="card-image">
                            <img src={lodgeImg1} />
                        </div>
                        <div className="card-image">
                            <img src={lodgeImg2} />
                        </div>
                        <div className="card-image">
                            <img src={lodgeImg3} />
                        </div>
                        <div className="card-image">
                            <img src={lodgeImg1} />
                        </div>
                        <div className="card-image">
                            <img src={lodgeImg2} />
                        </div>
                        <div className="card-image">
                            <img src={lodgeImg3} />
                        </div>
                    </div> <br/> <br/>
                    <button className="edit-profile-btn" >Add new images</button>
                </div>
            </div>
        </div>
    )
}

export default LodgeImages;