import React, { useState } from 'react'
import { useHistory} from "react-router-dom";
import '../../css/addingForm.css';
import Modal from 'react-modal';
import axios from 'axios';
import { useToasts } from "react-toast-notifications";

const AddLoyaltyProgramme = ({modalIsOpen, setModalIsOpen}) => {

    const SERVER_URL = process.env.REACT_APP_API; 
  const { addToast } = useToasts();
  const history = useHistory();
  const [systemIncome, setSystemIncome] = useState(0)
  const [clientIncome, setClientIncome] = useState(0)
  const [silverLimit, setSilverLimit] = useState(0)
  const [bronzeLimit, setBronzeLimit] = useState(0)
  const [goldLimit, setGoldLimit] = useState(0)
  

  const values = {
    systemIncome, 
    clientIncome,
    bronzeLimit,
    silverLimit,
    goldLimit
  }

  const handleSubmit = (e) => {
    
  }

    return (
        <div>
          <Modal className="fullscreen" isOpen={modalIsOpen}
              shouldCloseOnEsc={true}
              onRequestClose={() => setModalIsOpen(false)}
              ariaHideApp={false}>
                <div id="addLodge" className="adding-wrapper">
                <form onSubmit={handleSubmit}>
                  <div className="right">
                    <div className="info">
                      <h3>ADD LOYALTY PROGRAMME</h3>
                      <div className="info_data">
                        <div className="data">
                          <h4>System income (% from reservation): </h4>
                          <input type="number" min={0} max={100} required onChange={(e) => {setSystemIncome(e.target.value)}}  value={systemIncome} />
                        </div>
                        <div className="data">
                          <h4>Client points (% from reservation): </h4>
                          <input type="number" min={0} max={100} required onChange={(e) => {setClientIncome(e.target.value)}}  value={clientIncome} />
                        </div>
                        <div className="data">
                          <h4>Bronze (down limit): </h4>
                          <input type="number" min={0} max={100} required onChange={(e) => {setBronzeLimit(e.target.value)}}  value={bronzeLimit} />
                        </div>
                        <div className="data">
                          <h4>Silver (down limit): </h4>
                          <input type="number" min={0} max={100} required onChange={(e) => {setSilverLimit(e.target.value)}}  value={silverLimit}/>
                        </div>
                        <div className="data">
                          <h4>Gold (down limit): </h4>
                          <input type="number" min={0} max={100} required onChange={(e) => {setGoldLimit(e.target.value)}}  value={goldLimit} />
                        </div>
                        <input type="submit" className="submit"/>
                      </div>
                    </div>
                  </div>
                </form>
              </div>
          </Modal>
        </div>
      )
}

export default AddLoyaltyProgramme;