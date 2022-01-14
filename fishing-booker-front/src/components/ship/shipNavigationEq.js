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
    const [gps, setGps] = useState(false);
    const [radar, setRadar] = useState(false);
    const [vhf, setVhf] = useState(false);
    const [fishfinder, setFishfinder] = useState(false);

    const [newEq, setNewEq] = useState("");
    const [eq, setEq] = useState("");

    useEffect(() => {

        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + '/ships/shipNavEq/' + shipId, {headers: headers})
            .then(response => {
                var allEq = response.data;
                console.log(allEq)
                for(let eq of allEq){
                    if(eq == "GPS"){
                        setGps(true);
                    } 
                    if(eq == "Radar"){
                        setRadar(true);
                    }
                    if(eq == "VHF radio"){
                        setVhf(true);
                    }
                    if(eq == "Fishfinder"){
                        setFishfinder(true);
                    }
                }
                setNavEq(response.data); 
                console.log(response.data)
            });
    }, [])

    const addEq = () => {
        //eq = getNavEq();

        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        console.log(newEq);
        axios.put(SERVER_URL + '/ships/addNavEq/' + shipId, getNavEq(), {headers: headers})
        .then(response => {
            setEditEq(false);
            window.location.reload();
        })
    }

    const getNavEq = () => {
        var equipment = "";
        if(gps == true){
            equipment += "GPS";
        }
        if(radar == true){
            if(equipment == ""){
                equipment += "Radar";
            } else {
                equipment += "#Radar";
            }
        }
        if(vhf == true){
            if(equipment == ""){
                equipment += "VHF radio";
            } else {
                equipment += "#VHF radio";
            }
        }
        if(fishfinder == true){
            if(equipment == ""){
                equipment += "Fishfinder";
            } else {
                equipment += "#Fishfinder";
            }
        }
        return equipment;
    }

    const deleteEq = (index) => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.delete(SERVER_URL + '/ships/deleteNavEq/' + shipId + '/' + index, {headers: headers})
        .then(response => {
            window.location.reload();
        })
    }

    const allNavEq = navEq.length ? (
        navEq.map(eq => {
            return (
                <div >
                    * {eq}
                    <br/><br/>
                </div>
            )
        })
    ) : (
        <div>
            Add navigation equipment for your ship!
        </div>
    );

    const editNavEqForm = (
        <div>
            <div>
                <input type="checkbox" checked={gps} id="gps" name="card" onChange={(e) => setGps(!gps)} value="gps" />
                <label htmlFor="gps" aria-label="gps"> GPS</label>
            </div><br/>
            <div>
                <input type="checkbox" checked={radar} id="radar" name="card" onChange={(e) => setRadar(!radar)} value="radar" />
                <label htmlFor="radar" aria-label="radar"> Radar</label>
            </div><br/>
            <div>
                <input type="checkbox" checked={vhf} id="vhf" name="card" onChange={(e) => setVhf(!vhf)} value="vhf" />
                <label htmlFor="vhf" aria-label="vhf"> VHF radio</label>
            </div><br/>
            <div>
                <input type="checkbox" checked={fishfinder} id="fishfinder" name="card" onChange={(e) => setFishfinder(!fishfinder)} value="fishfinder" />
                <label htmlFor="fishfinder" aria-label="fishfinder"> Fishfinder</label>
            </div><br/>        
        </div>
    );

    const navEqForm = editEq ? (
            <div className="info-data">
                { editNavEqForm }
                <br/><br/>
                <button className="edit-profile-btn" onClick={() => addEq()}>
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
                    <Link className="sidebar-link" to={"/shipImages/" + shipId}>Images</Link><br/><br/>
                    <Link className="sidebar-link" to={"/shipRules/" + shipId}>Rules</Link><br/><br/>
                    <Link className="sidebar-link" to={"/shipPricelist/" + shipId}>Pricelist</Link><br/><br/>
                    <Link className="sidebar-link" to={"/shipActions/" + shipId}>Actions</Link><br/><br/>
                    <Link className="sidebar-link" to={"/shipReservationCalendar/" + shipId}>Reservation calendar</Link><br/><br/>
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