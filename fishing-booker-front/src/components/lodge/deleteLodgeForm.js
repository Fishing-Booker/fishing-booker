import React from 'react'
import { Link } from "react-router-dom";
import '../../css/addingForm.css'
import Modal from 'react-modal';
import axios from "axios";

const DeleteLogdeForm = ({modalIsOpen, setModalIsOpen, lodgeId}) => {

    const SERVER_URL = process.env.REACT_APP_API; 

    const deleteLodge = () => {
        const headers = {'Content-Type' : 'application/json',
                     'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.delete(SERVER_URL + '/lodges/deleteLodge/' + lodgeId, { headers: headers})    
        .then(response => {setModalIsOpen(false); window.location.reload();});
    }

   return (
       <div>
            <Modal className="fullscreen" isOpen={modalIsOpen}
            shouldCloseOnEsc={true}
            onRequestClose={() => setModalIsOpen(false)} >
                <div id="deleteLodge" className="deleting-wrapper">
                    <div className="right">
                        <div className="info">
                        <h3>DELETE YOUR LODGE</h3>
                        <div className="info_data">
                            <div className="data">
                                Are you sure you want to delete this lodge?
                            </div>
                            <div className="buttons">
                                <button className="cancel" onClick={() => setModalIsOpen(false)}>
                                    Cancel
                                </button>
                                <button className="delete" onClick={() => deleteLodge()}>
                                    Delete
                                </button><br/><br/><br/>
                            </div>
                        </div>
                        </div>
                    </div>
                </div>
            </Modal>
        </div>
   )
    
}

export default DeleteLogdeForm;