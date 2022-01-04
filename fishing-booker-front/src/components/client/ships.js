import axios from "axios";
import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import star from "../../images/star.png";
import Letters from "../letters";

const Ships = () => {
    const SERVER_URL = process.env.REACT_APP_API;
    const [ships, setShips] = useState([])
    const [isLogged, setIsLogged] = useState(false);
    const [url, setUrl] = useState(SERVER_URL + '/ships');
    const [name, setName] = useState('');
    const [letters, setLetters] = useState([])
    const [location, setLocation] = useState('');

    useEffect(() => {
        let token = localStorage.getItem('jwtToken');
        if (token != null) {
            setIsLogged(true)
        } else {
            setIsLogged(false)
        }

        axios.get(SERVER_URL + "/ships/letters")
        .then(response => setLetters(response.data));

        axios.get(url)
            .then(response => {setShips(response.data); console.log(response.data);})
    }, [url])

    const renderStars = (grade) => {
        let stars = []
        for (var i = 0; i < parseInt(grade); i++) {
            console.log(grade)
            stars.push(<img src={star}/>)
        }
        return stars;
    }

    const handleCallback = (childData) => {
        if (childData === '') {
            setUrl(SERVER_URL + '/ships');
        } else {
            setUrl(SERVER_URL + '/ships/search?name=' + name + '&letter=' + childData)
        }
        
    }

    const allShips = ships.length ? (
        ships.map((ship, index) => {
            return(
                <div className="col" key={index}>
                    <div className="card ship">
                    <div className="info"> <br></br>
                            <p className="entity-info name">{ship.name} <div className="stars">{renderStars(ship.averageGrade)} </div></p>
                            <p className="entity-info location">{ship.location.address}, {ship.location.city}, {ship.location.country}</p>
                            <p className="entity-info description">{ship.description}</p>

                            {isLogged && <Link className="reservation-link">Make reservation</Link>}
                        </div>
                    </div>
                </div>
            )
        })
    ) : (<div><p>No results.</p></div>)

    return (
        <div>
            <div className="card search">
                <input className="search-input" type="search" placeholder="  Enter entity name" value={name} 
                    onChange={(e) => {
                        console.log(e.target.value)
                    setName(e.target.value);
                    setUrl(SERVER_URL + '/ships/search?name=' + name + "&letter=");
                    if (e.target.value === '') {
                        setUrl(SERVER_URL + '/ships');
                    }
                    }}></input>
                <input className="search-input" type="search" placeholder="  Enter entity location" value={location}></input>
                <input className="search-input" type="date"></input>
                <input className="search-input btn" type="submit" value="Search"></input>
            </div>
            <Letters letters={letters} parentCallback={handleCallback}/>
            <div className="row-entities">{allShips}</div>
            
        </div>
    )
}

export default Ships;