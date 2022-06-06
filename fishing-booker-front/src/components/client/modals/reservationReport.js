import Modal from "react-modal"
import { useState } from "react"
import axios from "axios";
import { useToasts } from "react-toast-notifications";

const ReservationReport = ({modalIsOpen, setModalIsOpen, entityId, clientId}) => {
    const [complaintText, setComplaintText] = useState('')
    const SERVER_URL = process.env.REACT_APP_API;
    const { addToast } = useToasts();

    const dto = {
        entityId: entityId,
        clientId: clientId,
        text: complaintText
    }

    const handleSubmit = () => {
        const headers = {'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.jwtToken}`}
        axios.post(SERVER_URL + "/complaints/add", dto, { headers: headers })
            .then(response => console.log(response.data));
        addToast("Your complaint is successfully created!", { appearance: "success" });
        setModalIsOpen(false);
    }

    return (
        <div>
            <Modal isOpen={modalIsOpen}
            shouldCloseOnEsc={true}
            onRequestClose={() => setModalIsOpen(false)}>
                <div>
                    <h3 style={{marginLeft: '40px'}}>Write a report </h3>
                    <div style={{borderBottom: '2px solid cadetblue', padding: '5px', width: '400px', marginLeft: '38px'}}></div>
                    <br></br>
                    <textarea className="comment-area" placeholder="Write your report here" value={complaintText} onChange={(e) => setComplaintText(e.target.value)}></textarea>
                    <a className="reservation-link" onClick={handleSubmit} >submit</a>
                    <a className="cancel-link" onClick={() => {setComplaintText(''); setModalIsOpen(false);}} >cancel</a>
                </div>
                
            </Modal>
        </div>
    )
}

export default ReservationReport;