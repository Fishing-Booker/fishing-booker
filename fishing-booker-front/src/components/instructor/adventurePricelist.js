import React, { useEffect, useState } from "react";
import deleteImg from '../../images/trash.png';
import { Link, useParams } from "react-router-dom";
import '../../css/lodgePricelist.css';
import editImg from '../../images/pencil.png';
import axios from 'axios';
import AddAventurePrice from "./addAdventurePrice";
import EditAdventurePrice from "./editAdventurePrice";

const AdventurePricelist = () => {
    const {adventureId} = useParams();

    const SERVER_URL = process.env.REACT_APP_API; 

    const [prices, setPrices] = useState([]);
    const [editPrices, setEditPrices] = useState(false);

    const [addPriceForm, setAddPriceForm] = useState(false);
    const [editPriceForm, setEditPriceForm] = useState(false);

    const [priceId, setPriceId] = useState("");

    useEffect(() => {

        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + '/prices/entityPrices/' + adventureId, {headers: headers})
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
                <li className="table-row" key={price.id}>
                    <div className="col col-1">{price.name}</div>
                    <div className="col col-3">${price.price}</div>
                    <div className="col col-4">{price.serviceType}</div>
                </li>
            )
        })
    ) : (
        <div></div>
    );


    const editPricesForm = prices.length ? (
        prices.map( price => {
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
            <button className="edit-profile-btn" onClick={() => setEditPrices(false)}>
                Cancel
            </button>
        </div>
    ) : (
        <div>
            <div className="container-table">
                <ul className="responsive-table">
                    <li className="table-header">
                    <div className="col col-1">Service Name</div>
                    <div className="col col-3">Price</div>
                    <div className="col col-4">Service Type</div>
                    </li>
                    {allPrices}
                </ul>
            </div><br/> <br/>
            <button className="edit-profile-btn" onClick={() => addPrice()}>
                Add new price
            </button> 
            {prices.length !==0 && <button className="edit-profile-btn" onClick={() => setEditPrices(true)}>
                Edit prices
            </button>}
        </div>
    );

    return (
        <div className="wrapper">
            <div className="left">
                <h4>ADVENTURE PROFILE</h4><br/>
                <Link className="sidebar-link" to={"/adventureProfile/"+ adventureId}>Info</Link><br/><br/>
                <Link className="sidebar-link" to={"/adventureImages/" + adventureId}>Images</Link><br/><br/>
                <Link className="sidebar-link" to={"/adventureLocation/" + adventureId}>Location</Link><br/><br/>
                <Link className="sidebar-link" to={"/adventureRules/" + adventureId}>Rules</Link><br/><br/>
                <Link className="sidebar-link" to={"/adventurePricelist/" + adventureId}>Pricelist</Link><br/><br/>
                <Link className="sidebar-link" to={"/adventureActions/" + adventureId}>Actions</Link><br/><br/>
            </div>
            <div className="right">
                <div className="info">
                    <h3>ADVENTURE PRICELIST</h3>
                    <div className="info_data">
                        { pricesForm }
                    </div>
                </div>
            </div>
            <AddAventurePrice modalIsOpen={addPriceForm} setModalIsOpen={setAddPriceForm} entityId={adventureId}/>
            <EditAdventurePrice modalIsOpen={editPriceForm} setModalIsOpen={setEditPriceForm} entityId={adventureId} priceId={priceId}/>
        </div>
    )



}
export default AdventurePricelist;