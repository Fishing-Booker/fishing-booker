import React from 'react'
import { Link } from "react-router-dom";
import '../../css/addingForm.css'
import Modal from 'react-modal';
import axios from "axios";

const DeleteAdventure = ({modalIsOpen, setModalIsOpen, adventureId}) => {

    const SERVER_URL = process.env.REACT_APP_API; 

    const deleteAdventure = () => {
        const headers = {'Content-Type' : 'application/json',
                     'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.delete(SERVER_URL + '/adventures/deleteAdventure/' + adventureId, { headers: headers})    
        .then(response => {setModalIsOpen(false); window.location.reload();});
    }

   return (
       <div>
            <Modal className="fullscreen" isOpen={modalIsOpen}
            shouldCloseOnEsc={true}
            onRequestClose={() => setModalIsOpen(false)}
            ariaHideApp={false}>
                <div id="deleteLodge" className="deleting-wrapper">
                    <div className="right">
                        <div className="info">
                        <h3>DELETE YOUR ADVENTURE</h3>
                        <div className="info_data">
                            <div className="data">
                                Are you sure you want to delete this adventure?
                            </div>
                            <div className="buttons">

                                <Link to="/" onClick={() => setModalIsOpen(false)}>
                                    <button className="cancel" >
                                        Cancel
                                    </button>
                                </Link>
                                <Link to="/" onClick={() => deleteAdventure()}>
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

export default DeleteAdventure;