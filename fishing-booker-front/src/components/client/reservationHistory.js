import axios from "axios";
import { useState, useEffect } from "react";
import { format } from "date-fns";

const ReservationHistory = () => {
    const [reservations, setReservations] = useState([]);
    const SERVER_URL = process.env.REACT_APP_API;

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
                            <a className="subscribe-link">leave a comment</a>
                            <div style={{borderBottom: '2px solid cadetblue', padding: '5px', width: '47vw', marginLeft: '15px'}}></div>
                            <p style={{color: 'black', fontSize: '17px', marginLeft: '50px', marginTop: '20px'}}>Reservation period:</p>
                            <p style={{color: 'black', fontWeight: '700', fontSize: '15px', marginLeft: '55px', marginTop: '15px'}}> {format(reservation.startDate, 'dd.MM.yyyy')} - {format(reservation.endDate, 'dd.MM.yyyy.')}</p>
                        </div>
                    </div>
                </div>
            )
        })
    ) : (<div><p style={{marginLeft: '30px'}}>There are no available actions.</p></div>)


    return (
        <div>
            <div style={{top:'0',
            marginTop: '170px',
            marginLeft: '50px',
            position: 'absolute'}}>
            <h1 className="title-reservation">Reservation actions</h1> <br></br>
            </div>
            {allReservations}
        </div>
    )
}

export default ReservationHistory;