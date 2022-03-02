import axios from 'axios';
import { useState,useEffect } from 'react';
import './../css/usersProfile.css';
import { Link, useHistory } from "react-router-dom";
import { useToasts } from "react-toast-notifications";

const UserProfilePage = () => {
    const SERVER_URL = process.env.REACT_APP_API;
    const { addToast } = useToasts();
    const history = useHistory();
    const [user, setUser] = useState([]);
    const [name, setName] = useState("");
    const [surname, setSurname] = useState("");
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [phoneNumber, setPhoneNumber] = useState("");
    const [address, setAddress] = useState("");
    const [city, setCity] = useState("");
    const [country, setCountry] = useState("");
    const [penalties, setPenalties] = useState("");
    const values = {
        name, 
        surname,
        username,
        email,
        password: user.password,
        address,
        city,
        country,
        phoneNumber
    }

    const [role, setRole] = useState("");
    
    useEffect(() => {
        const headers = {'Content-Type': 'application/json',
                         'Authorization': `Bearer ${localStorage.jwtToken}`}
        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
            .then(response => {
                setUser(response.data);
                var user = response.data
                console.log(response.status);
                axios.get(SERVER_URL + `/users/getRole/${user.id}`, {headers:headers})
                .then(response => {
                    setRole(response.data);
                });
                axios.get(SERVER_URL + '/penalties?clientId=' + user.id)
                    .then(response => setPenalties(response.data));
                })
            .catch(error => {
                addToast("Server is not running!", { appearance: "error" });
            })
    }, [])

    const [isEditting, setVisibility] = useState(false);

    const onEditClick = () => {
        setVisibility(true);
        setName(user.name);
        setSurname(user.surname);
        setUsername(user.username);
        setEmail(user.email);
        setAddress(user.address);
        setPhoneNumber(user.phoneNumber);
        setCity(user.city);
        setCountry(user.country);
    }

    const onSaveClick = () => {
        if(name === "" || surname === "" || username === "" || email === "" || address === "" || city === "" || country === "" || phoneNumber === ""){
            addToast("All fields are required", { appearance: "error"})
        } else {
            setVisibility(false);
            console.log(values)
            const headers = {'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.jwtToken}`}
            axios.put(SERVER_URL + "/users/user/" + user.id, values, { headers: headers })
                .then(response => {
                    console.log(response.data);
                    addToast("Your account is successfully updated!", { appearance: "success"});
                    if (username !== user.username) {  
                        localStorage.removeItem('jwtToken');
                        history.push('/')
                        window.location.reload()
                    } else {
                        const timer = setTimeout(() => {
                            history.push("/profile");
                            window.location.reload();
                        }, 2000)
                    }
                })
                .catch(error => addToast("Cannot update: " + error.message, { appearance: "error"}))
        }
        
    }

    const onCancelClick = () => {
        setVisibility(false);
    }

    return (
        <div className="wrapper">
            <div className="left">
                <h4>{user.name} {user.surname}</h4>
                <p>{user.role}</p><br/>
                <Link to={`/changePassword/${user.id}`}>Change password</Link> <br/><br/>
                {(role !== "ROLE_ADMIN" || role !== "ROLE_DEFADMIN") && <Link to={`/deleteAccount/${user.id}`}>Delete your account</Link>}
            </div>
            <div className="right">
                <div className="info">
                    <h3>Information</h3>
                    <div className="info_data">
                    <div className="data">
                            <h4>Name</h4>
                            {!isEditting && <label>{user.name}</label>}
                            {isEditting && <input  value={name} onChange={(e) => setName(e.target.value) }/>}
                        </div>
                        <div className="data">
                            <h4>Surname</h4>
                            {!isEditting && <label>{user.surname}</label>}
                            {isEditting && <input  value={surname} onChange={(e) => setSurname(e.target.value)}/>}
                        </div>
                        <div className="data">
                            <h4>Username</h4>
                            {!isEditting && <label>{user.username}</label>}
                            {isEditting && <input  value={username} onChange={(e) => setUsername(e.target.value)}/>}
                        </div>
                        <div className="data">
                            <h4>Email</h4>
                            {!isEditting && <label>{user.email}</label>}
                            {isEditting && <input  value={email} onChange={(e) => setEmail(e.target.value)}/>}
                        </div>
                        <div className="data">
                            <h4>Phone Number</h4>
                            {!isEditting && <label>{user.phoneNumber}</label>}
                            {isEditting && <input  value={phoneNumber} onChange={(e) => setPhoneNumber(e.target.value)}/>}
                        </div>
                        <div className="data">
                            <h4>Address</h4>
                            {!isEditting && <label>{user.address}</label>}
                            {isEditting && <input  value={address} onChange={(e) => setAddress(e.target.value)}/>}
                        </div>
                        <div className="data">
                            <h4>City</h4>
                            {!isEditting && <label>{user.city}</label>}
                            {isEditting && <input   value={city} onChange={(e) => setCity(e.target.value)}/>}
                        </div>
                        <div className="data">
                            <h4>Country</h4>
                            {!isEditting && <label>{user.country}</label>}
                            {isEditting && <input  value={country} onChange={(e) => setCountry(e.target.value)}/>}
                        </div>
                        {(role === "ROLE_CLIENT") && 
                            <div className="data">
                                <h4>Penalties</h4>
                                {!isEditting && <label>{penalties}</label>}
                                {isEditting && <input disabled value={penalties} />}
                            </div>
                        }
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