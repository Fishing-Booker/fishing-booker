import React, { Component } from 'react'
import { useState } from 'react';
import { Link, BrowserRouter as Router, Route, Switch } from "react-router-dom";
import { withRouter } from 'react-router-dom';
import '../css/adminsProfile.css'

const LodgeProfile = () => {

    const [disabledEdit, setDisabledEdit] = useState(true);
        
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
                    <h3>LODGE NAME</h3>
                    <div className="info_data">
                        <div className="data">
                            <h4>Address</h4>
                            <input  value="Lodge address" disabled={disabledEdit}/>
                        </div>
                        <div className="data">
                            <h4>Bedrooms</h4>
                            <input value="Number of rooms and beds in them" disabled={disabledEdit}/>
                        </div>
                        <div className="data">
                            <h4>Additional services</h4>
                            <input  value="List of services" disabled={disabledEdit}/>
                        </div>
                        <div className="data">
                            <h4>Description</h4>
                            <textarea value="Lodge description" disabled={disabledEdit}/>
                        </div>
                    </div> <br/> <br/>
                    {disabledEdit ? (
                        <button className="edit-profile-btn" onClick={() => setDisabledEdit(false)}>
                            Edit
                        </button>
                    ) : (
                        <div className="edit-profile-btns">
                            <button className="edit-profile-cancel" onClick={() => setDisabledEdit(true)}>
                                Cancel
                            </button>
                            <button className="edit-profile-save" onClick={() => setDisabledEdit(true)}>
                                Save
                            </button>
                        </div>
                    )}
                    
                    
                </div>
            </div>
        </div>
    )

}
    

export default LodgeProfile;