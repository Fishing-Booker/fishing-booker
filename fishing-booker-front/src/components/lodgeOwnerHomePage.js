import Entities from "./entities";
import { useState } from "react";
import { Link, BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Navbar from "./navbar";
import React from 'react'
import './../css/homePage.css';
import lodge from '../images/lodge1.jpg';
import deleteImg from '../images/trash.png';
import editImg from '../images/pencil.png'
import addImg from '../images/plus.png'

const LodgeOwnerHomePage = () => {

    return (
        <div>
                
                <div class="container-home">
                    <div className="title">Welcome Lodge Owner!</div>

                    <div className="input-box-lodge">
                        <input type="text" placeholder="Search " />
                        <button title="Add lodge">
                        <img src={addImg}/>
                        </button>
                    </div>

                    <div class="lodge-card">
                    <div class="lodge-card-body">
                        <div class="lodge-image">
                            <img  src={lodge}  />
                        </div>
                        <div class="title">Lodge1</div>
                        <div class="text">
                            Address
                        </div>
                        <div class="buttons">
                            <button title="Edit lodge">
                                <img src={editImg}/>
                            </button>
                            <button title="Delete lodge">
                                <img src={deleteImg}/>
                            </button>
                        </div>
                    </div>
                    </div>

                    <div class="lodge-card">
                    <div class="lodge-card-body">
                        <div class="lodge-image">
                            <img  src={lodge}  />
                        </div>
                        <div class="title">Lodge1</div>
                        <div class="text">
                            Address
                        </div>
                        <div class="buttons">
                            <button title="Edit lodge">
                                <img src={editImg}/>
                            </button>
                            <button title="Delete lodge">
                                <img src={deleteImg}/>
                            </button>
                        </div>
                    </div>
                    </div>

                </div>
            
            </div>
    )
}

export default LodgeOwnerHomePage;

