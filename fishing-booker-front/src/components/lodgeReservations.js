import React, { Component } from 'react'
import { Link, BrowserRouter as Router, Route, Switch } from "react-router-dom";
import { withRouter } from 'react-router-dom';
import '../css/adminsProfile.css'

class LodgeReservations extends Component {

    state = {
        lodge: {
            id: 0,
            name: "LODGE1",
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
                <div className="reservations-right">
                    <div className="info">
                        <h3>RESERVATIONS</h3>
                        <Link to="/addLodgeReservation">
                            <button className="new-reservation-btn">
                                Create new reservation
                            </button>
                        </Link><br/><br/>
                        <div class="container-table-reservations">
                            <ul class="responsive-table">
                                <li class="table-header">
                                <div class="col col-1">Lodge</div>
                                <div class="col col-3">Reservation period</div>
                                <div class="col col-4">Client</div>
                                </li>
                                <li class="table-row">
                                <div class="col col-1" >Lodge 1</div>
                                <div class="col col-3" >30.11.2021. - 02.12.2021.</div>
                                <Link to="/clientProfile" class="col col-4" style={{textDecoration: 'none'}}>
                                    Marko Markovic
                                </Link>
                                </li>
                                <li class="table-row">
                                <div class="col col-1" >Lodge 1</div>
                                <div class="col col-3" >30.11.2021. - 02.12.2021.</div>
                                <Link to="/clientProfile" class="col col-4" style={{textDecoration: 'none'}}>
                                    Marko Markovic
                                </Link>
                                </li>
                                <li class="table-row">
                                <div class="col col-1" >Lodge 1</div>
                                <div class="col col-3" >30.11.2021. - 02.12.2021.</div>
                                <Link to="/clientProfile" class="col col-4" style={{textDecoration: 'none'}}>
                                    Marko Markovic
                                </Link>
                                </li>
                                <li class="table-row">
                                <div class="col col-1" >Lodge 1</div>
                                <div class="col col-3" >30.11.2021. - 02.12.2021.</div>
                                <Link to="/clientProfile" class="col col-4" style={{textDecoration: 'none'}}>
                                    Marko Markovic
                                </Link>
                                </li>
                                <li class="table-row">
                                <div class="col col-1" >Lodge 1</div>
                                <div class="col col-3" >30.11.2021. - 02.12.2021.</div>
                                <Link to="/clientProfile" class="col col-4" style={{textDecoration: 'none'}}>
                                    Marko Markovic
                                </Link>
                                </li>
                                <li class="table-row">
                                <div class="col col-1" >Lodge 1</div>
                                <div class="col col-3" >30.11.2021. - 02.12.2021.</div>
                                <Link to="/clientProfile" class="col col-4" style={{textDecoration: 'none'}}>
                                    Marko Markovic
                                </Link>
                                </li>
                                <li class="table-row">
                                <div class="col col-1" >Lodge 1</div>
                                <div class="col col-3" >30.11.2021. - 02.12.2021.</div>
                                <Link to="/clientProfile" class="col col-4" style={{textDecoration: 'none'}}>
                                    Marko Markovic
                                </Link>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        )

    }
    
}

export default LodgeReservations;