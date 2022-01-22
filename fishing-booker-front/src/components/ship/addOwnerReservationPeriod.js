import React, { useEffect, useState } from 'react'
import { Link } from "react-router-dom";
import '../../css/addingForm.css'
import Modal from 'react-modal';
import axios from 'axios';
import { format } from 'date-fns';
import { useToasts } from "react-toast-notifications";

const AddOwnerReservationPeriod = ({modalIsOpen, setModalIsOpen}) => {

    const SERVER_URL = process.env.REACT_APP_API; 

    const { addToast } = useToasts();

    const [user, setUser] = useState([]);

    const [startDate, setStartDate] = useState("");
    const [endDate, setEndDate] = useState("");

    const [start, setStart] = useState("");

    const [periods, setPeriods] = useState([]);

    const [minDate, setMinDate] = useState("");

    useEffect(() => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
            .then(response => {
                var owner = response.data;
                setUser(response.data);
             

        axios.get(SERVER_URL + "/ownerPeriods/allFreeOwnerPeriods/" + owner.id, { headers: headers })
        .then(response => {
            console.log(response.data);
            var periods = response.data;
            var allPeriods = [];
            for(let p of periods) {
                p.startDate = format(p.startDate, 'yyyy-MM-dd kk:mm');
                p.endDate = format(p.endDate, 'yyyy-MM-dd kk:mm');
                allPeriods.push(p);
            }

            console.log(allPeriods)
            setPeriods(allPeriods);

            var start = new Date();
            start = generateDate(start);
            setMinDate(start);

            })
        })
    }, [])

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
        endDate
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
                    axios.post(SERVER_URL + "/ownerPeriods/addOwnerReservationPeriod", newPeriod, {headers: headers})
                    .then(response => {
                        setModalIsOpen(false)
                        window.location.reload();
                    });
                }
                
            }
        }

        
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
                </div>
            )
        })
        ) : (
            <div>You don't have defined periods...</div>
        );

   return (
       <div>
            <Modal className="fullscreen" isOpen={modalIsOpen}
            shouldCloseOnEsc={true}
            onRequestClose={() => setModalIsOpen(false)} >
                <div id="addlodgeReservationPeriod" className="adding-wrapper">
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

export default AddOwnerReservationPeriod;