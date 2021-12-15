import { getDefaultNormalizer } from '@testing-library/react';
import { useState } from 'react';
import './../css/usersProfile.css';
const AdminsProfile = () => {
    const [user,setUser] = useState({
        name: "Petar",
        surname: "Petrović",
        role: "instructor",
        email: "petar@gmail.com",
        username: "petarp",
        password: "123456",
        phone: "0659876543",
        address: "Save Kovačevića 2",
        city: "Novi Sad",
        country: "Serbia"
    });

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
                <a  href="http://localhost:3000/changePassword">Change password</a><br/><br/>
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
                            {!isEditting && <label>{user.phone}</label>}
                            {isEditting && <input  value={user.phone}/>}
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

export default AdminsProfile;