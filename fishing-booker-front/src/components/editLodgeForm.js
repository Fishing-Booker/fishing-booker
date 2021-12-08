import React from 'react'
import { Link, BrowserRouter as Router, Route, Switch } from "react-router-dom";
import { withRouter } from 'react-router-dom';
import '../css/addingForm.css'

const EditLodgeFrom = () => {

   return (
        <div className="adding-wrapper">
            <div className="right">
                <div className="info">
                    <h3>EDIT YOUR LODGE</h3>
                    <div className="info_data">
                        <div className="data">
                            <h4>Name:</h4>
                            <input type="text" value="Lodge1" />
                        </div>
                        <div className="data">
                            <h4>Address:</h4>
                            <input type="text" value="Lodge address" />
                        </div>
                        <div className="data">
                            <h4>Bedrooms:</h4>
                            <textarea type="text" value="Lodge bedrooms"/>
                        </div>
                        <div className="data">
                            <h4>Description:</h4>
                            <textarea type="text" value="Lodge description" />
                        </div>
                        <Link to="/">
                            <button >
                                Save changes
                            </button>
                        </Link>
                    </div> <br/> <br/>
                </div>
            </div>
        </div>
   )
    
}

export default EditLodgeFrom;