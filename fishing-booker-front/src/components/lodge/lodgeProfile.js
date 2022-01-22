import React, { useEffect } from 'react'
import { useState } from 'react';
import { Link, useParams} from "react-router-dom";
import '../../css/usersProfile.css'
import axios from 'axios';
import { useToasts } from "react-toast-notifications";

const LodgeProfile = () => {

    const {lodgeId} = useParams();

    const { addToast } = useToasts();

    const SERVER_URL = process.env.REACT_APP_API; 

    const [lodge, setLodge] = useState([]);

    const [name, setName] = useState("");
    const [locationId, setLocationId] = useState("");
    const [address, setAddress] = useState("");
    const [city, setCity] = useState("");
    const [country, setCountry] = useState("");
    const [maxPersons, setMaxPersons] = useState("");
    const [bedrooms, setBedrooms] = useState([]);
    const [allBedrooms, setAllBedrooms] = useState([]);
    const [description, setDescription] = useState("");
    const [cancelConditions, setCancelConditions] = useState("");

    const [oneBed, setOneBed] = useState("");
    const [twoBed, setTwoBed] = useState("");
    const [threeBed, setThreeBed] = useState("");
    const [fourBed, setFourBed] = useState("");

    const [disabledEdit, setDisabledEdit] = useState(true);

    const [names, setNames] = useState([]);

    const editedLodge = {
        name, 
        locationId,
        address, 
        city,
        country,
        maxPersons,
        description,
        cancelConditions,
        oneBed, 
        twoBed,
        threeBed,
        fourBed
    }

    useEffect(() => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + '/lodges/lodge/' + lodgeId, {headers: headers})
            .then(response => {
                setLodge(response.data); 
                var lodge = response.data;
                console.log(lodge);
                setName(lodge.name);

                setAllBedrooms(lodge.bedrooms);
                var bedrooms = [];
                for(let b of lodge.bedrooms){
                    if(b.roomNumber > 0){
                        bedrooms.push(b);
                    }
                }
                setBedrooms(bedrooms);
                setLocationId(lodge.location.id);
                setAddress(lodge.location.address);
                setCity(lodge.location.city);
                setCountry(lodge.location.country);
                setMaxPersons(lodge.maxPersons);
                setDescription(lodge.description);
                setCancelConditions(lodge.cancelConditions);

                for(let b of lodge.bedrooms){
                    if(b.bedroomType === "oneBed"){
                        setOneBed(b.roomNumber);
                    } else if(b.bedroomType === "twoBed"){
                        setTwoBed(b.roomNumber);
                    } else if(b.bedroomType === "threeBed"){
                        setThreeBed(b.roomNumber);
                    } else {
                        setFourBed(b.roomNumber);
                    }
                }

                axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
                .then(response => {
                    var user = response.data;

                    axios.get(SERVER_URL + "/lodges/lodgeNames/" + user.id, {headers: headers})
                    .then(response => {
                        setNames(response.data);
                    })
                })
            });

    }, []) 

    const editLodge = () => {
        const headers = {'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + '/reservations/checkEntityFutureReservations/' + lodgeId, {headers: headers})
            .then(response => {
                var availableEdit = response.data;
                console.log(availableEdit);
                if(availableEdit === false){
                    setDisabledEdit(false);
                } else {
                    addToast("It is not available to edit lodge informations, because there are future reservations!", { appearance: "error" });
                }
            });
    }

    const cancelChanges = () => {
        setDisabledEdit(true);
        window.location.reload();
    }

    const saveChanges = () => {
        const headers = {'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.jwtToken}`}
        
        if(editedLodge.name == ""){
            addToast("Field for lodge name cannot be empty!", { appearance: "error" });
        } else if(!isNameValid(name)){
            addToast("You already have lodge with this name.", { appearance: "error" });
        } else {
            axios.put(SERVER_URL + '/lodges/updateLodge/' + lodgeId, editedLodge, {headers: headers})
            .then(response => {
                window.location.reload();
                console.log(response.data);
            });
        }
        
    }

    const isNameValid = (name) => {
        for(let n of names){
            if(n == name && lodge.name != name){
                return false;
            }
        }
        return true;
    }

    const allBedroomsForm = bedrooms.length ? (
        bedrooms.map(bedroom => {
            return(
                <div key={bedroom.Id}>
                    * <label>{bedroom.bedroomType}</label>
                    <input className="bedroom-input" type="number" value={bedroom.roomNumber} disabled={disabledEdit} />
                </div>
                
            )
        })
    ) : (
        <div>
            Add your bedrooms!
        </div>
    );

    const editBedroomsForm = (
        <div>
            <div>
                * <label>oneBed</label>
                <input className="bedroom-input" type="number" min="0" onChange={(e) => {setOneBed(e.target.value)}} value={oneBed} disabled={disabledEdit} />
            </div>
            <div>
                * <label>twoBed</label>
                <input className="bedroom-input" type="number" min="0" onChange={(e) => {setTwoBed(e.target.value)}} value={twoBed} disabled={disabledEdit} />
            </div>
            <div>
                * <label>threeBed</label>
                <input className="bedroom-input" type="number" min="0" onChange={(e) => {setThreeBed(e.target.value)}} value={threeBed} disabled={disabledEdit} />
            </div>
            <div>
                * <label>fourBed</label>
                <input className="bedroom-input" type="number" min="0" onChange={(e) => {setFourBed(e.target.value)}} value={fourBed} disabled={disabledEdit} />
            </div>
        </div>
    )
   
    return (
        <div className="wrapper">
            <div className="left">
                <h4>LODGE PROFILE</h4><br/>
                <Link className="sidebar-link" to={"/lodgeImages/" + lodgeId}>Images</Link><br/><br/>
                <Link className="sidebar-link" to={"/lodgeRules/" + lodgeId}>Rules</Link><br/><br/>
                <Link className="sidebar-link" to={"/lodgePricelist/" + lodgeId}>Pricelist</Link><br/><br/>
                <Link className="sidebar-link" to={"/lodgeActions/" + lodgeId}>Actions</Link><br/><br/>
                <Link className="sidebar-link" to={"/lodgeReservationCalendar/" + lodgeId}>Reservation calendar</Link><br/><br/>
            </div>
            <div className="right">
                <div className="info">
                    <h3>{name}</h3>
                    <div className="info_data">
                        {!disabledEdit ? (
                            <div className="data">
                                <h4>Name</h4>
                                <input required onChange={(e) => {setName(e.target.value)}}  value={name} disabled={disabledEdit}/>
                            </div>
                        ) : (
                            <div></div>
                        )}
                        <div className="data">
                            <h4>Address</h4>
                            <input required onChange={(e) => {setAddress(e.target.value)}}  value={address} disabled={disabledEdit}/>
                        </div>
                        <div className="data">
                            <h4>City</h4>
                            <input required onChange={(e) => {setCity(e.target.value)}}  value={city} disabled={disabledEdit}/>
                        </div>
                        <div className="data">
                            <h4>Country</h4>
                            <input required onChange={(e) => {setCountry(e.target.value)}}  value={country} disabled={disabledEdit}/>
                        </div>
                        <div className="data">
                            <h4>Max persons</h4>
                            <input type="number" min="0" onChange={(e) => {setMaxPersons(e.target.value)}}  value={maxPersons} disabled={disabledEdit}/>
                        </div>
                        <div className="data">
                            {disabledEdit ? (
                                <div>
                                    <h4>Bedrooms</h4>
                                    { allBedroomsForm }
                                </div>
                            ) : (
                                <div>
                                    <h4>Bedrooms</h4>
                                    { editBedroomsForm }
                                </div>
                            )}
                            
                        </div>
                        <div className="data">
                            <h4>Description</h4>
                            <textarea onChange={(e) => {setDescription(e.target.value)}} value={description} disabled={disabledEdit}/>
                        </div>
                        <div className="data">
                            <h4>Cancel conditions</h4>
                            <input type="number" min="0" onChange={(e) => {setCancelConditions(e.target.value)}}  value={cancelConditions} disabled={disabledEdit}/>
                        </div>
                    </div> <br/> <br/>
                    {disabledEdit ? (
                        <button className="edit-profile-btn" onClick={() => editLodge()}>
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
    

export default LodgeProfile;