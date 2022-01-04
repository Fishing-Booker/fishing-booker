import React from 'react'
import '../../css/addingForm.css'
import Modal from 'react-modal'
import { DateTimePickerComponent} from "@syncfusion/ej2-react-calendars"


const AddInstructorsAvailbalePeriod = ({modalIsOpen, setModalIsOpen}) => {
    return (
        <div>
            <Modal className="fullscreen" isOpen={modalIsOpen}
            shouldCloseOnEsc={true}
            onRequestClose={() => setModalIsOpen(false)}
            ariaHideApp={false} >
                <div id="addlodgeReservationPeriod" className="adding-wrapper">
                    <div className="right">
                        <div className="info">
                            <h3>DEFINE NEW RESERVATION PERIOD</h3>
                            <div className="info_data">
                                <div className="data2">
                                    <h4>Period start:</h4>
                                    <DateTimePickerComponent/>
                                </div>
                                <div className="data2">
                                    <h4>Period end:</h4>
                                    <DateTimePickerComponent/>
                                </div>
                                <button className="reservation-period-btn" onClick={() => setModalIsOpen(false)}>
                                    Add
                                </button>
                            </div> <br/> <br/>
                        </div>
                    </div> <br/> <br/>
                </div>
            </Modal>
        </div>
    )
}
export default AddInstructorsAvailbalePeriod;