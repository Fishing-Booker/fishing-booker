import React, { useEffect, useState } from 'react'
import axios from 'axios';

const ReservationReport = () => {
    const SERVER_URL = process.env.REACT_APP_API; 
    const [reservations, setReservations] = useState([]);
    const [profit, setProfit] = useState(0);

    useEffect(() => {
        const headers = {'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.jwtToken}`}
        axios.get(SERVER_URL + "/reservations/getReservationReportAdmin", { headers: headers })
            .then(response => {
                setReservations(response.data);
                calculateProfit(response.data);
            })
    }, [])

    const calculateProfit = (reservs) => {
        var sum = 0;
        reservs.forEach(element => {
            sum += element.price/10;
        });
        setProfit(sum);
        sum = 0;
    }

    const reservationList = reservations.length ? (
        reservations.map((report, index) => {
            return (
                <div className="col" key={index}>
                    <div className="card res-actions-div">
                        <div className="info"> <br></br>
                            <p className="entity-info name">#{index+1} Reservation</p>
                            <p style={{color: 'black', fontSize: '17px', marginLeft: '50px', marginTop: '20px'}}>Reservated entity: {report.entityName}</p>
                            <div style={{borderBottom: '2px solid cadetblue', padding: '5px', width: '47vw', marginLeft: '15px'}}></div>
                            <p style={{color: 'black', fontWeight: '700', fontSize: '15px', marginLeft: '55px', marginTop: '15px'}}>Booked by: {report.userName} {report.userSurname}</p>
                            <p style={{color: 'black', fontSize: '17px', marginLeft: '50px', marginTop: '20px'}}>{report.text} </p>
                            <p style={{color: 'red', fontWeight: '700', fontSize: '15px', marginLeft: '55px', marginTop: '15px'}}>{report.price} $</p>
                        </div>
                    </div>
                </div>
            )
        })
    ) : (<div><p style={{marginLeft: '30px'}}>You don't have any reservation reports.</p></div>)

    return (
        <div>
            <div className="card search">
                <button style={{fontSize: '20px'}} >Reservations profit:</button>
                <button style={{fontSize: '20px'}} >&nbsp; &nbsp; {profit} $</button>
            </div>
            {reservationList}
        </div>
    )
}
export default ReservationReport;