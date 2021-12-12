import './../css/registration.css';
import { Link, useParams } from "react-router-dom";
import { connect } from "react-redux";
import * as actions from "../actions/users";
import { useState, useEffect } from "react";
import { useToasts } from "react-toast-notifications";
import axios from 'axios';
import React from 'react'

const RegistrationForm = ({...props}) => {

  const { addToast } = useToasts();
  const { registrationType } = useParams();

  const SERVER_URL = process.env.REACT_APP_API; 

  const [name, setName] = useState("")
  const [surname, setSurname] = useState("")
  const [username, setUsername] = useState("")
  const [email, setEmail] = useState("")
  const [password, setPassword] = useState("")
  const [address, setAddress] = useState("")
  const [city, setCity] = useState("")
  const [country, setCountry] = useState("")
  const [phone, setPhone] = useState("")
  const [registrationReason, setRegistrationReason] = useState("")
  const [isDeleted, setIsDeleted] = useState(false)
  const [isApproved, setIsApproved] = useState(false)
  const [userType, setUserType] = useState("")

  const values = {
    name, 
    surname,
    username,
    email,
    password,
    address, 
    city,
    country,
    phone,
    isDeleted,
    isApproved
  }

  const owner = {
    name, 
    surname,
    username,
    email,
    password,
    address, 
    city,
    country,
    phone,
    registrationReason,
    registrationType
  }

  useEffect(() => {
    setUserType(registrationType);
  }, [])

  const handleSubmit = e => {
    e.preventDefault();

    if(registrationType != "client") {
      axios.post(SERVER_URL + 'newOwner/', owner)
            .then(response => console.log(response.data));
    } 
    else {
      const onSuccess = () => {
        addToast("User is registrated successfully", { appearance: "success" });
      }
      props.addUser(values, onSuccess)
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
            <input type="text" placeholder="Enter your name" required onChange={(e) => {setName(e.target.value); console.log(name)}} value={name}/>
          </div>
          <div className="input-box">
            <span className="details">Phone Number</span>
            <input type="text" placeholder="Enter your phone number" required onChange={(e) => setPhone(e.target.value)} value={phone}/>
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
            <input type="text" placeholder="Confirm your password" required/>
          </div>
          {registrationType != "client" ? (
            <div className="input-box-reasons">
              <span className="details">Registration reason</span>
              <textarea type="text" placeholder="Registration reason" required onChange={(e) => setRegistrationReason(e.target.value)} value={registrationReason}/>
            </div>) : null}
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

const mapStateToProps = state => {
  return {
    userList: state.users.list
  }
}

const mapActionToProps = {
  addUser: actions.add
}

export default connect(mapStateToProps, mapActionToProps)(RegistrationForm);