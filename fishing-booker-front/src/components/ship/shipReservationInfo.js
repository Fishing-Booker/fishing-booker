import React from 'react'
import '../../css/addingForm.css'
import Modal from 'react-modal';

const ShipReservationInfo = ({modalIsOpen, setModalIsOpen, reservation}) => {

   return (
       <div>
            <Modal className="fullscreen" isOpen={modalIsOpen}
            shouldCloseOnEsc={true}
            onRequestClose={() => setModalIsOpen(false)} >
            <div id="actionInfo" className="adding-wrapper">
                <div className="right">
                    <div className="info">
                        <h3>RESERVATION INFO</h3>
                        <div className="info_data">
                            <div className="client-data">
                                <h4>Booked by:</h4>
                                {reservation.clientUsername}
                            </div>
                            <div className="client-data">
                                <h4>Reservation type:</h4>
                                {reservation.reservationType}
                            </div>
                            <div className="client-data">
                                <h4>Start date:</h4>
                                {reservation.startDate}
                            </div>
                            <div className="client-data">
                                <h4>End date:</h4>
                                {reservation.endDate}
                            </div>
                            <div className="client-data">
                                <h4>Price:</h4>
                                ${reservation.price}
                            </div>
                            <div className="client-data">
                                <h4>Max persons:</h4>
                                {reservation.maxPersons}
                            </div>
                            <div className="client-data">
                                <h4>Additional services:</h4>
                                {reservation.additionalService}
                            </div>
                            <div className="client-data">
                                <h4>Regular service:</h4>
                                {reservation.regularService}
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

export default ShipReservationInfo;