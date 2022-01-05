import axios from "axios";
import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import star from "../../images/star.png";

const MakeReservation = () => {
    const SERVER_URL = process.env.REACT_APP_API;
    const [lodge, setLodge] = useState([]);
    const [address, setAddress] = useState('')
    const [city, setCity] = useState('')
    const [country, setCountry] = useState('')
    const [ownerName, setOwnerName] = useState('')
    const [ownerSurname, setOwnerSurname] = useState('')
    const { id } = useParams();

    useEffect(() => {
        axios.get(SERVER_URL + "/lodges/lodge?id=" + id)
            .then(response => {
                setLodge(response.data); 
                console.log(response.data); 
                setAddress(response.data.location.address);
                setCity(response.data.location.city);
                setCountry(response.data.location.country);
                setOwnerName(response.data.owner.name);
                setOwnerSurname(response.data.owner.surname);
            });
    }, [id])

    const renderStars = (grade) => {
        let stars = []
        for (var i = 0; i < parseInt(grade); i++) {
            stars.push(<img key={i} src={star}/>)
        }
        return stars;
    }

    return (
        <div className="card entity-details">
            <Link className="subscribe-link">Subscribe</Link>
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