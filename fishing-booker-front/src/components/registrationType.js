import './../css/registration.css';
import React from 'react'
import { Link } from 'react-router-dom';

const RegistrationType = () => {
    return (
      <div class="container-reg">
      <div className="title">Choose registration type</div>
      <div className="optins">
      <div class="option">
        <input type="radio" name="card" id="client" value="client" />
        <label class="typeName" for="client" aria-label="client">
          <span></span>
          
          Client
          
          <br/><label class="description">
            As a client you can book which ever adventure you want!
          </label>
          
        </label>
      </div>
      
      <div class="option">
        <input type="radio" name="card" id="boatOwner" value="boatOwner" />
        <label class="typeName" for="boatOwner" aria-label="boatOwner">
          <span></span>
          
          Boat owner
          
          <br/><label class="description">
            You can rent your boat for adventures to our clients and have professional insight in your business!
          </label>
          
        </label>
      </div>
      
      <div class="option">
        <input type="radio" name="card" id="lodgeOwner" value="lodgeOwner" />
        <label class="typeName" for="lodgeOwner" aria-label="lodgeOwner">
          <span></span>
          
          Lodge owner
          
          <br/><label class="description">
            You can rent your lodge for adventures to our clients and have professional insight in your business!
          </label>
          
        </label>
      </div>

      <div class="option">
        <input type="radio" name="card" id="instructor" value="instructor" />
        <label class="typeName" for="instructor" aria-label="instructor">
          <span></span>
          
          Fishing instructor
          
          <br/><label class="description">
            You can teach our clients how to fish and make their adventures more fun! You also have professional insight in your business!
          </label>
          
        </label>
      </div>

      <Link to='/registrationForm'>
        <div class="button">
            <input type="submit" value="Registration"/>
        </div>
      </Link>
      </div>

    </div>
    )
}

export default RegistrationType;