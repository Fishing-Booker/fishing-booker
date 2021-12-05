import React from 'react'
import "./../css/userProfile.css";
import { useState } from "react";

const UserProfilePage = () => {
    const [canEdit, setCanEdit] = useState(false);

    const handleEdit = () => {
        setCanEdit(true);
    }

    return (
        <div class="wrapper">
            <div class="left">
                <h4>Marija Petrovic</h4>
                <p>admin</p>
            </div>
            <div class="right">
                <div class="info">
                    <h3>Information</h3>
                    <div class="info_data">
                        <div class="data">
                            <h4>Email</h4>
                            <input disabled={!canEdit} className="input-edit-profile" type="text" required value="marija@gmail.com"/>
                        </div>
                        <div class="data">
                            <h4>Phone Number</h4>
                            <input disabled={!canEdit} className="input-edit-profile" type="text" required value="1234567"/>
                        </div>
                        <div class="data">
                            <h4>Address</h4>
                            <input disabled={!canEdit} className="input-edit-profile" type="text" required value="Save Kovacevica 2"/>
                        </div>
                        <div class="data">
                            <h4>City</h4>
                            <input disabled={!canEdit} className="input-edit-profile" type="text" required value="Novi Sad"/>
                        </div>
                        <div class="data">
                            <h4>Country</h4>
                            <input disabled={!canEdit} className="input-edit-profile" type="text" required value="Serbia"/>
                        </div>
                    </div> <br/> <br/>
                    <button className="edit-profile-btn" onClick={handleEdit}>edit profile</button>
                </div>
                <div class="projects">
                    <h3>Reservations</h3>
                </div>
            </div>
        </div>
    )
}

export default UserProfilePage;