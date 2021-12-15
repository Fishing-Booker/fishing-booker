import React, { useEffect, useState } from 'react'
import { Link, useParams} from "react-router-dom";
import '../../css/lodgeProfile.css';
import lodgeImg2 from "../../images/lodgeImg2.png";
import lodgeImg3 from "../../images/lodgeImg3.jpg";

const LodgeImages = () => {

    const {lodgeId} = useParams();

    const SERVER_URL = process.env.REACT_APP_API; 

    const [images, setImages] = useState([]);

    useEffect(() => {

        /*axios.get(SERVER_URL + 'lodgeImages/' + lodgeId)
            .then(response => {setImages(response.data); console.log(response.data)});
        }, [])*/

        setImages([
            {
                "id" : 1,
                "path" : "../images/lodgeImg1.jpg"
            },
            {
                "id" : 2,
                "path" : "../images/lodgeImg1.jpg"
            },
            {
                "id" : 3,
                "path" : "../images/lodgeImg1.jpg"
            }
        ])

    }, [])

    const allImages = images.length ? (
        images.map(image => {
            return(
                <div className="card-image" key={image.id}>
                    <img src={image.path} />
                </div>
            )
        }) 
    ): (
        <div className="center">
            Add images for your lodge
        </div>
    )

    return (
        <div className="wrapper">
            <div className="left">
                <h4>LODGE PROFILE</h4><br/>
                <Link className="sidebar-link" to={"/lodgeImages/" + lodgeId}>Images</Link><br/><br/>
                <Link className="sidebar-link" to={"/lodgeRules/" + lodgeId}>Rules</Link><br/><br/>
                <Link className="sidebar-link" to={"/lodgePricelist/" + lodgeId}>Pricelist</Link><br/><br/>
                <Link className="sidebar-link" to="/lodgeActions">Actions</Link><br/><br/>
                <Link className="sidebar-link" to="/lodgeReservationCalendar">Reservation calendar</Link><br/><br/>
            </div>
            <div className="right">
                <div className="info">
                    <h3>LODGE IMAGES</h3>
                    <div className="info_data-images">
                        { allImages }
                    </div> <br/> <br/>
                    <button className="edit-profile-btn" >Add new images</button>
                </div>
            </div>
        </div>
    )

}

export default LodgeImages;