import axios from "axios";
import { useState, useEffect } from "react";
import star from "../../images/star.png";
import { Link } from "react-router-dom";
import Letters from "../letters";

const Adventures = () => {
    const SERVER_URL = process.env.REACT_APP_API;
    const [adventures, setAdventures] = useState([])
    const [isLogged, setIsLogged] = useState(false);
    const [name, setName] = useState('');
    const [location, setLocation] = useState('');
    const [letters, setLetters] = useState([])
    const [url, setUrl] = useState(SERVER_URL + '/adventures');
    
    useEffect(() => {
        let token = localStorage.getItem('jwtToken');
        if (token != null) {
            setIsLogged(true)
        } else {
            setIsLogged(false)
        }

        axios.get(SERVER_URL + "/adventures/letters")
        .then(response => setLetters(response.data));
        
        axios.get(url)
            .then(response => {setAdventures(response.data); console.log(response.data);})
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
            setUrl(SERVER_URL + '/adventures');
        } else {
            setUrl(SERVER_URL + '/adventures/search?name=' + name + '&letter=' + childData)
        }
        
    }

    const allAdventures = adventures.length ? (
        adventures.map((adventure, index) => {
            return(
                <div className="col" key={index}>
                    <div className="card adventure">
                        <div className="info"> <br></br>
                            <p className="entity-info name">{adventure.name} <div className="stars">{renderStars(adventure.averageGrade)} </div></p>
                            <p className="entity-info location">{adventure.location.address}, {adventure.location.city}, {adventure.location.country}</p>
                            <p className="entity-info description">{adventure.description}</p>

                            {isLogged && <Link to={`/adventure-reservation/${adventure.id}`} className="reservation-link">Make reservation</Link>}
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
                    setUrl(SERVER_URL + '/adventures/search?name=' + name + "&letter=");
                    if (e.target.value === '') {
                        setUrl(SERVER_URL + '/adventures');
                    }
                    }}></input>
                <input className="search-input" type="search" placeholder="  Enter entity location" value={location}></input>
                <input className="search-input" type="date"></input>
                <input className="search-input btn" type="submit" value="Search"></input>
            </div>
            <Letters letters={letters} parentCallback={handleCallback}/>
            <div className="row-entities">{allAdventures}</div>
            
        </div>
    )
}

export default Adventures;