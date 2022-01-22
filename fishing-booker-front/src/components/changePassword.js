import { useState, useEffect } from 'react';
import './../css/usersProfile.css';
import { useToasts } from "react-toast-notifications";
import axios from 'axios';
import { useHistory, Link } from 'react-router-dom';

const ChangePassword = () => {
    const SERVER_URL = process.env.REACT_APP_API;
    const { addToast } = useToasts();
    const history = useHistory();
    const [user, setUser] = useState([]);
    const [password, setPassword] = useState("")
    const [confirmationPassword, setConfirmationPassword] = useState("")
    const dto = {
        password,
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

    const validate = (password, confirmationPassword) => {
        return password === confirmationPassword ? true : false;
      }

    const changePassword = e => {
        if(dto.password == ""){
            addToast("Password filed cannot be empty", { appearance: "error" })
        } else {
            e.preventDefault();
            const headers = {'Content-Type': 'application/json',
            'Authorization': `Bearer ${localStorage.jwtToken}`}
            if (validate(dto.password, confirmationPassword)) {
                axios.put(SERVER_URL + "/users/changePassword", dto, { headers: headers });
                addToast("Password is successfully changed!", { appearance: "success" });
                const timer = setTimeout(() => {
                    history.push('/profile');
                    window.location.reload();
                }, 3000)
            } else {
                addToast("Passwords don't match!", { appearance: "error" })
            }
        }
        
    }

    return (
        <div className="wrapper">
            <div className="left">
                <h4>{user.name} {user.surname}</h4>
                <p>{user.role}</p><br/>
                {(user.role !== "ROLE_ADMIN" || user.role !== "ROLE_DEFADMIN") && <Link to={`/deleteAccount/${user.id}`}>Delete your account</Link>}
            </div>
            <div className="right">
                <div className="info">
                    <h3>Change password</h3>
                    <div className="info_data">
                    <div className="data">
                            <h4>Password</h4>
                            <input type="password" placeholder='Enter new password' value={password} onChange={(e) => setPassword(e.target.value)}/>
                        </div>
                        <div className="data">
                            <h4>Confirm password</h4>
                            <input type="password" placeholder="Confirm your password" required onChange={(e) => setConfirmationPassword(e.target.value)} value={confirmationPassword}/>
                        </div>
                    </div> <br /> <br /> <br /> <br /> <br /> <br /> <br /> <br /> <br /> <br />
                    <button className="edit-profile-btn" onClick={changePassword}>Save password</button>
                </div>
            </div>
        </div>
    );
}
export default ChangePassword;