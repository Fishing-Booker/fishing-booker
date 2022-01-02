import React, { useEffect } from 'react'
import { useState } from 'react';
import { Link, useParams} from "react-router-dom";
import '../../css/usersProfile.css'
import axios from 'axios';

const LodgeProfile = () => {

    const {lodgeId} = useParams();

    const SERVER_URL = process.env.REACT_APP_API; 

    const [lodge, setLodge] = useState([]);
    const [address, setAddress] = useState("");
    const [bedrooms, setBedrooms] = useState([]);
    const [additionalServices, setAdditionalServices] = useState("");

    const [disabledEdit, setDisabledEdit] = useState(true);

    useEffect(() => {
        const headers = {'Content-Type' : 'application/json',
                     'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + '/lodges/lodge/' + lodgeId, {headers: headers})
            .then(response => {
                setLodge(response.data); 
                var lodge = response.data;
                setBedrooms(lodge.bedrooms);
                setAddress(lodge.location.address);
                console.log(response.data);
            });

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
                    <h3>{lodge.name}</h3>
                    <div className="info_data">
                        <div className="data">
                            <h4>Address</h4>
                            <input  value={address} disabled={disabledEdit}/>
                        </div>
                        <div className="data">
                            <h4>Bedrooms</h4>
                            { allBedrooms }
                        </div>
                        <div className="data">
                            <h4>Description</h4>
                            <textarea value={lodge.description} disabled={disabledEdit}/>
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