import React from 'react'
import { Link, BrowserRouter as Router, Route, Switch } from "react-router-dom";
import { withRouter } from 'react-router-dom';
import '../css/addingForm.css'

const AddLodgeFrom = () => {

   return (
        <div className="adding-wrapper">
            <div className="right">
                <div className="info">
                    <h3>ADD YOUR LODGE</h3>
                    <div className="info_data">
                        <div className="data">
                            <h4>Name:</h4>
                            <input type="text"/>
                        </div>
                        <div className="data">
                            <h4>Address:</h4>
                            <input type="text"/>
                        </div>
                        <div className="data">
                            <h4>Bedrooms:</h4>
                            <textarea type="text"/>
                        </div>
                        <div className="data">
                            <h4>Description:</h4>
                            <textarea type="text"/>
                        </div>
                        <Link to="/">
                            <button >
                                Add
                            </button>
                        </Link>
                    </div> <br/> <br/>
                </div>
            </div>
        </div>
   )
    
}

export default AddLodgeFrom;