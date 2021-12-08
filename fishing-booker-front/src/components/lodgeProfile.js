import React, { Component } from 'react'
import { Link, BrowserRouter as Router, Route, Switch } from "react-router-dom";
import { withRouter } from 'react-router-dom';
import '../css/adminsProfile.css'

class LodgeProfile extends Component {

    state = {
        lodge: {
            id: 0,
            name: "",
            location: "",
            description: "",
            rules: "",
            images: []
        }
    }

    componentDidMount() {
        const lodgeId = this.props.match.params.lodgeId;

        /* OVAKO SE RADI GET ZAHTEV */
        /*axios.get('https://jsonplaceholder.typicode.com/posts/' + id)
            .then(response => {
                this.setState({
                    post: response.data
                })
            })*/

        /* OVO TREBA OBRISATI KADA SE URADI GET */
        const allLodges = [
            {
                id: 1,
                name: "Lodge1",
                location: "Novi Sad",
                description: "Our lodge is the best",
                rules: "RULE1, RULE2, RULE3",
                images: []
            },
            {
                id: 2,
                name: "Lodge2",
                location: "Beograd",
                description: "Our lodge is the best too",
                rules: "RULE4, RULE5, RULE6",
                images: []
            }
        ]

        for (let l of allLodges) {
            if(l.id == lodgeId){
                this.setState({
                    lodge: l
                })
                break;
            }
        }

        /* SVE IZNAD SE BRISE */ 

    }

    render() {
        const {lodge} = this.state;
        
        return (
            <div className="wrapper">
                <div className="left">
                    <h4>LODGE PROFILE</h4><br/>
                    <Link className="sidebar-link" to={"/lodgeImages/" + lodge.id }>Images</Link><br/><br/>
                    <Link className="sidebar-link" to={"/lodgeRules/" + lodge.id}>Rules</Link><br/><br/>
                    <Link className="sidebar-link" to="/lodgePricelist">Pricelist</Link><br/><br/>
                    <Link className="sidebar-link" to="/lodgeActions">Actions</Link><br/><br/>
                    <Link className="sidebar-link" to="/lodgeReservationCalendar">Reservation calendar</Link><br/><br/>
                    <a href="">Reservations reports</a><br/><br/>
                </div>
                <div className="right">
                    <div className="info">
                        <h3>{lodge.name}</h3>
                        <div className="info_data">
                            <div className="data">
                                <h4>Address</h4>
                                <input  value={lodge.location} disabled/>
                            </div>
                            <div className="data">
                                <h4>Bedrooms</h4>
                                <input value="Number of rooms and beds in them" disabled/>
                            </div>
                            <div className="data">
                                <h4>Additional services</h4>
                                <input  value="List of services" disabled/>
                            </div>
                            <div className="data">
                                <h4>Description</h4>
                                <textarea value={lodge.description} disabled/>
                            </div>
                        </div> <br/> <br/>
                        <Link to="/editLodge">
                            <button className="edit-profile-btn" >
                                Edit
                            </button>
                        </Link>
                    </div>
                </div>
            </div>
        )

    }
    
}

export default withRouter(LodgeProfile);