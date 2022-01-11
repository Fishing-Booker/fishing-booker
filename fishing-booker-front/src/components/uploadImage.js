import React from 'react'
import '../css/addingForm.css'
import Modal from 'react-modal'
import { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';

const UploadImage = ({modalIsOpen, setModalIsOpen, entityId}) => {
    const [uploadedImage, setUploadedImage] = useState("");
    const SERVER_URL = process.env.REACT_APP_API; 
    const {adventureId} = useParams();
    const [base64, setBase64] = useState("");

    const [user, setUser] = useState([]);

    useEffect(() => {
        const headers = {'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.jwtToken}`}
        
        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
            .then(response => setUser(response.data))
    }, [])

    const values = {
        owner: user.id,
        base64,
        entityId : adventureId
    }

    const imageHandler = (e) => {
        const reader = new FileReader();
        reader.onload = () =>{
            setUploadedImage(reader.result);
            setBase64(reader.result);
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
                                    <img alt='preview' src={uploadedImage} width="200" height="200"/>
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