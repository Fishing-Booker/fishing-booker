import React, { useEffect } from 'react'
import { useState } from 'react';
import { Link, useParams} from "react-router-dom";
import '../../css/usersProfile.css'
import axios from 'axios';
import { useToasts } from "react-toast-notifications";

const ShipProfile = () => {

    const {shipId} = useParams();

    const SERVER_URL = process.env.REACT_APP_API; 

    const { addToast } = useToasts();

    const [ship, setShip] = useState([]);

    const [name, setName] = useState("");
    const [locationId, setLocationId] = useState("");
    const [address, setAddress] = useState("");
    const [city, setCity] = useState("");
    const [country, setCountry] = useState("");
    const [description, setDescription] = useState("");
    const [shipType, setShipType] = useState("");
    const [length, setLength] = useState(0);
    const [engineNumber, setEngineNumber] = useState(0);
    const [enginePower, setEnginePower] = useState(0);
    const [maxSpeed, setMaxSpeed] = useState(0);
    const [capacity, setCapacity] = useState(0);

    const [disabledEdit, setDisabledEdit] = useState(true);

    const [names, setNames] = useState([]);

    const editedShip = {
        name, 
        locationId,
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

    useEffect(() => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + '/ships/ship/' + shipId, {headers: headers})
            .then(response => {
                setShip(response.data); 
                var ship = response.data;
                console.log(ship);
                setName(ship.name);
                setLocationId(ship.location.id);
                setAddress(ship.location.address);
                setCity(ship.location.city);
                setCountry(ship.location.country);
                setDescription(ship.description);
                setShipType(ship.shipType);
                setLength(ship.length);
                setEngineNumber(ship.engineNumber);
                setEnginePower(ship.enginePower);
                setMaxSpeed(ship.maxSpeed);
                setCapacity(ship.maxPersons);

                axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
                .then(response => {
                    var user = response.data;

                    axios.get(SERVER_URL + "/ships/shipNames/" + user.id, {headers: headers})
                    .then(response => {
                        setNames(response.data);
                    })
                })
            });

    }, []) 

    const editShip = () => {
        const headers = {'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + '/reservations/checkEntityFutureReservations/' + shipId, {headers: headers})
            .then(response => {
                var availableEdit = response.data;
                console.log(availableEdit);
                if(availableEdit === false){
                    setDisabledEdit(false);
                } else {
                    addToast("It is not available to edit ship informations, because there are future reservations!", { appearance: "error" });
                }
            });
    }

    const cancelChanges = () => {
        setDisabledEdit(true);
        window.location.reload();
    }

    const saveChanges = () => {
        const headers = {'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.jwtToken}`}
        
        if(editedShip.name == ""){
            addToast("Field for ship name cannot be empty!", { appearance: "error" });
        } else if(!isNameValid(name)){
            addToast("You already have ship with this name.", { appearance: "error" });
        } else {
            axios.put(SERVER_URL + '/ships/updateShip/' + shipId, editedShip, {headers: headers})
            .then(response => {
                window.location.reload();
                console.log(response.data);
            });
        }
    }

    const isNameValid = (name) => {
        for(let n of names){
            if(n == name && ship.name != name){
                return false;
            }
        }
        return true;
    }
   
    return (
        <div className="wrapper">
            <div className="left">
                <h4>SHIP PROFILE</h4><br/>
                <Link className="sidebar-link" to={"/shipNavEq/" + shipId}>Navigation equipment</Link><br/><br/>
                <Link className="sidebar-link" to={"/shipFishEq/" + shipId}>Fishing equipment</Link><br/><br/>
                <Link className="sidebar-link" to={"/shipImages/" + shipId}>Images</Link><br/><br/>
                <Link className="sidebar-link" to={"/shipRules/" + shipId}>Rules</Link><br/><br/>
                <Link className="sidebar-link" to={"/shipPricelist/" + shipId}>Pricelist</Link><br/><br/>
                <Link className="sidebar-link" to={"/shipActions/" + shipId}>Actions</Link><br/><br/>
                <Link className="sidebar-link" to={"/shipReservationCalendar/" + shipId}>Reservation calendar</Link><br/><br/>
            </div>
            <div className="right">
                <div className="info">
                    <h3>{name}</h3>
                    <div className="info_data">
                        {!disabledEdit ? (
                            <div className="data">
                                <h4>Name</h4>
                                <input type="text" onChange={(e) => {setName(e.target.value)}}  value={name} disabled={disabledEdit}/>
                            </div>
                        ) : (
                            <div></div>
                        )}
                        <div className="data">
                            <h4>Address</h4>
                            <input type="text" onChange={(e) => {setAddress(e.target.value)}}  value={address} disabled={disabledEdit}/>
                        </div>
                        <div className="data">
                            <h4>City</h4>
                            <input type="text" onChange={(e) => {setCity(e.target.value)}}  value={city} disabled={disabledEdit}/>
                        </div>
                        <div className="data">
                            <h4>Country</h4>
                            <input type="text" onChange={(e) => {setCountry(e.target.value)}}  value={country} disabled={disabledEdit}/>
                        </div>
                        <div className="data">
                            <h4>Description</h4>
                            <textarea type="text" onChange={(e) => {setDescription(e.target.value)}} value={description} disabled={disabledEdit}/>
                        </div>
                        <div className="data">
                            <h4>Ship type</h4>
                            <input type="text" onChange={(e) => {setShipType(e.target.value)}}  value={shipType} disabled={disabledEdit}/>
                        </div>
                        <div className="data">
                            <h4>Length</h4>
                            <input type="number" onChange={(e) => {setLength(e.target.value)}}  value={length} disabled={disabledEdit}/>
                        </div>
                        <div className="data">
                            <h4>Number of engines</h4>
                            <input type="number" min="0" onChange={(e) => {setEngineNumber(e.target.value)}}  value={engineNumber} disabled={disabledEdit}/>
                        </div>
                        <div className="data">
                            <h4>Engine power</h4>
                            <input type="number" min="0" onChange={(e) => {setEnginePower(e.target.value)}}  value={enginePower} disabled={disabledEdit}/>
                        </div>
                        <div className="data">
                            <h4>Max speed</h4>
                            <input type="number" min="0" onChange={(e) => {setMaxSpeed(e.target.value)}}  value={maxSpeed} disabled={disabledEdit}/>
                        </div>
                        <div className="data">
                            <h4>Capacity</h4>
                            <input type="number" min="0" onChange={(e) => {setCapacity(e.target.value)}}  value={capacity} disabled={disabledEdit}/>
                        </div>
                    </div> <br/> <br/>
                    {disabledEdit ? (
                        <button className="edit-profile-btn" onClick={() => editShip()}>
                            Edit
                        </button>
                    ) : (
                        <div className="edit-profile-btns">
                            <button className="edit-profile-cancel" onClick={() => cancelChanges()}>
                                Cancel
                            </button>
                            <button className="edit-profile-save" onClick={() => saveChanges()}>
                                Save
                            </button>
                        </div>
                    )}
                    
                    
                </div>
            </div>
        </div>
    )

}
    

export default ShipProfile;