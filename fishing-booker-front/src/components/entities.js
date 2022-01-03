import React from 'react'
import { Link } from 'react-router-dom'

const Entities = () => {
    return (
        <div className="col">
            <Link to="/adventures">
                <div className="card card1">
                    <h5> Adventures</h5>
                    <p> See our adventures! </p>
                </div>
            </Link>
            <Link to="/lodges">
                <div className="card card2">
                    <h5> Lodges</h5>
                    <p> See our lodges! </p>
                </div>
            </Link>
            <Link to="/ships">
                <div className="card card3">
                    <h5> Ships</h5>
                    <p> See our ships! </p>
                </div>
            </Link>
        </div>
    )
}

export default Entities