import React from 'react'
import '../../css/addingForm.css'
import Modal from 'react-modal';

const ShipActionInfo = ({modalIsOpen, setModalIsOpen, action}) => {

   return (
       <div>
            <Modal className="fullscreen" isOpen={modalIsOpen}
            shouldCloseOnEsc={true}
            onRequestClose={() => setModalIsOpen(false)} >
            <div id="actionInfo" className="adding-wrapper">
                <div className="right">
                    <div className="info">
                        <h3>ACTION INFO</h3>
                        <div className="info_data">
                            <div className="client-data">
                                <h4>Booked by:</h4>
                                {action.bookedBy}
                            </div>
                            <div className="client-data">
                                <h4>Start date:</h4>
                                {action.startDate}
                            </div>
                            <div className="client-data">
                                <h4>End date:</h4>
                                {action.endDate}
                            </div>
                            <div className="client-data">
                                <h4>Price:</h4>
                                {action.price}
                            </div>
                            <div className="client-data">
                                <h4>Max persons:</h4>
                                {action.maxPersons}
                            </div>
                            <div className="client-data">
                                <h4>Additional services:</h4>
                                {action.additionalServices}
                            </div>
                            <button className="client-btn" onClick={() => setModalIsOpen(false)}>
                                Done
                            </button>
                        </div> <br/> <br/>
                    </div>
                </div>
            </div>
            </Modal>
        </div>
   )
    
}

export default ShipActionInfo;