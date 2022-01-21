
import { useState, useEffect } from "react";
import { Link} from "react-router-dom";
import React from 'react'
import '../../css/homePage.css';
import deleteImg from '../../images/trash.png';
import addImg from '../../images/plus.png'
import axios from "axios";
import AddLodgeFrom from "./addLodgeForm";
import DeleteLodgeForm from "./deleteLodgeForm";
import noImg from '../../images/noProfilePicture.jpg';
import { useToasts } from "react-toast-notifications";

const LodgeOwnerHomePage = () => {

    const SERVER_URL = process.env.REACT_APP_API; 

    const { addToast } = useToasts();

    const [user, setUser]  =useState([]);
    const [lodges, setLodges] = useState([]);
    const [lodgeId, setLodgeId] = useState("");
    const [searchField, setSearchField] = useState("");
    const [filteredLodges, setFilteredLodges] = useState([]);
    const [addLodge, setAddLodge] = useState(false);
    const [deleteLodgeForm, setDeleteLodgeForm] = useState(false);

    const [lodgeName, setLodgeName] = useState("");
    const [url, setUrl] = useState(SERVER_URL + '/lodges');

    useEffect(() => {
        const headers = {'Content-Type' : 'application/json',
                     'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + "/users/getLoggedUser", { headers: headers })
        .then(response => {
            setUser(response.data);
            var user = response.data;
        
            axios.get(SERVER_URL + '/lodges/ownerLodges/' + user.id, { headers: headers})    
                .then(response => {
                    setLodges(response.data); 

                    var lodges = response.data;
                    var allLodges = [];
                    for(let lodge of lodges){
                        if(lodge.profileImage == ""){
                            lodge.profileImage = noImg;
                        }
                        allLodges.push(lodge);
                    }
                    setLodges(allLodges);
                });
        });

    }, [])


    useEffect(() => {

        const headers = {'Content-Type' : 'application/json',
                     'Authorization' : `Bearer ${localStorage.jwtToken}`}

        axios.get(url, {headers: headers})
            .then(response => {
                console.log(response.data);
                var lodges = response.data;
                    var allLodges = [];
                    for(let lodge of lodges){
                        if(lodge.profileImage == ""){
                            lodge.profileImage = noImg;
                        }
                        allLodges.push(lodge);
                    }
                    setLodges(allLodges);
            })
        

    }, [url])

    

    const deleteLodge = (id) => {
        setLodgeId(id);
        
        const headers = {'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.jwtToken}`}

        axios.get(SERVER_URL + '/reservations/checkEntityFutureReservations/' + id, {headers: headers})
            .then(response => {
                var availableDelete = response.data;
                if(availableDelete === false){
                    setLodgeId(id);
                    setDeleteLodgeForm(true);
                } else {
                    addToast("It is not available to delete lodge, because there are future reservations!", { appearance: "error" });
                }
            });
    }

    const allLodges = lodges.length ? (
        lodges.map(lodge => {
            return (
                <div className="lodge-card" key={lodge.id}>
                    <div className="lodge-card-body">
                        <div className="lodge-image">
                            <img  src={lodge.profileImage}  />
                        </div>
                        <Link to={'/lodge/' + lodge.id} style={{textDecoration: 'none', color: 'black'}}><div className="title">{lodge.name}</div></Link>
                        
                        <div className="buttons">
                            <button title="Delete lodge" onClick={() => deleteLodge(lodge.id)}>
                                <img src={deleteImg}/>
                            </button>
                        </div>
                    </div>
                </div>
            )
        })
    ) : (
        <div className="center">
            Add your lodge
        </div>
    );

    return (
        <div>
                
            <div className="container-home">
                
                <div className="title">Welcome {user.username}!</div>

                <div className="input-box-lodge">
                    <input type="search" placeholder="Enter lodge name " value={lodgeName}
                        onChange={(e) => {
                            setLodgeName(e.target.value)
                            setUrl(SERVER_URL + '/lodges/searchLodge?name=' + lodgeName + "&owner=" + user.id);
                            if(e.target.value === ''){
                                setUrl(SERVER_URL + '/lodges/ownerLodges/' + user.id);
                            }
                        }}
                    />
                    <div className="modal-place">
                        <Link to="#addLodge" onClick={() => setAddLodge(true)}>
                            <button title="Add lodge">
                                <img src={addImg}/>
                            </button>
                        </Link>
                        <AddLodgeFrom modalIsOpen={addLodge} setModalIsOpen={setAddLodge} />
                    </div>
                    
                </div>
                
                { allLodges }
                
            </div>
            
            <DeleteLodgeForm className="deleting-wrapper" modalIsOpen={deleteLodgeForm} setModalIsOpen={setDeleteLodgeForm} lodgeId={lodgeId}/>
        </div>
        
        
    )
    
}


export default LodgeOwnerHomePage;

