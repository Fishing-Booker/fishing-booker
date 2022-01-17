import React, { useEffect, useState } from 'react'
import { Link } from "react-router-dom";
import '../../css/addingForm.css'
import Modal from 'react-modal';
import axios from 'axios';

const AddLodgeReservationPeriod = ({modalIsOpen, setModalIsOpen, entityId}) => {

    const SERVER_URL = process.env.REACT_APP_API; 

    const [user, setUser] = useState([]);

    const [startDate, setStartDate] = useState("");
    const [endDate, setEndDate] = useState("");

    const [start, setStart] = useState("");

    useEffect(() => {
        var start = new Date();
        start = generateDate(start);
        setStart(start);

        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
            .then(response => {
                setUser(response.data);
                var user = response.data;
                console.log(user.id)
            })
    }, [modalIsOpen])

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

    const newPeriod = {
        owner: user.id,
        startDate, 
        endDate,
        entityId
    }

    const addPeriod = () => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.post(SERVER_URL + "/periods/addReservationPeriod", newPeriod, {headers: headers})
          .then(response => {
            setModalIsOpen(false)
            window.location.reload();
        });
    }

   return (
       <div>
            <Modal className="fullscreen" isOpen={modalIsOpen}
            shouldCloseOnEsc={true}
            onRequestClose={() => setModalIsOpen(false)} >
                <div id="addlodgeReservationPeriod" className="adding-wrapper">
                    <div className="right">
                        <div className="info">
                            <h3>DEFINE NEW RESERVATION PERIOD</h3>
                            <div className="info_data">
                                <div className="data">
                                    <h4>Period start date and time:</h4>
                                    <input type="datetime-local" min={start} required onChange={(e) => {setStartDate(e.target.value)}}  value={startDate}/>
                                </div>
                                <div className="data">
                                    <h4>Period end date and time:</h4>
                                    <input type="datetime-local" min={start} required onChange={(e) => {setEndDate(e.target.value)}}  value={endDate}/>
                                </div>
                                <button class="reservation-period-btn" onClick={() => addPeriod()}>
                                    Add
                                </button><br/> <br/>
                            </div>
                        </div> 
                    </div>
                </div>
            </Modal>
        </div>
   )
    
}

export default AddLodgeReservationPeriod;