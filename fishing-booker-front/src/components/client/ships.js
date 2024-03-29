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
    const [date, setDate] = useState('')
    const [grade, setGrade] = useState(null)

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
            .then(response => setShips(response.data))
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
            setUrl(SERVER_URL + '/ships/search?name=' + name + '&letter=' + childData + "&location=" + location + "&grade=" + grade)
        }
    }

    const getByDate = (date) => {
        var dto = { date }
        axios.post(SERVER_URL + '/ships/byDate', dto)
            .then(response => setShips(response.data))
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

                            {isLogged && <Link to={`/ship-reservation/${ship.id}`} className="reservation-link">Make reservation</Link>}
                        </div>
                    </div>
                </div>
            )
        })
    ) : (<div><p>No results.</p></div>)

    const handleChange = (e) => {
        setUrl(SERVER_URL + '/ships/search?name=' + name + "&letter=" + "&location=" + location + "&grade=" + e.target.value)
    }

    const handleSort = (e) => {
        setUrl(SERVER_URL + '/ships/sort?type=' + e.target.value);
    }

    return (
        <div>
            <div className="card search">
                <input className="search-input" type="search" placeholder="  Enter entity name" value={name} 
                    onChange={(e) => {
                        console.log(e.target.value)
                        setName(e.target.value);
                        setUrl(SERVER_URL + '/ships/search?name=' + name + "&letter=" + "&location=" + location);
                        if (e.target.value === '') {
                            setUrl(SERVER_URL + '/ships');
                        }
                    }}></input>
                <input className="search-input" type="search" placeholder="  Enter entity location" value={location}
                    onChange={(e) => {
                        console.log(e.target.value)
                        setLocation(e.target.value);
                        setUrl(SERVER_URL + '/ships/search?name=' + name + "&letter=" + "&location=" + location)
                        if (e.target.value === '') 
                            setUrl(SERVER_URL + '/ships');
                    }}
                ></input>
                <select className="search-grade" value={grade} onChange={(e) => handleChange(e)}>
                    <option>Select ship grade</option>
                    <option value='1'>1</option>
                    <option value='2'>2</option>
                    <option value='3'>3</option>
                    <option value='4'>4</option>
                    <option value='5'>5</option>
                </select>
                <select className="search-grade" onChange={(e) => handleSort(e)}>
                    <option>Sort ships</option>
                    <option value='gradeA'>By grade (ascending)</option>
                    <option value='gradeD'>By grade (descending)</option>
                    <option value='nameA'>By name (ascending)</option>
                    <option value='nameD'>By name (descending)</option>
                </select>
            </div>
            {isLogged && <div className="card search">
                <input className="search-input" type="datetime-local" value={date} onChange={(e) => setDate(e.target.value)}></input>
                <input className="search-input" placeholder=" Enter number of guests"></input> 
                <input className="search-input btn" type="submit" value="See available reservations" onClick={() => getByDate(date)}></input>
            </div>}
            <Letters letters={letters} parentCallback={handleCallback}/>
            <div className="row-entities">{allShips}</div>
            
        </div>
    )
}

export default Ships;