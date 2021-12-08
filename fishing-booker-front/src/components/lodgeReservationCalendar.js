import React, { Component } from 'react'
import { Link, BrowserRouter as Router, Route, Switch } from "react-router-dom";
import { withRouter } from 'react-router-dom';
import '../css/adminsProfile.css'

class LodgeReservationCalendar extends Component {

    state = {
        lodge: {
            id: 0,
            name: "",
            location: "",
            description: "",
            rules: "",
            images: []
        }
    }

    componentDidMount() {

    }

    render() {
        const {lodge} = this.state;
        
        return (
            <div className="wrapper">
                <div className="left">
                    <h4>LODGE RPROFILE</h4><br/>
                    <Link className="sidebar-link" to={"/lodgeImages/" + lodge.id }>Images</Link><br/><br/>
                    <Link className="sidebar-link" to={"/lodgeRules/" + lodge.id}>Rules</Link><br/><br/>
                    <Link className="sidebar-link" to="/lodgePricelist">Pricelist</Link><br/><br/>
                    <Link className="sidebar-link" to="/lodgeActions">Actions</Link><br/><br/>
                    <Link className="sidebar-link" to="/lodgeReservationCalendar">Reservation calendar</Link><br/><br/>
                </div>
                <div className="right">
                    <div className="info">
                        <h3>LODGE RESERVATION CALENDAR</h3>
                        <div className="info_data">
                            <Link to="/addLodgeReservationPeriod">
                                <button className="new-period-btn">
                                    Define new reservation period
                                </button>
                            </Link><br/><br/>
                        </div> <br/> <br/>

                    </div>
                </div>
            </div>
        )

    }
    
}

export default LodgeReservationCalendar;