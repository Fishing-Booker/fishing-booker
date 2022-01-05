import axios from "axios";
import { useEffect, useState } from "react";
import { useParams, useHistory } from "react-router-dom";
import star from "../../images/star.png";
import { useToasts } from "react-toast-notifications";

const MakeReservation = () => {
    const SERVER_URL = process.env.REACT_APP_API;
    const [lodge, setLodge] = useState([]);
    const [address, setAddress] = useState('')
    const [city, setCity] = useState('')
    const [country, setCountry] = useState('')
    const [ownerName, setOwnerName] = useState('')
    const [ownerSurname, setOwnerSurname] = useState('')
    const { id } = useParams();
    const [user, setUser] = useState([]);
    const [isSubscribed, setIsSubscribed] = useState(true)
    const [entityId, setEntityId] = useState(id)
    const { addToast } = useToasts();
    const history = useHistory();


    useEffect(() => {
        axios.get(SERVER_URL + "/lodges/lodge?id=" + id)
            .then(response => {
                setLodge(response.data); 
                setAddress(response.data.location.address);
                setCity(response.data.location.city);
                setCountry(response.data.location.country);
                setOwnerName(response.data.owner.name);
                setOwnerSurname(response.data.owner.surname);
                setEntityId(response.data.id)
            });
        
        const headers = {'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.jwtToken}`}
        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
            .then(response => {
                setUser(response.data); 
                var subscriberDTO = {entityId, userId: response.data.id}
                axios.post(SERVER_URL + "/subscribe/isSubscribed", subscriberDTO)
                    .then(response => setIsSubscribed(response.data))
            })
        
    }, [id, isSubscribed])

    const renderStars = (grade) => {
        let stars = []
        for (var i = 0; i < parseInt(grade); i++) {
            stars.push(<img key={i} src={star}/>)
        }
        return stars;
    }

    const handleSubscribe = (entityId, userId) => {
        var subscriberDTO = {entityId, userId}
        axios.post(SERVER_URL + "/subscribe", subscriberDTO)
            .then(() => {
                addToast("You are successfully subscribed.", { appearance: "success" });
                setIsSubscribed(true);
            });
    }

    const handleUnsubscribe = (entityId, userId) => {
        axios.delete(SERVER_URL + "/subscribe/unsubscribe?entityId=" + entityId + "&userId=" + userId)
            .then(() => {
                addToast("You are no longer subscribed.", { appearance: "success" });
                setIsSubscribed(false);
            })
    }

    return (
        <div className="card entity-details">
            {isSubscribed && <a className="subscribe-link" onClick={() => handleUnsubscribe(lodge.id, user.id)}>Unsubscribe</a>}
            {!isSubscribed && <a className="subscribe-link" onClick={() => handleSubscribe(lodge.id, user.id)}>Subscribe</a>}
            <p className="entity-info name" style={{marginTop: '2%'}}>{lodge.name}
                <div className="stars">{renderStars(lodge.averageGrade)} </div>
            </p>
            <p className="entity-info location">{address}, {city}, {country}</p>
            <p className="entity-info description">{lodge.description}</p>
            <p className="entity-info description">Owner: {ownerName} {ownerSurname}</p>
            <p className="entity-info description">Price: 1,000.00 </p>
            <br></br>
            <h4 style={{marginLeft: '3%'}}>Please choose a period for reservation:</h4>
            <input className="reservation-date" type="datetime-local"></input>
            <input className="reservation-date" type="datetime-local"></input>
            <a className="available-dates">See available dates</a>

        </div>
    )
}

export default MakeReservation;