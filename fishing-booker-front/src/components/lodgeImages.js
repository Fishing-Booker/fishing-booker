import React, { Component } from 'react'
import { Link, BrowserRouter as Router, Route, Switch } from "react-router-dom";
import '../css/lodgeProfile.css';
import lodgeImg1 from "../images/lodgeImg1.jpg";
import lodgeImg2 from "../images/lodgeImg2.png";
import lodgeImg3 from "../images/lodgeImg3.jpg";
import { withRouter } from 'react-router-dom';

class LodgeImages extends Component {

    state = {
        images: [],
        lodgeId: 0
    }

    componentDidMount() {
        const lodgeId = this.props.match.params.lodgeId;
        this.setState({
            lodgeId : lodgeId
        })

        /* OVO TREBA OBRISATI KADA SE URADI GET */
        const allLodges = [
            {
                id: 1,
                name: "Lodge1",
                location: "Novi Sad",
                description: "Our lodge is the best",
                rules: "RULE1, RULE2, RULE3",
                images: [lodgeImg1, lodgeImg1, lodgeImg1, lodgeImg1, lodgeImg1, lodgeImg1]
            },
            {
                id: 2,
                name: "Lodge2",
                location: "Beograd",
                description: "Our lodge is the best too",
                rules: "RULE4, RULE5, RULE6",
                images: [lodgeImg2, lodgeImg2, lodgeImg2, lodgeImg2, lodgeImg2, lodgeImg2]
            }
        ]

        for (let l of allLodges) {
            if(l.id == lodgeId){
                this.setState({
                    images: l.images
                })
                break;
            }
        }

        /* SVE IZNAD SE BRISE */ 

    }

    render() {
        const {images} = this.state;
        const {lodgeId} = this.state;

        const imageList = images.length ? (
            images.map(image => {
                return(
                    <div className="card-image">
                        <img src={image} />
                    </div>
                )
            }) 
        ): (
            <div className="center">
                Add images for your lodge
            </div>
        )

        return (
            <div class="wrapper">
                <div class="left">
                    <h4>Lodge name</h4><br/>
                    <a href="">Images</a><br/><br/>
                    <Link className="sidebar-link" to={"/lodgeRules/" + lodgeId }>Rules</Link><br/><br/>
                    <Link className="sidebar-link" to="/lodgePricelist">Pricelist</Link><br/><br/>
                    <a href="">Reservation calendar</a><br/><br/>
                    <a href="">Actions for reservations</a><br/><br/>
                    <a href="">Reservations reports</a><br/><br/>
                </div>
                <div class="right">
                    <div class="info">
                        <h3>LODGE IMAGES</h3>
                        <div class="info_data-images">
                            {imageList}
                        </div> <br/> <br/>
                        <button className="edit-profile-btn" >Add new images</button>
                    </div>
                </div>
            </div>
        )

    }

    
}

export default withRouter(LodgeImages);