import React, { useEffect, useState } from 'react'
import { Link} from "react-router-dom";
import '../../css/addingForm.css';
import Modal from 'react-modal';
import axios from 'axios';
import { useToasts } from "react-toast-notifications";

const AddLodgeFrom = ({modalIsOpen, setModalIsOpen}) => {

    const SERVER_URL = process.env.REACT_APP_API; 

    const { addToast } = useToasts();

    const [user, setUser] = useState({});

    const [lodges, setLodges] = useState([]);

    const [name, setName] = useState("");
    const [maxPersons, setMaxPersons] = useState("0");
    const [description, setDescription] = useState("");
    const [cancelConditions, setCancelConditions] = useState("0");
    const [oneBed, setOneBed] = useState("0");
    const [twoBed, setTwoBed] = useState("0");
    const [threeBed, setThreeBed] = useState("0");
    const [fourBed, setFourBed] = useState("0");

    useEffect(() => {
        const headers = {'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.jwtToken}`}
        
        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
            .then(response => {
                setUser(response.data);
                var user = response.data;

                axios.get(SERVER_URL + "/lodges/lodgeNames/" + user.id, {headers: headers})
                .then(response => {
                    setLodges(response.data);
                })
            })
    }, [])

    const newLodge = {
        owner: user.id,
        name, 
        maxPersons,
        description,
        cancelConditions,
        oneBed,
        twoBed, 
        threeBed,
        fourBed
    }

    const addLodge = () => {
        if(newLodge.name == ""){
            addToast("Field for lodge name cannot be empty!", { appearance: "error" });
        } else if(!isNameValid(name)){
            addToast("You already have lodge with this name.", { appearance: "error" });
        } else {
            const headers = {'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.jwtToken}`}
            
            axios.post(SERVER_URL + "/lodges/addLodge", newLodge, {headers: headers})
            .then(response => {
              setModalIsOpen(false);
              window.location.reload();
          });
        }
        
    }

    const isNameValid = (name) => {
        for(let lodge of lodges){
            if(lodge == name){
                return false;
            }
        }
        return true;
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
                                    <h4>Max persons:</h4>
                                    <input type="number" min="0" required onChange={(e) => {setMaxPersons(e.target.value)}} value={maxPersons}/>
                                </div>
                                <div className="data">
                                    <h4>Description:</h4>
                                    <textarea type="text" required onChange={(e) => {setDescription(e.target.value)}} value={description}/>
                                </div>
                                <div className="data">
                                    <h4>Cancel conditions:</h4>
                                    <input type="number" min="0" required onChange={(e) => {setCancelConditions(e.target.value)}} value={cancelConditions}/>
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
                                <div className="buttons">
                                    <button className="cancel" onClick={() => setModalIsOpen(false)}>
                                        Cancel
                                    </button>
                                    <button className="add" onClick={() => addLodge()}>
                                        Add
                                    </button><br/>
                                </div>
                            </div> <br/> <br/>
                        </div>
                    </div>
                </div>
            </Modal>
        </div>
   )
    
}

export default AddLodgeFrom;