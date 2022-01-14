import Modal from 'react-modal';
import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import axios from 'axios';
import moment from "moment";
import { useToasts } from "react-toast-notifications";

Modal.setAppElement('#root')
const NewReservation = ({modalIsOpen, setModalIsOpen, startOfPeriod, endOfPeriod, maxGuests}) => {
    const SERVER_URL = process.env.REACT_APP_API; 
    const [startDate, setStartDate] = useState('')
    const [endDate, setEndDate] = useState('')
    const [numberOfGuests, setNumberOfGuests] = useState(0)
    const [clientId, setClientId] = useState(0)
    const { id } = useParams()
    const [entityId, setEntityId] = useState(id)
    const { addToast } = useToasts();

    useEffect(() => {
        const headers = {'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.jwtToken}`}
        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
            .then(response => setClientId(response.data.id))
    }, [])

    const dto = {
        clientId,
        entityId,
        startDate,
        endDate,
        numberOfGuests
    }

    const handleSubmit = () => {
        console.log(moment(startDate).format())
        console.log(moment(endDate).format())
        if (isInRangeOne(moment(startDate).format()) && isInRangeOne(moment(endDate).format()) && numberOfGuests <= maxGuests) {
            axios.post(SERVER_URL + "/reservations/makeReservation", dto)
                .then(response => {
                    addToast("Successfull reservation! You will get an email with additional information.", { appearance: "success" });
                    setTimeout(() => {
                        setModalIsOpen(false)
                        window.location.reload();
                      }, 3000)
                })
        } else if (numberOfGuests > maxGuests) {
            addToast("It is not possible to make a reservation for that number of guests! Please try again!", { appearance: "error" });
        } else {
            addToast("It is not possible to make a reservation in selected period! Please try again!", { appearance: "error" });
        }
        setStartDate('');
        setEndDate('');
    }

    const isDateInRage = (startDate, endDate) => (dateToCheck) => {
        return dateToCheck >= startDate && dateToCheck <= endDate
    }

    const isInRangeOne = isDateInRage(moment(startOfPeriod).format(), moment(endOfPeriod).format())

    return (
        <div >
            <Modal isOpen={modalIsOpen}
            shouldCloseOnEsc={true}
            onRequestClose={() => setModalIsOpen(false)} >
                <div className='make-res-modal' >
                    <h4 style={{marginLeft: '9%'}}>Please enter reservation details:</h4>
                    <div style={{borderBottom: '2px solid cadetblue', padding: '5px', width: '50vw', marginLeft: '38px'}}></div>
                    <br></br>
                    <p className='modal-info-res'>Start date:</p>
                    <input className="reservation-date modal" type="datetime-local" value={startDate} onChange={(e) => {setStartDate(e.target.value); } }></input>
                    <p className='modal-info-res'>End date:</p>
                    <input className="reservation-date modal" type="datetime-local" value={endDate} onChange={(e) => {setEndDate(e.target.value); }}></input> 
                    <p className='modal-info-res'>Number of guests:</p>
                    <input className="reservation-date modal" type="number" value={numberOfGuests} onChange={(e) => setNumberOfGuests(e.target.value)} ></input> 
                    <a  className="reservation-link" onClick={() => handleSubmit()}>Make reservation</a>
                    <p className='modal-info-res'>Additional services:</p>
                    <p className='modal-info-res'>Price:</p>
                </div>
            </Modal>
        </div>
    )
}

export default NewReservation;