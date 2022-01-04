import axios from "axios";
import { useState, useEffect } from "react";
import Search from "../search";
import star from "../../images/star.png";
import { Link } from "react-router-dom";

const Adventures = () => {
    const SERVER_URL = process.env.REACT_APP_API;
    const [adventures, setAdventures] = useState([])
    const [isLogged, setIsLogged] = useState(false);
    
    useEffect(() => {
        let token = localStorage.getItem('jwtToken');
        if (token != null) {
            setIsLogged(true)
        } else {
            setIsLogged(false)
        }
        
        axios.get(SERVER_URL + '/adventures')
            .then(response => {setAdventures(response.data); console.log(response.data);})
    }, [])

    const renderStars = (grade) => {
        let stars = []
        for (var i = 0; i < parseInt(grade); i++) {
            console.log(grade)
            stars.push(<img src={star}/>)
        }
        return stars;
    }

    const allAdventures = adventures.length ? (
        adventures.map((adventure, index) => {
            return(
                <div className="col" key={index}>
                    <div className="card lodge">
                        <div className="info"> <br></br>
                            <p className="entity-info name">{adventure.name} <div className="stars">{renderStars(adventure.averageGrade)} </div></p>
                            <p className="entity-info location">{adventure.location.address}, {adventure.location.city}, {adventure.location.country}</p>
                            <p className="entity-info description">{adventure.description}</p>

                            {isLogged && <Link className="reservation-link">Make reservation</Link>}
                        </div>
                    </div>
                </div>
            )
        })
    ) : (<div><p>No results.</p></div>)

    return (
        <div>
            <Search/> <br></br>
            <div className="row-entities">{allAdventures}</div>
            
        </div>
    )
}

export default Adventures;