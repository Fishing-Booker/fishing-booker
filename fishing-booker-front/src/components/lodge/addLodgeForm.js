import React, { useEffect, useState } from 'react'
import { Link} from "react-router-dom";
import '../../css/addingForm.css';
import Modal from 'react-modal';
import axios from 'axios';

const AddLodgeFrom = ({modalIsOpen, setModalIsOpen}) => {

    const SERVER_URL = process.env.REACT_APP_API; 

    const [user, setUser] = useState({});

    const [name, setName] = useState("");
    const [address, setAddress] = useState("");
    const [city, setCity] = useState("");
    const [country, setCountry] = useState("");
    const [maxPersons, setMaxPersons] = useState("");
    const [description, setDescription] = useState("");
    const [oneBed, setOneBed] = useState("0");
    const [twoBed, setTwoBed] = useState("0");
    const [threeBed, setThreeBed] = useState("0");
    const [fourBed, setFourBed] = useState("0");

    useEffect(() => {
        const headers = {'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.jwtToken}`}
        
        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
            .then(response => setUser(response.data))
    }, [])

    const newLodge = {
        owner: user.id,
        name, 
        address, 
        city, 
        country, 
        maxPersons,
        description,
        oneBed,
        twoBed, 
        threeBed,
        fourBed
    }

    const addLodge = () => {
        console.log(newLodge);
        axios.post(SERVER_URL + "/lodges/addLodge", newLodge)
          .then(response => {
            setModalIsOpen(false);
            window.location.reload();
        });
    }

   return (
        <div >
            <Modal className="fullscreen" isOpen={modalIsOpen}
            shouldCloseOnEsc={true}
            onRequestClose={() => setModalIsOpen(false)}>
                <div id="addLodge" className="adding-wrapper">
                    <div className="right">
                        <div className="info">
                            <h3>ADD YOUR LODGE</h3>
                            <div className="info_data">
                                <div className="data">
                                    <h4>Name:</h4>
                                    <input type="text" required onChange={(e) => {setName(e.target.value)}}  value={name}/>
                                </div>
                                <div className="data">
                                    <h4>Address:</h4>
                                    <input type="text" required onChange={(e) => {setAddress(e.target.value)}} value={address}/>
                                </div>
                                <div className="data" >
                                    <h4>City:</h4>
                                    <input type="text" required onChange={(e) => {setCity(e.target.value)}} value={city}/>
                                </div>
                                <div className="data">
                                    <h4>Country:</h4>
                                    <input type="text" required onChange={(e) => {setCountry(e.target.value)}} value={country}/>
                                </div>
                                <div className="data">
                                    <h4>Max persons:</h4>
                                    <input type="number" min="0" required onChange={(e) => {setMaxPersons(e.target.value)}} value={maxPersons}/>
                                </div>
                                <div className="data">
                                    <h4>Description:</h4>
                                    <textarea type="text" required onChange={(e) => {setDescription(e.target.value)}} value={description}/>
                                </div>
                                <div className="data" style={{width: '150%'}}>
                                    <h4>Bedrooms:</h4>
                                    <div style={{display:'flex'}}>
                                        <div style={{display:'flex'}}>
                                            <label>*One bed: </label>
                                            <input style={{'margin-left':'10%', width: '18%', height: '100%'}} type="number" min="0" step="1" onChange={(e) => {setOneBed(e.target.value)}}  value={oneBed} />
                                        </div>
                                        <div style={{display:'flex'}}>
                                            <label>*Two bed: </label>
                                            <input style={{'margin-left':'10%', width: '18%', height: '100%'}} type="number" min="0" step="1" onChange={(e) => {setTwoBed(e.target.value)}}  value={twoBed}/>
                                        </div>
                                    </div><br/>
                                    <div style={{display:'flex'}}>
                                        <div style={{display:'flex'}}>
                                            <label>*Three bed: </label>
                                            <input style={{'margin-left':'10%', width: '18%', height: '100%'}} type="number" min="0" step="1" onChange={(e) => {setThreeBed(e.target.value)}}  value={threeBed}/>
                                        </div>
                                        <div style={{display:'flex', 'margin-left': '-2%'}}>
                                            <label>*Four bed: </label>
                                            <input style={{'margin-left':'10%', width: '18%', height: '100%'}} type="number" min="0" step="1" onChange={(e) => {setFourBed(e.target.value)}}  value={fourBed}/>
                                        </div>
                                    </div>
                                </div>
                                <Link to="/" onClick={() => addLodge()} >
                                    <button>
                                        Add
                                    </button>
                                </Link>
                            </div> <br/> <br/>
                        </div>
                    </div>
                </div>
            </Modal>
        </div>
   )
    
}

export default AddLodgeFrom;