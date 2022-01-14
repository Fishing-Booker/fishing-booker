import React, { useEffect, useState } from 'react'
import { Link, useParams} from "react-router-dom";
import '../../css/lodgeProfile.css';
import lodgeImg2 from "../../images/lodgeImg2.png";
import lodgeImg3 from "../../images/lodgeImg3.jpg";
import axios from "axios";
import UploadImage from '../uploadImage';
import DeleteImageDialog from '../deleteImageDialog';

const ShipImages = () => {

    const {shipId} = useParams();

    const SERVER_URL = process.env.REACT_APP_API; 

    const [images, setImages] = useState([]);

    const [uploadImage, setUploadImage] = useState(false);
    const[deleteImage, setDeleteImage] = useState(false);
    const [imageId, setImageId] = useState(0);

    useEffect(() => {

        const headers = {'Content-Type': 'application/json',
                        'Authorization': `Bearer ${localStorage.jwtToken}`}
        axios.get(SERVER_URL + '/images/getImages/' + shipId, {headers:headers})
        .then(response => {
            setImages(response.data);
            console.log(response.data);

        });

    }, [])

    const deleteImageFunc = (image) => {
        console.log(image)
        setDeleteImage(true)
        setImageId(image.imageId)
    }

    const allImages = images.length ? (
        images.map(image => {
            return(
                <div className="card-image" key={image.imageId}>
                    <img src={image.base64} onClick={() => deleteImageFunc(image)} />
                </div>
            )
        }) 
    ): (
        <div className="center">
            Add images for your ship
        </div>
    )

    return (
        <div className="wrapper">
            <div className="left">
                <h4>SHIP PROFILE</h4><br/>
                <Link className="sidebar-link" to={"/shipImages/" + shipId}>Images</Link><br/><br/>
                <Link className="sidebar-link" to={"/shipRules/" + shipId}>Rules</Link><br/><br/>
                <Link className="sidebar-link" to={"/shipPricelist/" + shipId}>Pricelist</Link><br/><br/>
                <Link className="sidebar-link" to={"/shipActions/" + shipId}>Actions</Link><br/><br/>
                <Link className="sidebar-link" to={"/shipReservationCalendar/" + shipId}>Reservation calendar</Link><br/><br/>
            </div>
            <div className="right">
                <div className="info">
                    <h3>SHIP IMAGES</h3>
                    <div className="info_data-images">
                        { allImages }
                    </div> <br/> <br/>
                    <button className="edit-profile-btn" onClick={() => setUploadImage(true)}>Add new images</button>
                </div>
            </div>

            <UploadImage modalIsOpen={uploadImage} setModalIsOpen={setUploadImage} entityId={shipId} />
            <DeleteImageDialog modalIsOpen={deleteImage} setModalIsOpen={setDeleteImage} imageId={imageId}/>
        </div>
    )

}

export default ShipImages;