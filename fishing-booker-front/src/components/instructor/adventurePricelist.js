import { useEffect, useState } from "react";
import deleteImg from '../../images/trash.png';
import { Link, useParams } from "react-router-dom";
import '../../css/lodgePricelist.css';

const AdventurePricelist = () => {
    const {adventureId} = useParams();

    const [prices, setPrices] = useState([]);
    const [editPrices, setEditPrices] = useState(false);

    useEffect(() => {

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
                <li className="table-row" key={price.id}>
                    <div className="col col-1">{price.name}</div>
                    <div className="col col-3">${price.price}</div>
                    <div className="col col-4">{price.type}</div>
                </li>
            )
        })
    ) : (
        <div></div>
    );


    const editPricesForm = prices.length ? (
        prices.map( price => {
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
            <button className="edit-profile-btn" onClick={() => setEditPrices(true)}>Edit prices</button>
        </div>
    );

    return (
        <div className="wrapper">
            <div className="left">
                <h4>ADVENTURE PROFILE</h4><br/>
                <Link className="sidebar-link" to={"/adventureImages/" + adventureId}>Images</Link><br/><br/>
                <Link className="sidebar-link" to={"/adventureRules/" + adventureId}>Rules</Link><br/><br/>
                <Link className="sidebar-link" to={"/adventurePricelist/" + adventureId}>Pricelist</Link><br/><br/>
                <Link className="sidebar-link" to={"/adventureActions/" + adventureId}>Actions</Link><br/><br/>
                <Link className="sidebar-link" to={"/adventureReservationCalendar/" + adventureId}>Reservation calendar</Link><br/><br/>
            </div>
            <div className="right">
                <div className="info">
                    <h3>ADVENTURE PRICELIST</h3>
                    <div className="info_data">
                        { pricesForm }
                    </div>
                </div>
            </div>
        </div>
    )



}
export default AdventurePricelist;