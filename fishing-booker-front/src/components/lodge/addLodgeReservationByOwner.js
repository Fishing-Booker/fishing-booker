import React, { useEffect, useState } from 'react'
import { Link} from "react-router-dom";
import '../../css/addingForm.css'
import Modal from 'react-modal';
import axios from 'axios';
import { useToasts } from "react-toast-notifications";
import { DateTimePickerComponent} from "@syncfusion/ej2-react-calendars"
import { format } from 'date-fns';
import MakeLodgeReservation from './makeLodgeReservation';

const AddLodgeReservationByOwner = ({modalIsOpen, setModalIsOpen}) => {

    const SERVER_URL = process.env.REACT_APP_API; 

    const [startDate, setStartDate] = useState("");
    const [endDate, setEndDate] = useState("");
    const [clientUsername, setClientUsername] = useState("");
    const [lodgeNames, setLodgeNames] = useState([]);
    const [lodges, setLodges] = useState([]);
    const [entityId, setEntityId] = useState("");
    const [user, setUser] = useState("");

    const [lodgeName, setLodgeName] = useState("");

    const [allowSearch, setAllowSearch] = useState(false);

    const [periods, setPeriods] = useState([]);

    const [showPeriods, setShowPeriods] = useState(false);

    const [makeReservationForm, setMakeReservationForm] = useState(false);

    const [maxPersons, setMaxPersons] = useState("");

    const { addToast } = useToasts();

    useEffect(() => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
            .then(response => {
                setUser(response.data);
                var owner = response.data;
                console.log(owner);

                axios.get(SERVER_URL + "/lodges/ownerLodges/" + user.id, {headers: headers})
                    .then(response => {
                        setLodges(response.data);
                        var lodges = response.data;
                        setLodgeName(lodges[0].name);

                        var lodgeNames = [];
                        for(let lodge of lodges){
                            lodgeNames.push(lodge.name);
                        }
                        setLodgeNames(lodgeNames);
                    })
            })
    }, [modalIsOpen])


    const getClient = (name) => {
        setLodgeName(name);

        for(let lodge of lodges){
            if(lodge.name == name){
                setEntityId(lodge.id);
                setMaxPersons(lodge.maxPersons);
            }
        }

        axios.get(SERVER_URL + "/reservations/getClientUsername/" + name + "/" + user.id)
            .then(response => {
                setClientUsername(response.data);
                if(response.data != ""){
                    setAllowSearch(true);
                } else {
                    setAllowSearch(false);
                    addToast("There isn't any active reservation for this entity.", { appearance: "error" });
                }
            })
    }

    const getFreePeriods = () => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + "/periods/freePeriods/" + user.id + "/" + lodgeName, {headers: headers})
            .then(response => {
                var periods = (response.data);
                setPeriods(periods);
                setShowPeriods(true);
            })
    }

    const makeReservation = (start, end) => {
        setStartDate(start);
        setEndDate(end);
        setMakeReservationForm(true);
        setModalIsOpen(false);
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
                <div id="addLodgeReservation" className="adding-wrapper">
                    <div className="right">
                        <div className="info">
                            <h3>ADD NEW RESERVATION</h3>
                            <div className="info_data">
                                <div className="data">
                                    <h4>Lodge:</h4>
                                    <select onChange={(e) => getClient(e.target.value)} value={lodgeName}>
                                        {lodgeNames.map((name) => (
                                            <option className="card-image" key={name}>
                                                {name}
                                            </option>
                                        ))}
                                    </select>
                                </div>
                                
                                <button disabled={!allowSearch} onClick={() => getFreePeriods()}>
                                    Search periods
                                </button>

                                {showPeriods && 
                                    <div>
                                        <br/>
                                        {freePeriods}
                                    </div>
                                }

                            </div> <br/> <br/>
                        </div>
                    </div>
                </div>
            </Modal>
            
            <MakeLodgeReservation modalIsOpen={modalIsOpen} setModalIsOpen={modalIsOpen} startOfPeriod={startDate} endOfPeriod={endDate} maxGuests={maxPersons} clientUsername={clientUsername} entityOfId={entityId} />
        </div>
   )
    
}

export default AddLodgeReservationByOwner;