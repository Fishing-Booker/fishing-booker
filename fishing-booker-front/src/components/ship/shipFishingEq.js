import React, { Component } from 'react'
import { Link, useParams } from "react-router-dom";
import '../../css/usersProfile.css';
import { useState, useEffect } from 'react';
import deleteImg from '../../images/trash.png';
import addImg from '../../images/plus.png'
import axios from 'axios';

const ShipFishingEquipment = () => {

    const {shipId} = useParams();

    const SERVER_URL = process.env.REACT_APP_API; 

    const [fishEq, setFishEq] = useState([]);
    const [editEq, setEditEq] = useState(false);
    const [user, setUser] = useState([]);

    const [equipment, setEquipment] = useState("");

    const eq = {
        owner: user.id,
        equipment
    }

    useEffect(() => {

        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
            .then(response => {
                setUser(response.data);
                axios.get(SERVER_URL + '/fishEquipment/shipFishingEquipment/' + shipId, {headers: headers})
                .then(response => {
                    setFishEq(response.data); 
                    console.log(response.data)
            });
        })
    }, [])

    const addFishEq = () => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        console.log(equipment);
        axios.put(SERVER_URL + '/fishEquipment/addFishingEquipment/' + shipId, eq, {headers: headers})
        .then(response => {
            window.location.reload();
        })
    }

    const deleteFishEq = (eqId) => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.delete(SERVER_URL + '/fishEquipment/deleteFishingEquipment/' + shipId + '/' + eqId, {headers: headers})
        .then(response => {
            window.location.reload();
        })
    }

    const allFishEq = fishEq.length ? (
        fishEq.map(eq => {
            return (
                <div key={eq.id}>
                    * {eq.name}
                    <br/><br/>
                </div>
            )
        })
    ) : (
        <div>
            Add fishing equipment for your ship!
        </div>
    );

    const editFishEqForm = fishEq.length ? (
        fishEq.map((eq) => {
            return (
                <div key={eq.id}>
                    * {eq.name} 
                    <button className='rules-btn' onClick={() => deleteFishEq(eq.id)} >
                        <img src={deleteImg} />
                    </button>
                    <br/><br/>
                </div>
            )
        })
    ) : (
        <div></div>
    );

    const fishEqForm = editEq ? (
            <div className="info-data">
                { editFishEqForm }
                <p style={{color:'black', fontSize: 'small', fontStyle: 'italic'}}>Add new fishing equipment</p>
                <input className='rules-input' type="text" onChange={(e) => {setEquipment(e.target.value)}} value={equipment}/> 
                <button className='rules-btn' onClick={() => addFishEq()}>
                    <img src={addImg} />
                </button><br/><br/>
                <button className="edit-profile-btn" onClick={() => setEditEq(false)}>
                    Done
                </button>
            </div>
        ) : (
            <div>
                <div className="rules-info-data">
                    {allFishEq}
                </div> <br/> <br/>
                <button className="edit-profile-btn" onClick={() => setEditEq(true)}>Edit equipment</button>
            </div>
    )

    return (
        <div className="wrapper">
            <div className="left">
                <h4>SHIP PROFILE</h4><br/>
                <Link className="sidebar-link" to={"/shipNavEq/" + shipId}>Navigation equipment</Link><br/><br/>
                <Link className="sidebar-link" to={"/shipFishEq/" + shipId}>Fishing equipment</Link><br/><br/>
                <Link className="sidebar-link" to={"/lodgeImages/"}>Images</Link><br/><br/>
                <Link className="sidebar-link" to={"/shipRules/" + shipId}>Rules</Link><br/><br/>
                <Link className="sidebar-link" to={"/lodgePricelist/"}>Pricelist</Link><br/><br/>
                <Link className="sidebar-link" to="/lodgeActions">Actions</Link><br/><br/>
                <Link className="sidebar-link" to="/lodgeReservationCalendar">Reservation calendar</Link><br/><br/>
            </div>
            <div className="right">
                <div className="info">
                    <h3>SHIP FISHING EQUIPMENT</h3>
                    { fishEqForm }
                </div>
            </div>
        </div>
    )

}

export default ShipFishingEquipment;