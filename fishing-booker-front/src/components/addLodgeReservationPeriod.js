import React from 'react'
import { Link, BrowserRouter as Router, Route, Switch } from "react-router-dom";
import { withRouter } from 'react-router-dom';
import '../css/addingForm.css'
import Modal from 'react-modal'

const AddLodgeReservationPeriod = ({modalIsOpen, setModalIsOpen}) => {

   return (
       <div>
            <Modal className="fullscreen" isOpen={modalIsOpen}
            shouldCloseOnEsc={true}
            onRequestClose={() => setModalIsOpen(false)} >
                <div id="addlodgeReservationPeriod" className="adding-wrapper">
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
                                <Link to="/lodgeReservationCalendar" onClick={() => setModalIsOpen(false)}>
                                    <button class="reservation-period-btn">
                                        Add
                                    </button>
                                </Link>
                            </div> <br/> <br/>
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
            </Modal>
        </div>
   )
    
}

export default AddLodgeReservationPeriod;