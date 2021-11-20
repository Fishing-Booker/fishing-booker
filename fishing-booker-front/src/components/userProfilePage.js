import React from 'react'
import { Link, BrowserRouter as Router, Route, Switch } from "react-router-dom";
import '../css/userProfile.css'

const UserProfilPage = () => {
    return (
        <div class="wrapper">
            <div class="left">
                <h4>Marija Petrovic</h4>
                <p>admin</p><br/>
                <a href="">Change password</a><br/><br/>
                <a href="">Delete your account</a>
            </div>
            <div class="right">
                <div class="info">
                    <h3>Information</h3>
                    <div class="info_data">
                        <div class="data">
                            <h4>Email</h4>
                            <input disabled value="marija@gmail.com"/>
                        </div>
                        <div class="data">
                            <h4>Phone Number</h4>
                            <input disabled value="1234567"/>
                        </div>
                        <div class="data">
                            <h4>Address</h4>
                            <input disabled value="Save Kovacevica 2"/>
                        </div>
                        <div class="data">
                            <h4>City</h4>
                            <input disabled  value="Novi Sad"/>
                        </div>
                        <div class="data">
                            <h4>Country</h4>
                            <input disabled value="Serbia"/>
                        </div>
                    </div> <br/> <br/>
                    <button className="edit-profile-btn" >Save changes</button>
                </div>
            </div>
        </div>
    )
}

export default UserProfilPage;