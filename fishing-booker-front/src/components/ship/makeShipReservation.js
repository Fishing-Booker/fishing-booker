import Modal from 'react-modal';
import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import axios from 'axios';
import moment from "moment";
import { useToasts } from "react-toast-notifications";
import { format, set } from "date-fns";
import '../../css/addingForm.css'

const MakeShipReservation  = ({modalIsOpen, setModalIsOpen, startOfPeriod, endOfPeriod, maxGuests, clientUsername, entityOfId}) => {
    const SERVER_URL = process.env.REACT_APP_API; 
    const [startDate, setStartDate] = useState("")
    const [endDate, setEndDate] = useState("")
    const [numberOfGuests, setNumberOfGuests] = useState(1)
    const [clientId, setClientId] = useState(0)
    const [entityId, setEntityId] = useState(entityOfId)
    const { addToast } = useToasts();
    const [minDate, setMinDate] = useState("");
    const [maxDate, setMaxDate] = useState("");
    const [price, setPrice] = useState(0);

    const [services, setServices] = useState([]);
    const [servicesR, setServicesR] = useState([]);
    const [choosenServices, setChoosenServices] = useState([]);
    const [choosenServicesR, setChoosenServicesR] = useState("");
    const [regularS, setRegularS] = useState("");
    const [additionalServices, setAdditionalServices] = useState("");
    const [regularService, setRegularService] = useState("");

    useEffect(() => {
        const headers = {'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.jwtToken}`}
        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
            .then(response => setClientId(response.data.id))
        
        axios.get(SERVER_URL + "/prices/additionalServices2/" + entityOfId, { headers: headers })
        .then(response => {
            setServices(response.data);
        })

        axios.get(SERVER_URL + "/prices/regularServices/" + entityOfId, { headers: headers })
            .then(response => {
                console.log(response.data);
                setServicesR(response.data);
                setChoosenServicesR(response.data[0]);
        })

        startOfPeriod = new Date(startOfPeriod)
        endOfPeriod = new Date(endOfPeriod)
        startOfPeriod = generateDate(startOfPeriod);
        endOfPeriod = generateDate(endOfPeriod);
        setMinDate(startOfPeriod);
        setMaxDate(endOfPeriod);
        setEntityId(entityOfId);
    }, [startOfPeriod, endOfPeriod, modalIsOpen])

    const generateDate = (date) => {
        var day = date.getDate();
        var month = date.getMonth()+1;
        var year = date.getFullYear();
        var hours = date.getHours();
        var minutes = date.getMinutes();
        if(getlength(day) === 1) {
            day = "0"+day;
        }
        if(getlength(month) === 1) {
            month = "0"+month;
        }
        if(getlength(hours) === 1) {
            hours = "0"+hours;
        }
        if(getlength(minutes) === 1) {
            minutes = "0"+minutes;
        }
        return year + '-' + month + '-' + day + 'T' + hours + ':' + minutes;
    }

    const getlength = (number) => {
        return number.toString().length;
    }

    const dto = {
        clientId: 5,
        entityId,
        startDate,
        endDate,
        numberOfGuests,
        price,
        additionalServices,
        regularService
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
        setRegularS(e.target.value);
        setChoosenServicesR(value);
        setPriceFunc(choosenServices, value);
    }

    const setPriceFunc = (_additional, _regular) => {
        var price = 0;
        var services = [];
        for(let i = 0; i < _additional.length; i++) {
            services.push(_additional[i]);
        }
        for(let i = 0; i < _regular.length; i++) {
            for(let j = 0; j < numberOfGuests; j++) {
                services.push(_regular[i]);
            }
            
        }
        for(let i = 0; i < services.length; i++) {
            var split1 = services[i].split(" ");
            var split2 = split1[split1.length-1].split("$")
            price += parseInt(split2[0]);
        }
        setPrice(price);
    }

    const handleSubmit = () => {
        if(startDate==="" || endDate === "" || choosenServicesR === []) {
            addToast("You have to set all fields!", { appearance: "error" });
        } else {
            dto.additionalServices = setServicesAsString();

            let end = regularS.lastIndexOf(" ");
            let reg = regularS.substring(0, end);

            dto.regularService = reg;
            console.log(dto);

            const headers = {'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.jwtToken}`}
            axios.post(SERVER_URL + "/reservations/addReservation", dto, {headers:headers})
            .then(response => {
                addToast("You made reservation successfully!", {appearance : "success"});
                setModalIsOpen(false);
                window.location.reload();
            })
        }
        

    }

    return (
        <div >
            <Modal isOpen={modalIsOpen}
            shouldCloseOnEsc={true}
            onRequestClose={() => setModalIsOpen(false)} >
                <div className='make-res-modal' >
                    <h4 style={{marginLeft: '9%'}}>Please enter reservation details:</h4>
                    <div style={{borderBottom: '2px solid cadetblue', padding: '5px', width: '50vw', marginLeft: '38px'}}></div>
                    <br></br>
                    <p className='modal-info-res'>Client:</p>
                    <input disabled className="reservation-date modal" type="text" value={clientUsername} />
                    <p className='modal-info-res'>Start date:</p>
                    <input className="reservation-date modal" min={minDate} max={maxDate} type="datetime-local" value={startDate} onChange={(e) => {setStartDate(e.target.value); } }></input>
                    <p className='modal-info-res'>End date:</p>
                    <input className="reservation-date modal" min={minDate} max={maxDate} type="datetime-local" value={endDate} onChange={(e) => {setEndDate(e.target.value); }}></input> 
                    <p className='modal-info-res'>Number of guests:</p>
                    <input className="reservation-date modal" min="1" type="number" value={numberOfGuests} max={maxGuests} onChange={(e) => setNumberOfGuests(e.target.value)} ></input> 
                    <p className='modal-info-res'>Regular service:</p>
                    <select className="reservation-date modal" value={choosenServicesR} onChange={(e) => handleSelectChangeR(e)}>
                        {servicesR.map((service) => (
                            <option>
                                {service.service_name} {service.price}$
                            </option>
                        ))}
                    </select>
                    <p className='modal-info-res'>Additional services:</p>
                    <select className="reservation-date modal" style={{height: '90px'}}  value={choosenServices} multiple onChange={(e) => handleSelectChange(e)}>
                        {services.map((service) => (
                            <option>
                                {service.service_name} {service.price}$
                            </option>
                        ))}
                    </select>
                    <p className='modal-info-res'>Price: {price}$</p>
                    <a  className="reservation-link2" onClick={() => handleSubmit()}>Make reservation</a>
                </div>
            </Modal>
        </div>
    )
}
export default MakeShipReservation;