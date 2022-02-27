import React, { useEffect, useState, useLayoutEffect} from 'react'
import { Link, useParams} from "react-router-dom";
import { useToasts } from "react-toast-notifications";
import '../../css/lodgeProfile.css';
import axios from "axios";
import L from 'leaflet';

const AdventureLocation = () => {

    const {adventureId} = useParams();

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

        axios.get(SERVER_URL + "/locations/entityLocation/" + adventureId, {headers: headers})
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

                var coordinates = [0, 0];
                async function onMapClick(e) {

                    setHaveLocation(true);

                    addToast("Don't forget to save changes", { appearance: "warning" });

                    if (coordinates[0] !== 0 || (location.longitude!=0 && location.latitude!=0)) {
                        map.removeLayer(marker);
                    }
                    coordinates = e.latlng.toString().substring(7, 25).split(", ");
                    var lat = parseFloat(coordinates[0])
                    setLatitude(lat);
                    var long = parseFloat(coordinates[1])
                    setLongitude(long);
                    marker = L.marker([coordinates[0], coordinates[1]]);
                    marker.addTo(map);
                    var data = await (
                    await fetch(GEOCODE_URL + `${coordinates[1]},${coordinates[0]}`)
                    ).json();
                    console.log(data.address);
                    setAddress(data.address.Address);
                    setCity(data.address.City);
                    setCountry(data.address.CountryCode);
                }
                
                map.on('click', onMapClick);


            }) 


    }, [])

    const saveLocation = () => {
        if(oldlongitude!=longitude || oldlatitude!=latitude){
            const headers = {'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.jwtToken}`}
            console.log(longitude);
            console.log(latitude);
            axios.put(SERVER_URL + "/locations/updateLocation", location, {headers: headers})
            .then(response => {
                window.location.reload();
            })
        }
    }

    const initLocation = haveLocation ? (
        <div>
            {address}, {city}, {country}
            
        </div>
    ) : (
        <div>You didn't add location for your adventure</div>
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
            <h4>ADVENTURE PROFILE</h4><br/>
            <Link className="sidebar-link" to={"/adventureProfile/"+ adventureId}>Info</Link><br/><br/>
            <Link className="sidebar-link" to={"/adventureImages/" + adventureId}>Images</Link><br/><br/>
            <Link className="sidebar-link" to={"/adventureLocation/" + adventureId}>Location</Link><br/><br/>
            <Link className="sidebar-link" to={"/adventureRules/" + adventureId}>Rules</Link><br/><br/>
            <Link className="sidebar-link" to={"/adventurePricelist/" + adventureId}>Pricelist</Link><br/><br/>
            <Link className="sidebar-link" to={"/adventureActions/" + adventureId}>Actions</Link><br/><br/>
            </div>
            <div className="right">
                <div className="info">
                    <h3>SHIP LOCATION</h3>
                    <div className="info_data-images">
                        {initLocation}
                        <div id='map' className='map'/><br/>
                        <button className="edit-profile-btn" style={{'marginLeft': '35%'}} onClick={() => saveLocation()}>Save location </button>
                    </div> <br/> <br/>
                    
                </div>
            </div>

        </div>
    )

}

export default AdventureLocation;