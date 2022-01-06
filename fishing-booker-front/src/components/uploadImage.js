import React, { Component } from 'react'
import { Link, BrowserRouter as Router, Route, Switch } from "react-router-dom";
import { withRouter } from 'react-router-dom';
import '../css/addingForm.css'
import Modal from 'react-modal'
import { useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';

const UploadImage = ({modalIsOpen, setModalIsOpen}) => {
    const [uploadedImage, setUploadedImage] = useState("");
    const SERVER_URL = process.env.REACT_APP_API; 
    const {adventureId} = useParams();
    const [base64, setBase64] = useState("");
    const [entityId, setEntityId] = useState(0);

    const values = {
        base64,
        entityId
    }

    const imageHandler = (e) => {
        const reader = new FileReader();
        reader.onload = () =>{
            setUploadedImage(reader.result);
            setBase64(reader.result);
            setEntityId(adventureId);
            console.log(reader.result);
        }
        reader.readAsDataURL(e.target.files[0])
      };

    const uploadImage = () => {
        setModalIsOpen(false);
        const headers = {'Content-Type': 'application/json',
                        'Authorization': `Bearer ${localStorage.jwtToken}`}
        axios.post(SERVER_URL + '/images/uploadImage', values, {headers:headers});
        window.location.reload();

    }

    return(
        <div>
            <Modal className="fullscreen" isOpen={modalIsOpen}
            shouldCloseOnEsc={true}
            onRequestClose={() => setModalIsOpen(false)} 
            ariaHideApp={false}>
                <div id="addLodgeAction" className="adding-wrapper">
                    <div className="right">
                        <div className="info">
                            <h3>UPLOAD IMAGE</h3>
                            <div className="info_data">
                                <div className="data">
                                    <h4>Choose image:</h4>
                                    <input type="file" name="image" accept="image/png, image/jpeg" onChange={(e) => imageHandler(e)} />
                                </div>
                                <div className="image-style">
                                    <img alt="Image preview" src={uploadedImage} width="200" height="200"/>
                                </div>
                                <button onClick={() => uploadImage()}>
                                    Add
                                </button>
                            </div> <br/> <br/>
                        </div>
                    </div>
                </div>
            </Modal>
        </div>
    )
}
export default UploadImage;