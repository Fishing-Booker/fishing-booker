import { Link } from "react-router-dom"

const Homepage = () => {
    return (
        <div className="col">
            <h1> Fishing booker </h1>
            <p className="main-description"> Book your next fishing trip! </p>
            <button className="explore-btn" type="button"><Link className="explore-link" to ="/explore">Explore</Link> </button>
        </div>
    )
}

export default Homepage;