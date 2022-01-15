import React, { useEffect, useState } from 'react'
import { Link} from "react-router-dom";
import '../../css/addingForm.css'
import Modal from 'react-modal';
import axios from 'axios';
import { useToasts } from "react-toast-notifications";
import { format } from "date-fns";
import ReservationForm from './reservationForm';

const AddAventureReservation = ({modalIsOpen, setModalIsOpen}) => {
    const SERVER_URL = process.env.REACT_APP_API; 

    const [user, setUser] = useState("");
    const [startDate, setStartDate] = useState("");
    const [endDate, setEndDate] = useState("");
    const [maxPersons, setMaxPersons] = useState(0);

    const [entityId, setEntityId] = useState(0);
    const [clientAdventure, setClientAdventure] = useState("");

    const [availablePeriods, setAvailablePeriods] = useState([])
    const [activeClients, setActiveClients] = useState([]);
    const [choosenClient, setChoosenClient] = useState("");
    const [modalIsOpen2, setModalIsOpen2] = useState(false);
    const [start, setStart] = useState("");
    const [end, setEnd] = useState("")

    const [sDate, setSDate] = useState("");
    const [eDate, setEDate] = useState("");

    useEffect(() => {
        const headers = {'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.jwtToken}`}
        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
            .then(response => {
                setUser(response.data);
                var user = response.data;
                axios.get(SERVER_URL + "/reservations/getClientsOfActiveReservations/" + user.id, { headers: headers })
                .then(res => {
                    setActiveClients(res.data);
                })
            })
            generateDate();
        
    }, [])



    const seeAvailableReservations = (startDate, endDate) => {
        var periodDTO = { entityId: entityId, startDate, endDate }

        axios.post(SERVER_URL + "/ownerPeriods/availablePeriods", periodDTO)
            .then(response => setAvailablePeriods(response.data))
    }

    const choose = (e) => {
        var clientName = e.target.value;
        setChoosenClient(clientName);
        if(clientName === "") {
            setClientAdventure("");
            setChoosenClient("");
            return;
        }
        const headers = {'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.jwtToken}`}
        axios.get(SERVER_URL + "/reservations/getEntityNameOfClientActiveReservation/" + user.id + "/" + clientName, { headers: headers })
                .then(res => {
                    setClientAdventure(res.data.name);
                    setEntityId(res.data.id)
                    setMaxPersons(res.data.maxPersons)
                })

    }

    const generateDate = () => {
        var today = new Date();
        var day = today.getDate();
        var month = today.getMonth()+1;
        var year = today.getFullYear();
        var hours = today.getHours();
        var minutes = today.getMinutes();
        if(getlength(day) === 1) {
            day = "0"+day;
        }
        if(getlength(month) === 1) {
            month = "0"+month;
        }
        if(getlength(hours) === 1) {
            hours = "0"+hours;
        }
        if(getlength(minutes) === 1) {
            minutes = "0"+minutes;
        }
        

        setSDate(year + '-' + month + '-' + day + 'T' + hours + ':' + minutes);
    }

    const getlength = (number) => {
        return number.toString().length;
    }

    const generateStartDate = (e) => {
        console.log(e.target.value);
        setStartDate(e.target.value);
        generateEndDate(e.target.value);
    }

    const generateEndDate = (today) => {
        setEDate(today);
    }

    const periods =  availablePeriods.length ? (
        availablePeriods.map((period, index) => {
            return (
                <div key={index} className="period-card">
                    <p style={{color: 'black', fontSize: '17px', marginLeft: '50px', marginTop: '15px'}}>Available reservation in a period:</p>
                    <p style={{color: 'black', fontWeight: '600', fontSize: '15px', marginLeft: '55px', marginTop: '15px'}}> {format(period.startDate, 'dd.MM.yyyy')} - {format(period.endDate, 'dd.MM.yyyy.')}</p>
                    <a className="reservation-link" onClick={() => {setStart(period.startDate); setEnd(period.endDate); setModalIsOpen2(true);}}>make reservation</a>
                </div>
            )
        })
    ) : (
        <div><p style={{color: 'black', fontSize: '17px', marginLeft: '50px', marginTop: '15px'}}>No available reservations for choosen period.</p></div>
    )

    return (
        <div>
             <Modal className="fullscreen" isOpen={modalIsOpen}
             shouldCloseOnEsc={true}
             onRequestClose={() => setModalIsOpen(false)} >
                 <div className="card-res entity-details">
                    <label className="reservation-client">Choose client:</label>
                    <select value={choosenClient} onChange={(e) => choose(e)} className="reservation-date">
                        <option></option>
                        {activeClients.map((client, index) => {
                            return <option key={index}> {client.name} </option>
                        })}
                    </select> <br />
                    <label className="reservation-client">Client adventure:</label> 
                    <label className="reservation-client"> {clientAdventure}</label> <br />
                    <input className="reservation-date" type="datetime-local" min={sDate} value={startDate} onChange={(e) => generateStartDate(e)}></input>
                    <input className="reservation-date" type="datetime-local" min={eDate} value={endDate} onChange={(e) => setEndDate(e.target.value)}></input>
                    {entityId!==0 && startDate!=="" && endDate !== "" && <a className="available-dates" onClick={() => seeAvailableReservations(startDate, endDate)}>See available dates</a>}
                    {periods}
                 </div>
             </Modal>
             <ReservationForm modalIsOpen={modalIsOpen2} setModalIsOpen={setModalIsOpen2} startOfPeriod={start} endOfPeriod={end} maxGuests={maxPersons} entityOfId={entityId}/>
         </div>
    )
}
export default AddAventureReservation;