import React, { useEffect, useState } from 'react'
import { Link } from "react-router-dom";
import '../../css/addingForm.css'
import Modal from 'react-modal';
import axios from 'axios';

const AddLodgeReservationPeriod = ({modalIsOpen, setModalIsOpen, entityId}) => {

    const SERVER_URL = process.env.REACT_APP_API; 

    const [startDate, setStartDate] = useState("");
    const [endDate, setEndDate] = useState("");

    const newPeriod = {
        startDate, 
        endDate,
        entityId
    }

    const addPeriod = () => {
        console.log(newPeriod);
        axios.post(SERVER_URL + "/periods/addReservationPeriod", newPeriod)
          .then(response => {
            setModalIsOpen(false)
            window.location.reload();
        });
    }

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
                                    <h4>Period start date and time:</h4>
                                    <input type="datetime-local" required onChange={(e) => {setStartDate(e.target.value)}}  value={startDate}/>
                                </div>
                                <div className="data">
                                    <h4>Period end date and time:</h4>
                                    <input type="datetime-local" required onChange={(e) => {setEndDate(e.target.value)}}  value={endDate}/>
                                </div>
                                <button class="reservation-period-btn" onClick={() => addPeriod()}>
                                    Add
                                </button><br/> <br/>
                            </div>
                        </div> 
                    </div>
                </div>
            </Modal>
        </div>
   )
    
}

export default AddLodgeReservationPeriod;