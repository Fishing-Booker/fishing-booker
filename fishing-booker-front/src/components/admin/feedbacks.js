import React, { useEffect, useState } from 'react'
import axios from 'axios';
import ComplainInfo from './complainInfo';
import ComplainResponse from './complainResponse';

const Feedbacks = () => {
    const SERVER_URL = process.env.REACT_APP_API; 
    const [seeReports, setSeeReports] = useState(false);
    const [seeComplaints, setSeeComplaints] = useState(true);
    const [seeComments, setSeeComments] = useState(false);
    const [complaints, setComplaints] = useState([]);
    const [reports, setReports] = useState([]);
    const [comments, setComments] = useState([]);
    const [user, setUser] = useState("");

    const [complainInfoModal, setComplainInfoModal] = useState(false);
    const [complainResponseModal, setComplainResponseModal] = useState(false);
    const [reportInfo, setReportInfo] = useState(false);

    const [complainInfo, setComplainInfo] = useState("");


    useEffect(() => {
        const headers = {'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.jwtToken}`}
        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
            .then(response => {
                setUser(response.data);
                axios.get(SERVER_URL + "/complaints/get", {headers: headers})
                .then(res => {
                    setComplaints(res.data);
                    console.log(res.data);
                })
            })
    }, [])


    const complaintsList = complaints.length ? (
        complaints.map((complaint, index) => {
            return (
                <div className="col" key={index}>
                    <div className="card res-actions-div">
                        <div className="info"> <br></br>
                            <p className="entity-info name">#{index+1} Complain about : {complaint.entityName}</p>
                            <a className="subscribe-link" onClick={() => {setComplainInfo(complaint); setComplainInfoModal(true);}}>details</a>
                            <a className="reservation-link" onClick={() =>{setComplainResponseModal(true); setComplainInfo(complaint);}}>respond</a>
                            <p style={{color: 'black', fontSize: '17px', marginLeft: '50px', marginTop: '20px'}}>Owner: {complaint.entityOwner}</p>
                            <div style={{borderBottom: '2px solid cadetblue', padding: '5px', width: '47vw', marginLeft: '15px'}}></div>
                            <p style={{color: 'black', fontWeight: '700', fontSize: '15px', marginLeft: '55px', marginTop: '15px'}}>Complaint from: {complaint.clientName} {complaint.clientSurname}</p>
                            {complaint.responded &&<p style={{color: 'red', fontWeight: '700', fontSize: '15px', marginLeft: '55px', marginTop: '15px'}}>RESPONDED</p>}
                        </div>
                    </div>
                </div>
            )
        })
    ) : (<div><p style={{marginLeft: '30px'}}>You don't have any active reservations.</p></div>)


    return (
        <div>
            <div className="card search">
                <button style={{fontSize: '20px'}}>Reservation reports</button>
                <button style={{fontSize: '20px'}}>&nbsp; &nbsp; Complaints</button>
                <button style={{fontSize: '20px'}}>&nbsp; &nbsp; Grades and comments</button>
            </div>
            {complaintsList}
            <ComplainInfo modalIsOpen={complainInfoModal} setModalIsOpen={setComplainInfoModal} complainInfo={complainInfo}/>
            <ComplainResponse modalIsOpen={complainResponseModal} setModalIsOpen={setComplainResponseModal} complainInfo={complainInfo}/>
        </div>
    )
}
export default Feedbacks;