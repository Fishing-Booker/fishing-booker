import { useEffect, useState } from 'react';
import '../../css/usersProfile.css';
import axios from 'axios';
import { Link } from 'react-router-dom';
import AdminFirstLogin from './adminFirstLogin';
import AddLoyaltyProgramme from './addLoyaltyProgramme';

const AdminHomepage = () => {
    const SERVER_URL = process.env.REACT_APP_API;
    const [role, setRole] = useState("");
    const [firstLogin, setFirstLogin] = useState(false);
    const [addLoyalty, setAddLoyalty] = useState(false);
    const [loyaltyProgramme, setLoyaltyProgramme] = useState([]);
    const [addLoy, setAddLoy] = useState(false);
    const [isEditing, setIsEditing] = useState(false);
    const [ownerIncome, setOwnerIncome] = useState(0)
    const [clientIncome, setClientIncome] = useState(0)
    const [silverLimit, setSilverLimit] = useState(0)
    const [bronzeLimit, setBronzeLimit] = useState(0)
    const [goldLimit, setGoldLimit] = useState(0)
  

  const values = {
    ownerIncome, 
    clientIncome,
    bronzeLimit,
    silverLimit,
    goldLimit
  }
    useEffect(() => {
        const headers = {'Content-Type' : 'application/json',
                             'Authorization' : `Bearer ${localStorage.jwtToken}`}
        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers})
            .then(response => {
                var user = response.data;
                console.log(user);
                axios.get(SERVER_URL + `/users/getRole/${user.id}`, {headers:headers})
                .then(response => {
                    setRole(response.data);
                    if(response.data === "ROLE_ADMIN" && user.firstLogin===true) {
                        setFirstLogin(true);
                    }
                });
                axios.get(SERVER_URL + "/loyaltyProgramme/get", {headers: headers})
                .then(r => {
                    setLoyaltyProgramme(r.data);
                    if(r.data == null) {
                        setAddLoy(true);
                    } else {
                        setOwnerIncome(r.data.ownerIncome)
                        setClientIncome(r.data.clientIncome)
                        setBronzeLimit(r.data.bronzeLimit)
                        setSilverLimit(r.data.silverLimit)
                        setGoldLimit(r.data.goldLimit)
                        setAddLoy(false);
                    }
                });
            });
    }, [SERVER_URL])
    return(
        <div>
            <div className="container-home1">
                <div className="title">
                    LOYALTY PROGRAMME
                    {addLoy && <button className="add-loyalty-btn" onClick={(e) => {setAddLoyalty(true)}}>+</button>}
                </div>
                
            </div>
            <div className="col">
                    <div className="container-home-loy">
                        <div className="info">
                            <p style={{color: 'black', fontWeight: '700', fontSize: '18px', marginTop: '15px'}}>Entity owner income: {!isEditing && <p style={{color: 'blue', fontWeight: '700', fontSize: '20px', marginTop: '15px'}}>{loyaltyProgramme.ownerIncome} %</p>} </p>
                            {isEditing && <div> <input className='edit-loyalty-input' type="number" min={0} max={100} onChange={(e) => {setOwnerIncome(e.target.value)}} value={ownerIncome}/> % <br /> </div> }
                            <p style={{color: 'black', fontWeight: '700', fontSize: '18px', marginTop: '15px'}}>Client income: {!isEditing && <p style={{color: 'blue', fontWeight: '700', fontSize: '20px', marginTop: '15px'}}> {loyaltyProgramme.clientIncome} %</p>} </p>
                            {isEditing && <div> <input className='edit-loyalty-input' type="number" min={0} max={100} onChange={(e) => {setClientIncome(e.target.value)}} value={clientIncome}/> % <br /> </div> }
                            <p style={{color: 'black', fontWeight: '700', fontSize: '18px', marginTop: '15px'}}>Bronze limit: {!isEditing && <p style={{color: 'blue', fontWeight: '700', fontSize: '20px', marginTop: '15px'}}> {loyaltyProgramme.bronzeLimit} points</p>} </p>
                            {isEditing && <div> <input className='edit-loyalty-input' type="number" min={0} onChange={(e) => {setBronzeLimit(e.target.value)}} value={bronzeLimit}/> points <br /> </div>}
                            <p style={{color: 'black', fontWeight: '700', fontSize: '18px', marginTop: '15px'}}>Silver limit: {!isEditing && <p style={{color: 'blue', fontWeight: '700', fontSize: '20px', marginTop: '15px'}}> {loyaltyProgramme.silverLimit} points</p>} </p>
                            {isEditing && <div> <input className='edit-loyalty-input' type="number" min={0} onChange={(e) => {setSilverLimit(e.target.value)}} value={silverLimit}/> points <br /> </div>}
                            <p style={{color: 'black', fontWeight: '700', fontSize: '18px', marginTop: '15px'}}>Gold limit: {!isEditing && <p style={{color: 'blue', fontWeight: '700', fontSize: '20px', marginTop: '15px'}}> {loyaltyProgramme.goldLimit} points</p>} </p>
                            {isEditing && <div> <input className='edit-loyalty-input' type="number" onChange={(e) => {setGoldLimit(e.target.value)}} value={goldLimit}/> points </div>}
                        </div>
                        {!isEditing && <button className="edit-loyalty-btn" onClick={(e) => {setIsEditing(true)}}>edit</button>}
                        {isEditing && <button className="cancel-loyalty-btn" onClick={(e) => {setIsEditing(false)}}>cancel</button>}
                        {isEditing && <button className="confirm-loyalty-btn" onClick={(e) => {setIsEditing(false)}}>confirm</button>}
                    </div>
                </div>
            <AddLoyaltyProgramme modalIsOpen={addLoyalty} setModalIsOpen={setAddLoyalty}/>
            <AdminFirstLogin modalIsOpen={firstLogin} setModalIsOpen={setFirstLogin}/>
        </div>
    )
}
export default AdminHomepage;