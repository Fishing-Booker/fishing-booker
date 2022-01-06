import axios from "axios";
import { useState, useEffect } from "react";
import star from "../../images/star.png";
import { Link } from "react-router-dom";
import Letters from "../letters";

const Lodges = () => {
    const SERVER_URL = process.env.REACT_APP_API;
    const [lodges, setLodges] = useState([])
    const [isLogged, setIsLogged] = useState(false);
    const [name, setName] = useState('');
    const [location, setLocation] = useState('');
    const [letters, setLetters] = useState([])
    const [url, setUrl] = useState(SERVER_URL + '/lodges');

    useEffect(() => {
        let token = localStorage.getItem('jwtToken');
        if (token != null) {
            setIsLogged(true)
        } else {
            setIsLogged(false)
        }
        axios.get(SERVER_URL + "/lodges/letters")
            .then(response => setLetters(response.data));

        axios.get(url)
            .then(response => {setLodges(response.data); console.log(response.data);})
    }, [url])

    const renderStars = (grade) => {
        let stars = []
        for (var i = 0; i < parseInt(grade); i++) {
            stars.push(<img key={i} src={star}/>)
        }
        return stars;
    }

    const handleCallback = (childData) => {
        if (childData === '') {
            setUrl(SERVER_URL + '/lodges');
        } else {
            setUrl(SERVER_URL + '/lodges/search?name=' + name + '&letter=' + childData)
        }
        
    }

    const allLodges = lodges.length ? (
        lodges.map((lodge, index) => {
            return(
                <div className="col" key={index}>
                    <div className="card lodge">
                        <div className="info"> <br></br>
                            <p className="entity-info name">{lodge.name} <div className="stars">{renderStars(lodge.averageGrade)} </div></p>
                            <p className="entity-info location">{lodge.location.address}, {lodge.location.city}, {lodge.location.country}</p>
                            <p className="entity-info description">{lodge.description}</p>

                            {isLogged && <Link to={`/lodge-reservation/${lodge.id}`} className="reservation-link">Make reservation</Link>}
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
                    setUrl(SERVER_URL + '/lodges/search?name=' + name + "&letter=");
                    if (e.target.value === '') {
                        setUrl(SERVER_URL + '/lodges');
                    }
                    }}></input>
                <input className="search-input" type="search" placeholder="  Enter entity location" value={location}></input>
                <input className="search-input" type="date"></input>
                <input className="search-input btn" type="submit" value="Search"></input>
            </div>
            <Letters letters={letters} parentCallback={handleCallback}/>
            <div className="row-entities">{allLodges}</div>
                    
        </div>
    )
}

export default Lodges;