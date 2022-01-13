import React, { useEffect, useState } from 'react'
import '../../css/addingForm.css'
import Modal from 'react-modal';
import axios from 'axios';


const AddInstructorsAvailbalePeriod = ({modalIsOpen, setModalIsOpen}) => {

    const SERVER_URL = process.env.REACT_APP_API; 

    const [user, setUser] = useState([]);

    const [startDate, setStartDate] = useState("");
    const [endDate, setEndDate] = useState("");
    const [sDate, setSDate] = useState("");
    const [eDate, setEDate] = useState("");

    const newPeriod = {
        owner: user.id,
        startDate, 
        endDate
    }

    useEffect(() => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
            .then(response => {
                setUser(response.data);
            })
        generateDate();
    }, [])

    const generateDate = () => {
        var today = new Date();
        var day = today.getDate();
        var month = today.getMonth()+1;
        var year = today.getFullYear();
        var hours = today.getHours();
        var minutes = today.getMinutes();
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
        

        setSDate(year + '-' + month + '-' + day + 'T' + hours + ':' + minutes);
    }

    const getlength = (number) => {
        return number.toString().length;
    }

    const generateStartDate = (e) => {
        console.log(e.target.value);
        setStartDate(e.target.value);
        generateEndDate(e.target.value);
    }

    const generateEndDate = (today) => {
        setEDate(today);
    }

    const addReservationPeriod = () => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.post(SERVER_URL + "/ownerPeriods/addOwnerReservationPeriod", newPeriod, {headers: headers})
          .then(response => {
            setModalIsOpen(false);
            setStartDate(null);
            setEndDate(null);
            window.location.reload();
        });
    }

    return (
        <div>
            <Modal className="fullscreen" isOpen={modalIsOpen}
            shouldCloseOnEsc={true}
            onRequestClose={() => setModalIsOpen(false)}
            ariaHideApp={false} >
                <form onSubmit={addReservationPeriod}>
                    <div id="addlodgeReservationPeriod" className="adding-wrapper">
                        <div className="right">
                            <div className="info">
                                <h3>DEFINE NEW RESERVATION PERIOD</h3>
                                <div className="info_data">
                                    <div className="data2">
                                        <h4>Period start:</h4>
                                        <input min={sDate} type="datetime-local" required onChange={(e) => {generateStartDate(e)}}  value={startDate}/>
                                    </div>
                                    <div className="data2">
                                        <h4>Period end:</h4>
                                        <input min={eDate} type="datetime-local" required onChange={(e) => {setEndDate(e.target.value)}}  value={endDate}/>
                                    </div>
                                    <input type="submit" className="submit"/>
                                </div> <br/> <br/>
                            </div>
                        </div> <br/> <br/>
                    </div>
                </form>
            </Modal>
        </div>
    )
}
export default AddInstructorsAvailbalePeriod;