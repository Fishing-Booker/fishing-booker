import React, { useEffect } from 'react'
import { useState } from 'react';
import { Link, useParams} from "react-router-dom";
import '../../css/usersProfile.css'
import axios from 'axios';

const LodgeProfile = () => {

    const {lodgeId} = useParams();

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

    const [oneBed, setOneBed] = useState("");
    const [twoBed, setTwoBed] = useState("");
    const [threeBed, setThreeBed] = useState("");
    const [fourBed, setFourBed] = useState("");

    const [disabledEdit, setDisabledEdit] = useState(true);

    const editedLodge = {
        name, 
        locationId,
        address, 
        city,
        country,
        maxPersons,
        description,
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
            });

    }, []) 

    const saveChanges = () => {
        const headers = {'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.jwtToken}`}

        axios.put(SERVER_URL + '/lodges/updateLodge/' + lodgeId, editedLodge, {headers: headers})
            .then(response => {
                window.location.reload();
                console.log(response.data);
            });
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
                <Link className="sidebar-link" to="/lodgeActions">Actions</Link><br/><br/>
                <Link className="sidebar-link" to={"/lodgeReservationCalendar/" + lodgeId}>Reservation calendar</Link><br/><br/>
            </div>
            <div className="right">
                <div className="info">
                    <h3>{name}</h3>
                    <div className="info_data">
                        {!disabledEdit ? (
                            <div className="data">
                                <h4>Name</h4>
                                <input onChange={(e) => {setName(e.target.value)}}  value={name} disabled={disabledEdit}/>
                            </div>
                        ) : (
                            <div></div>
                        )}
                        <div className="data">
                            <h4>Address</h4>
                            <input onChange={(e) => {setAddress(e.target.value)}}  value={address} disabled={disabledEdit}/>
                        </div>
                        <div className="data">
                            <h4>City</h4>
                            <input onChange={(e) => {setCity(e.target.value)}}  value={city} disabled={disabledEdit}/>
                        </div>
                        <div className="data">
                            <h4>Country</h4>
                            <input onChange={(e) => {setCountry(e.target.value)}}  value={country} disabled={disabledEdit}/>
                        </div>
                        <div className="data">
                            <h4>Max persons</h4>
                            <input onChange={(e) => {setMaxPersons(e.target.value)}}  value={maxPersons} disabled={disabledEdit}/>
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
                    </div> <br/> <br/>
                    {disabledEdit ? (
                        <button className="edit-profile-btn" onClick={() => setDisabledEdit(false)}>
                            Edit
                        </button>
                    ) : (
                        <div className="edit-profile-btns">
                            <button className="edit-profile-cancel" onClick={() => setDisabledEdit(true)}>
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