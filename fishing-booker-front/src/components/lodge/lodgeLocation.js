import React, { useEffect, useState,} from 'react'
import { Link, useParams} from "react-router-dom";
import '../../css/lodgeProfile.css';
import axios from "axios";
import L from 'leaflet';
import AddLodgeLocation from './addLodgeLocation';

const LodgeLocation = () => {

    const {lodgeId} = useParams();

    const SERVER_URL = process.env.REACT_APP_API; 

    const GEOCODE_URL = "https://geocode.arcgis.com/arcgis/rest/services/World/GeocodeServer/reverseGeocode?f=pjson&langCode=EN&location=";

    const [map, setMap] = useState([]);

    const [haveLocation, setHaveLocation] = useState(false);

    const [newLocation, setNewLocation] = useState(false);

    useEffect(() => {

        var container = L.DomUtil.get('map');
        if(container != null){
            container._leaflet_id = null;
        }

        var current_lat = 45.2635752;
        var current_long = 19.8434573;
        var current_zoom = 16;
        

        const headers = {'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + "/locations/entityLocation/" + lodgeId, {headers: headers})
            .then(response => {
                var location = response.data;

                if(location.longitude!=0 && location.latitude!=0){

                    setHaveLocation(true);

                    current_lat = location.latitude;
                    current_long = location.longitude;

                    var center_lat = current_lat;
                    var center_long = current_long;
                    var center_zoom = current_zoom;


                    let map = L.map('map', {
                        center: [center_lat, center_long],
                        zoom: center_zoom
                    });

                    L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
                        attribution: '&copy; <a href="https://openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                    }).addTo(map);

                    var marker = L.marker([current_lat, current_long])
                    marker.addTo(map);

                } 

            }) 


    })

    const initLocation = haveLocation ? (
        <div id='map' className='map'/>
    ): (
        <div>
            <div>You didn't add location for your lodge!</div><br/><br/>
            <button className="edit-profile-btn" onClick={() => setNewLocation(true)}>Add location</button>
        </div>
    )


    return (
        <div className="wrapper">

            <link
                    rel="stylesheet"
                    href="https://unpkg.com/leaflet@1.7.1/dist/leaflet.css"
                    integrity="sha512-xodZBNTC5n17Xt2atTPuE1HxjVMSvLVW9ocqUKLsCC5CXdbqCmblAshOMAS6/keqq/sMZMZ19scR4PsZChSR7A=="
                    crossOrigin=""
                />
                <script
                    src="https://unpkg.com/leaflet@1.7.1/dist/leaflet.js"
                    integrity="sha512-XQoYMqMTK8LvdxXYG3nZ448hOEQiglfqkJs1NOQV44cWnUrBc8PkAOcXy20w0vlaXaVUearIOBhiXZ5V3ynxwA=="
                    crossOrigin=""
                ></script>
                <script
                    src="https://unpkg.com/esri-leaflet@3.0.4/dist/esri-leaflet.js"
                    integrity="sha512-oUArlxr7VpoY7f/dd3ZdUL7FGOvS79nXVVQhxlg6ij4Fhdc4QID43LUFRs7abwHNJ0EYWijiN5LP2ZRR2PY4hQ=="
                    crossOrigin=""
                ></script>
                <link
                    rel="stylesheet"
                    href="https://unpkg.com/esri-leaflet-geocoder@3.1.1/dist/esri-leaflet-geocoder.css"
                    integrity="sha512-IM3Hs+feyi40yZhDH6kV8vQMg4Fh20s9OzInIIAc4nx7aMYMfo+IenRUekoYsHZqGkREUgx0VvlEsgm7nCDW9g=="
                    crossOrigin=""
                />
                <script src="https://unpkg.com/esri-leaflet-geocoder@3.0.0/dist/esri-leaflet-geocoder.js"></script>



            <div className="left">
                <h4>LODGE PROFILE</h4><br/>
                <Link className="sidebar-link" to={"/lodgeImages/" + lodgeId}>Images</Link><br/><br/>
                <Link className="sidebar-link" to={"/lodgeLocation/" + lodgeId}>Location</Link><br/><br/>
                <Link className="sidebar-link" to={"/lodgeRules/" + lodgeId}>Rules</Link><br/><br/>
                <Link className="sidebar-link" to={"/lodgePricelist/" + lodgeId}>Pricelist</Link><br/><br/>
                <Link className="sidebar-link" to={"/lodgeActions/" + lodgeId}>Actions</Link><br/><br/>
                <Link className="sidebar-link" to={"/lodgeReservationCalendar/" + lodgeId}>Reservation calendar</Link><br/><br/>
            </div>
            <div className="right">
                <div className="info">
                    <h3>LODGE LOCATION</h3>
                    <div className="info_data-images">
                        {initLocation}
                        
                    </div> <br/> <br/>
                    
                </div>
            </div>

            <AddLodgeLocation modalIsOpen={newLocation} setModalIsOpen={setNewLocation} />

        </div>
    )

}

export default LodgeLocation;