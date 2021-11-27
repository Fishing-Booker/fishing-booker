import React, { Component } from 'react'
import { Link, BrowserRouter as Router, Route, Switch } from "react-router-dom";
import '../css/lodgeProfile.css';
import { withRouter } from 'react-router-dom'

class LodgeRules extends Component {

    state = {
        rules: "",
        lodgeId: 0
    }

    componentDidMount() {
        const lodgeId = this.props.match.params.lodgeId;
        this.setState({
            lodgeId: lodgeId
        })

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
                    rules: l.rules
                })
                break;
            }
        }

        /* SVE IZNAD SE BRISE */
    }

    render() {
        const {rules} = this.state;
        const {lodgeId} = this.state;

        return (
            <div className="wrapper">
                <div className="left">
                    <h4>Lodge name</h4><br/>
                    <Link className="sidebar-link" to={"/lodgeImages/" + lodgeId}>Images</Link><br/><br/>
                    <a href="">Rules</a><br/><br/>
                    <Link className="sidebar-link" to="/lodgePricelist">Pricelist</Link><br/><br/>
                    <a href="">Reservation calendar</a><br/><br/>
                    <a href="">Actions for reservations</a><br/><br/>
                    <a href="">Reservations reports</a><br/><br/>
                </div>
                <div className="right">
                    <div className="info">
                        <h3>LODGE RULES</h3>
                        <div className="info_data">
                            {rules}
                        </div> <br/> <br/>
                        <button className="edit-profile-btn" >Save changes</button>
                    </div>
                </div>
            </div>
        )

    }

    
}

export default withRouter(LodgeRules);