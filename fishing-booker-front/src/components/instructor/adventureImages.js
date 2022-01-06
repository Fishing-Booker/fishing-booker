import axios from "axios";
import { useState, useEffect } from "react";
import { useParams, Link } from "react-router-dom";
import '../../css/lodgeProfile.css';
import UploadImage from "../uploadImage";

const AdventureImages = () => {
    const {adventureId} = useParams();

    const SERVER_URL = process.env.REACT_APP_API;

    const [images, setImages] = useState([]);

    const [uploadImage, setUploadImage] = useState(false);

    useEffect(() => {

        const headers = {'Content-Type': 'application/json',
                        'Authorization': `Bearer ${localStorage.jwtToken}`}
        axios.get(SERVER_URL + '/images/getImages/' + adventureId, {headers:headers})
        .then(response => {
            setImages(response.data);
        });


    }, [])

    const allImages = images.length ? (
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
                    <h3>ADVENTURE IMAGES</h3>
                    <div className="info_data-images">
                        { allImages }
                    </div> <br/> <br/>
                    <button className="edit-profile-btn" onClick={() => setUploadImage(true)}>Add new images</button>
                </div>
            </div>
            <UploadImage modalIsOpen={uploadImage} setModalIsOpen={setUploadImage}/>
        </div>
    )

} 
export default AdventureImages;