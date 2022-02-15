import React, { useEffect, useState } from 'react'
import { Link} from "react-router-dom";
import '../../css/addingForm.css';
import Modal from 'react-modal';
import axios from 'axios';
import { useToasts } from "react-toast-notifications";
import L from 'leaflet';

const AddLodgeLocation = ({modalIsOpen, setModalIsOpen}) => {

    const SERVER_URL = process.env.REACT_APP_API; 

    const { addToast } = useToasts();

    const [user, setUser] = useState({});

    useEffect(() => {

        /*var current_lat = 45.2635752;
        var current_long = 19.8434573;
        var current_zoom = 16;
        
        var center_lat = current_lat;
        var center_long = current_long;
        var center_zoom = current_zoom;

        let map = L.map('newMap', {
            center: [center_lat, center_long],
            zoom: center_zoom
        });

        L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
            attribution: '&copy; <a href="https://openstreetmap.org/copyright">OpenStreetMap</a> contributors'
        }).addTo(map);*/

    }, [modalIsOpen])


   return (
        <div >
            <Modal className="fullscreen" isOpen={modalIsOpen}
            shouldCloseOnEsc={true}
            onRequestClose={() => setModalIsOpen(false)}>
                <div id="addLodgeLocation" className="adding-wrapper">
                    <div className="right">
                        <div className="info">
                            <h3>ADD LOCATION</h3>
                            <div className="info_data">
                                <div className="data">
                                    <h4>Location:</h4>
                                    <div id='map' />
                                </div>
                                <div className="buttons">
                                    <button className="cancel" onClick={() => setModalIsOpen(false)}>
                                        Cancel
                                    </button>
                                    <button className="add" >
                                        Add
                                    </button><br/>
                                </div>
                            </div> <br/> <br/>
                        </div>
                    </div>
                </div>
            </Modal>
        </div>
   )
    
}

export default AddLodgeLocation;