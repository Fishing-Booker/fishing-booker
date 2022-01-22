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
import DeleteShipForm from "./deleteShipForm";
import noImg from '../../images/noProfilePicture.jpg';
import star from "../../images/star.png";
import { useToasts } from "react-toast-notifications";

const ShipOwnerHomePage = () => {

    const SERVER_URL = process.env.REACT_APP_API; 

    const { addToast } = useToasts();

    const [user, setUser]  =useState([]);
    const [ships, setShips] = useState([]);
    const [shipId, setShipId] = useState("");
    const [searchField, setSearchField] = useState("");
    const [filteredLodges, setFilteredLodges] = useState([]);
    const [addShip, setAddShip] = useState(false);
    const [deleteShipForm, setDeleteShipForm] = useState(false);

    const [url, setUrl] = useState("");
    const [shipName, setShipName] = useState("");

    useEffect(() => {

        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
        .then(response => {
            setUser(response.data);
            var user = response.data;

            axios.get(SERVER_URL + '/ships/ownerShips/' + user.id, { headers: headers})    
                .then(response => {

                    var ships = response.data;
                    var allShips = [];
                    for(let ship of ships){
                        if(ship.profileImage == ""){
                            ship.profileImage = noImg;
                        }
                        allShips.push(ship);
                    }
                    setShips(allShips);

                });
        
        });

    }, [])

    useEffect(() => {

        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.get(url, {headers: headers})
            .then(response => {
                console.log(response.data);
                var ships = response.data;
                    var allShips = [];
                    for(let ship of ships){
                        if(ship.profileImage == ""){
                            ship.profileImage = noImg;
                        }
                        allShips.push(ship);
                    }
                    setShips(allShips);
            })
        

    }, [url])

    const renderStars = (grade) => {
        let stars = []
        for (var i = 0; i < parseInt(grade); i++) {
            stars.push(<img key={i} src={star}/>)
        }
        return stars;
    }

    const deleteShip = (id) => {
        setShipId(id);

        const headers = {'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + '/reservations/checkEntityFutureReservations/' + id, {headers: headers})
            .then(response => {
                var availableDelete = response.data;
                if(availableDelete === false){
                    setShipId(id);
                    setDeleteShipForm(true);
                } else {
                    addToast("It is not available to delete this ship, because there are future reservations!", { appearance: "error" });
                }
            });
    }

    const allShips = ships.length ? (
        ships.map(ship => {
            return (
                <div className="lodge-card" key={ship.id}>
                    <div className="lodge-card-body">
                        <div className="lodge-image">
                            <img  src={ship.profileImage}  />
                        </div>
                        <Link to={'/ship/' + ship.id} style={{textDecoration: 'none', color: 'black'}}><div className="title">{ship.name}</div></Link>
                        <div className="stars" style={{'margin-left': '0%', 'margin-top': '1%'}}>{renderStars(ship.averageGrade)} </div>
                        <div className="buttons">
                            <button title="Delete ship" onClick={() => deleteShip(ship.id)}>
                                <img src={deleteImg}/>
                            </button>
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
                
                <div className="title">Welcome {user.username}!</div>

                <div className="input-box-lodge">
                <input type="search" placeholder="Enter ship name " value={shipName}
                        onChange={(e) => {
                            setShipName(e.target.value)
                            setUrl(SERVER_URL + '/ships/searchShip?name=' + shipName + "&owner=" + user.id);
                            if(e.target.value === ''){
                                setUrl(SERVER_URL + '/ships/ownerShips/' + user.id);
                            }
                        }}
                    />
                    <div className="modal-place">
                        <button title="Add ship" onClick={() => setAddShip(true)}>
                            <img src={addImg}/>
                        </button>
                        <AddShipForm modalIsOpen={addShip} setModalIsOpen={setAddShip} />
                    </div>
                    
                </div>
                
                { allShips }
                
            </div>
            
            <DeleteShipForm className="deleting-wrapper" modalIsOpen={deleteShipForm} setModalIsOpen={setDeleteShipForm} shipId={shipId} />
        </div>
        
        
    )
    
}


export default ShipOwnerHomePage;

