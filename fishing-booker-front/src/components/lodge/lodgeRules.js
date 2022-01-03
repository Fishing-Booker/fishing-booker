import React, { Component } from 'react'
import { Link, useParams } from "react-router-dom";
import '../../css/usersProfile.css';
import { useState, useEffect } from 'react';
import deleteImg from '../../images/trash.png';
import addImg from '../../images/plus.png'
import axios from 'axios';

const LodgeRules = () => {

    const {lodgeId} = useParams();

    const SERVER_URL = process.env.REACT_APP_API; 

    const [rules, setRules] = useState([]);
    const [editRules, setEditRules] = useState(false);

    const [newRule, setNewRule] = useState("");

    useEffect(() => {

        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + '/lodges/lodgeRules/' + lodgeId, {headers: headers})
            .then(response => {
                setRules(response.data); 
                console.log(response.data)
            });

    }, [])

    const addRule = () => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        console.log(newRule);
        axios.put(SERVER_URL + '/lodges/addRule/' + lodgeId, newRule, {headers: headers})
        .then(response => {
            window.location.reload();
        })
    }

    const deleteRule = (index) => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.delete(SERVER_URL + '/lodges/deleteRule/' + lodgeId + '/' + index, {headers: headers})
        .then(response => {
            window.location.reload();
        })
    }

    const allRules = rules.length ? (
        rules.map(rule => {
            return (
                <div >
                    * {rule}
                    <br/><br/>
                </div>
            )
        })
    ) : (
        <div>
            Add rules for your lodge!
        </div>
    );

    const editRulesForm = rules.length ? (
        rules.map((rule, i) => {
            return (
                <div key={i}>
                    * {rule} 
                    <button className='rules-btn' onClick={() => deleteRule(i)} >
                        <img src={deleteImg} />
                    </button>
                    <br/><br/>
                </div>
            )
        })
    ) : (
        <div>
            Add rules for your lodge!
        </div>
    );

    const rulesForm = editRules ? (
            <div className="info-data">
                { editRulesForm }
                <p style={{color:'black', fontSize: 'small', fontStyle: 'italic'}}>Add new rule</p>
                <input className='rules-input' type="text" onChange={(e) => {setNewRule(e.target.value)}} value={newRule}/> 
                <button className='rules-btn' onClick={() => addRule()}>
                    <img src={addImg} />
                </button><br/><br/>
                <button className="edit-profile-btn" onClick={() => setEditRules(false)}>
                    Done
                </button>
            </div>
        ) : (
            <div>
                <div className="rules-info-data">
                    {allRules}
                </div> <br/> <br/>
                <button className="edit-profile-btn" onClick={() => setEditRules(true)}>Edit rules</button>
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
                <Link className="sidebar-link" to="/lodgeReservationCalendar">Reservation calendar</Link><br/><br/>
            </div>
            <div className="right">
                <div className="info">
                    <h3>LODGE RULES</h3>
                    { rulesForm }
                </div>
            </div>
        </div>
    )

}

export default LodgeRules;