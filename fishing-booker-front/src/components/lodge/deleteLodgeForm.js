import React from 'react'
import { Link, BrowserRouter as Router, Route, Switch } from "react-router-dom";
import { withRouter } from 'react-router-dom';
import '../../css/addingForm.css'
import Modal from 'react-modal';

const DeleteLogdeForm = ({modalIsOpen, setModalIsOpen}) => {

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
                                <Link to="/" onClick={() => setModalIsOpen(false)}>
                                    <button className="cancel" >
                                        Cancel
                                    </button>
                                </Link>
                                <Link to="/" onClick={() => setModalIsOpen(false)}>
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

export default DeleteLogdeForm;