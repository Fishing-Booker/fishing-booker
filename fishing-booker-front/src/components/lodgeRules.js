import React from 'react'
import { Link, BrowserRouter as Router, Route, Switch } from "react-router-dom";
import '../css/lodgeProfile.css';

const LodgeRules = () => {
    return (
        <div class="wrapper">
            <div class="left">
                <h4>Lodge name</h4><br/>
                <Link className="sidebar-link" to="/lodgeImages" >Images</Link><br/><br/>
                <a href="">Rules</a><br/><br/>
                <Link className="sidebar-link" to="/lodgePricelist">Pricelist</Link><br/><br/>
                <a href="">Reservation calendar</a><br/><br/>
                <a href="">Actions for reservations</a><br/><br/>
                <a href="">Reservations reports</a><br/><br/>
            </div>
            <div class="right">
                <div class="info">
                    <h3>LODGE RULES</h3>
                    <div class="info_data">
                        1. Enter the number of sentences you’d like to generate. In the ‘number of sentences’ box, enter the number of sentences you’d like the tool to create.<br/><br/>
                        2. Choose what types of sentences you’re looking for. Using the check boxes, select which types of sentences you’re interested in. Our tool offers regular sentences, phrases, and questions.<br/><br/>
                        3. Choose your sentence length. Our generator can create short sentences or long sentences. You can also select the ‘all’ option if you’re interested in sentences of all lengths.<br/><br/>
                        4. Click the green ‘generate sentences’ button.<br/><br/>
                        5. Copy or save your generated sentences. Once you’ve generated your sentences, you can copy or save the ones that catch your eye. Just click the ‘copy generated sentences’ button and the sentences will be saved for you to paste into another document or webpage. OR click on the actual sentence to save it below and you can keep creating more sentences while saving the ones you like!
                    </div> <br/> <br/>
                    <button className="edit-profile-btn" >Save changes</button>
                </div>
            </div>
        </div>
    )
}

export default LodgeRules;