import React from 'react'
import { Link, BrowserRouter as Router, Route, Switch } from "react-router-dom";
import { withRouter } from 'react-router-dom';
import '../../css/addingForm.css'
import Modal from 'react-modal'

const AddLodgeFrom = ({modalIsOpen, setModalIsOpen}) => {

   return (
        <div >
            <Modal className="fullscreen" isOpen={modalIsOpen}
            shouldCloseOnEsc={true}
            onRequestClose={() => setModalIsOpen(false)}>
                <div id="addLodge" className="adding-wrapper">
                    <div className="right">
                        <div className="info">
                            <h3>ADD YOUR LODGE</h3>
                            <div className="info_data">
                                <div className="data">
                                    <h4>Name:</h4>
                                    <input type="text"/>
                                </div>
                                <div className="data">
                                    <h4>Address:</h4>
                                    <input type="text"/>
                                </div>
                                <div className="data">
                                    <h4>Bedrooms:</h4>
                                    <textarea type="text"/>
                                </div>
                                <div className="data">
                                    <h4>Description:</h4>
                                    <textarea type="text"/>
                                </div>
                                <Link to="/" onClick={() => setModalIsOpen(false)} >
                                    <button >
                                        Add
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

export default AddLodgeFrom;