import React from 'react'
import { useEffect } from 'react';
import { useState } from 'react';
import { Link, useParams } from "react-router-dom";
import '../../css/lodgePricelist.css';
import axios from 'axios';
import deleteImg from '../../images/trash.png';
import editImg from '../../images/pencil.png';
import AddShipPrice from './addShipPrice';
import EditShipPrice from './editShipPrice';

const ShipPriceList = () => {

    const { shipId } = useParams();

    const SERVER_URL = process.env.REACT_APP_API; 

    const [prices, setPrices] = useState([]);
    const [editPrices, setEditPrices] = useState(false);

    const [addPriceForm, setAddPriceForm] = useState(false);
    const [editPriceForm, setEditPriceForm] = useState(false);

    const [priceId, setPriceId] = useState("");

    useEffect(() => {

        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + '/prices/entityPrices/' + shipId, {headers: headers})
            .then(response => {
                setPrices(response.data); 
                console.log(response.data)});

    }, [])

    const addPrice = () => {
        setAddPriceForm(true);
    }

    const editPrice = (id) => {
        setPriceId(id);
        setEditPriceForm(true);
    }

    const deletePrice = (id) => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.delete(SERVER_URL + "/prices/deletePrice/" + id, {headers: headers})
          .then(response => {
            window.location.reload();
        });
    }

    const allPrices = prices.length ? (
        prices.map(price => {
            return(
                <li class="table-row" key={price.id}>
                    <div class="col col-1-price" >{price.name}</div>
                    <div class="col col-3-price" >${price.price}</div>
                    <div class="col col-4-price" >{price.serviceType}</div>
                </li>
            )
        })
    ) : (
        <div></div>
    );

    const editPricesForm = prices.length ? (
        prices.map(price => {
            return(
                <div className='edit-pricelist-form' key={price.id}>
                    <div className='edit-pricelist'>
                        <label style={{'font-weight': 'bold'}}>Service name: </label>
                        {price.name}<br/>
                        <label style={{'font-weight': 'bold'}}>Price: </label>
                        ${price.price}<br/>
                        <label style={{'font-weight': 'bold'}}>Type: </label>
                        {price.serviceType}
                    </div>
                    <button className='rules-btn' onClick={() => editPrice(price.id)}>
                        <img src={editImg} />
                    </button>
                    <button className='rules-btn' onClick={() => deletePrice(price.id)}>
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
            <button className="edit-profile-btn" onClick={() => addPrice()}> 
                Add new price
            </button>
        </div>
    ) : (
        <div>
            <div class="container-table">
                <ul class="responsive-table">
                    <li class="table-header">
                    <div class="col col-1-price">Service Name</div>
                    <div class="col col-3-price">Price</div>
                    <div class="col col-4-price">Service Type</div>
                    </li>
                    {allPrices}
                </ul>
            </div><br/> <br/>
            <button className="edit-profile-btn" onClick={() => setEditPrices(true)}>
                Edit prices
            </button>
        </div>
    )

    return (
        <div class="wrapper">
            <div class="left">
                <h4>SHIP PROFILE</h4><br/>
                <Link className="sidebar-link" to={"/shipNavEq/" + shipId}>Navigation equipment</Link><br/><br/>
                <Link className="sidebar-link" to={"/shipFishEq/" + shipId}>Fishing equipment</Link><br/><br/>
                <Link className="sidebar-link" to={"/shipImages/" + shipId}>Images</Link><br/><br/>
                <Link className="sidebar-link" to={"/shipLocation/" + shipId}>Location</Link><br/><br/>
                <Link className="sidebar-link" to={"/shipRules/" + shipId}>Rules</Link><br/><br/>
                <Link className="sidebar-link" to={"/shipPricelist/" + shipId}>Pricelist</Link><br/><br/>
                <Link className="sidebar-link" to={"/shipActions/" + shipId}>Actions</Link><br/><br/>
                <Link className="sidebar-link" to={"/shipReservationCalendar/" + shipId}>Reservation calendar</Link><br/><br/>
            </div>
            <div className="right">
                <div className="info">
                    <h3>SHIP PRICELIST</h3>
                    <div class="info_data">
                        { pricesForm }
                    </div>
                </div>
            </div>

            <EditShipPrice modalIsOpen={editPriceForm} setModalIsOpen={setEditPriceForm} entityId={shipId} priceId={priceId} />
            <AddShipPrice modalIsOpen={addPriceForm} setModalIsOpen={setAddPriceForm} entityId={shipId} />
        </div>
    )
}

export default ShipPriceList;