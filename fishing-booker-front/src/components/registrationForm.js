import './../css/registration.css';
import { Link } from "react-router-dom";

const RegistrationForm = () => {
  return (
  <div class="container-reg">
    <div class="title">Registration</div>
    <div class="content">
      <form action="#">
        <div class="user-details">
          <div class="input-box">
            <span class="details">Name</span>
            <input type="text" placeholder="Enter your name" required/>
          </div>
          <div class="input-box">
            <span class="details">Phone Number</span>
            <input type="text" placeholder="Enter your phone number" required/>
          </div>
          <div class="input-box">
            <span class="details">Surname</span>
            <input type="text" placeholder="Enter your surname" required/>
          </div>
          <div class="input-box">
            <span class="details">Email</span>
            <input type="text" placeholder="Enter your email" required/>
          </div>
          <div class="input-box">
            <span class="details">Address</span>
            <input type="text" placeholder="Enter your address" required/>
          </div>
          <div class="input-box">
            <span class="details">Password</span>
            <input type="text" placeholder="Enter your password" required/>
          </div>
          <div class="input-box">
            <span class="details">City</span>
            <input type="text" placeholder="Enter your city" required/>
          </div>
          <div class="input-box">
            <span class="details">Confirm Password</span>
            <input type="text" placeholder="Confirm your password" required/>
          </div>
          <div class="input-box">
            <span class="details">Country</span>
            <input type="text" placeholder="Enter your country" required/>
          </div>
        </div>
        <div class="button">
          <p className="reg-message">Alredy have an account? <Link className="link" to="/login">Log in</Link></p> <br/>
          <input type="submit" value="Register"/>
        </div>
      </form>
    </div>
  </div>
  )
}

export default RegistrationForm;