import React, { useEffect, useState } from 'react'
import { Link} from "react-router-dom";
import '../../css/addingForm.css';
import Modal from 'react-modal';
import axios from 'axios';
import { useToasts } from 'react-toast-notifications';

const AddShipForm = ({modalIsOpen, setModalIsOpen}) => {

    const SERVER_URL = process.env.REACT_APP_API; 

    const { addToast } = useToasts();

    const [user, setUser] = useState({});

    const [name, setName] = useState("");
    const [description, setDescription] = useState("");
    const [shipType, setShipType] = useState("");
    const [length, setLength] = useState(0);
    const [engineNumber, setEngineNumber] = useState(0);
    const [enginePower, setEnginePower] = useState(0);
    const [maxSpeed, setMaxSpeed] = useState(0);
    const [capacity, setCapacity] = useState(0);

    const [shipNames, setShipNames] = useState("");

    useEffect(() => {
        const headers = {'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.jwtToken}`}
        
        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
            .then(response => {
                setUser(response.data);
            
                axios.get(SERVER_URL + "/ships/shipNames/" + user.id, {headers: headers})
                .then(response => {
                    setShipNames(response.data);
                })
            })
    }, [])

    const newShip = {
        owner: user.id,
        name, 
        description,
        shipType,
        length,
        engineNumber,
        enginePower,
        maxSpeed,
        capacity
    }

    const addShip = () => {
        if(newShip.name == ""){
            addToast("Field for ship name cannot be empty!", { appearance: "error" });
        } else if(!isNameValid(name)){
            addToast("You already have ship with this name.", { appearance: "error" });
        } else {
            const headers = {'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.jwtToken}`}

            axios.post(SERVER_URL + "/ships/addShip", newShip, {headers: headers})
            .then(response => {
                setModalIsOpen(false);
                window.location.reload();
            });
        }
        
    }

    const isNameValid = (name) => {
        for(let ship of shipNames){
            if(ship == name){
                return false;
            }
        }
        return true;
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
                                <div className="buttons">
                                    <button className="cancel" onClick={() => setModalIsOpen(false)}>
                                        Cancel
                                    </button>
                                    <button className="add" onClick={() => addShip()}>
                                        Add
                                    </button><br/>
                                </div>
                            </div> <br/> <br/>
                        </div>
                    </div>
                </div>
            </Modal>
        </div>
   )
    
}

export default AddShipForm;