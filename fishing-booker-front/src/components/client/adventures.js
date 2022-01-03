import axios from "axios";
import { useState, useEffect } from "react";
import Search from "../search";

const Adventures = () => {
    const SERVER_URL = process.env.REACT_APP_API;
    const [adventures, setAdventures] = useState([])

    useEffect(() => {
        axios.get(SERVER_URL + '/adventures')
            .then(response => {setAdventures(response.data); console.log(response.data);})
    }, [])

    const allAdventures = adventures.length ? (
        adventures.map((adventure, index) => {
            return(
                <div className="col" key={index}>
                    <div className="card lodge">
                        <p className="lodge-info">{adventure.name}</p>
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