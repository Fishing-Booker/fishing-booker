import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import deleteImg from '../../images/trash.png';
import addImg from '../../images/plus.png'
import '../../css/usersProfile.css';
import axios from "axios";

const AdventureRules = () => {
    const {adventureId} = useParams();
    const SERVER_URL = process.env.REACT_APP_API; 

    const [rules, setRules] = useState([]);
    const [editRules, setEditRules] = useState(false);

    const [newRule, setNewRule] = useState("");
    
    useEffect(() => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}
        axios.get(SERVER_URL + '/adventures/adventureRules/' + adventureId, {headers:headers})
        .then(response => {
            setRules(response.data);
            console.log(response.data);
        });

    }, [])

    const addRule = () => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.put(SERVER_URL + '/adventures/addRule/' + adventureId, newRule, {headers:headers})
        .then(response => {
            window.location.reload();
        });
    }

    const deleteRule = (index) => {
        const headers = {'Content-Type' : 'application/json', 'Authorization' : `Bearer ${localStorage.jwtToken}`}
        axios.delete(SERVER_URL + '/adventures/deleteRule/'+adventureId + '/' + index, {headers:headers})
        .then(response => {
            window.location.reload();
        });
    }

    const allRules = rules.length ? (
        rules.map(rule => {
            return (
                <div>
                    * {rule}
                    <br/><br/>
                </div>
            )
        })
    ) : (
        <div>
            Add rules for your adventure!
        </div>
    );

    const editRulesForm = rules.length ? (
        rules.map((rule, i) => {
            return (
                <div key={i}>
                    * {rule} 
                    <button className='rules-btn' onClick={() => deleteRule(i)}>
                        <img alt="Cannot load" src={deleteImg} />
                    </button>
                    <br/><br/>
                </div>
            )
        })
    ) : (
        <div>
            Add rules for your adventure!
        </div>
    );

    const rulesForm = editRules ? (
            <div className="info-data">
                { editRulesForm }
                <p style={{color:'black', fontSize: 'small', fontStyle: 'italic'}}>Add new rule</p>
                <input className='rules-input' type="text" onChange={(e) => {setNewRule(e.target.value)}} value={newRule} /> 
                <button className='rules-btn' onClick={() => addRule()}>
                    <img alt="Cannot load" src={addImg} />
                </button><br/><br/>
                <button className="edit-profile-btn" onClick={() => setEditRules(false)}>
                    Done
                </button>
            </div>
        ) : (
            <div>
                <div className="rules-info-data">
                    {allRules}
                </div> <br/> <br/>
                <button className="edit-profile-btn" onClick={() => setEditRules(true)}>Edit rules</button>
            </div>
    )

    return (
        <div className="wrapper">
            <div className="left">
                <h4>ADVENTURE PROFILE</h4><br/>
                <Link className="sidebar-link" to={"/adventureProfile/"+ adventureId}>Info</Link><br/><br/>
                <Link className="sidebar-link" to={"/adventureImages/" + adventureId}>Images</Link><br/><br/>
                <Link className="sidebar-link" to={"/adventureLocation/" + adventureId}>Location</Link><br/><br/>
                <Link className="sidebar-link" to={"/adventureRules/" + adventureId}>Rules</Link><br/><br/>
                <Link className="sidebar-link" to={"/adventurePricelist/" + adventureId}>Pricelist</Link><br/><br/>
                <Link className="sidebar-link" to={"/adventureActions/" + adventureId}>Actions</Link><br/><br/>
            </div>
            <div className="right">
                <div className="info">
                    <h3>ADVENTURE RULES</h3>
                    { rulesForm }
                </div>
            </div>
        </div>
    )

}
export default AdventureRules;