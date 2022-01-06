import axios from "axios";
import React from 'react'
import '../css/addingForm.css'
import Modal from 'react-modal';
import { useEffect } from "react";

const DeleteImageDialog = ({modalIsOpen, setModalIsOpen, imageId}) => {
    const SERVER_URL = process.env.REACT_APP_API; 

    const deleteImage = () => {
        const headers = {'Content-Type': 'application/json',
                        'Authorization': `Bearer ${localStorage.jwtToken}`}
        axios.delete(SERVER_URL + "/images/deleteImage/"+imageId, {headers:headers})
          .then(response => {
            setModalIsOpen(false);
            window.location.reload();
        });
    }

    useEffect(() => {

        console.log(imageId)


    }, [])
    return (
        <div>
            <Modal className="fullscreen" isOpen={modalIsOpen}
            shouldCloseOnEsc={true}
            onRequestClose={() => setModalIsOpen(false)} 
            ariaHideApp={false}>
                <div id="addLodgeAction" className="adding-wrapper">
                    <div className="right">
                        <div className="info">
                            <h3>DELETE IMAGE</h3>
                            <div className="info_data">
                                <div className="data">
                                    <h4>Do you really want do delete this image?</h4>
                                </div>
                                <button onClick={() => deleteImage()}>
                                    Yes
                                </button>
                                <button onClick={() => setModalIsOpen(false)}>
                                    No
                                </button>
                            </div> <br/> <br/>
                        </div>
                    </div>
                </div>
            </Modal>
        </div>
    )
}

export default DeleteImageDialog;