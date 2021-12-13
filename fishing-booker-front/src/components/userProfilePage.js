import React from 'react'
import { Link, BrowserRouter as Router, Route, Switch } from "react-router-dom";
import '../css/adminsProfile.css'

const UserProfilPage = () => {
    return (
        <div className="wrapper">
            <div className="left">
                <h4>Marija Petrovic</h4>
                <p>admin</p><br/>
                <a href="">Change password</a><br/><br/>
                <a href="">Delete your account</a>
            </div>
            <div className="right">
                <div className="info">
                    <h3>Information</h3>
                    <div className="info_data">
                        <div className="data">
                            <h4>Email</h4>
                            <input  value="marija@gmail.com"/>
                        </div>
                        <div className="data">
                            <h4>Phone Number</h4>
                            <input  value="1234567"/>
                        </div>
                        <div className="data">
                            <h4>Address</h4>
                            <input  value="Save Kovacevica 2"/>
                        </div>
                        <div className="data">
                            <h4>City</h4>
                            <input   value="Novi Sad"/>
                        </div>
                        <div className="data">
                            <h4>Country</h4>
                            <input  value="Serbia"/>
                        </div>
                    </div> <br/> <br/>
                    <button className="edit-profile-btn" >Save changes</button>
                </div>
            </div>
        </div>
    )
}

export default UserProfilPage;
