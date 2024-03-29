import React, { useEffect, useState } from 'react'
import { Link} from "react-router-dom";
import '../../css/addingForm.css';
import Modal from 'react-modal';
import axios from 'axios';

const AddAdventureForm = ({modalIsOpen, setModalIsOpen}) => {
    const SERVER_URL = process.env.REACT_APP_API; 
    const [user, setUser] = useState({});
    const [name, setName] = useState("");
    const [description, setDescription] = useState("");
    const [biography, setBiography] = useState("");
    const [maxPersons, setMaxPersons] = useState("");
    const [cancelConditions, setCancelConditions] = useState("");
    const [fishingEquipment, setFishingEquipment] = useState("");

    useEffect(() => {
        const headers = {'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.jwtToken}`}
        
        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
            .then(response => setUser(response.data))
    }, [])

    const newAdventure = {
        owner: user.id,
        name,
        description,
        biography,
        maxPersons,
        cancelConditions,
        fishingEquipment
    }

    const addAdventure = () => {
        console.log(newAdventure);
        const headers = {'Content-Type': 'application/json',
                        'Authorization': `Bearer ${localStorage.jwtToken}`}
        axios.post(SERVER_URL + "/adventures/addAdventure", newAdventure, {headers:headers})
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
                <form onSubmit={addAdventure}>
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
                                <div className="data">
                                    <h4>Cancel conditions:</h4>
                                    <textarea type="text" required onChange={(e) => {setCancelConditions(e.target.value)}} value={cancelConditions}/>
                                </div>
                                <div className="data">
                                    <h4>Fishing equipment:</h4>
                                    <textarea type="text" required onChange={(e) => {setFishingEquipment(e.target.value)}} value={fishingEquipment}/>
                                </div>
                                <input type="submit" className="submit" value="Add" />
                            </div> <br/> <br/>
                        </div>
                    </div>
                </div>
                </form>
            </Modal>
        </div>
    )
}
export default AddAdventureForm;