import axios from "axios";
import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import star from "../../images/star.png";
import { useToasts } from "react-toast-notifications";
import { format } from "date-fns";
import Modal from "react-modal";
import NewReservation from "./modals/newReservation";

Modal.setAppElement("#root")
const ShipReservation = () => {
    const SERVER_URL = process.env.REACT_APP_API;
    const [ship, setShip] = useState([]);
    const [ownerName, setOwnerName] = useState('')
    const [ownerSurname, setOwnerSurname] = useState('')
    const { id } = useParams();
    const [user, setUser] = useState([]);
    const [isSubscribed, setIsSubscribed] = useState(true)
    const [entityId, setEntityId] = useState(id)
    const { addToast } = useToasts();
    const [availablePeriods, setAvailablePeriods] = useState([])
    const [modalIsOpen, setModalIsOpen] = useState(false)
    const [start, setStart] = useState('')
    const [end, setEnd] = useState('')
    const [startDate, setStartDate] = useState('')
    const [endDate, setEndDate] = useState('')
    const [maxPersons, setMaxPersons] = useState('')
    const [images, setImages] = useState([])
    const [servicesAdditional, setServicesAdditional] = useState([]);
    const [servicesRegular, setServicesRegular] = useState([]);
    const [fishEq, setFishEq] = useState([]);
    const [navEq, setNavEq] = useState([]);

    useEffect(() => {
        const headers = {'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + "/ships/ship?id=" + id, { headers: headers })
            .then(response => {
                setShip(response.data); 
                setOwnerName(response.data.owner.name);
                setOwnerSurname(response.data.owner.surname);
                setEntityId(response.data.id)
                setMaxPersons(response.data.maxPersons)
            });

        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
            .then(response => {
                setUser(response.data); 
                var subscriberDTO = {entityId, userId: response.data.id}
                axios.post(SERVER_URL + "/subscribe/isSubscribed", subscriberDTO)
                    .then(response => setIsSubscribed(response.data))
            });

        axios.get(SERVER_URL + '/images/getImages/' + id, {headers:headers})
        .then(response => {
            setImages(response.data);
            console.log(response.data);
    
        });
        
        axios.get(SERVER_URL + "/prices/additionalServices2/" + id, { headers: headers })
            .then(response => {
                setServicesAdditional(response.data);
            });
        
        axios.get(SERVER_URL + "/prices/regularServices/" + id, { headers: headers })
                .then(response => {
                    setServicesRegular(response.data);
            });
            
        axios.get(SERVER_URL + '/fishEquipment/shipFishingEquipment/' + id, {headers: headers})
        .then(response => {
            setFishEq(response.data); 
            console.log(response.data)
        });

        axios.get(SERVER_URL + '/ships/shipNavEq/' + id, {headers: headers})
        .then(response => {
            setNavEq(response.data); 
            console.log(response.data)
        });
        
    }, [id, isSubscribed, modalIsOpen])

    const renderStars = (grade) => {
        let stars = []
        for (var i = 0; i < parseInt(grade); i++) {
            stars.push(<img key={i} src={star}/>)
        }
        return stars;
    }

    const handleSubscribe = (entityId, userId) => {
        const headers = {'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.jwtToken}`}
        var subscriberDTO = {entityId, userId}
        axios.post(SERVER_URL + "/subscribe", subscriberDTO, { headers: headers })
            .then(() => {
                addToast("You are successfully subscribed.", { appearance: "success" });
                setIsSubscribed(true);
            });
    }

    const handleUnsubscribe = (entityId, userId) => {
        const headers = {'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.jwtToken}`}
        axios.delete(SERVER_URL + "/subscribe/unsubscribe?entityId=" + entityId + "&userId=" + userId, { headers: headers })
            .then(() => {
                addToast("You are no longer subscribed.", { appearance: "success" });
                setIsSubscribed(false);
            })
    }

    const seeAvailableReservations = (startDate, endDate) => {
        const headers = {'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.jwtToken}`}
        var periodDTO = { entityId: id, startDate, endDate }
        console.log(periodDTO)
        axios.post(SERVER_URL + "/periods/availablePeriods", periodDTO, { headers: headers })
            .then(response => setAvailablePeriods(response.data))
    }

    const allImages = images.length ? (
        images.map(image => {
            return(
                <div className="card-image" key={image.imageId}>
                    <img src={image.base64} />
                </div>
            )
        }) 
    ): null;

    const regularServices = servicesRegular.length ? (
        servicesRegular.map((service, index) => {
            return (
                <div key={index}>
                    {service.service_name}
                </div>
            )
        })
    ) : (
        <div>
            None
        </div>
    );

    const additionalServices = servicesAdditional.length ? (
        servicesAdditional.map((service, index) => {
            return (
                <div key={index}>
                    {service.service_name}
                </div>
            )
        })
    ) : (
        <div>
            None
        </div>
    );

    
    const allFishEq = fishEq.length ? (
        fishEq.map(eq => {
            return (
                <div key={eq.id}>
                    * {eq.equipment}
                </div>
            )
        })
    ) : (
        <div>
            No fishing equipment.
        </div>
    );

    const allNavEq = navEq.length ? (
        navEq.map((eq, index) => {
            return (
                <div key={index}>
                    * {eq}
                </div>
            )
        })
    ) : (
        <div>
            No navigation equipment.
        </div>
    );
    
    const periods = availablePeriods.length ? (
        availablePeriods.map((period, index) => {
            return (
                <div key={index} className="period-card">
                    <p style={{color: 'black', fontSize: '17px', marginLeft: '50px', marginTop: '15px'}}>Available reservation in a period:</p>
                    <p style={{color: 'black', fontWeight: '600', fontSize: '15px', marginLeft: '55px', marginTop: '15px'}}> {format(period.startDate, 'dd.MM.yyyy')} - {format(period.endDate, 'dd.MM.yyyy.')}</p>
                    <a className="reservation-link" onClick={() => {setModalIsOpen(true); setStart(period.startDate); setEnd(period.endDate)}}>book ship</a>
                </div>
            )
        })
    ) : (<div><p style={{color: 'black', fontSize: '17px', marginLeft: '50px', marginTop: '15px'}}>No available reservations for choosen period.</p></div>)

    return (
        <div className="card entity-details">
            {isSubscribed && <a className="subscribe-link" onClick={() => handleUnsubscribe(ship.id, user.id)}>Unsubscribe</a>}
            {!isSubscribed && <a className="subscribe-link" onClick={() => handleSubscribe(ship.id, user.id)}>Subscribe</a>}
            <p className="entity-info name" style={{marginTop: '2%'}}>{ship.name}
                <div className="stars">{renderStars(ship.averageGrade)} </div>
            </p> <br></br>
            <p className="entity-info description">{ship.description}</p>
            <p className="entity-info description">Owner: {ownerName} {ownerSurname}</p>
            <p className="entity-info description">Max number of persons: {maxPersons}</p>
            <p className="entity-info description">Price: 1,000.00 </p>
            <p className="entity-info description">Regular services: {regularServices}</p>
            <p className="entity-info description">Additional services: {additionalServices}</p>
            <p className="entity-info description">Fishing equipment: {allFishEq}</p>
            <p className="entity-info description">Navigation equipment: {allNavEq}</p>
            <div className="info_data-images">
                { allImages }
            </div>
            <br></br>
            <h4 style={{marginLeft: '3%'}}>Please enter reservation details:</h4>
            <div style={{borderBottom: '2px solid cadetblue', padding: '5px', width: '50vw', marginLeft: '30px'}}></div>
            <br></br>
            <input className="reservation-date" type="datetime-local" value={startDate} onChange={(e) => setStartDate(e.target.value)}></input>
            <input className="reservation-date" type="datetime-local" value={endDate} onChange={(e) => setEndDate(e.target.value)}></input>
            <a className="available-dates"  onClick={() => seeAvailableReservations(startDate, endDate)}>See available dates</a>
            <Link to={`/ship-actions/${id}`} className="available-dates" >See available actions</Link>
            <br></br> <br></br>
            {periods}
            <NewReservation modalIsOpen={modalIsOpen} setModalIsOpen={setModalIsOpen} startOfPeriod={start} endOfPeriod={end} maxGuests={maxPersons}/>
        </div>
    )
}

export default ShipReservation;