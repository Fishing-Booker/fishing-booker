import React, { Component } from 'react'
import { Link, BrowserRouter as Router, Route, Switch } from "react-router-dom";
import { withRouter } from 'react-router-dom';
import '../css/adminsProfile.css'

class LodgeActions extends Component {

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
                <div className="left">
                    <h4>{lodge.name}</h4><br/>
                    <Link className="sidebar-link" to={"/lodgeImages/" + lodge.id }>Images</Link><br/><br/>
                    <Link className="sidebar-link" to={"/lodgeRules/" + lodge.id}>Rules</Link><br/><br/>
                    <Link className="sidebar-link" to="/lodgePricelist">Pricelist</Link><br/><br/>
                    <Link className="sidebar-link" to="/lodgeActions">Actions</Link><br/><br/>
                    <Link className="sidebar-link" to="/lodgeReservationCalendar">Reservation calendar</Link><br/><br/>
                </div>
                <div className="right">
                    <div className="info">
                        <h3>ACTIONS</h3>
                        <Link to="/addLodgeAction">
                            <button className="new-action-btn">
                                Create new action
                            </button>
                        </Link><br/><br/>
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
            </div>
        )

    }
    
}

export default LodgeActions;