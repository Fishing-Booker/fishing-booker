import menuIcon from "../images/menu.png"
import Entities from "./entities";

const FrontPage = () =>  {
    return (
        <div>
            <div class="container">
                <div class="navbar">
                    <nav>
                        <ul>
                            <li><a href="">HOME</a></li>
                            <li><a href="">REGISTER</a></li>
                            <li><a href="">LOG IN</a></li>
                        </ul>
                    </nav>
                    <img src={menuIcon} class="menu-icon"/>
                </div>

                <div class="row">
                    <div class="col">
                        <h1> Fishing booker </h1>
                        <p class="main-description"> Book your next fishing trip! </p>
                        <button class="explore-btn" type="button">Explore</button>
                    </div>
                    <Entities/>
                </div>
	        </div>
        </div>
    )
}

export default FrontPage;

