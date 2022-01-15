import React, { useEffect, useState } from 'react'
import { Link, useParams} from "react-router-dom";
import { withRouter } from 'react-router-dom';
import '../../css/addingForm.css'
import Modal from 'react-modal';
import axios from 'axios';

const AddShipAction = ({modalIsOpen, setModalIsOpen, shipId}) => {

    const SERVER_URL = process.env.REACT_APP_API; 

    const [services, setServices] = useState([]);
    const [choosenServices, setChoosenServices] = useState([]);

    const [maxPersons, setMaxPersons] = useState("");
    const [startDate, setStartDate] = useState("");
    const [endDate, setEndDate] = useState("");
    const [price, setPrice] = useState("");
    const [user, setUser] = useState("");
    const [additionalServices, setAdditionlServices] = useState("");

    useEffect(() => {
        const headers = {'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.jwtToken}`}
        
        axios.get(SERVER_URL + "/prices/additionalServices/" + shipId, { headers: headers })
            .then(response => {
                setServices(response.data);
                
                axios.get(SERVER_URL + "/ships/ship/" + shipId, {headers: headers})
                    .then(response => {
                        var lodge = response.data;
                        setMaxPersons(lodge.maxPersons);

                        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
                            .then(response => {
                                setUser(response.data);
                            })
                    })
            })
    }, [])

    const newAction = {
        owner : user.id, 
        entityId : shipId,
        startDate,
        endDate,
        price,
        additionalServices,
        maxPersons
    }

    const addAction = () => {
        var services = setServicesAsString();
        newAction.additionalServices = services;

        const headers = {'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.jwtToken}`}
        
        axios.post(SERVER_URL + "/actions/addAction/", newAction, { headers: headers })
            .then(response => {
                setModalIsOpen(false);
                window.location.reload();
            })
    }

    const setServicesAsString = () => {
        var services = "";
        for(let service of choosenServices){
            services += service;
            services += "#";
        }
        services = services.substring(0, services.length - 1);
        return services;
    }

    const handleSelectChange = (e) => {
        let value = Array.from(e.target.selectedOptions, option => option.value);
        setChoosenServices(value);
    }

   return (
       <div>
            <Modal className="fullscreen" isOpen={modalIsOpen}
            shouldCloseOnEsc={true}
            onRequestClose={() => setModalIsOpen(false)} >
                <div id="addLodgeAction" className="adding-wrapper">
                    <div className="right">
                        <div className="info">
                            <h3>ADD NEW ACTION</h3>
                            <div className="info_data">
                                <div className="data">
                                    <h4>Reservation start:</h4>
                                    <input type="datetime-local" required onChange={(e) => {setStartDate(e.target.value)}}  value={startDate}/>
                                </div>
                                <div className="data">
                                    <h4>Reservation end:</h4>
                                    <input type="datetime-local" required onChange={(e) => {setEndDate(e.target.value)}}  value={endDate}/>
                                </div>
                                <div className="data">
                                    <h4>Number of persons:</h4>
                                    <input type="number" min="1" step="1" required onChange={(e) => {setMaxPersons(e.target.value)}}  value={maxPersons}/>
                                </div>
                                <div className="data">
                                    <h4>Additional services:</h4>
                                    <select style={{height: '90px'}}  value={choosenServices} multiple onChange={(e) => handleSelectChange(e)}>
                                        {services.map((service) => (
                                            <option>
                                                {service}
                                            </option>
                                        ))}
                                    </select>
                                </div>
                                <div className="data">
                                    <h4>Price:</h4>
                                    <input type="number" min="0" required onChange={(e) => {setPrice(e.target.value)}}  value={price}/>
                                </div>
                                <button onClick={() => addAction()}>
                                    Add
                                </button>
                            </div> <br/> <br/>
                        </div>
                    </div>
                </div>
            </Modal>
        </div>
   )
    
}

export default AddShipAction;