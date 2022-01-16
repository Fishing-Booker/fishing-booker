import { useParams } from "react-router-dom";
import axios from "axios";
import { useState, useEffect } from "react";
import { format } from "date-fns";

const LodgeReservationActions = () => {
    const { id } = useParams();
    const SERVER_URL = process.env.REACT_APP_API;
    const [actions, setActions] = useState([])
    const [user, setUser] = useState([]);
    const [isBooked, setIsBooked] = useState(false)

    useEffect(() => {
        axios.get(SERVER_URL + "/actions/available/" + id)
            .then(response => {setActions(response.data); console.log(response.data)})

        const headers = {'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.jwtToken}`}
        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
            .then(response => {
                setUser(response.data);
            })
    }, [!isBooked])

    const handleClick = (actionId) => {
        var dto = { actionId: actionId, clientId: user.id}
        console.log(dto)
        axios.post(SERVER_URL + "/actions/makeReservation", dto)
            .then(response => setIsBooked(true))
    }

    const allActions = actions.length ? (
        actions.map((action, index) => {
            return (
                <div className="col" key={index}>
                    <div className="card res-actions-div">
                        <div className="info"> <br></br>
                            <p className="entity-info name">Action #{action.actionId}</p>
                            <a className="subscribe-link" onClick={() => handleClick(action.actionId)}>make reservation</a>
                            <div style={{borderBottom: '2px solid cadetblue', padding: '5px', width: '47vw', marginLeft: '15px'}}></div>
                            <p style={{color: 'black', fontSize: '17px', marginLeft: '50px', marginTop: '20px'}}>Action is available in a period:</p>
                            <p style={{color: 'black', fontWeight: '700', fontSize: '15px', marginLeft: '55px', marginTop: '15px'}}> {format(action.startDate, 'dd.MM.yyyy')} - {format(action.endDate, 'dd.MM.yyyy.')}</p>
                            <p style={{color: 'cadetblue', fontWeight: '450', fontSize: '15px', marginLeft: '55px', marginTop: '15px'}}>Max number of guests: {action.maxPersons}</p>
                            <p style={{color: 'cadetblue', fontWeight: '450', fontSize: '15px', marginLeft: '55px', marginTop: '15px'}}>Additional services: {actions.additionalServices}</p>
                            <p style={{color: 'black', fontWeight: '700', fontSize: '15px', marginLeft: '55px', marginTop: '15px'}}>{action.price},00$</p>
                        </div>
                    </div>
                </div>
            )
        })
    ) : (<div><p style={{marginLeft: '30px'}}>There are no available actions.</p></div>)

    return (
        <div>
            <div style={{top:'0',
            marginTop: '170px',
            marginLeft: '50px',
            position: 'absolute'}}>
            <h1 className="title-reservation">Reservation actions</h1> <br></br>
            </div>
            {allActions}
        </div>
    )
}

export default LodgeReservationActions;