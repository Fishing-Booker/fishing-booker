import Entities from "../entities";
import { useState, useEffect } from "react";
import { Link, BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Navbar from "../navbar";
import React from 'react'
import '../../css/homePage.css';
import lodge1 from '../../images/lodge1.jpg';
import lodge2 from '../../images/lodge2.jpg';
import deleteImg from '../../images/trash.png';
import editImg from '../../images/pencil.png'
import addImg from '../../images/plus.png'
import axios from "axios";
import AddLodgeFrom from "./addLodgeForm";
import DeleteLodgeForm from "./deleteLodgeForm";

const LodgeOwnerHomePage = () => {

    const SERVER_URL = process.env.REACT_APP_API; 

    const [user, setUser]  =useState([]);
    const [lodges, setLodges] = useState([]);
    const [lodgeId, setLodgeId] = useState("");
    const [searchField, setSearchField] = useState("");
    const [filteredLodges, setFilteredLodges] = useState([]);
    const [addLodge, setAddLodge] = useState(false);
    const [deleteLodgeForm, setDeleteLodgeForm] = useState(false);

    useEffect(() => {

        const headers = {'Content-Type' : 'application/json',
                     'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
        .then(response => {
            setUser(response.data);
            var user = response.data;

            axios.get(SERVER_URL + '/lodges/ownerLodges/' + user.id, { headers: headers})    
                .then(response => {setLodges(response.data); console.log(response.data)});
        
        });

    }, [])

    const deleteLodge = (id) => {
        setLodgeId(id);
        setDeleteLodgeForm(true);
    }

    const allLodges = lodges.length ? (
        lodges.map(lodge => {
            return (
                <div className="lodge-card" key={lodge.id}>
                    <div className="lodge-card-body">
                        <div className="lodge-image">
                            <img  src={lodge1}  />
                        </div>
                        <Link to={'/lodge/' + lodge.id} style={{textDecoration: 'none', color: 'black'}}><div className="title">{lodge.name}</div></Link>
                        
                        <div className="buttons">
                            <Link to="#deleteLodge" onClick={() => deleteLodge(lodge.id)}>
                                <button title="Delete lodge">
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
            Add your lodge
        </div>
    );

    return (
        <div>
                
            <div className="container-home">
                
                <div className="title">Welcome Lodge Owner!</div>

                <div className="input-box-lodge">
                    <input type="text" placeholder="Search..."/>
                    <div className="modal-place">
                        <Link to="#addLodge" onClick={() => setAddLodge(true)}>
                            <button title="Add lodge">
                                <img src={addImg}/>
                            </button>
                        </Link>
                        <AddLodgeFrom modalIsOpen={addLodge} setModalIsOpen={setAddLodge} />
                    </div>
                    
                </div>
                
                { allLodges }
                
            </div>
            
            <DeleteLodgeForm className="deleting-wrapper" modalIsOpen={deleteLodgeForm} setModalIsOpen={setDeleteLodgeForm} lodgeId={lodgeId}/>
        </div>
        
        
    )
    
}


export default LodgeOwnerHomePage;

