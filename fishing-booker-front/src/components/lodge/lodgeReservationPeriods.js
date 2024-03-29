import React, { useEffect, useState } from 'react'
import { Link } from "react-router-dom";
import '../../css/addingForm.css'
import Modal from 'react-modal';
import axios from 'axios';
import { useToasts } from "react-toast-notifications";
import deleteImg from '../../images/trash.png';
import { format } from 'date-fns';

const LodgeReservationPeriods = ({modalIsOpen, setModalIsOpen, entityId}) => {

    const SERVER_URL = process.env.REACT_APP_API; 

    const { addToast } = useToasts();

    const [user, setUser] = useState([]);

    const [startDate, setStartDate] = useState("");
    const [endDate, setEndDate] = useState("");

    const [minDate, setMinDate] = useState("");

    const [periods, setPeriods] = useState([]);

    useEffect(() => {

        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + "/periods/entityPeriods/" + entityId, { headers: headers })
            .then(response => {
                var periods = response.data;
                for(let p of periods){
                    p.startDate = format(p.startDate, 'dd.MM.yyyy. kk:mm');
                    p.endDate = format(p.endDate, 'dd.MM.yyyy. kk:mm');
                }
                setPeriods(periods);
            });

        var start = new Date();
        start = generateDate(start);
        setMinDate(start);

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
        
        if(newPeriod.startDate == "" || newPeriod.endDate == ""){
            addToast("Start and end date are required!", { appearance: "error" });
        } else {

            var start = new Date(startDate);
            var end = new Date(endDate);
            if(start > end){
                addToast("Start date has to be before end date!", { appearance: "error" });
            } else {
                var min = new Date();
                if(start < minDate || end < minDate){
                    addToast("Start date and end date have to be after current time!", { appearance: "error" });
                } else if(start.getDate() == min.getDate() && start.getMonth()+1 == min.getMonth()+1 && start.getFullYear() == min.getFullYear() && start.getHours() < min.getHours()){
                    addToast("Start date has to be after current time!", { appearance: "error" });
                } else {
                    axios.post(SERVER_URL + "/periods/addReservationPeriod", newPeriod, {headers: headers})
                        .then(response => {
                            setModalIsOpen(false);
                            window.location.reload();
                        });
                }
                
            }
        }
        
    }

    const deletePeriod = (id) => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + "/periods/checkIsPeriodFree/" + id, {headers: headers})
        .then(response => {
            var available = response.data;
            if(available === true){
                axios.delete(SERVER_URL + "/periods/deletePeriod/" + id + "/" + entityId, { headers: headers })
                .then(response => {
                    setModalIsOpen(false);
                    window.location.reload();
                })
            } else {
                addToast("It is not available to delete this period, because there are active reservations in it!", { appearance: "error" });
            }
        })

        
    }


    const allPeriods = periods.length ? (
        periods.map(period => {
            return(
                <div className='res-period-form' key={period.id}>
                    <div className='res-period'>
                        <label style={{'font-weight': 'bold'}}>Start date: </label>
                        {period.startDate}<br/>
                        <label style={{'font-weight': 'bold'}}>End date: </label>
                        {period.endDate}<br/>
                    </div>
                    <button className='rules-btn' onClick={() => deletePeriod(period.id)}>
                        <img src={deleteImg} />
                    </button>
                </div>
            )
        })
        ) : (
            <div>This lodge doesn't have defined periods...</div>
        );

   return (
       <div>
            <Modal className="fullscreen" isOpen={modalIsOpen}
            shouldCloseOnEsc={true}
            onRequestClose={() => setModalIsOpen(false)} >
                <div id="lodgeReservationPeriods" className="adding-wrapper">
                    <div className="right">
                        <div className="info">
                            <h3>RESERVATION PERIODS</h3>
                            {allPeriods}
                            <br/>

                            <div className="info_data">
                                <div className="data">
                                    <h4>Period start date and time:</h4>
                                    <input type="datetime-local" min={minDate} required onChange={(e) => {setStartDate(e.target.value)}}  value={startDate}/>
                                </div>
                                <div className="data">
                                    <h4>Period end date and time:</h4>
                                    <input type="datetime-local" min={minDate} required onChange={(e) => {setEndDate(e.target.value)}}  value={endDate}/>
                                </div>
                                <div className="buttons">
                                    <button className="cancel" onClick={() => setModalIsOpen(false)}>
                                        Cancel
                                    </button>
                                    <button className="add" onClick={() => addPeriod()}>
                                        Add
                                    </button><br/>
                                </div><br/><br/>
                            </div>
                        </div> 
                    </div>
                </div>
            </Modal>
        </div>
   )
    
}

export default LodgeReservationPeriods;