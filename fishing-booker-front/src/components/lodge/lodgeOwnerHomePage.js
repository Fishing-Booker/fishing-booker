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
import EditLodgeFrom from "./editLodgeForm";
import DeleteLodgeForm from "./deleteLodgeForm";

const LodgeOwnerHomePage = () => {

    const SERVER_URL = process.env.REACT_APP_API; 
    const [lodges, setLodges] = useState([]);
    const [searchField, setSearchField] = useState("");
    const [filteredLodges, setFilteredLodges] = useState([]);
    const [addLodge, setAddLodge] = useState(false);
    const [editLodge, setEditLodge] = useState(false);
    const [deleteLodge, setDeleteLodge] = useState(false);

    useEffect(() => {

        /*axios.get(SERVER_URL + 'ownerLodges')    // dodati id ulogovanog vlasnika
            .then(response => {setLodges(response.data); console.log(response.data)});
        }, [])*/

        setLodges(
            [{
                id: 1,
                name: "Lodge1",
                location: "Novi Sad",
                description: "Our lodge is the best",
                rules: "RULE1, RULE2, RULE3",
                images: [lodge1]
            },
            {
                id: 2,
                name: "Lodge2",
                location: "Beograd",
                description: "Our lodge is the best too",
                rules: "RULE4, RULE5, RULE6",
                images: [lodge2]
            }]
        )

        setFilteredLodges(lodges);

    }, [])

    const filterLodges = (text) => {
        filteredLodges.filter(
            lodge => {
                return(
                    lodge.name.toLowerCase().includes(text.toLowerCase())
                );
            }
        )
    }

    const allLodges = lodges.length ? (
        lodges.map(lodge => {
            return (
                <div className="lodge-card" key={lodge.id}>
                    <div className="lodge-card-body">
                        <div className="lodge-image">
                            <img  src={lodge.images[0]}  />
                        </div>
                        <Link to={'/lodge/' + lodge.id} style={{textDecoration: 'none', color: 'black'}}><div className="title">{lodge.name}</div></Link>
                        
                        <div className="buttons">
                            <Link to="#editLodge" onClick={() => setEditLodge(true)}>
                                <button title="Edit lodge">
                                    <img src={editImg}/>
                                </button>
                            </Link>
                            <Link to="#deleteLodge" onClick={() => setDeleteLodge(true)}>
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
                    <input type="text" placeholder="Search..." onChange={(e) => filterLodges(e.target.value)}/>
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
            
5           <EditLodgeFrom className="adding-wrapper" modalIsOpen={editLodge} setModalIsOpen={setEditLodge} /> 
            <DeleteLodgeForm className="deleting-wrapper" modalIsOpen={deleteLodge} setModalIsOpen={setDeleteLodge} />
        </div>
        
        
    )
    
}


export default LodgeOwnerHomePage;

