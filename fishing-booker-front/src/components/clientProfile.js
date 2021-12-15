import React from 'react'
import { Link, BrowserRouter as Router, Route, Switch } from "react-router-dom";
import { withRouter } from 'react-router-dom';
import '../css/addingForm.css'
import Modal from 'react-modal';

const ClientProfile = ({modalIsOpen, setModalIsOpen}) => {

   return (
       <div>
            <Modal className="fullscreen" isOpen={modalIsOpen}
            shouldCloseOnEsc={true}
            onRequestClose={() => setModalIsOpen(false)} >
            <div id="clientProfile" className="adding-wrapper">
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
                            <Link to="/lodgeReservations" onClick={() => setModalIsOpen(false)}>
                                <button className="client-btn" >
                                    Done
                                </button>
                            </Link>
                        </div> <br/> <br/>
                    </div>
                </div>
            </div>
            </Modal>
        </div>
   )
    
}

export default ClientProfile;