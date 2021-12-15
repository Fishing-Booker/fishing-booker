import React from 'react'
import { useEffect } from 'react';
import { useState } from 'react';
import { Link, useParams } from "react-router-dom";
import '../../css/lodgePricelist.css';
import axios from 'axios';
import deleteImg from '../../images/trash.png';

const LodgePriceList = () => {

    const { lodgeId } = useParams();

    const [prices, setPrices] = useState([]);
    const [editPrices, setEditPrices] = useState(false);

    useEffect(() => {

        /*axios.get(SERVER_URL + 'lodgePrices/' + lodgeId)
            .then(response => {setPrices(response.data); console.log(response.data)});*/

        setPrices([
            {
                "id" : 1,
                "name": "1 night",
                "price": 350.0,
                "type": "Regular" 
            },
            {
                "id" : 2,
                "name": "1 night with breakfast",
                "price": 400.0,
                "type": "Regular" 
            },
            {
                "id" : 3,
                "name": "Weekend",
                "price": 500.0,
                "type": "Regular" 
            }
        ])

    }, [])

    const allPrices = prices.length ? (
        prices.map(price => {
            return(
                <li class="table-row" key={price.id}>
                    <div class="col col-1" >{price.name}</div>
                    <div class="col col-3" >${price.price}</div>
                    <div class="col col-4" >{price.type}</div>
                </li>
            )
        })
    ) : (
        <div></div>
    );

    const editPricesForm = prices.length ? (
        prices.map(price => {
            return(
                <div className='edit-pricelist-form'>
                    <div className='edit-pricelist'>
                        <label style={{'font-weight': 'bold'}}>Service name: </label>
                        {price.name}<br/>
                        <label style={{'font-weight': 'bold'}}>Price: </label>
                        ${price.price}<br/>
                        <label style={{'font-weight': 'bold'}}>Type: </label>
                        {price.type}
                    </div>
                    <button className='rules-btn' >
                        <img src={deleteImg} />
                    </button>
                    <br/><br/>
                </div>
            )
        })
    ) : (
        <div></div>
    );

    const pricesForm = editPrices ? (
        <div>
            {editPricesForm}
            <br/><br/>
            <button className="edit-profile-btn"> Add new price</button>
        </div>
    ) : (
        <div>
            <div class="container-table">
                <ul class="responsive-table">
                    <li class="table-header">
                    <div class="col col-1">Service Name</div>
                    <div class="col col-3">Price</div>
                    <div class="col col-4">Service Type</div>
                    </li>
                    {allPrices}
                </ul>
            </div><br/> <br/>
            <button className="edit-profile-btn" onClick={() => setEditPrices(true)}>Edit prices</button>
        </div>
    )

    return (
        <div class="wrapper">
            <div class="left">
                <h4>LODGE PROFILE</h4><br/>
                <Link className="sidebar-link" to={"/lodgeImages/" + lodgeId}>Images</Link><br/><br/>
                    <Link className="sidebar-link" to={"/lodgeRules/" + lodgeId}>Rules</Link><br/><br/>
                    <Link className="sidebar-link" to={"/lodgePricelist/" + lodgeId}>Pricelist</Link><br/><br/>
                    <Link className="sidebar-link" to="/lodgeActions">Actions</Link><br/><br/>
                    <Link className="sidebar-link" to="/lodgeReservationCalendar">Reservation calendar</Link><br/><br/>
            </div>
            <div className="right">
                <div className="info">
                    <h3>LODGE PRICELIST</h3>
                    <div class="info_data">
                        { pricesForm }
                    </div>
                </div>
            </div>
        </div>
    )
}

export default LodgePriceList;