import React, { useEffect } from 'react'
import { useState } from 'react';
import { Link, useParams} from "react-router-dom";
import '../../css/usersProfile.css'
import axios from 'axios';
const AdventureProfile = () => {
    const {lodgeId} = useParams();

    const SERVER_URL = process.env.REACT_APP_API; 

    const [adventure, setAdventure] = useState({})
    const [lodge, setLodge] = useState({});
    const [bedrooms, setBedrooms] = useState([]);
    const [additionalServices, setAdditionalServices] = useState("");

    const [disabledEdit, setDisabledEdit] = useState(true);

    useEffect(() => {
        /*axios.get(SERVER_URL + 'lodge/' + lodgeId)
            .then(response => {setLodge(response.data); console.log(response.data)});*/

        setAdventure({
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
        })
        
        setLodge({
            "id": 1,
            "name": "Lodge1",
            "address" : "Lodge address",
            "additionServices": "#A1#A2#A3",
            "description" : "Decription of our lodge...."
        })

        /*axios.get(SERVER_URL + 'lodgeBedrooms' + lodgeId)
            .then(response => {setBedrooms(response.data); console.log(response.data)});*/

        setBedrooms([
            {
                "BedroomType" : "Single",
                "RoomNuber": 2 
            }, 
            {
                "BedroomType" : "Double",
                "RoomNuber": 1
            }
        ])

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
                <Link className="sidebar-link" to={"/adventureReservationCalendar/" + adventure.id}>Reservation calendar</Link><br/><br/>
            </div>
            <div className="right">
                <div className="info">
                    <h3>{adventure.name}</h3>
                    <div className="info_data">
                        <div className="data">
                            <h4>Address</h4>
                            <input hidden={disabledEdit}/>
                            <label hidden={!disabledEdit}>Adresa</label>
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