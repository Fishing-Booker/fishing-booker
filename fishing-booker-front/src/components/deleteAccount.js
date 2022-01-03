import { useState, useEffect } from 'react';
import './../css/usersProfile.css';
import { useToasts } from "react-toast-notifications";
import axios from 'axios';
import { Link } from 'react-router-dom';

const DeleteAccount = () => {
    const SERVER_URL = process.env.REACT_APP_API;
    const { addToast } = useToasts();
    const [user, setUser] = useState([]);
    const [reason, setReason] = useState("")
    const dto = {
        reason,
        id: user.id
    }

    useEffect(() => {
        const headers = {'Content-Type': 'application/json',
                         'Authorization': `Bearer ${localStorage.jwtToken}`}
        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
            .then(response => setUser(response.data))
            .catch(error => {
                if (error.message === 'Network Error'){
                    addToast("Server is not running!", { appearance: "error" });
                }
            })
    }, [])

    const sendDeleteRequest = () => {
        const headers = {'Content-Type': 'application/json',
                         'Authorization': `Bearer ${localStorage.jwtToken}`}
        axios.post(SERVER_URL + "/users/deleteAccount", dto, { headers : headers });
        console.log(dto)
        setReason("");
    }

    return (
        <div className="wrapper">
            <div className="left">
                <h4>{user.name} {user.surname}</h4>
                <p>{user.role}</p><br/>
                <Link to={`/changePassword/${user.id}`}>Change password</Link>
            </div>
            <div className="right">
                <div className="info">
                    <h3>Delete your account</h3>
                    <div className="info_data">
                    <div className="data">
                            <h4>Why are you deleting your account?</h4>
                            <textarea placeholder='Enter reason here' onChange={(e) => setReason(e.target.value)} value={reason}></textarea>
                        </div>
                    </div> <br /> <br /> <br /> <br /> <br /> <br /> <br /> <br /> <br /> <br />
                    <button className="edit-profile-btn" onClick={sendDeleteRequest}>SEND DELETE REQUEST</button>
                </div>
            </div>
        </div>
    );
}
export default DeleteAccount;