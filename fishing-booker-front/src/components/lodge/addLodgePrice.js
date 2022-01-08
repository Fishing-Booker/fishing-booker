import React, { useEffect, useState } from 'react'
import { Link} from "react-router-dom";
import '../../css/addingForm.css';
import Modal from 'react-modal';
import axios from 'axios';

const AddLodgePrice = ({modalIsOpen, setModalIsOpen, entityId}) => {

    const SERVER_URL = process.env.REACT_APP_API; 

    const [user, setUser] = useState({});

    const [name, setName] = useState("");
    const [price, setPrice] = useState("");
    const [serviceType, setServiceType] = useState("");

    useEffect(() => {
        const headers = {'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.jwtToken}`}
        
        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
            .then(response => setUser(response.data))
    }, [])

    const newPrice = {
        name,
        price,
        serviceType,
        entityId
    }

    const addPrice = () => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        console.log(newPrice);
        axios.post(SERVER_URL + "/prices/addPrice", newPrice, {headers: headers})
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
                            <h3>ADD PRICE FOR YOUR LODGE</h3>
                            <div className="info_data">
                                <div className="data">
                                    <h4>Name:</h4>
                                    <input type="text" required onChange={(e) => {setName(e.target.value)}}  value={name}/>
                                </div>
                                <div className="data">
                                    <h4>Price:</h4>
                                    <input type="number" required onChange={(e) => {setPrice(e.target.value)}} value={price}/>
                                </div>
                                <div className="data">
                                    <h4>Service type:</h4>
                                    <select required onChange={(e) => {setServiceType(e.target.value)}} value={serviceType}>
                                        <option>Regular service</option>
                                        <option>Additional service</option>
                                    </select>
                                </div>
                                <button onClick={() => addPrice()}>
                                    Add
                                </button>
                            </div> <br/> <br/>
                        </div>
                    </div>
                </div>
            </Modal>
        </div>
   )
    
}

export default AddLodgePrice;