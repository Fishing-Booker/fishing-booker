import { useState } from 'react';
import './../css/usersProfile.css';

const ChangePassword = () => {
    const [user,setUser] = useState({
        name: "Marija",
        surname: "Petrović",
        role: "admin",
        email: "marija@gmail.com",
        username: "marijap",
        password: "123456",
        phone: "0659876543",
        address: "Save Kovačevića 2",
        city: "Novi Sad",
        country: "Serbia"
    });
    const [isEditting, setVisibility] = useState(false);
    return (
        <div className="wrapper">
            <div className="left">
                <h4>{user.name} {user.surname}</h4>
                <p>{user.role}</p><br/>
                <a href="">Delete your account</a>
            </div>
            <div className="right">
                <div className="info">
                    <h3>Information</h3>
                    <div className="info_data">
                    <div className="data">
                            <h4>Password</h4>
                            <input type="password"/>
                        </div>
                        <div className="data">
                            <h4>Confirm password</h4>
                            <input type="password"/>
                        </div>
                    </div> <br /> <br /> <br /> <br /> <br /> <br /> <br /> <br /> <br /> <br />
                    <button className="edit-profile-btn">Save password</button>
                </div>
            </div>
        </div>
    );
}
export default ChangePassword;