import { useState } from 'react';
import './../css/adminsProfile.css';

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
        <div class="wrapper">
            <div class="left">
                <h4>{user.name} {user.surname}</h4>
                <p>{user.role}</p><br/>
                <a href="">Delete your account</a>
            </div>
            <div class="right">
                <div class="info">
                    <h3>Information</h3>
                    <div class="info_data">
                    <div class="data">
                            <h4>Password</h4>
                            <input type="password"/>
                        </div>
                        <div class="data">
                            <h4>Confirm password</h4>
                            <input type="password"/>
                        </div>
                    </div> <br /> <br /> <br /> <br /> <br /> <br /> <br /> <br /> <br /> <br />
                    <button class="edit-profile-btn">Save password</button>
                </div>
            </div>
        </div>
    );
}
export default ChangePassword;