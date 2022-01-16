import React, { useEffect, useState } from 'react'
import { Link, BrowserRouter as Router, Route, Switch } from "react-router-dom";
import { withRouter } from 'react-router-dom';
import '../../css/addingForm.css'
import Modal from 'react-modal';
import { DateTimePickerComponent} from "@syncfusion/ej2-react-calendars"
import axios from 'axios';

const AddAdventureActionFrom = ({modalIsOpen, setModalIsOpen, adventureId}) => {

    const SERVER_URL = process.env.REACT_APP_API; 

    const [services, setServices] = useState([]);
    const [servicesR, setServicesR] = useState([]);
    const [choosenServices, setChoosenServices] = useState([]);
    const [choosenServicesR, setChoosenServicesR] = useState("");

    const [regularServices, setRegularServices] = useState([]);
    const [price2, setPrice2] = useState(0);
    const [adventure, setAdventure] = useState("");

    const [maxPersons, setMaxPersons] = useState(0);
    const [startDate, setStartDate] = useState("");
    const [endDate, setEndDate] = useState("");
    const [price, setPrice] = useState(0);
    const [user, setUser] = useState("");
    const [additionalServices, setAdditionlServices] = useState("");

    useEffect(() => {
        const headers = {'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.jwtToken}`}
        
        axios.get(SERVER_URL + "/prices/additionalServices2/" + adventureId, { headers: headers })
            .then(response => {
                setServices(response.data);
                axios.get(SERVER_URL + "/prices/regularServices/" + adventureId, { headers: headers })
                .then(response => {
                    setServicesR(response.data);
                })
                
                axios.get(SERVER_URL + "/adventures/adventure/" + adventureId, {headers: headers})
                    .then(response => {
                        var adventure = response.data;
                        setMaxPersons(adventure.maxPersons);
                        setAdventure(response.data);
                        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
                            .then(response => {
                                setUser(response.data);
                            })
                    })
            })
    }, [])

    const newAction = {
        owner : user.id, 
        entityId : adventureId,
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
        for(let choosenService of choosenServices){
            let service = choosenService.split(" ")
            services += service[0];
            services += "#";
        }
        services = services.substring(0, services.length - 1);
        console.log(services)
        return services;
    }

    const handleSelectChange = (e) => {
        let value = Array.from(e.target.selectedOptions, option => option.value);
        setChoosenServices(value);
        setPriceFunc(value, choosenServicesR);
    }

    const handleSelectChangeR = (e) => {
        let value = Array.from(e.target.selectedOptions, option => option.value);
        setChoosenServicesR(value);
        setPriceFunc(choosenServices, value);
    }

    const setPriceFunc = (_additional, _regular) => {
        var price = 0;
        var services = [];
        for(let i = 0; i < _additional.length; i++) {
            services.push(_additional[i]);
        }
        if(_regular[0] !== "") {
            for(let i = 0; i < _regular.length; i++) {
                for(let j = 0; j < maxPersons; j++) {
                    services.push(_regular[i]);
                }
                
            }
        }
        for(let i = 0; i < services.length; i++) {
            var split1 = services[i].split(" ");
            var split2 = split1[split1.length-1].split("$")
            price += parseInt(split2[0]);
        }
        setPrice(price);
    }

    return (
       <div>
            <Modal className="fullscreen" isOpen={modalIsOpen}
            shouldCloseOnEsc={true}
            onRequestClose={() => setModalIsOpen(false)} 
            ariaHideApp={false}>
                <form onSubmit={addAction}>
                    <div id="addLodgeAction" className="adding-wrapper">
                        <div className="right">
                            <div className="info">
                                <h3>ADD NEW ACTION</h3>
                                <div className="info_data">
                                    <div className="data">
                                        <h4>Action start:</h4>
                                        <input type="datetime-local" required onChange={(e) => {setStartDate(e.target.value)}}  value={startDate}/>
                                    </div>
                                    <div className="data">
                                        <h4>Action end:</h4>
                                        <input type="datetime-local" required onChange={(e) => {setEndDate(e.target.value)}}  value={endDate}/>
                                    </div>
                                    <div className="data">
                                        <h4>Number of persons:</h4>
                                        <input type="number" min="1" step="1" max={adventure.maxPersons} required onChange={(e) => {setMaxPersons(e.target.value)}}  value={maxPersons}/>
                                    </div>
                                    <div className="data">
                                        <h4>Regular services:</h4>
                                        <select  value={choosenServicesR} onChange={(e) => handleSelectChangeR(e)}>
                                            <option></option>
                                            {servicesR.map((service) => (
                                                <option>
                                                    {service.service_name} {service.price}$
                                                </option>
                                            ))}
                                        </select>
                                    </div>
                                    <div className="data">
                                        <h4>Additional services:</h4>
                                        <select style={{height: '90px'}}  value={choosenServices} multiple onChange={(e) => handleSelectChange(e)}>
                                            {services.map((service) => (
                                                <option>
                                                    {service.service_name} {service.price}$
                                                </option>
                                            ))}
                                        </select>
                                    </div>
                                    <div className="data">
                                        <h4>Price:</h4>
                                        <label>{price}$</label>
                                    </div>
                                    <input type="submit" className="submit"/>
                                </div> <br/> <br/>
                            </div>
                        </div>
                    </div>
                </form>
            </Modal>
        </div>
   )
    
}

export default AddAdventureActionFrom;