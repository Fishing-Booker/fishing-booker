import axios from "axios";
import { useState, useEffect } from "react";
import Search from "../search";

const Ships = () => {
    const SERVER_URL = process.env.REACT_APP_API;
    const [ships, setShips] = useState([])

    useEffect(() => {
        axios.get(SERVER_URL + '/ships')
            .then(response => {setShips(response.data); console.log(response.data);})
    }, [])

    const allShips = ships.length ? (
        ships.map((ship, index) => {
            return(
                <div className="col" key={index}>
                    <div className="card lodge">
                        <div className="info">
                            <p className="lodge-info">{ship.name}</p> 
                        </div>
                    </div>
                </div>
            )
        })
    ) : (<div><p>No results.</p></div>)

    return (
        <div>
            <Search/> <br></br>
            <div className="row-entities">{allShips}</div>
            
        </div>
    )
}

export default Ships;