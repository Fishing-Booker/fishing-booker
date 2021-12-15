import { getDefaultNormalizer } from '@testing-library/react';
import axios from 'axios';
import { useState,useEffect } from 'react';
import './../css/usersProfile.css';
import { Link } from "react-router-dom";
import { useToasts } from "react-toast-notifications";

const UserProfilePage = () => {
    const SERVER_URL = process.env.REACT_APP_API;
    const [user, setUser] = useState([]);
    const { addToast } = useToasts();

    useEffect(() => {
        const headers = {'Content-Type': 'application/json',
                         'Authorization': `Bearer ${localStorage.jwtToken}`}
        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
            .then(response => {setUser(response.data); console.log(response.status)})
            .catch(error => {
                addToast("Server is not running!", { appearance: "error" });
            })
    }, [])

    const [isEditting, setVisibility] = useState(false);

    const onEditClick = () => {
        setVisibility(true);
    }

    const onSaveClick = () => {
        setVisibility(false);
        //...
    }

    const onCancelClick = () => {
        setVisibility(false);
    }

    return (
        <div className="wrapper">
            <div className="left">
                <h4>{user.name} {user.surname}</h4>
                <p>{user.role}</p><br/>
                <a href="http://localhost:3000/changePassword">Change password</a> <br/><br/>
                <Link to={`/changePassword/${user.id}`}>Change password</Link> <br/><br/>
                <a href="">Delete your account</a>
            </div>
            <div className="right">
                <div className="info">
                    <h3>Information</h3>
                    <div className="info_data">
                    <div className="data">
                            <h4>Name</h4>
                            {!isEditting && <label>{user.name}</label>}
                            {isEditting && <input  value={user.name}/>}
                        </div>
                        <div className="data">
                            <h4>Surname</h4>
                            {!isEditting && <label>{user.surname}</label>}
                            {isEditting && <input  value={user.surname}/>}
                        </div>
                        <div className="data">
                            <h4>Username</h4>
                            {!isEditting && <label>{user.username}</label>}
                            {isEditting && <input  value={user.username}/>}
                        </div>
                        <div className="data">
                            <h4>Email</h4>
                            {!isEditting && <label>{user.email}</label>}
                            {isEditting && <input  value={user.email}/>}
                        </div>
                        <div className="data">
                            <h4>Phone Number</h4>
                            {!isEditting && <label>{user.phoneNumber}</label>}
                            {isEditting && <input  value={user.phoneNumber}/>}
                        </div>
                        <div className="data">
                            <h4>Address</h4>
                            {!isEditting && <label>{user.address}</label>}
                            {isEditting && <input  value={user.address}/>}
                        </div>
                        <div className="data">
                            <h4>City</h4>
                            {!isEditting && <label>{user.city}</label>}
                            {isEditting && <input   value={user.city}/>}
                        </div>
                        <div className="data">
                            <h4>Country</h4>
                            {!isEditting && <label>{user.country}</label>}
                            {isEditting && <input  value={user.country}/>}
                        </div>
                    </div>
                    {!isEditting && <button className="edit-profile-btn" onClick={onEditClick}>Edit profile</button>} <br />
                    {isEditting && <button className="edit-profile-btn" onClick={onSaveClick}>Save changes</button>} <br/>
                    {isEditting && <button className="edit-profile-btn" onClick={onCancelClick}>Cancel</button>}
                </div>
            </div>
        </div>
    );
}

export default UserProfilePage;