import React, { useEffect, useState } from 'react'
import { Link} from "react-router-dom";
import '../../css/addingForm.css'
import Modal from 'react-modal';
import axios from 'axios';
import { useToasts } from "react-toast-notifications";
import { DateTimePickerComponent} from "@syncfusion/ej2-react-calendars"

const AddLodgeReservationByOwner = ({modalIsOpen, setModalIsOpen}) => {

    const SERVER_URL = process.env.REACT_APP_API; 

    const [startDate, setStartDate] = useState("");
    const [endDate, setEndDate] = useState("");
    const [clientUsername, setClientUsername] = useState("");
    const [lodgeNames, setLodgeNames] = useState([]);

    const [user, setUser] = useState("");

    const [lodgeName, setLodgeName] = useState("");

    const [allowAdding, setAllowAdding] = useState(false);

    const [dates, setDates] = useState([]);

    const { addToast } = useToasts();

    useEffect(() => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
            .then(response => {
                setUser(response.data);
                var owner = response.data;

                axios.get(SERVER_URL + "/lodges/lodgeNames/" + owner.id, {headers: headers})
                    .then(response => {
                        setLodgeNames(response.data);
                        var names = response.data;
                        getClient(names[0])
                        var entityId = 7;
                        axios.get(SERVER_URL + "/periods/freePeriods/" + entityId, {headers: headers})
                        .then(response => {
                            console.log(response.data);
                            setDates(response.data);
                        });               
                    })
            })
    }, [modalIsOpen])

    const newReservation = {
        owner: user.id,
        entityName: lodgeName,
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
        setLodgeName(name);
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

    return (
       <div>
            <Modal className="fullscreen" isOpen={modalIsOpen}
            shouldCloseOnEsc={true}
            onRequestClose={() => setModalIsOpen(false)} >
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
                                <div className="data">
                                    <h4>Client:</h4>
                                    <input type="text" disabled value={clientUsername}/>
                                </div>
                                <div className="data">
                                    <h4>Reservation start:</h4>
                                    <input type="datetime-local" required onChange={(e) => {setStartDate(e.target.value)}}  value={startDate}/>
                                </div>
                                <div className="data">
                                    <h4>Reservation end:</h4>
                                    <input type="datetime-local" required onChange={(e) => {setEndDate(e.target.value)}}  value={endDate}/>
                                </div>
                                <button disabled={!allowAdding} onClick={() => addReservation()}>
                                    Add
                                </button>
                            </div> <br/> <br/>
                        </div>
                    </div>
                </div>
            </Modal>
        </div>
   )
    
}

export default AddLodgeReservationByOwner;