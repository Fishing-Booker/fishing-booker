import axios from 'axios';
import { useState,useEffect } from 'react';
import '../../css/userInfo.css';
import { Link, useHistory } from "react-router-dom";
import { useToasts } from "react-toast-notifications";
import { useParams } from 'react-router-dom';
import noImg from '../../images/noProfilePicture.jpg';
const UserInfo = () => {

    const {userId} = useParams();

    const SERVER_URL = process.env.REACT_APP_API;
    const { addToast } = useToasts();
    const [user, setUser] = useState([]);
    const [entities, setEntities] = useState([]);

    useEffect(() => {
        console.log(userId);
        const headers = {'Content-Type': 'application/json',
                         'Authorization': `Bearer ${localStorage.jwtToken}`}
        axios.get(SERVER_URL + "/users/getUser/" + userId, { headers: headers })
            .then(response => {
                setUser(response.data);
                console.log(response.data);
                })
            .catch(error => {
                addToast("Server is not running!", { appearance: "error" });
            })
        axios.get(SERVER_URL + "/users/getUserEntities/" + userId, {headers : headers})
        .then(response => {
            setEntities(response.data);
            console.log(response.data);
        });
    }, [])
    return (
        <div>
            <div className="wrapper-info">
                <div className="left">
                    <h4>{user.name}  {user.surname}</h4> <br />
                    {user.role==="ROLE_DEFADMIN" && <p>ADMIN</p>}
                    {user.role==="ROLE_ADMIN" && <p>ADMIN</p>}
                    {user.role==="ROLE_CLIENT" && <p>CLIENT</p>}
                    {user.role==="ROLE_INSTRUCTOR" && <p>INSTRUCTOR</p>}
                    {user.role==="ROLE_SHIPOWNER" && <p>SHIP OWNER</p>}
                    {user.role==="ROLE_LODGEOWNER" && <p>LODGE OWNER</p>}
                    <button className="del-button">Delete user</button>
                </div>
                <div className="right">
                    <div className="info">
                        <h3>Information</h3>
                        <div className="info-data">
                            <div className="data">
                                <h4>Email</h4>
                                <label>{user.email}</label>
                            </div> 
                            <div className="data">
                                <h4>Username</h4>
                                <label>{user.username}</label>
                            </div> 
                            <div className="data">
                                <h4>Address</h4>
                                <label>{user.address}, {user.city}, {user.country}</label>
                            </div> 
                            <div className="data">
                                <h4>Phone</h4>
                                <label>{user.phoneNumber}</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <ol>
                {entities.map((entity) => (
                    <div className="container-entities">
                        <li className="withBorder-ent" key={entity.entityId}>
                            <span className="list">{entity.serialNumber}.</span>
                            <span>
                            {entity.profileImage !=="" && <img className="entImage" src={entity.profileImage} alt="" />}
                            {entity.profileImage ==="" && <img className="entImage" src={noImg} alt="" />}
                                <div>
                                    <p><b>Name: </b>{entity.name}</p>
                                    <p><b>Address: </b> {entity.location}</p>
                                    <p><b>Description: </b> {entity.description}</p>
                                    <p><b>Number of persons: </b> {entity.maxPersons}</p>
                                    <p><b>Average grade: </b> {entity.averageGrade}</p>
                                </div>
                            </span>
                            <button className="del-ent-button">
                                remove
                            </button>
                        </li>
                    </div>
                ))}
            </ol>

        </div>
    )
}

export default UserInfo;