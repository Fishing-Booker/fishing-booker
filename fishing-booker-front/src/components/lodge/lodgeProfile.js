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
    const [bedrooms, setBedrooms] = useState([]);
    const [description, setDescription] = useState("");

    const [disabledEdit, setDisabledEdit] = useState(true);

    const editedLodge = {
        name, 
        locationId,
        address, 
        city,
        country,
        description
    }

    useEffect(() => {
        const headers = {'Content-Type' : 'application/json',
                     'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + '/lodges/lodge/' + lodgeId, {headers: headers})
            .then(response => {
                setLodge(response.data); 
                var lodge = response.data;
                setName(lodge.name);
                setBedrooms(lodge.bedrooms);
                setLocationId(lodge.location.id);
                setAddress(lodge.location.address);
                setCity(lodge.location.city);
                setCountry(lodge.location.country);
                setDescription(lodge.description);
                console.log(response.data);
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

    const allBedrooms = bedrooms.length ? (
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
   
    return (
        <div className="wrapper">
            <div className="left">
                <h4>LODGE PROFILE</h4><br/>
                <Link className="sidebar-link" to={"/lodgeImages/" + lodgeId}>Images</Link><br/><br/>
                <Link className="sidebar-link" to={"/lodgeRules/" + lodgeId}>Rules</Link><br/><br/>
                <Link className="sidebar-link" to={"/lodgePricelist/" + lodgeId}>Pricelist</Link><br/><br/>
                <Link className="sidebar-link" to="/lodgeActions">Actions</Link><br/><br/>
                <Link className="sidebar-link" to="/lodgeReservationCalendar">Reservation calendar</Link><br/><br/>
            </div>
            <div className="right">
                <div className="info">
                    <h3>{name}</h3>
                    <div className="info_data">
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
                            <h4>Bedrooms</h4>
                            { allBedrooms }
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