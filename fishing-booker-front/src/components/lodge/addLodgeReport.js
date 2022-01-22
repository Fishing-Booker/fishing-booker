import React, { useEffect, useState } from 'react'
import { Link, useParams} from "react-router-dom";
import { withRouter } from 'react-router-dom';
import '../../css/addingForm.css'
import Modal from 'react-modal';
import axios from 'axios';

const AddLodgeReport = ({modalIsOpen, setModalIsOpen, reservationId}) => {

    const SERVER_URL = process.env.REACT_APP_API; 

    const [text, setText] = useState("");
    const [forPenalty, setForPenalty] = useState(false);
    const [skippedReservation, setSkippedReservation] = useState(false);

    useEffect(() => {
        console.log(reservationId);
    }, [reservationId])

    const newReport = {
        reservationId,
        text,
        forPenalty,
        skippedReservation
    }

    const addReport = () => {
        const headers = {'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.jwtToken}`}
        
        axios.post(SERVER_URL + "/reports/addReport", newReport, {headers: headers})
        .then(response => {
            setModalIsOpen(false);
            window.location.reload();
        })
    }

   return (
       <div>
            <Modal className="fullscreen" isOpen={modalIsOpen}
            shouldCloseOnEsc={true}
            onRequestClose={() => setModalIsOpen(false)} >
                <div id="addLodgeReport" className="adding-wrapper">
                    <div className="right">
                        <div className="info">
                            <h3>ADD RESERVATION REPORT</h3>
                            <div className="info_data">
                                <div className='report-options'>
                                    <div className='data'>
                                        <input type="checkbox" id="penalty" name="card" onChange={(e) => setForPenalty(!forPenalty)} value="forPenalty" />
                                        <label htmlFor="penalty" aria-label="penalty"> Demand for penalty</label>
                                    </div>
                                    <div className='data'>
                                        <input type="checkbox" id="skipped" name="card" onChange={(e) => setSkippedReservation(!skippedReservation)} value="skippedReservation" />
                                        <label htmlFor="skipped" aria-label="skipped"> Client skipped reservation</label>
                                    </div>
                                </div><br/>
                                <div className="data">
                                    <h4>Comment:</h4>
                                    <textarea required onChange={(e) => {setText(e.target.value)}}  value={text}/>
                                </div>
                                <div className="buttons">
                                    <button className="cancel" onClick={() => setModalIsOpen(false)}>
                                        Cancel
                                    </button>
                                    <button className="add" onClick={() => addReport()}>
                                        Post
                                    </button><br/>
                                </div>
                            </div> <br/>
                        </div>
                    </div>
                </div>
            </Modal>
        </div>
   )
    
}

export default AddLodgeReport;