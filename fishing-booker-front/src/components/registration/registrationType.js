import '../../css/registration.css';
import React from 'react'
import { Link } from 'react-router-dom';
import { useState } from 'react';

const RegistrationType = () => {
    const[role, setRole] = useState("")
    return (
      <div className="container-reg">
      <div className="title">Choose registration type</div> <br></br>
      <div className="optins">
      <div className="option">
        <input type="radio" name="card" id="client" onChange={(e) => setRole(e.target.value)} value="ROLE_CLIENT" />
        <label className="typeName" htmlFor="client" aria-label="client">
          <span></span>
          
          Client
          
          <br/><label className="description">
            As a client you can book which ever adventure you want!
          </label>
          
        </label>
      </div>
      
      <div className="option">
        <input type="radio" name="card" id="boatOwner" onChange={(e) => setRole(e.target.value)} value="ROLE_SHIPOWNER" />
        <label className="typeName" htmlFor="boatOwner" aria-label="boatOwner">
          <span></span>
          
          Boat owner
          
          <br/><label className="description">
            You can rent your boat for adventures to our clients and have professional insight in your business!
          </label>
          
        </label>
      </div>
      
      <div className="option">
        <input type="radio" name="card" id="lodgeOwner" onChange={(e) => setRole(e.target.value)} value="ROLE_LODGEOWNER" />
        <label className="typeName" htmlFor="lodgeOwner" aria-label="lodgeOwner">
          <span></span>
          
          Lodge owner
          
          <br/><label className="description">
            You can rent your lodge for adventures to our clients and have professional insight in your business!
          </label>
          
        </label>
      </div>

      <div className="option">
        <input type="radio" name="card" id="instructor" onChange={(e) => setRole(e.target.value)} value="ROLE_INSTRUCTOR" />
        <label className="typeName" htmlFor="instructor" aria-label="instructor">
          <span></span>
          
          Fishing instructor
          
          <br/><label className="description">
            You can teach our clients how to fish and make their adventures more fun! You also have professional insight in your business!
          </label>
          
        </label>
      </div>

      <Link to={`/registrationForm/${role}`}>
        <div className="button">
            <input type="submit" value="Next"/>
        </div>
      </Link>
      </div>

    </div>
    )
}

export default RegistrationType;