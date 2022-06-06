import axios from "axios";
import { useState, useEffect } from "react";
import { format } from "date-fns";
import moment from "moment";
import { useToasts } from "react-toast-notifications";

const Reservation = () => {
    const [reservations, setReservations] = useState([]);
    const SERVER_URL = process.env.REACT_APP_API;
    const { addToast } = useToasts();
    var today = new Date();
	const startDate = (moment(today.getFullYear() + '-' + (today.getMonth() + 1) + '-' + today.getDate()).format('YYYY-MM-DD'))
    const [isCanceled, setIsCanceled] = useState(false)

    useEffect(() => {
        const headers = {'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.jwtToken}`}
        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
            .then(response => {
                var dto = { date: startDate, clientId: response.data.id }
                axios.post(SERVER_URL + "/reservations/currentReservations", dto, { headers: headers })
                    .then(res => setReservations(res.data))
            })
    }, [isCanceled])

    const handleCanceling = (start, id) => {
        const headers = {'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.jwtToken}`}
        var differenceInTime = new Date(start) - today;
        var differenceInDays = differenceInTime / (1000 * 3600 * 24)
        if (differenceInDays < 4) {
            addToast("It's not possible to cancel the reservation 3 days before its start!", { appearance: "error" })
        } else {
            axios.delete(SERVER_URL + "/reservations/cancel/" + id, { headers: headers })
                .then(setIsCanceled(!isCanceled))
        }
    }

    const allReservations = reservations.length ? (
        reservations.map((reservation, index) => {
            return (
                <div className="col" key={index}>
                    <div className="card res-actions-div">
                        <div className="info"> <br></br>
                            <p className="entity-info name">Reservation #{index}: {reservation.entityName}</p>
                            <a className="subscribe-link" onClick={() => handleCanceling(reservation.startDate, reservation.reservationId)}>cancel reservation</a>
                            <div style={{borderBottom: '2px solid cadetblue', padding: '5px', width: '47vw', marginLeft: '15px'}}></div>
                            <p style={{color: 'black', fontSize: '17px', marginLeft: '50px', marginTop: '20px'}}>Reservation period:</p>
                            <p style={{color: 'black', fontWeight: '700', fontSize: '15px', marginLeft: '55px', marginTop: '15px'}}> {format(reservation.startDate, 'dd.MM.yyyy')} - {format(reservation.endDate, 'dd.MM.yyyy.')}</p>
                            <p className="entity-info description reserv" style={{fontSize: '15px', fontWeight:'600'}}>Price: {reservation.price}$</p>
                            <p className="entity-info description reserv">Regular services: {reservation.regularService}</p>
                            <p className="entity-info description reserv">Additional services: {reservation.additionalService}</p>
                        </div>
                    </div>
                </div>
            )
        })
    ) : (<div><p style={{marginLeft: '30px'}}>You don't have any active reservations.</p></div>)


    return (
        <div>
            <div style={{top:'0',
            marginTop: '170px',
            marginLeft: '50px',
            position: 'absolute'}}>
            <h1 className="title-reservation">Active reservations</h1> <br></br>
            {allReservations}
            </div>
        </div>
    )
}

export default Reservation;