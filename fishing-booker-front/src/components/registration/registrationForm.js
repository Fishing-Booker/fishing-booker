import '../../css/registration.css';
import { Link, useHistory } from "react-router-dom";
import { useState, useEffect } from "react";
import { useToasts } from "react-toast-notifications";
import React from 'react'
import axios from 'axios';

const RegistrationForm = () => {
  const SERVER_URL = process.env.REACT_APP_API; 
  const { addToast } = useToasts();
  const history = useHistory();
  const url = window.location.href;
  const [role, setRole] = useState("ROLE_DEFADMIN");
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
  const [isApproved, setIsApproved] = useState(true)
  const [registrationReason, setRegistrationReason] = useState("")
  const [confirmationPassword, setConfirmationPassword] = useState("")

  useEffect(() => {
    const splitted = url.split("/")
    setRole(splitted[splitted.length-1])
  })

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
    role
  }

  const validate = (password, confirmationPassword) => {
    return password === confirmationPassword ? true : false;
  }

  const handleSubmit = e => {
    console.log(values);
    e.preventDefault();
    if (validate(values.password, confirmationPassword)) {
      if(role == "ROLE_CLIENT" || role=="ROLE_DEFADMIN"){
        axios.post(SERVER_URL + "/auth/register", values)
          .then(response => {
        addToast("You are registered successfully! Please check email to verify your account.", { appearance: "success" });
        const timer = setTimeout(() => {
          history.push('/');
          window.location.reload();
        }, 3000)
       });
      } else {
        axios.post(SERVER_URL + "/auth/registerOwner", values)
          .then(response => {
        history.push('/');
        window.location.reload();
       });
       addToast("Your registration request is successfully sent! You will receive email from our administrators.", { appearance: "success" });
      }
      
    } else {
      addToast("Passwords don't match!", { appearance: "error" });
    }

  }

  return (
  <div className="container-reg">
    <div className="title">Registration</div>
    <div className="content">
      <form onSubmit={handleSubmit}>
        <div className="user-details">
          <div className="input-box">
            <span className="details">Name</span>
            <input type="text" placeholder="Enter your name" required onChange={(e) => {setName(e.target.value)}} value={name}/>
          </div>
          <div className="input-box">
            <span className="details">Phone Number</span>
            <input type="text" placeholder="Enter your phone number" required onChange={(e) => setPhoneNumber(e.target.value)} value={phoneNumber}/>
          </div>
          <div className="input-box">
            <span className="details">Surname</span>
            <input type="text" placeholder="Enter your surname" required onChange={(e) => setSurname(e.target.value)} value={surname}/>
          </div>
          <div className="input-box">
            <span className="details">Email</span>
            <input type="text" placeholder="Enter your email" required onChange={(e) => setEmail(e.target.value)} value={email}/>
          </div>
          <div className="input-box">
            <span className="details">Address</span>
            <input type="text" placeholder="Enter your address" required onChange={(e) => setAddress(e.target.value)} value={address}/>
          </div>
          <div className="input-box">
            <span className="details">Username</span>
            <input type="text" placeholder="Choose your username" required onChange={(e) => setUsername(e.target.value)} value={username}/>
          </div>
          <div className="input-box">
            <span className="details">City</span>
            <input type="text" placeholder="Enter your city" required onChange={(e) => setCity(e.target.value)} value={city}/>
          </div>
          <div className="input-box">
            <span className="details">Password</span>
            <input type="password" placeholder="Enter your password" required onChange={(e) => setPassword(e.target.value)} value={password}/>
          </div>
          <div className="input-box">
            <span className="details">Country</span>
            <input type="text" placeholder="Enter your country" required onChange={(e) => setCountry(e.target.value)} value={country}/>
          </div>
          <div className="input-box">
            <span className="details">Confirm Password</span>
            <input type="password" placeholder="Confirm your password" required onChange={(e) => setConfirmationPassword(e.target.value)} value={confirmationPassword}/>
          </div>
          <div className="input-box-reasons">
            <span className="details">Registration reason</span>
            <textarea type="text" placeholder="Registration reason" required onChange={(e) => setRegistrationReason(e.target.value)} value={registrationReason}/>
          </div>
        </div>
        <p className="reg-message">Alredy have an account? <Link className="link" to="/login">Log in</Link></p>
        <div className="button">
          <input type="submit" value="Register"/>
        </div>
      </form>
    </div>
  </div>
  )
}


export default RegistrationForm;
