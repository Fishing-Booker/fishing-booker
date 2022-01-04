import React, { useEffect, useState } from 'react'
import { Link} from "react-router-dom";
import '../../css/addingForm.css';
import Modal from 'react-modal';
import axios from 'axios';

const AddAdventureForm = ({modalIsOpen, setModalIsOpen}) => {
    const SERVER_URL = process.env.REACT_APP_API; 

    const [user, setUser] = useState({});

    const [name, setName] = useState("");
    const [address, setAddress] = useState("");
    const [city, setCity] = useState("");
    const [country, setCountry] = useState("");
    const [description, setDescription] = useState("");
    const [biography, setBiography] = useState("");
    const [maxPersons, setMaxPersons] = useState("");

    useEffect(() => {
        const headers = {'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.jwtToken}`}
        
        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
            .then(response => setUser(response.data))
    }, [])

    const newAdventure = {
        owner: user.id,
        name,
        address,
        city,
        country,
        description,
        biography,
        maxPersons
    }

    /*const addLodge = () => {
        console.log(newLodge);
        axios.post(SERVER_URL + "/lodges/addLodge", newLodge)
          .then(response => {
            setModalIsOpen(false);
            window.location.reload();
        });
        
    }*/

    const addAdventure = () => {
        console.log(newAdventure);
        axios.post(SERVER_URL + "/adventures/addAdventure", newAdventure)
          .then(response => {
            setModalIsOpen(false);
            window.location.reload();
        });
    }

    return(
        <div>
            <Modal className="fullscreen" isOpen={modalIsOpen}
            shouldCloseOnEsc={true}
            onRequestClose={() => setModalIsOpen(false)}
            ariaHideApp={false}>
                <div id="addLodge" className="adding-wrapper">
                    <div className="right">
                        <div className="info">
                            <h3>ADD YOUR ADVENTURE</h3>
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
                                    <h4>Description:</h4>
                                    <textarea type="text" required onChange={(e) => {setDescription(e.target.value)}} value={description}/>
                                </div>
                                <div className="data">
                                    <h4>Instructors biography:</h4>
                                    <textarea type="text" required onChange={(e) => {setBiography(e.target.value)}} value={biography}/>
                                </div>
                                <div className="data" style={{width: '100%'}}>
                                    <h4>Max persons:</h4>
                                    <input style={{ width: '18%', height: '100%'}} type="number" min="1" step="1" onChange={(e) => {setMaxPersons(e.target.value)}}  value={maxPersons} />
                                </div>
                                <Link to="/" onClick={() => addAdventure()} >
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
export default AddAdventureForm;