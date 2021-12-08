import React from 'react'
import { Link, BrowserRouter as Router, Route, Switch } from "react-router-dom";
import { withRouter } from 'react-router-dom';
import '../css/addingForm.css'

const DeleteLogdeForm = () => {

   return (
        <div className="deleting-wrapper">
            <div className="right">
                <div className="info">
                <h3>DELETE YOUR LODGE</h3>
                <div className="info_data">
                    <div className="data">
                        Are you sure you want to delete this lodge?
                    </div>
                    <div className="buttons">
                        <Link to="/">
                            <button className="cancel" >
                                Cancel
                            </button>
                        </Link>
                        <Link to="/">
                            <button className="delete" >
                                Delete
                            </button>
                        </Link>
                    </div>
                </div>
                </div>
            </div>
        </div>
   )
    
}

export default DeleteLogdeForm;