import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import deleteImg from '../../images/trash.png';
import addImg from '../../images/plus.png'
import '../../css/usersProfile.css';

const AdventureRules = () => {
    const {adventureId} = useParams();

    const [rules, setRules] = useState([]);
    const [editRules, setEditRules] = useState(false);
    
    useEffect(() => {
        setRules([
            {
                "id" : 1,
                "text": "Rule1"
            },
            {
                "id" : 2,
                "text": "Rule2"
            },
            {
                "id" : 3,
                "text": "Rule3"
            },
            {
                "id" : 4,
                "text": "Rule4"
            },
            {
                "id" : 5,
                "text": "Rule5"
            },
            {
                "id" : 6,
                "text": "Rule6"
            }
        ])

    }, [])

    const allRules = rules.length ? (
        rules.map(rule => {
            return (
                <div key={rule.id}>
                    * {rule.text}
                    <br/><br/>
                </div>
            )
        })
    ) : (
        <div>
            Add rules for your lodge!
        </div>
    );

    const editRulesForm = rules.length ? (
        rules.map(rule => {
            return (
                <div key={rule.id}>
                    * {rule.text} 
                    <button className='rules-btn' >
                        <img alt="Cannot load" src={deleteImg} />
                    </button>
                    <br/><br/>
                </div>
            )
        })
    ) : (
        <div>
            Add rules for your lodge!
        </div>
    );

    const rulesForm = editRules ? (
            <div className="info-data">
                { editRulesForm }
                <p style={{color:'black', fontSize: 'small', fontStyle: 'italic'}}>Add new rule</p>
                <input className='rules-input' type="text" /> 
                <button className='rules-btn'>
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
                <Link className="sidebar-link" to={"/adventureImages/" + adventureId}>Images</Link><br/><br/>
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