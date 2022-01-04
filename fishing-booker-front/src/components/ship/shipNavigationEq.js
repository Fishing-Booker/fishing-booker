import React, { Component } from 'react'
import { Link, useParams } from "react-router-dom";
import '../../css/usersProfile.css';
import { useState, useEffect } from 'react';
import deleteImg from '../../images/trash.png';
import addImg from '../../images/plus.png'
import axios from 'axios';

const ShipNavigationEquipment = () => {

    const {shipId} = useParams();

    const SERVER_URL = process.env.REACT_APP_API; 

    const [navEq, setNavEq] = useState([]);
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
                axios.get(SERVER_URL + '/navEquipment/shipNavigationEquipment/' + shipId, {headers: headers})
                .then(response => {
                    setNavEq(response.data); 
                    console.log(response.data)
            });
        })
    }, [])

    const addNavEq = () => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        console.log(equipment);
        axios.put(SERVER_URL + '/navEquipment/addNavigationEquipment/' + shipId, eq, {headers: headers})
        .then(response => {
            window.location.reload();
        })
    }

    const deleteNavEq = (eqId) => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.delete(SERVER_URL + '/navEquipment/deleteNavigationEquipment/' + shipId + '/' + eqId, {headers: headers})
        .then(response => {
            window.location.reload();
        })
    }

    const allNavEq = navEq.length ? (
        navEq.map(eq => {
            return (
                <div key={eq.id}>
                    * {eq.name}
                    <br/><br/>
                </div>
            )
        })
    ) : (
        <div>
            Add navigation equipment for your ship!
        </div>
    );

    const editNavEqForm = navEq.length ? (
        navEq.map((eq) => {
            return (
                <div key={eq.id}>
                    * {eq.name} 
                    <button className='rules-btn' onClick={() => deleteNavEq(eq.id)} >
                        <img src={deleteImg} />
                    </button>
                    <br/><br/>
                </div>
            )
        })
    ) : (
        <div></div>
    );

    const navEqForm = editEq ? (
            <div className="info-data">
                { editNavEqForm }
                <p style={{color:'black', fontSize: 'small', fontStyle: 'italic'}}>Add new navigation equipment</p>
                <input className='rules-input' type="text" onChange={(e) => {setEquipment(e.target.value)}} value={equipment}/> 
                <button className='rules-btn' onClick={() => addNavEq()}>
                    <img src={addImg} />
                </button><br/><br/>
                <button className="edit-profile-btn" onClick={() => setEditEq(false)}>
                    Done
                </button>
            </div>
        ) : (
            <div>
                <div className="rules-info-data">
                    {allNavEq}
                </div> <br/> <br/>
                <button className="edit-profile-btn" onClick={() => setEditEq(true)}>Edit equipment</button>
            </div>
    )

    return (
        <div className="wrapper">
            <div className="left">
                <h4>SHIP PROFILE</h4><br/>
                <Link className="sidebar-link" to={"/shipNavEq/" + shipId}>Navigation equipment</Link><br/><br/>
                <Link className="sidebar-link" to={"/lodgeImages/"}>Fishing equipment</Link><br/><br/>
                <Link className="sidebar-link" to={"/lodgeImages/"}>Images</Link><br/><br/>
                <Link className="sidebar-link" to={"/lodgeRules/"}>Rules</Link><br/><br/>
                <Link className="sidebar-link" to={"/lodgePricelist/"}>Pricelist</Link><br/><br/>
                <Link className="sidebar-link" to="/lodgeActions">Actions</Link><br/><br/>
                <Link className="sidebar-link" to="/lodgeReservationCalendar">Reservation calendar</Link><br/><br/>
            </div>
            <div className="right">
                <div className="info">
                    <h3>SHIP NAVIGATION EQUIPMENT</h3>
                    { navEqForm }
                </div>
            </div>
        </div>
    )

}

export default ShipNavigationEquipment;