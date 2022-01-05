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
    const [address, setAddress] = useState("");
    const [maxPersons, setMaxPersons] = useState(1);
    const [additionalServices, setAdditionalServices] = useState("");
    const [description, setDescription] = useState("");
    const [biography, setBiography] = useState("");
    const [fishingEquipment, setFishingEquipment] = useState("");
    const [cancelConditions, setCancelConditions] = useState("");


    const values = {
        name,
        address : adventure.location,
        maxPersons,
        additionalServices,
        description,
        biography,
        fishingEquipment,
        cancelConditions

    }

    useEffect(() => {
        axios.get(SERVER_URL + '/adventures/adventure/' + adventureId)
            .then(response => {setAdventure(response.data); console.log(response.data)});

        values.name = adventure.name
        values.address = adventure.location.address
        values.maxPersons = adventure.maxPersons
        values.additionalServices = adventure.additionalServices
        values.description = adventure.description
        values.biography = adventure.biography
        values.fishingEquipment = adventure.fishingEquipment
        values.cancelConditions = adventure.cancelConditions

        /*setAdventure({
            "id" : 1,
            "name" : "Adventure 1",
            "location" : {
                "id" : 1,
                "longitude" : 15,
                "latitude" : 20,
                "address" : "Zeleznicka 5",
                "city" : "Novi Sad",
                "country" : "Serbia"
            },
            "description" : "Adventure description",
            "rules" : "Some rules..",
            "cancelConditions" : "Free cancelation",
            "averageGrade": 5,
            "biography": "Neka biografija...",
            "maxPersons" : 10,
            "fishingEquipment" : "Pecaroska oprema..."
        })*/

        prepareServices();

    }, []) 

    const prepareServices = () => {
        //var services = lodge.additionServices;
        var services = "bla";
        services = services.replace("#", "", 0);
        setAdditionalServices(services.replace(/#/g, '\n'));
        console.log(additionalServices)
    }

    const saveChanges = () => {
        setDisabledEdit(true)
        /*axios.post(SERVER_URL + 'editLodge/' + lodgeId, lodge)
            .then(response => console.log(response.data));*/
    }
   
    return (
        <div className="wrapper">
            <div className="left">
                <h4>ADVENTURE PROFILE</h4><br/>
                <Link className="sidebar-link" to={"/adventureImages/" + adventure.id}>Images</Link><br/><br/>
                <Link className="sidebar-link" to={"/adventureRules/" + adventure.id}>Rules</Link><br/><br/>
                <Link className="sidebar-link" to={"/adventurePricelist/" + adventure.id}>Pricelist</Link><br/><br/>
                <Link className="sidebar-link" to={"/adventureActions/" + adventure.id}>Actions</Link><br/><br/>
            </div>
            <div className="right">
                <div className="info">
                    <h3>{adventure.name}</h3>
                    <div className="info_data">
                        <div className="data">
                            <h4>Address</h4>
                            <input hidden={disabledEdit} />
                            <label hidden={!disabledEdit}>Kako ovde staviti adresu</label>
                        </div> <br />
                        <div className="data">
                            <h4>Maximum number of persons</h4>
                            <input hidden={disabledEdit}/>
                            <label hidden={!disabledEdit}>{adventure.maxPersons} persons</label>
                        </div> <br />
                        <div className="data">
                            <h4>Additional services</h4>
                            {disabledEdit ? (
                                <label disabled={disabledEdit}>{additionalServices}</label>
                            ) : (
                                <div>
                                <p>Please follow the pattern</p>
                                <input value={adventure.additionalServices} disabled={disabledEdit}/>
                                </div>
                            )}
                            
                        </div> <br />
                        <div className="data">
                            <h4>Description</h4>
                            <textarea hidden={disabledEdit}/>
                            <label hidden={!disabledEdit}>{adventure.description}</label>
                        </div> <br />
                        <div className="data">
                            <h4>Instructor biography </h4>
                            <textarea hidden={disabledEdit}/>
                            <label hidden={!disabledEdit}>{adventure.biography}</label>
                        </div> <br />
                        <div className="data">
                            <h4>Fishing equipment </h4>
                            <textarea hidden={disabledEdit}/>
                            <label hidden={!disabledEdit}>{adventure.fishingEquipment}</label>
                        </div> <br />
                        <div className="data">
                            <h4>Cancel conditions </h4>
                            <textarea hidden={disabledEdit}/>
                            <label hidden={!disabledEdit}>{adventure.cancelConditions}</label>
                        </div> <br />
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
export default AdventureProfile;