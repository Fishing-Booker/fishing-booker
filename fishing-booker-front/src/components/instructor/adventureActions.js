import React, { Component } from 'react'
import { useState, useEffect } from 'react';
import { Link, BrowserRouter as Router, Route, Switch } from "react-router-dom";
import {useParams }from 'react-router-dom';
import AddAdventureActionForm from "./addAdventureActionForm";
import '../../css/usersProfile.css'
import infoImg from '../../images/info.png';
import { format } from 'date-fns';
import axios from 'axios';
import DeleteAction from './deleteAction';
import AdventureActionInfo from './adventureActionInfo';


const AdventureActions = () => {
    const [addAction, setAddAction] = useState(false);
    const {adventureId} = useParams();

    const SERVER_URL = process.env.REACT_APP_API; 

    const [actions, setActions] = useState([]);
    const [action, setAction] = useState([]);
    const [actionInfo, setActionInfo] = useState(false);
    const [deleteActionModal, setDeleteActionModal] = useState(false);
    const [actionId, setActionId] = useState(0);

    useEffect(() => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + "/actions/entityActions/" + adventureId, {headers: headers})
          .then(response => {
            var actions = response.data;
            for(let action of actions){
                action.startDate = format(action.startDate, 'dd.MM.yyyy. kk:mm');
                action.endDate = format(action.endDate, 'dd.MM.yyyy. kk:mm');
            }
            setActions(actions);
          });
    }, [])

    const showInfo = (action) => {
        var services = action.additionalServices.split("#");
        var additionalServices = "";
        for(let service of services){
            additionalServices += service + ", "
        }
        additionalServices = additionalServices.substring(0, additionalServices.length - 2);
        action.additionalServices = additionalServices;
        setAction(action);
        setActionInfo(true);
    }

    const deleteAction = (id) => {
        console.log("Delete action by id: " + id)
        setActionId(id);
        setDeleteActionModal(true);
    }

    const allActions = actions.length ? (
        actions.map(action => {
            return(
                <li className="table-row" key={action.actionId}>
                    <div className="col col-1-action" onClick={() => deleteAction(action.actionId)}>{action.bookedBy}</div>
                    <div className="col col-2-action" >{action.startDate}</div>
                    <div className="col col-3-action" >{action.endDate}</div>
                    <div className="col col-4-action" >${action.price}</div>
                    <div className="col col-5-action" onClick={() => showInfo(action)}>
                    <img className='info-img' src={infoImg} />
                    </div>
                </li>
            )
        })
    ) : (
        <div></div>
    );

    return (
        <div className="wrapper">
            <div className="left">
                <h4>ADVENTURE PROFILE</h4><br/>
                <Link className="sidebar-link" to={"/adventureProfile/"+ adventureId}>Info</Link><br/><br/>
                <Link className="sidebar-link" to={"/adventureImages/"+ adventureId}>Images</Link><br/><br/>
                <Link className="sidebar-link" to={"/adventureRules/"+ adventureId}>Rules</Link><br/><br/>
                <Link className="sidebar-link" to={"/adventurePricelist/" + adventureId}>Pricelist</Link><br/><br/>
                <Link className="sidebar-link" to={"/adventureActions/" + adventureId}>Actions</Link><br/><br/>
            </div>
            <div className="right">
                <div className="info">
                    <h3>ACTIONS</h3>
                    <button className="new-action-btn" onClick={() => setAddAction(true)}>
                        Create new action
                    </button><br/><br/>
                    <div className="container-table">
                        <ul className="responsive-table">
                        <li className="table-header">
                            <div className="col col-1-action">Booked by</div>
                            <div className="col col-2-action">Action start</div>
                            <div className="col col-3-action">Action end</div>
                            <div className="col col-4-action">Price</div>
                            <div className="col col-5-action">Info</div>
                            </li>
                            {allActions}
                        </ul>
                    </div>
                </div>
            </div>
            <AdventureActionInfo modalIsOpen={actionInfo} setModalIsOpen={setActionInfo} action={action} />
            <AddAdventureActionForm modalIsOpen={addAction} setModalIsOpen={setAddAction} adventureId={adventureId}/>
            <DeleteAction modalIsOpen={deleteActionModal} setModalIsOpen={setDeleteActionModal} actionId={actionId} adventureId={adventureId}/>
        </div>
    )
}
export default AdventureActions;