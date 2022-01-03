import { useState, useEffect } from "react";
import { useParams, Link } from "react-router-dom";
import '../../css/lodgeProfile.css';

const AdventureImages = () => {
    const {adventureId} = useParams();
    const lodgeId= 1;

    const SERVER_URL = process.env.REACT_APP_API;

    const [images, setImages] = useState([]);

    useEffect(() => {

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
                <h4>ADVENTURE PROFILE</h4><br/>
                <Link className="sidebar-link" to={"/adventureImages/" + adventureId}>Images</Link><br/><br/>
                <Link className="sidebar-link" to={"/adventureRules/" + adventureId}>Rules</Link><br/><br/>
                <Link className="sidebar-link" to={"/adventurePricelist/" + adventureId}>Pricelist</Link><br/><br/>
                <Link className="sidebar-link" to={"/adventureActions/" + adventureId}>Actions</Link><br/><br/>
                <Link className="sidebar-link" to={"/adventureReservationCalendar/" + adventureId}>Reservation calendar</Link><br/><br/>
            </div>
            <div className="right">
                <div className="info">
                    <h3>ADVENTURE IMAGES</h3>
                    <div className="info_data-images">
                        { allImages }
                    </div> <br/> <br/>
                    <button className="edit-profile-btn" >Add new images</button>
                </div>
            </div>
        </div>
    )

} 
export default AdventureImages;