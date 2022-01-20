import React, { useEffect, useState } from 'react'
import { Link} from "react-router-dom";
import '../../css/addingForm.css'
import Modal from 'react-modal';
import axios from 'axios';
import { useToasts } from "react-toast-notifications";
import { DateTimePickerComponent} from "@syncfusion/ej2-react-calendars"
import { format } from 'date-fns';
import MakeLodgeReservation from './makeLodgeReservation';
import MakeLodgeAction from './makeLodgeAction';

const AddLodgeActionForm = ({modalIsOpen, setModalIsOpen, entityId}) => {

    const SERVER_URL = process.env.REACT_APP_API; 

    const [startDate, setStartDate] = useState("");
    const [endDate, setEndDate] = useState("");
    const [lodges, setLodges] = useState([]);
    const [user, setUser] = useState("");

    const [lodgeName, setLodgeName] = useState("");

    const [periods, setPeriods] = useState([]);

    const [showPeriods, setShowPeriods] = useState(false);

    const [makeActionForm, setMakeActionForm] = useState(false);

    const [maxPersons, setMaxPersons] = useState("");

    const { addToast } = useToasts();

    useEffect(() => {

        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
            .then(response => {
                setUser(response.data);
                var owner = response.data;

                axios.get(SERVER_URL + "/lodges/ownerLodges/" + owner.id, {headers: headers})
                    .then(response => {
                        setLodges(response.data);
                        console.log(response.data);
                        var lodges = response.data;
                        for(let lodge of lodges){
                            if(lodge.id == entityId){
                                setLodgeName(lodge.name);
                                setMaxPersons(lodge.maxPersons);
                            }
                        }
                    })
            })
    }, [])

    const getFreePeriods = () => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + "/periods/freePeriods/" + user.id + "/" + lodgeName, {headers: headers})
            .then(response => {
                var periods = (response.data);
                setPeriods(periods);
                setShowPeriods(true);
            })
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
                <div id="addLodgeReservation" className="adding-wrapper">
                    <div className="right">
                        <div className="info">
                            <h3>ADD NEW RESERVATION</h3>
                            <div className="info_data">
                                <div className="data">
                                    <h4>Lodge:</h4>
                                    <input type="text" disabled value={lodgeName} />
                                </div>
                                
                                <button onClick={() => getFreePeriods()}>
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
            
            <MakeLodgeAction modalIsOpen={makeActionForm} setModalIsOpen={setMakeActionForm} startOfPeriod={startDate} endOfPeriod={endDate} maxGuests={maxPersons} entityOfId={entityId} />
        </div>
   )
    
}

export default AddLodgeActionForm;