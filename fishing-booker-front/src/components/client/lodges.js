import axios from "axios";
import { useState, useEffect } from "react";
import Search from "../search";
import star from "../../images/star.png";
import { Link } from "react-router-dom";

const Lodges = () => {
    const SERVER_URL = process.env.REACT_APP_API;
    const [lodges, setLodges] = useState([])

    useEffect(() => {
        axios.get(SERVER_URL + '/lodges')
            .then(response => {setLodges(response.data); console.log(response.data);})
    }, [])

    const renderStars = (grade) => {
        let stars = []
        for (var i = 0; i < parseInt(grade); i++) {
            console.log(grade)
            stars.push(<img src={star}/>)
        }
        return stars;
    }

    const allLodges = lodges.length ? (
        lodges.map((lodge, index) => {
            return(
                <div className="col" key={index}>
                    <div className="card entity">
                        <div className="info"> <br></br>
                            <p className="entity-info name">{lodge.name} <div className="stars">{renderStars(lodge.averageGrade)} </div></p>
                            <p className="entity-info location">{lodge.location.address}, {lodge.location.city}, {lodge.location.country}</p>
                            <p className="entity-info description">{lodge.description}</p>

                            <Link className="reservation-link">Make reservation</Link>
                        </div>
                    </div>
                </div>
            )
        })
    ) : (<div><p>No results.</p></div>)

    return (
        <div>
            <Search/> <br></br>
            <div className="row-entities">{allLodges}</div>
            
        </div>
    )
}

export default Lodges;