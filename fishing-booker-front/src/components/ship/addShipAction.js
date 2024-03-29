import React, { useEffect, useState } from 'react'
import { Link} from "react-router-dom";
import '../../css/addingForm.css'
import Modal from 'react-modal';
import axios from 'axios';
import { useToasts } from "react-toast-notifications";
import { DateTimePickerComponent} from "@syncfusion/ej2-react-calendars"
import { format } from 'date-fns';
import MakeShipReservation from './makeShipReservation';
import MakeShipAction from './makeShipAction';

const AddShipAction = ({modalIsOpen, setModalIsOpen, entityId}) => {

    const SERVER_URL = process.env.REACT_APP_API; 

    const [startDate, setStartDate] = useState("");
    const [endDate, setEndDate] = useState("");
    const [ships, setShips] = useState([]);
    const [user, setUser] = useState("");

    const [shipName, setShipName] = useState("");
    const [ownerService, setOwnerService] = useState(false);

    const [periods, setPeriods] = useState([]);

    const [showPeriods, setShowPeriods] = useState(false);

    const [makeActionForm, setMakeActionForm] = useState(false);

    const [maxPersons, setMaxPersons] = useState("");

    const [disabled, setDisabled] = useState(false);
    

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
                        for(let ship of ships){
                            if(ship.id == entityId){
                                setShipName(ship.name);
                                setMaxPersons(ship.maxPersons);
                            }
                        }
                    })
            })
    }, [modalIsOpen])

    const cancel = () => {
        setDisabled(false);
        setModalIsOpen(false);
        window.location.reload();
    }


    const getFreePeriods = () => {
        setDisabled(true);

        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        if(ownerService){
            axios.get(SERVER_URL + "/periods/freeOwnerAndShipPeriods/" + user.id + "/" + shipName, {headers: headers})
                .then(response => {
                    var periods = (response.data);
                    setPeriods(periods);
                    setShowPeriods(true);
                })
        } else {
            axios.get(SERVER_URL + "/periods/freePeriods/" + user.id + "/" + shipName, {headers: headers})
                .then(response => {
                    var periods = (response.data);
                    setPeriods(periods);
                    setShowPeriods(true);
                })
        } 
    }

    const makeAction = (start, end) => {
        setStartDate(start);
        setEndDate(end);
        setMakeActionForm(true);
        setModalIsOpen(false);
    }

    const freePeriods = periods.length ? (
        periods.map((period, index) => {
            return(
                <div key={index} className="period-card-reservation">
                    <p style={{color: 'black', fontSize: '17px', marginLeft: '50px', marginTop: '15px'}}>Available reservation in a period:</p>
                    <p style={{color: 'black', fontWeight: '600', fontSize: '15px', marginLeft: '55px', marginTop: '15px'}}> {format(period.startDate, 'dd.MM.yyyy')} - {format(period.endDate, 'dd.MM.yyyy.')}</p>
                    <a className="reservation-link" onClick={() => makeAction(period.startDate, period.endDate)}>make action</a>
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
                            <h3>ADD NEW ACTION</h3>
                            <div className="info_data">
                                <div className="data">
                                    <div style={{'font-size': '15px', display: 'flex', width: '200%'}}>
                                        <input disabled={disabled} style={{transform: 'scale(0.5)', 'margin-left': '-90%'}}  type="checkbox" checked={ownerService} id="owner" name="card" onChange={(e) => setOwnerService(!ownerService)} value="ownerService" />
                                        <label style={{width: '100%', 'margin-left': '-90%', 'margin-top': '2%'}} htmlFor="ownerService" aria-label="ownerService"> Use owner service (as a captain)</label>
                                    </div><br/>
                                    <h4>Ship:</h4>
                                    <input type="text" disabled value={shipName}/>
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
            
            <MakeShipAction modalIsOpen={makeActionForm} setModalIsOpen={setMakeActionForm} startOfPeriod={startDate} endOfPeriod={endDate} maxGuests={maxPersons} entityOfId={entityId} isOwnerInvolved={ownerService}/>
        </div>
   )
    
}

export default AddShipAction;