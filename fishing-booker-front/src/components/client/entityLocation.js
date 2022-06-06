import React, { useEffect, useState, useLayoutEffect} from 'react'
import { Link, useParams} from "react-router-dom";
import { useToasts } from "react-toast-notifications";
import '../../css/lodgeProfile.css';
import axios from "axios";
import L from 'leaflet';

const EntityLocation = ({ entityId }) => {

    const { addToast } = useToasts();

    const SERVER_URL = process.env.REACT_APP_API; 

    const GEOCODE_URL = "https://geocode.arcgis.com/arcgis/rest/services/World/GeocodeServer/reverseGeocode?f=pjson&langCode=EN&location=";

    const [id, setId] = useState(0);
    const [longitude, setLongitude] = useState(0);
    const [latitude, setLatitude] = useState(0);
    const [address, setAddress] = useState("");
    const [city, setCity] = useState("");
    const [country, setCountry] = useState("");

    const [oldlongitude, setOldLongitude] = useState(0);
    const [oldlatitude, setOldLatitude] = useState(0);

    const [haveLocation, setHaveLocation] = useState(false);

    const location = {
        id,
        longitude,
        latitude,
        address,
        city,
        country
    }

    useEffect(() => {
        

        var current_lat = 45.2635752;
        var current_long = 19.8434573;
        var current_zoom = 20;
        

        const headers = {'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + "/locations/entityLocation/" + entityId, {headers: headers})
            .then(response => {
                var location = response.data;
                setId(location.id);
                setLongitude(location.longitude);
                setLatitude(location.latitude);
                setAddress(location.address);
                setCity(location.city);
                setCountry(location.country);

                setOldLongitude(location.longitude);
                setOldLatitude(location.latitude);

                var container = L.DomUtil.get('map');
                if(container != null){
                    container._leaflet_id = null;
                }

                if(location.longitude!=0 && location.latitude!=0){
                    current_lat = location.latitude;
                    current_long = location.longitude;
                    setHaveLocation(true);
                    var map = L.map('map', {
                        center: [current_lat, current_long],
                        zoom: current_zoom
                    });
                } else {
                    var map = L.map('map', {
                        center: [45.2635752, 19.8434573],
                        zoom: current_zoom
                    });
                }
                

                L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
                        attribution: '&copy; <a href="https://openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                    }).addTo(map);

                var marker;
                if(location.longitude!=0 && location.latitude!=0){
                    marker = L.marker([current_lat, current_long])
                    marker.addTo(map);
                }


            }) 


    }, [])

    const initLocation = haveLocation ? (
        <div>
            {address}, {city}, {country}
            
        </div>
    ) : (
        <div>This entity does not have location</div>
    )


    return (
        <div style={{marginTop: '5%', marginLeft: '10%', width: '20%'}}>

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

                <div id='map' className='map'/><br/>

        </div>
    )

}

export default EntityLocation;