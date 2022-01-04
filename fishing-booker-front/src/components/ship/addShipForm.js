import React, { useEffect, useState } from 'react'
import { Link} from "react-router-dom";
import '../../css/addingForm.css';
import Modal from 'react-modal';
import axios from 'axios';
import { useToasts } from 'react-toast-notifications';

const AddShipForm = ({modalIsOpen, setModalIsOpen}) => {

    const SERVER_URL = process.env.REACT_APP_API; 

    const [user, setUser] = useState({});

    const [name, setName] = useState("");
    const [address, setAddress] = useState("");
    const [city, setCity] = useState("");
    const [country, setCountry] = useState("");
    const [description, setDescription] = useState("");
    const [shipType, setShipType] = useState("");
    const [length, setLength] = useState("");
    const [engineNumber, setEngineNumber] = useState("");
    const [enginePower, setEnginePower] = useState("");
    const [maxSpeed, setMaxSpeed] = useState("");
    const [capacity, setCapacity] = useState("");

    useEffect(() => {
        const headers = {'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.jwtToken}`}
        
        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
            .then(response => setUser(response.data))
    }, [])

    const newShip = {
        owner: user.id,
        name, 
        address, 
        city, 
        country, 
        description,
        shipType,
        length,
        engineNumber,
        enginePower,
        maxSpeed,
        capacity
    }

    const addShip = () => {
        console.log(newShip);
        axios.post(SERVER_URL + "/ships/addShip", newShip)
          .then(response => {
            setModalIsOpen(false);
            window.location.reload();
        });
        
    }

   return (
        <div >
            <Modal className="fullscreen" isOpen={modalIsOpen}
            shouldCloseOnEsc={true}
            onRequestClose={() => setModalIsOpen(false)}>
                <div id="addShip" className="adding-wrapper">
                    <div className="right">
                        <div className="info">
                            <h3>ADD YOUR SHIP</h3>
                            <div className="info_data">
                                <div className="data">
                                    <h4>Name:</h4>
                                    <input type="text" required onChange={(e) => {setName(e.target.value)}}  value={name}/>
                                </div>
                                <div className="data">
                                    <h4>Address:</h4>
                                    <input type="text" required onChange={(e) => {setAddress(e.target.value)}} value={address}/>
                                </div>
                                <div className="data" >
                                    <h4>City:</h4>
                                    <input type="text" required onChange={(e) => {setCity(e.target.value)}} value={city}/>
                                </div>
                                <div className="data">
                                    <h4>Country:</h4>
                                    <input type="text" required onChange={(e) => {setCountry(e.target.value)}} value={country}/>
                                </div>
                                <div className="data">
                                    <h4>Description:</h4>
                                    <textarea type="text" required onChange={(e) => {setDescription(e.target.value)}} value={description}/>
                                </div>
                                <div className="data">
                                    <h4>Ship type:</h4>
                                    <input type="text" required onChange={(e) => {setShipType(e.target.value)}} value={shipType}/>
                                </div>
                                <div className="data">
                                    <h4>Lenght:</h4>
                                    <input type="number" required onChange={(e) => {setLength(e.target.value)}} value={length}/>
                                </div>
                                <div className="data">
                                    <h4>Number of engines:</h4>
                                    <input type="number" required onChange={(e) => {setEngineNumber(e.target.value)}} value={engineNumber}/>
                                </div>
                                <div className="data">
                                    <h4>Engine power:</h4>
                                    <input type="number" required onChange={(e) => {setEnginePower(e.target.value)}} value={enginePower}/>
                                </div>
                                <div className="data">
                                    <h4>Max speed:</h4>
                                    <input type="number" required onChange={(e) => {setMaxSpeed(e.target.value)}} value={maxSpeed}/>
                                </div>
                                <div className="data">
                                    <h4>Capacity:</h4>
                                    <input type="number" required onChange={(e) => {setCapacity(e.target.value)}} value={capacity}/>
                                </div>
                                <Link to="/" onClick={() => addShip()} >
                                    <button>
                                        Add
                                    </button>
                                </Link>
                            </div> <br/> <br/>
                        </div>
                    </div>
                </div>
            </Modal>
        </div>
   )
    
}

export default AddShipForm;