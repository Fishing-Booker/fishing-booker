import React, { Component } from 'react'
import { useState, useEffect } from 'react';
import { Link, useParams} from "react-router-dom";
import { withRouter } from 'react-router-dom';
import '../../css/usersProfile.css'
import AddLodgeActionFrom from './addLodgeActionForm';
import axios from 'axios';
import { format } from 'date-fns';
import infoImg from '../../images/info.png';
import LodgeActionInfo from './lodgeActionInfo';

const LodgeActions = () => {

    const {lodgeId} = useParams();

    const SERVER_URL = process.env.REACT_APP_API; 

    const [actions, setActions] = useState([]);

    const [action, setAction] = useState([]);

    const [addAction, setAddAction] = useState(false);
    const [actionInfo, setActionInfo] = useState(false);

    useEffect(() => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + "/actions/entityActions/" + lodgeId, {headers: headers})
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

    const allActions = actions.length ? (
        actions.map(action => {
            return(
                <li class="table-row" key={action.id}>
                    <div class="col col-1-action" >{action.bookedBy}</div>
                    <div class="col col-2-action" >{action.startDate}</div>
                    <div class="col col-3-action" >{action.endDate}</div>
                    <div class="col col-4-action" >{action.price}</div>
                    <div class="col col-5-action" onClick={() => showInfo(action)}>
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
                <h4>LODGE PROFILE</h4><br/>
                <Link className="sidebar-link" to={"/lodgeImages/" + lodgeId}>Images</Link><br/><br/>
                <Link className="sidebar-link" to={"/lodgeLocation/" + lodgeId}>Location</Link><br/><br/>
                <Link className="sidebar-link" to={"/lodgeRules/" + lodgeId}>Rules</Link><br/><br/>
                <Link className="sidebar-link" to={"/lodgePricelist/" + lodgeId}>Pricelist</Link><br/><br/>
                <Link className="sidebar-link" to={"/lodgeActions/" + lodgeId}>Actions</Link><br/><br/>
                <Link className="sidebar-link" to={"/lodgeReservationCalendar/" + lodgeId}>Reservation calendar</Link><br/><br/>
            </div>
            <div className="right">
                <div className="info">
                    <h3>ACTIONS</h3>
                    <button className="new-action-btn" onClick={() => setAddAction(true)}>
                        Create new action
                    </button><br/><br/>
                    <div class="container-table">
                        <ul class="responsive-table">
                            <li class="table-header">
                            <div class="col col-1-action">Booked by</div>
                            <div class="col col-2-action">Action start</div>
                            <div class="col col-3-action">Action end</div>
                            <div class="col col-4-action">Price</div>
                            <div class="col col-5-action">Info</div>
                            </li>
                            {allActions}
                        </ul>
                    </div>
                </div>
            </div>

            <LodgeActionInfo modalIsOpen={actionInfo} setModalIsOpen={setActionInfo} action={action} />
            <AddLodgeActionFrom modalIsOpen={addAction} setModalIsOpen={setAddAction} entityId={lodgeId}/>
        </div>
    )

}

export default LodgeActions;