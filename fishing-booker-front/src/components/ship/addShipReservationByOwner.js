import React, { useEffect, useState } from 'react'
import { Link} from "react-router-dom";
import '../../css/addingForm.css'
import Modal from 'react-modal';
import axios from 'axios';
import { useToasts } from "react-toast-notifications";
import { DateTimePickerComponent} from "@syncfusion/ej2-react-calendars"
import { format } from 'date-fns';

const AddShipReservationByOwner = ({modalIsOpen, setModalIsOpen}) => {

    const SERVER_URL = process.env.REACT_APP_API; 

    const [startDate, setStartDate] = useState("");
    const [endDate, setEndDate] = useState("");
    const [clientUsername, setClientUsername] = useState("");
    const [shipNames, setShipNames] = useState([]);

    const [user, setUser] = useState("");

    const [shipName, setShipName] = useState("");
    const [ownerService, setOwnerService] = useState(false);

    const [allowAdding, setAllowAdding] = useState(false);

    const [dates, setDates] = useState([]);

    const { addToast } = useToasts();

    useEffect(() => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
            .then(response => {
                setUser(response.data);
                var owner = response.data;
                console.log(owner);

                axios.get(SERVER_URL + "/ships/shipNames/" + user.id, {headers: headers})
                    .then(response => {
                        setShipNames(response.data);
                        var names = response.data;
          
                    })
            })
    }, [modalIsOpen])

    const newReservation = {
        owner: user.id,
        entityName: shipName,
        startDate,
        endDate,
        clientUsername
    }


    const addReservation = () => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        if(allowAdding){
            axios.post(SERVER_URL + "/reservations/addReservation", newReservation, {headers: headers})
            .then(response => {
                setModalIsOpen(false)
                window.location.reload();
            });
        } else {
            addToast("You cannot make reservation for this entity.", { appearance: "error" });
        }
    }

    const getClient = (name) => {
        setShipName(name);
        axios.get(SERVER_URL + "/reservations/getClientUsername/" + name + "/" + user.id)
            .then(response => {
                setClientUsername(response.data);
                if(response.data != ""){
                    setAllowAdding(true);
                } else {
                    setAllowAdding(false);
                    addToast("There isn't any active reservation for this entity.", { appearance: "error" });
                }
            })
    }

    const getFreePeriods = () => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        if(ownerService){
            axios.get(SERVER_URL + "/periods/freeOwnerAndShipPeriods/" + user.id + "/" + shipName, {headers: headers})
                .then(response => {
                    var periods = (response.data);
                    var allPeriods = [];
                    for(let p of periods){
                        p.startDate = format(p.startDate, 'dd.MM.yyyy. kk:mm');
                        p.endDate = format(p.endDate, 'dd.MM.yyyy. kk:mm');
                        allPeriods.push(p);
                    }
                    console.log(allPeriods);
                })
        } else {
            axios.get(SERVER_URL + "/periods/freePeriods/" + user.id + "/" + shipName, {headers: headers})
                .then(response => {
                    var periods = (response.data);
                    var allPeriods = [];
                    for(let p of periods){
                        p.startDate = format(p.startDate, 'dd.MM.yyyy. kk:mm');
                        p.endDate = format(p.endDate, 'dd.MM.yyyy. kk:mm');
                        allPeriods.push(p);
                    }
                    console.log(allPeriods);
                })
        }

        
    }

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
                                    <div className="data">
                                        <input type="checkbox" checked={ownerService} id="owner" name="card" onChange={(e) => setOwnerService(!ownerService)} value="ownerService" />
                                        <label htmlFor="ownerService" aria-label="ownerService"> Use owner service</label>
                                    </div>
                                    <h4>Ship:</h4>
                                    <select onChange={(e) => setShipName(e.target.value)} value={shipName}>
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
                            </div> <br/> <br/>
                        </div>
                    </div>
                </div>
            </Modal>
        </div>
   )
    
}

export default AddShipReservationByOwner;