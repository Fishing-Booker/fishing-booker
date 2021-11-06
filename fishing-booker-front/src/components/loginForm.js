import './../css/registration.css';
import { Link } from "react-router-dom";

const LoginForm = () => {
    return (
        <div class="container-reg">
        <div class="title">Log In</div>
        <div class="content">
          <form action="#">
            <div class="user-details">
              <div class="input-box">
                <span class="details">Email</span>
                <input type="text" placeholder="Enter your email" required/>
              </div>
              <div class="input-box">
                <span class="details">Password</span>
                <input type="text" placeholder="Enter your password" required/>
              </div>
            </div>
            <div class="button">
              <p className="reg-message">Don't have an account? <Link className="link" to="/register">Register</Link></p> <br/>
              <input type="submit" value="Log in"/>
            </div>
          </form>
        </div>
      </div>
    )
}

export default LoginForm;