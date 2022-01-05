import axios from "axios";
import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import star from "../../images/star.png";
import { useToasts } from "react-toast-notifications";

const Subscriptions = () => {

    const [subscriptions, setSubscriptions] = useState([])
    const SERVER_URL = process.env.REACT_APP_API;
    const { id } = useParams();
    const { addToast } = useToasts();
    const [isSubscribed, setIsSubscribed] = useState(true)

    useEffect(() => {
        axios.get(SERVER_URL + "/subscribe/subscriptions?id=" + id)
            .then(response => setSubscriptions(response.data))

    }, [!isSubscribed])

    const renderStars = (grade) => {
        let stars = []
        for (var i = 0; i < parseInt(grade); i++) {
            stars.push(<img key={i} src={star}/>)
        }
        return stars;
    }

    const handleUnsubscribe = (entityId) => {
        axios.delete(SERVER_URL + "/subscribe/unsubscribe?entityId=" + entityId + "&userId=" + id)
            .then(() => {
                addToast("You are no longer subscribed.", { appearance: "success" });
                setIsSubscribed(!isSubscribed);
            })
    }

    const allSubscriptions = subscriptions.length ? (
        subscriptions.map((subscription, index) => {
            return (
                <div className="col" key={index}>
                    <div className="card subscription">
                        <div className="info"> <br></br>
                            <a className="subscribe-link" onClick={() => handleUnsubscribe(subscription.id)}>Unsubscribe</a>
                            <p className="entity-info name">{subscription.name} <div className="stars">{renderStars(subscription.averageGrade)} </div></p>
                            <p className="entity-info location">{subscription.location.address}, {subscription.location.city}, {subscription.location.country}</p>
                            <p className="entity-info description">{subscription.description}</p>
                        </div>
                    </div>
                </div>
            )
        })
    ) : (<div><p style={{marginLeft: '30px'}}>You don't have any subscriptions yet.</p></div>)

    return (
        <div style={{top:'0',
            marginTop: '170px',
            marginLeft: '50px',
            position: 'absolute'}}>
            <h1 className="title-reservation">Subscriptions</h1> <br></br>
            {allSubscriptions}
        </div>
    )
}

export default Subscriptions;