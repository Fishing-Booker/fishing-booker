import React, { useEffect, useState } from 'react'
import { Link, useHistory} from "react-router-dom";
import '../../css/addingForm.css';
import Modal from 'react-modal';
import axios from 'axios';
import { useToasts } from "react-toast-notifications";

const AddNewAdminForm = ({modalIsOpen, setModalIsOpen}) => {
  const SERVER_URL = process.env.REACT_APP_API; 
  const { addToast } = useToasts();
  const history = useHistory();
  const url = window.location.href;
  const [role, setRole] = useState("ROLE_ADMIN");
  const [name, setName] = useState("")
  const [surname, setSurname] = useState("")
  const [username, setUsername] = useState("")
  const [email, setEmail] = useState("")
  const [password, setPassword] = useState("")
  const [address, setAddress] = useState("")
  const [city, setCity] = useState("")
  const [country, setCountry] = useState("")
  const [phoneNumber, setPhoneNumber] = useState("")
  const [isDeleted, setIsDeleted] = useState(false)
  const [isApproved, setIsApproved] = useState(false)
  const [isFirstLogin, setIsFirstLogin] = useState(true);
  const [registrationReason, setRegistrationReason] = useState("")

  const values = {
    name, 
    surname,
    username,
    email,
    registrationReason,
    password,
    address, 
    city,
    country,
    phoneNumber,
    isDeleted,
    isApproved,
    isFirstLogin,
    role
  }

  const handleSubmit = (e) => {
    console.log(values)
    e.preventDefault();
    axios.post(SERVER_URL + "/auth/registerAdmin", values)
          .then(response => {
        addToast("You registered admin successfully!", { appearance: "success" });
        const timer = setTimeout(() => {
          history.push('/');
          window.location.reload();
        }, 3000)
       });
  }


    return (
      <div>
        <Modal className="fullscreen" isOpen={modalIsOpen}
            shouldCloseOnEsc={true}
            onRequestClose={() => setModalIsOpen(false)}
            ariaHideApp={false}>
              <div id="addLodge" className="adding-wrapper">
                <form onSubmit={handleSubmit}>
                  <div className="right">
                    <div className="info">
                      <h3>ADD ADMIN</h3>
                      <div className="info_data">
                        <div className="data">
                          <h4>Name: </h4>
                          <input type="text" required onChange={(e) => {setName(e.target.value)}}  value={name} />
                        </div>
                        <div className="data">
                          <h4>Surname: </h4>
                          <input type="text" required onChange={(e) => {setSurname(e.target.value)}}  value={surname} />
                        </div>
                        <div className="data">
                          <h4>Email: </h4>
                          <input type="text" required onChange={(e) => {setEmail(e.target.value)}}  value={email} />
                        </div>
                        <div className="data">
                          <h4>Username: </h4>
                          <input type="text" required onChange={(e) => {setUsername(e.target.value)}}  value={username}/>
                        </div>
                        <div className="data">
                          <h4>Password: </h4>
                          <input type="text" required onChange={(e) => {setPassword(e.target.value)}}  value={password} />
                        </div>
                        <div className="data">
                          <h4>Address: </h4>
                          <input type="text" required onChange={(e) => {setAddress(e.target.value)}}  value={address}/>
                        </div>
                        <div className="data">
                          <h4>City: </h4>
                          <input type="text" required onChange={(e) => {setCity(e.target.value)}}  value={city} />
                        </div>
                        <div className="data">
                          <h4>Country: </h4>
                          <input type="text" required onChange={(e) => {setCountry(e.target.value)}}  value={country} />
                        </div>
                        <div className="data">
                          <h4>Phone number: </h4>
                          <input type="text" required onChange={(e) => {setPhoneNumber(e.target.value)}}  value={phoneNumber}/>
                        </div>
                        <input type="submit" className="submit"/>
                      </div>
                    </div>
                  </div>
                </form>
              </div>
        </Modal>
      </div>
    )
}

export default AddNewAdminForm;