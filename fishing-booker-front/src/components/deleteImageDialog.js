import axios from "axios";
import React from 'react'
import '../css/addingForm.css';
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
                <div id="addLodge" className="deleting-wrapper">
                    <div className="right">
                        <div className="info">
                            <h3>DELETE IMAGE</h3>
                            <div className="info_data">
                                <div className="data">
                                    <h4>Are you sure you want to delete this image?</h4>
                                </div><br/>
                                <div className="buttons">
                                    <button className="reject-request" onClick={() => setModalIsOpen(false)}>
                                        Cancel
                                    </button>
                                    <button className="accept-request" onClick={() => deleteImage()}>
                                        Delete
                                    </button>
                                </div>
                            </div> <br/>
                        </div>
                    </div>
                </div>
            </Modal>
        </div>
    )
}

export default DeleteImageDialog;