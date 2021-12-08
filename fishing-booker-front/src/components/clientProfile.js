import React from 'react'
import { Link, BrowserRouter as Router, Route, Switch } from "react-router-dom";
import { withRouter } from 'react-router-dom';
import '../css/addingForm.css'

const ClientProfile = () => {

   return (
        <div className="adding-wrapper">
            <div className="right">
                <div className="info">
                    <h3>CLIENT NAME</h3>
                    <div className="info_data">
                        <div className="client-data">
                            <h4>Name:</h4>
                            Client name and surname
                        </div>
                        <div className="client-data">
                            <h4>Username:</h4>
                            Client username
                        </div>
                        <div className="client-data">
                            <h4>Email:</h4>
                            Client email 
                        </div>
                        <div className="client-data">
                            <h4>Address:</h4>
                            Client address
                        </div>
                        <div className="client-data">
                            <h4>Phone number:</h4>
                            Client phone number
                        </div>
                        <Link to="/lodgeReservations">
                            <button className="client-btn" >
                                Done
                            </button>
                        </Link>
                    </div> <br/> <br/>
                </div>
            </div>
        </div>
   )
    
}

export default ClientProfile;