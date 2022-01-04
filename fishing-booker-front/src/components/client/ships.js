import axios from "axios";
import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import star from "../../images/star.png";

const Ships = () => {
    const SERVER_URL = process.env.REACT_APP_API;
    const [ships, setShips] = useState([])
    const [isLogged, setIsLogged] = useState(false);

    useEffect(() => {
        let token = localStorage.getItem('jwtToken');
        if (token != null) {
            setIsLogged(true)
        } else {
            setIsLogged(false)
        }

        axios.get(SERVER_URL + '/ships')
            .then(response => {setShips(response.data); console.log(response.data);})
    }, [])

    const renderStars = (grade) => {
        let stars = []
        for (var i = 0; i < parseInt(grade); i++) {
            console.log(grade)
            stars.push(<img src={star}/>)
        }
        return stars;
    }

    const allShips = ships.length ? (
        ships.map((ship, index) => {
            return(
                <div className="col" key={index}>
                    <div className="card lodge">
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
            <br></br>
            <div className="row-entities">{allShips}</div>
            
        </div>
    )
}

export default Ships;