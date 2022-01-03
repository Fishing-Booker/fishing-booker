import axios from "axios";
import { useState, useEffect } from "react";

const Lodges = () => {
    const SERVER_URL = process.env.REACT_APP_API;
    const [lodges, setLodges] = useState([])

    useEffect(() => {
        axios.get(SERVER_URL + '/lodges')
            .then(response => {setLodges(response.data); console.log(response.data);})
    }, [])

    const allLodges = lodges.length ? (
        lodges.map((lodge, index) => {
            return(
                <div className="col">
                    <div key={index}>
                        <div className="card lodge">
                            <p className="lodge-info">{lodge.name}</p>
                        </div> 
                    </div>
                </div>
            )
        })
    ) : (<div><p>No results.</p></div>)

    return (
        <div>
            {allLodges}
        </div>
    )
}

export default Lodges;