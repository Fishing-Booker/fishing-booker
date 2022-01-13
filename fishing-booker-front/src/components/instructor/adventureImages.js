import axios from "axios";
import { useState, useEffect } from "react";
import { useParams, Link } from "react-router-dom";
import '../../css/lodgeProfile.css';
import DeleteImageDialog from "../deleteImageDialog";
import UploadImage from "../uploadImage";

const AdventureImages = () => {
    const {adventureId} = useParams();

    const SERVER_URL = process.env.REACT_APP_API;

    const [images, setImages] = useState([]);

    const [uploadImage, setUploadImage] = useState(false);

    const[deleteImage, setDeleteImage] = useState(false);

    const [imageId, setImageId] = useState(0);

    useEffect(() => {

        const headers = {'Content-Type': 'application/json',
                        'Authorization': `Bearer ${localStorage.jwtToken}`}
        axios.get(SERVER_URL + '/images/getImages/' + adventureId, {headers:headers})
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

    return (
        <div className="wrapper">
            <div className="left">
                <h4>ADVENTURE PROFILE</h4><br/>
                <Link className="sidebar-link" to={"/adventureProfile/"+ adventureId}>Info</Link><br/><br/>
                <Link className="sidebar-link" to={"/adventureImages/" + adventureId}>Images</Link><br/><br/>
                <Link className="sidebar-link" to={"/adventureRules/" + adventureId}>Rules</Link><br/><br/>
                <Link className="sidebar-link" to={"/adventurePricelist/" + adventureId}>Pricelist</Link><br/><br/>
                <Link className="sidebar-link" to={"/adventureActions/" + adventureId}>Actions</Link><br/><br/>
            </div>
            <div className="right">
                <div className="info">
                    <h3>ADVENTURE IMAGES</h3>
                    {images.length==0 && <div>Add images</div>}
                    {images && <div className="info_data-images">
                        {images.map((image) => (
                            <div className="card-image" key={image.imageId}>
                                <img src={image.base64} onClick={() => deleteImageFunc(image)} />
                            </div>
                        ))}
                    </div> }<br/> <br/>
                    <button className="edit-profile-btn" onClick={() => setUploadImage(true)}>Add new images</button>
                </div>
            </div>
            <UploadImage modalIsOpen={uploadImage} setModalIsOpen={setUploadImage}/>
            <DeleteImageDialog modalIsOpen={deleteImage} setModalIsOpen={setDeleteImage} imageId={imageId}/>
        </div>
    )

} 
export default AdventureImages;