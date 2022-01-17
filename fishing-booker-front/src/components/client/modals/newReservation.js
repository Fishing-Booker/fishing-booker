import Modal from 'react-modal';
import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import axios from 'axios';
import moment from "moment";
import { useToasts } from "react-toast-notifications";

Modal.setAppElement('#root')
const NewReservation = ({modalIsOpen, setModalIsOpen, startOfPeriod, endOfPeriod, maxGuests}) => {
    const SERVER_URL = process.env.REACT_APP_API; 
    const [startDate, setStartDate] = useState('')
    const [endDate, setEndDate] = useState('')
    const [numberOfGuests, setNumberOfGuests] = useState(0)
    const [clientId, setClientId] = useState(0)
    const { id } = useParams()
    const [entityId, setEntityId] = useState(id)
    const { addToast } = useToasts();

    const [price, setPrice] = useState(0)
    const [services, setServices] = useState([]);
    const [servicesR, setServicesR] = useState([]);
    const [choosenServices, setChoosenServices] = useState([]);
    const [choosenServicesR, setChoosenServicesR] = useState("");
    const [additionalServices, setAdditionalServices] = useState("");
    const [regularService, setRegularService] = useState("");

    useEffect(() => {
        const headers = {'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.jwtToken}`}
        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
            .then(response => setClientId(response.data.id))

        axios.get(SERVER_URL + "/prices/additionalServices2/" + entityId, { headers: headers })
        .then(response => {
            setServices(response.data);
        })

        axios.get(SERVER_URL + "/prices/regularServices/" + entityId, { headers: headers })
            .then(response => {
                setServicesR(response.data);
        })
    }, [])

    const dto = {
        clientId,
        entityId,
        startDate,
        endDate,
        numberOfGuests,
        additionalServices,
        regularService,
        price
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

    const changeRegularServiceFormat = () => {
        var pom = choosenServicesR[0].split(" ");
        var regService = "";
        for(let i=0; i < pom.length-1; i++) {
            regService += pom[i] + " ";
        }
        regService = regService.substring(0, regService.length-1);
        return regService;
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
        console.log(moment(startDate).format())
        console.log(moment(endDate).format())
        dto.additionalServices = setServicesAsString();
        dto.regularService = changeRegularServiceFormat();
        console.log(dto)
        if (isInRangeOne(moment(startDate).format()) && isInRangeOne(moment(endDate).format()) && numberOfGuests <= maxGuests) {
            axios.post(SERVER_URL + "/reservations/makeReservation", dto)
                .then(response => {
                    addToast("Successfull reservation! You will get an email with additional information.", { appearance: "success" });
                    setTimeout(() => {
                        setModalIsOpen(false)
                        window.location.reload();
                      }, 3000)
                })
        } else if (numberOfGuests > maxGuests) {
            addToast("It is not possible to make a reservation for that number of guests! Please try again!", { appearance: "error" });
        } else {
            addToast("It is not possible to make a reservation in selected period! Please try again!", { appearance: "error" });
        }
        setStartDate('');
        setEndDate('');
    }

    const isDateInRage = (startDate, endDate) => (dateToCheck) => {
        return dateToCheck >= startDate && dateToCheck <= endDate
    }

    const isInRangeOne = isDateInRage(moment(startOfPeriod).format(), moment(endOfPeriod).format())

    return (
        <div >
            <Modal isOpen={modalIsOpen}
            shouldCloseOnEsc={true}
            onRequestClose={() => setModalIsOpen(false)} >
                <div className='make-res-modal' >
                    <h4 style={{marginLeft: '9%'}}>Please enter reservation details:</h4>
                    <div style={{borderBottom: '2px solid cadetblue', padding: '5px', width: '50vw', marginLeft: '38px'}}></div>
                    <br></br>
                    <p className='modal-info-res'>Start date:</p>
                    <input className="reservation-date modal" type="datetime-local" value={startDate} onChange={(e) => {setStartDate(e.target.value); } }></input>
                    <p className='modal-info-res'>End date:</p>
                    <input className="reservation-date modal" type="datetime-local" value={endDate} onChange={(e) => {setEndDate(e.target.value); }}></input> 
                    <p className='modal-info-res'>Number of guests:</p>
                    <input className="reservation-date modal" type="number" value={numberOfGuests} onChange={(e) => setNumberOfGuests(e.target.value)} ></input> 
                    <a  className="reservation-link" onClick={() => handleSubmit()}>Make reservation</a>
                    <p className='modal-info-res'>Regular service:</p>
                    <select className="reservation-date modal" value={choosenServicesR} onChange={(e) => handleSelectChangeR(e)}>
                        <option></option>
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
                </div>
            </Modal>
        </div>
    )
}

export default NewReservation;