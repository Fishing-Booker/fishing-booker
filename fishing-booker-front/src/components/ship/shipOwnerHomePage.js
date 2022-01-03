import { useState, useEffect } from "react";
import { Link, BrowserRouter as Router, Route, Switch } from "react-router-dom";
import React from 'react'
import '../../css/homePage.css';
import boat1 from '../../images/boat1.jpg';
import deleteImg from '../../images/trash.png';
import editImg from '../../images/pencil.png'
import addImg from '../../images/plus.png'
import axios from "axios";
import AddShipForm from "./addShipForm";

const ShipOwnerHomePage = () => {

    const SERVER_URL = process.env.REACT_APP_API; 

    const [user, setUser]  =useState([]);
    const [ships, setShips] = useState([]);
    const [shipId, setShipId] = useState("");
    const [searchField, setSearchField] = useState("");
    const [filteredLodges, setFilteredLodges] = useState([]);
    const [addShip, setAddShip] = useState(false);

    useEffect(() => {

        const headers = {'Content-Type' : 'application/json',
                     'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
        .then(response => {
            setUser(response.data);
            var user = response.data;

            axios.get(SERVER_URL + '/ships/ownerShips/' + user.id, { headers: headers})    
                .then(response => {setShips(response.data); console.log(response.data)});
        
        });

    }, [])

    const allShips = ships.length ? (
        ships.map(ship => {
            return (
                <div className="lodge-card" key={ship.id}>
                    <div className="lodge-card-body">
                        <div className="lodge-image">
                            <img  src={boat1}  />
                        </div>
                        <Link to={'/lodge/' + ship.id} style={{textDecoration: 'none', color: 'black'}}><div className="title">{ship.name}</div></Link>
                        
                        <div className="buttons">
                            <Link to="#deleteLodge" >
                                <button title="Delete ship">
                                    <img src={deleteImg}/>
                                </button>
                            </Link>
                        </div>
                    </div>
                </div>
            )
        })
    ) : (
        <div className="center">
            Add your ship
        </div>
    );

    return (
        <div>
                
            <div className="container-home">
                
                <div className="title">Welcome Ship Owner!</div>

                <div className="input-box-lodge">
                    <input type="text" placeholder="Search..."/>
                    <div className="modal-place">
                        <Link to="#addShip" onClick={() => setAddShip(true)}>
                            <button title="Add ship">
                                <img src={addImg}/>
                            </button>
                        </Link>
                        <AddShipForm modalIsOpen={addShip} setModalIsOpen={setAddShip} />
                    </div>
                    
                </div>
                
                { allShips }
                
            </div>
            
        </div>
        
        
    )
    
}


export default ShipOwnerHomePage;

