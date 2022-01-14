import Modal from "react-modal"
import { useState } from "react"

const ReservationReport = ({modalIsOpen, setModalIsOpen}) => {

    return (
        <div>
            <Modal isOpen={modalIsOpen}
            shouldCloseOnEsc={true}
            onRequestClose={() => setModalIsOpen(false)}>
                <div>
                    <h3 style={{marginLeft: '40px'}}>Write a report </h3>
                    <div style={{borderBottom: '2px solid cadetblue', padding: '5px', width: '400px', marginLeft: '38px'}}></div>
                    <br></br>
                    <textarea className="comment-area" placeholder="Write your report here"></textarea>
                    <a className="reservation-link" >submit</a>
                    <a className="cancel-link" >cancel</a>
                </div>
                
            </Modal>
        </div>
    )
}

export default ReservationReport;