import React, { useEffect } from 'react'
import { useState } from 'react';
import { Link, useParams} from "react-router-dom";
import '../../css/usersProfile.css'
import axios from 'axios';
const AdventureProfile = () => {

    const SERVER_URL = process.env.REACT_APP_API; 
    const {adventureId} = useParams();

    const [adventure, setAdventure] = useState({});

    const [disabledEdit, setDisabledEdit] = useState(true);

    const [name, setName] = useState("");
    const [maxPersons, setMaxPersons] = useState(0);
    const [description, setDescription] = useState("");
    const [biography, setBiography] = useState("");
    const [fishingEquipment, setFishingEquipment] = useState("");
    const [cancelConditions, setCancelConditions] = useState("");

    const values = {
        adventureId,
        name,
        description,
        biography,
        maxPersons,
        cancelConditions,
        fishingEquipment
    }

    useEffect(() => {
        const headers = {'Content-Type': 'application/json',
                        'Authorization': `Bearer ${localStorage.jwtToken}`}
        axios.get(SERVER_URL + '/adventures/adventure/' + adventureId, {headers:headers})
            .then(response => {
                setAdventure(response.data);
                console.log(response.data);
            });
    }, []) 

    const saveChanges = () => {
        console.log(values);
        const headers = {'Content-Type': 'application/json',
                        'Authorization': `Bearer ${localStorage.jwtToken}`}
        axios.put(SERVER_URL + '/adventures/editAdventure', values, {headers : headers})
        .then(response => {
            window.location.reload();
            setDisabledEdit(true);
        })
    }

    const onEditClick = () => {
        setDisabledEdit(false);
        setName(adventure.name);
        setMaxPersons(adventure.maxPersons);
        setDescription(adventure.description);
        setBiography(adventure.biography);
        setFishingEquipment(adventure.fishingEquipment);
        setCancelConditions(adventure.cancelConditions);
    }

   
    return (
        <div className="wrapper">
            <div className="left">
                <h4>ADVENTURE PROFILE</h4><br/>
                <Link className="sidebar-link" to={"/adventureProfile/"+ adventure.id}>Info</Link><br/><br/>
                <Link className="sidebar-link" to={"/adventureImages/" + adventure.id}>Images</Link><br/><br/>
                <Link className="sidebar-link" to={"/adventureLocation/" + adventureId}>Location</Link><br/><br/>
                <Link className="sidebar-link" to={"/adventureRules/" + adventure.id}>Rules</Link><br/><br/>
                <Link className="sidebar-link" to={"/adventurePricelist/" + adventure.id}>Pricelist</Link><br/><br/>
                <Link className="sidebar-link" to={"/adventureActions/" + adventure.id}>Actions</Link><br/><br/>
            </div>
            <div className="right">
                <div className="info">
                    <h3>{adventure.name}</h3>
                    <div className="info_data">
                        <div className="data">
                            <h4>Maximum number of persons</h4>
                            <input hidden={disabledEdit} value={maxPersons} onChange={(e) => setMaxPersons(e.target.value)}/>
                            <label hidden={!disabledEdit}>{adventure.maxPersons} persons</label>
                        </div> <br />
                        <div className="data">
                            <h4>Description</h4>
                            <textarea hidden={disabledEdit} value={description} onChange={(e) => setDescription(e.target.value)}/>
                            <label hidden={!disabledEdit}>{adventure.description}</label>
                        </div> <br />
                        <div className="data">
                            <h4>Instructor biography </h4>
                            <textarea hidden={disabledEdit} value={biography} onChange={(e) => setBiography(e.target.value)}/>
                            <label hidden={!disabledEdit}>{adventure.biography}</label>
                        </div> <br />
                        <div className="data">
                            <h4>Fishing equipment </h4>
                            <textarea hidden={disabledEdit} value={fishingEquipment} onChange={(e) => setFishingEquipment(e.target.value)}/>
                            <label hidden={!disabledEdit}>{adventure.fishingEquipment}</label>
                        </div> <br />
                        <div className="data">
                            <h4>Cancel conditions </h4>
                            <textarea hidden={disabledEdit} value={cancelConditions} onChange={(e) => setCancelConditions(e.target.value)}/>
                            <label hidden={!disabledEdit}>{adventure.cancelConditions}</label>
                        </div> <br />
                    </div> <br/> <br/>
                    {disabledEdit ? (
                        <button className="edit-profile-btn" onClick={onEditClick}>
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
export default AdventureProfile;