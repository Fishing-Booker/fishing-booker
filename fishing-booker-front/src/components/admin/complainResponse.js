import { useState } from 'react';
import Modal from 'react-modal';
import { useToasts } from "react-toast-notifications";
import axios from 'axios';
const ComplainResponse = ({modalIsOpen, setModalIsOpen, complainInfo}) => {
    const SERVER_URL = process.env.REACT_APP_API; 
    const [complaintResponseText, setComplaintResponseText] = useState("");
    const [clientId, setClientId] = useState(0);
    const [ownerId, setOwnerId] = useState(0);
    const [text, setText] = useState("");
    const [compliantId, setCompliantId] = useState(0);

    const { addToast } = useToasts();

    const dto = {
        clientId,
        ownerId,
        text,
        compliantId
    }

    const handleSubmit = () => {
        if(text === "") {
            addToast("You have to enter response!", { appearance: "error" });
            return;
        }
        dto.clientId = complainInfo.clientId;
        dto.ownerId = complainInfo.entityOwnerId;
        dto.compliantId = complainInfo.id;
        console.log(dto);
        const headers = {'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.jwtToken}`}
        axios.post(SERVER_URL + "/complaints/respond", dto, {headers: headers})
            .then(response => {
                setModalIsOpen(false);
                setText("");
                addToast("You successfully responded to compliant! Mail will be sent to client and owner.", { appearance: "success" });
            })
    }
    return(
        <div>
            <Modal className="fullscreen" isOpen={modalIsOpen}
            shouldCloseOnEsc={true}
            onRequestClose={() => setModalIsOpen(false)}
            ariaHideApp={false}>
                <div className="card-res entity-details">
                    <h3 style={{marginLeft: '40px'}}>Write response </h3>
                    <div style={{borderBottom: '2px solid cadetblue', padding: '5px', width: '400px', marginLeft: '38px'}}></div>
                    <br></br>
                    <textarea className="comment-area" placeholder="Write your response here" value={text} onChange={(e) => setText(e.target.value)}></textarea>
                    <a className="reservation-link" onClick={handleSubmit} >submit</a>
                    <a className="cancel-link" onClick={() => {setText(""); setModalIsOpen(false);}}>cancel</a>
                </div>
            </Modal>
        </div>
    )
}
export default ComplainResponse;