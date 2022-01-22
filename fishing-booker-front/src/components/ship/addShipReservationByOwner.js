import React, { useEffect, useState } from 'react'
import { Link} from "react-router-dom";
import '../../css/addingForm.css'
import Modal from 'react-modal';
import axios from 'axios';
import { useToasts } from "react-toast-notifications";
import { DateTimePickerComponent} from "@syncfusion/ej2-react-calendars"
import { format } from 'date-fns';
import MakeShipReservation from './makeShipReservation';

const AddShipReservationByOwner = ({modalIsOpen, setModalIsOpen}) => {

    const SERVER_URL = process.env.REACT_APP_API; 

    const [startDate, setStartDate] = useState("");
    const [endDate, setEndDate] = useState("");
    const [clientUsername, setClientUsername] = useState("");
    const [shipNames, setShipNames] = useState([]);
    const [ships, setShips] = useState([]);
    const [entityId, setEntityId] = useState("");
    const [user, setUser] = useState("");

    const [shipName, setShipName] = useState("");
    const [ownerService, setOwnerService] = useState(false);

    const [allowSearch, setAllowSearch] = useState(true);

    const [periods, setPeriods] = useState([]);

    const [showPeriods, setShowPeriods] = useState(false);

    const [makeReservationForm, setMakeReservationForm] = useState(false);

    const [maxPersons, setMaxPersons] = useState("");

    const [usernames, setUsernames] = useState([]);

    const { addToast } = useToasts();

    useEffect(() => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
            .then(response => {
                setUser(response.data);
                var owner = response.data;
                console.log(owner);

                axios.get(SERVER_URL + "/ships/ownerShips/" + user.id, {headers: headers})
                    .then(response => {
                        setShips(response.data);
                        var ships = response.data;

                        var shipNames = [];
                        for(let ship of ships){
                            shipNames.push(ship.name);
                        }
                        setShipNames(shipNames);

                        axios.get(SERVER_URL + "/reservations/getClientsOfActiveReservations/" + user.id, {headers: headers})
                        .then(response => {
                            var usernames = response.data;
                            var names = []
                            for(let name of usernames){
                                names.push(name.name);
                            }
                            setUsernames(names);

                        })

                    })
            })
    }, [modalIsOpen])


    const getFreePeriods = () => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        if(clientUsername === "" || shipName === ""){
            addToast("You have to choose client and entity for reservation!", { appearance: "error" });
        } else {

            if(ownerService){
                axios.get(SERVER_URL + "/periods/freeOwnerAndShipPeriods/" + user.id + "/" + shipName, {headers: headers})
                    .then(response => {
                        var periods = (response.data);
                        setAllowSearch(false);
                        setPeriods(periods);
                        setShowPeriods(true);
                    })
            } else {
                axios.get(SERVER_URL + "/periods/freePeriods/" + user.id + "/" + shipName, {headers: headers})
                    .then(response => {
                        var periods = (response.data);
                        setAllowSearch(false);
                        setPeriods(periods);
                        setShowPeriods(true);
                    })
            } 
        }
    }

    const makeReservation = (start, end) => {
        setStartDate(start);
        setEndDate(end);

        for(let s of ships){
            if(s.name == shipName){
                setEntityId(s.id);
                setMaxPersons(s.maxPersons);
            }
        }
        setMakeReservationForm(true);
        setModalIsOpen(false);
    }

    const cancel = () => {
        setModalIsOpen(false);
        window.location.reload();
    }

    const freePeriods = periods.length ? (
        periods.map((period, index) => {
            return(
                <div key={index} className="period-card-reservation">
                    <p style={{color: 'black', fontSize: '17px', marginLeft: '50px', marginTop: '15px'}}>Available reservation in a period:</p>
                    <p style={{color: 'black', fontWeight: '600', fontSize: '15px', marginLeft: '55px', marginTop: '15px'}}> {format(period.startDate, 'dd.MM.yyyy')} - {format(period.endDate, 'dd.MM.yyyy.')}</p>
                    <a className="reservation-link" onClick={() => makeReservation(period.startDate, period.endDate)}>make reservation</a>
                </div>
            ) 
        })
    ) : (
        <div>We don't have available periods for this entity.</div>
    )

    return (
       <div>
            <Modal className="fullscreen" isOpen={modalIsOpen}
            shouldCloseOnEsc={true}
            onRequestClose={() => setModalIsOpen(false)} 
            ariaHideApp={false}>
                <div id="addShipReservation" className="adding-wrapper">
                    <div className="right">
                        <div className="info">
                            <h3>ADD NEW RESERVATION</h3>
                            <div className="info_data">
                                <div className="data">
                                    <h4>Client:</h4>
                                    <select disabled={!allowSearch} onChange={(e) => setClientUsername(e.target.value)} value={clientUsername}>
                                        <option></option>
                                        {usernames.map((username) => (
                                            <option className="card-image" key={username}>
                                                {username}
                                            </option>
                                        ))}
                                    </select>
                                </div>
                                <div className="data">
                                    <div style={{'font-size': '15px', display: 'flex', width: '200%'}}>
                                        <input disabled={!allowSearch} style={{transform: 'scale(0.5)', 'margin-left': '-90%'}}  type="checkbox" checked={ownerService} id="owner" name="card" onChange={(e) => setOwnerService(!ownerService)} value="ownerService" />
                                        <label style={{width: '100%', 'margin-left': '-90%', 'margin-top': '2%'}} htmlFor="ownerService" aria-label="ownerService"> Use owner service (as a captain)</label>
                                    </div>
                                    <h4>Ship:</h4>
                                    <select disabled={!allowSearch} onChange={(e) => setShipName(e.target.value)} value={shipName}>
                                        <option></option>
                                        {shipNames.map((name) => (
                                            <option className="card-image" key={name}>
                                                {name}
                                            </option>
                                        ))}
                                    </select>
                                </div>
                                
                                <button onClick={() => getFreePeriods()}>
                                    Search periods
                                </button>

                                {showPeriods && 
                                    <div>
                                        <br/>
                                        {freePeriods}
                                        <button onClick={() => cancel()}>
                                            Cancel
                                        </button>
                                    </div>
                                }

                            </div> <br/> <br/>
                        </div>
                    </div>
                </div>
            </Modal>
            
            <MakeShipReservation modalIsOpen={makeReservationForm} setModalIsOpen={setMakeReservationForm} startOfPeriod={startDate} endOfPeriod={endDate} maxGuests={maxPersons} clientUsername={clientUsername} entityOfId={entityId} isOwnerInvolved={ownerService}/>
        </div>
   )
    
}

export default AddShipReservationByOwner;