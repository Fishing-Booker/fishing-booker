import React from 'react'
import { Link } from "react-router-dom";
import '../../css/addingForm.css'
import Modal from 'react-modal';
import axios from "axios";

const DeleteAction = ({modalIsOpen, setModalIsOpen, actionId, adventureId}) => {
    const SERVER_URL = process.env.REACT_APP_API; 

    const deleteLodge = () => {
        const headers = {'Content-Type' : 'application/json',
                     'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.delete(SERVER_URL + '/actions/deleteAction/' + actionId, { headers: headers})    
        .then(response => {
            setModalIsOpen(false); 
            window.location.reload();
        });
    }

    return (
    <div>
            <Modal className="fullscreen" isOpen={modalIsOpen}
            shouldCloseOnEsc={true}
            onRequestClose={() => setModalIsOpen(false)} >
                <div id="deleteLodge" className="deleting-wrapper">
                    <div className="right">
                        <div className="info">
                        <h3>DELETE SELECTED ACTION</h3>
                        <div className="info_data">
                            <div className="data">
                                Are you sure you want to delete this action?
                            </div>
                            <div className="buttons">
                                <Link to={"/adventureActions/" + adventureId} onClick={() => setModalIsOpen(false)}>
                                    <button className="cancel" >
                                        Cancel
                                    </button>
                                </Link>
                                <Link to={"/adventureActions/" + adventureId} onClick={() => deleteLodge()}>
                                    <button className="delete" >
                                        Delete
                                    </button>
                                </Link><br/><br/>
                            </div>
                        </div>
                        </div>
                    </div>
                </div>
            </Modal>
        </div>
   )
}

export default DeleteAction;