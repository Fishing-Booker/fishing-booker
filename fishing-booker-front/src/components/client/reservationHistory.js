import axios from "axios";
import { useState, useEffect } from "react";
import { format } from "date-fns";
import Modal from "react-modal";
import ReservationRating from "./modals/reservationRating";
import ReservationReport from "./modals/reservationReport";

const ReservationHistory = () => {
    const [reservations, setReservations] = useState([]);
    const SERVER_URL = process.env.REACT_APP_API;
    const [modalIsOpen, setModalIsOpen] = useState(false)
    const [modalReportIsOpen, setModalReportIsOpen] = useState(false)
    const [entityId, setEntityId] = useState('')

    useEffect(() => {
        const headers = {'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.jwtToken}`}
        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
            .then(response => {
                axios.get(SERVER_URL + "/reservations/" + response.data.id)
                    .then(res => setReservations(res.data))
            })
    }, [])

    const allReservations = reservations.length ? (
        reservations.map((reservation, index) => {
            return (
                <div className="col" key={index}>
                    <div className="card res-actions-div">
                        <div className="info"> <br></br>
                            <p className="entity-info name">Reservation #{index}: {reservation.entityName}</p>
                            <a className="subscribe-link" onClick={() => {setModalIsOpen(true); setEntityId(reservation.entityId)}}>leave a comment</a>
                            <div style={{borderBottom: '2px solid cadetblue', padding: '5px', width: '47vw', marginLeft: '15px'}}></div>
                            <p style={{color: 'black', fontSize: '17px', marginLeft: '50px', marginTop: '20px'}}>Reservation period:</p>
                            <p style={{color: 'black', fontWeight: '700', fontSize: '15px', marginLeft: '55px', marginTop: '15px'}}> {format(reservation.startDate, 'dd.MM.yyyy')} - {format(reservation.endDate, 'dd.MM.yyyy.')}</p>
                            <a className="reservation-link" onClick={() => setModalReportIsOpen(true)}>report</a>
                        </div>
                    </div>
                </div>
            )
        })
    ) : (<div><p style={{marginLeft: '30px'}}>You don't have any reservations.</p></div>)


    return (
        <div>
            <div style={{top:'0',
            marginTop: '170px',
            marginLeft: '50px',
            position: 'absolute'}}>
            <h1 className="title-reservation">Reservation history</h1> <br></br>
            {allReservations}
            <ReservationRating modalIsOpen={modalIsOpen} setModalIsOpen={setModalIsOpen} entityId={entityId} />
            <ReservationReport modalIsOpen={modalReportIsOpen} setModalIsOpen={setModalReportIsOpen} />
            </div>
        </div>
    )
}

export default ReservationHistory;