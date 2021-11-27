import Entities from "./entities";
import { Component, useState } from "react";
import { Link, BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Navbar from "./navbar";
import React from 'react'
import './../css/homePage.css';
import lodge1 from '../images/lodge1.jpg';
import lodge2 from '../images/lodge2.jpg';
import deleteImg from '../images/trash.png';
import editImg from '../images/pencil.png'
import addImg from '../images/plus.png'

class LodgeOwnerHomePage extends Component{

    state = {
        lodges : []
    }

    componentDidMount() {

        const allLodges = [
            {
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
            }
        ]

        this.setState({
            lodges : allLodges
        })

    }

    render() {
        const {lodges} = this.state;

        const lodgeList = lodges.length ? (
            lodges.map(lodge => {
                return (
                    <div className="lodge-card" key={lodge.id}>
                        <div className="lodge-card-body">
                            <div className="lodge-image">
                                <img  src={lodge.images}  />
                            </div>
                            <Link to={'/lodge/' + lodge.id} style={{textDecoration: 'none', color: 'black'}}><div className="title">{lodge.name}</div></Link>
                            <div className="text">
                                {lodge.location}
                            </div>
                            <div className="buttons">
                                <button title="Edit lodge">
                                    <img src={editImg}/>
                                </button>
                                <button title="Delete lodge">
                                    <img src={deleteImg}/>
                                </button>
                            </div>
                        </div>
                    </div>
                )
            })
        ) : (
            <div className="center">
                Add your lodge
            </div>
        )

        return (
            <div>
                    
                <div className="container-home">
                    <div className="title">Welcome Lodge Owner!</div>

                    <div className="input-box-lodge">
                        <input type="text" placeholder="Search " />
                        <button title="Add lodge">
                        <img src={addImg}/>
                        </button>
                    </div>

                    {lodgeList}

                </div>
                
            </div>
        )

    }
    
}

export default LodgeOwnerHomePage;

