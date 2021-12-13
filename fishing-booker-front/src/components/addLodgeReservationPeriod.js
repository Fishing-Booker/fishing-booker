import React from 'react'
import { Link, BrowserRouter as Router, Route, Switch } from "react-router-dom";
import { withRouter } from 'react-router-dom';
import '../css/addingForm.css'

const AddLodgeReservationPeriod = () => {

   return (
        <div className="adding-wrapper">
            <div className="right">
                <div className="info">
                    <h3>DEFINE NEW RESERVATION PERIOD</h3>
                    <div className="info_data">
                        <div className="data">
                            <h4>Period start date:</h4>
                            <input type="date"/>
                        </div>
                        <div className="data">
                            <h4>Period start time:</h4>
                            <input type="date"/>
                        </div>
                        <div className="data">
                            <h4>Period end date:</h4>
                            <input type="date"/>
                        </div>
                        <div className="data">
                            <h4>Period end time:</h4>
                            <input type="date"/>
                        </div>
                        <Link to="/lodgeReservationCalendar">
                            <button className="reservation-period-btn">
                                Add
                            </button>
                        </Link>
                    </div> <br/> <br/>
                </div>
            </div>
        </div>
   )
    
}

export default AddLodgeReservationPeriod;