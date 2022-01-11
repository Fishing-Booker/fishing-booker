import React, { useEffect, useState } from 'react'
import { Link, useHistory} from "react-router-dom";
import '../../css/addingForm.css';
import Modal from 'react-modal';
import axios from 'axios';
import { useToasts } from "react-toast-notifications";
const AdminFirstLogin = ({modalIsOpen, setModalIsOpen}) => {
    const SERVER_URL = process.env.REACT_APP_API;
    const [user, setUser] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const { addToast } = useToasts();
    const [error, setError] = useState("");
    const [disableSubmit, setDisableSubmit] = useState(true);
    const history = useHistory();
    const dto = {
        password,
        id: user.id
    }

    const handleSubmit = () => {
        const headers = {'Content-Type': 'application/json',
                         'Authorization': `Bearer ${localStorage.jwtToken}`}
        axios.put(SERVER_URL + "/users/changePasswordAdmin", dto, { headers: headers })
        console.log(dto);
        addToast("You successfully changed your password!", { appearance: "success" });
        setModalIsOpen(false);
        history.push('/');
    }
    const validate = (e) => {
        setConfirmPassword(e.target.value);
        if(password !== e.target.value) {
            setError("Passwords don't match!");
            setDisableSubmit(true);
        } else {
            setError("");
            setDisableSubmit(false);
        }
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

    return (
        <Modal className="fullscreen" isOpen={modalIsOpen}
        ariaHideApp={false}
        shouldCloseOnOverlayClick={false}
        shouldCloseOnEsc={false}>
            <div id="addLodge" className="adding-wrapper">
                <form onSubmit={handleSubmit}>
                  <div className="right">
                    <div className="info">
                      <h3>CHANGE PASSWORD</h3>
                      <div className="info_data">
                        <div className="data2">
                            <label>Please, change you password to continue</label>
                            <h4>New password: </h4>
                            <input type="password" required  onChange={(e) => {setPassword(e.target.value)}}  value={password}/>
                        </div>
                        <div className="data2">
                            <h4>Confirm password: </h4>
                            <input type="password" required onChange={(e) => {validate(e)}}  value={confirmPassword}/>
                            <label style={{color: 'red'}}>{error}</label>
                        </div>
                        <input disabled={disableSubmit} type="submit" className="submit"/>
                      </div>
                    </div>
                  </div>
                </form>
              </div>
        </Modal>
    )
}
export default AdminFirstLogin;