import React from 'react'
import { Link } from "react-router-dom";
import '../../css/addingForm.css'
import Modal from 'react-modal';
import axios from "axios";

const DeleteShipForm = ({modalIsOpen, setModalIsOpen, shipId}) => {

    const SERVER_URL = process.env.REACT_APP_API; 

    const deleteShip = () => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.delete(SERVER_URL + '/ships/deleteShip/' + shipId, { headers: headers})    
        .then(response => {setModalIsOpen(false); window.location.reload();});
    }

   return (
       <div>
            <Modal className="fullscreen" isOpen={modalIsOpen}
            shouldCloseOnEsc={true}
            onRequestClose={() => setModalIsOpen(false)} >
                <div id="deleteShip" className="deleting-wrapper">
                    <div className="right">
                        <div className="info">
                        <h3>DELETE YOUR SHIP</h3>
                        <div className="info_data">
                            <div className="data">
                                Are you sure you want to delete this ship?
                            </div>
                            <div className="buttons">
                                <Link to="/" onClick={() => setModalIsOpen(false)}>
                                    <button className="cancel" >
                                        Cancel
                                    </button>
                                </Link>
                                <Link to="/" onClick={() => deleteShip()}>
                                    <button className="delete" >
                                        Delete
                                    </button>
                                </Link>
                            </div>
                        </div>
                        </div>
                    </div>
                </div>
            </Modal>
        </div>
   )
    
}

export default DeleteShipForm;