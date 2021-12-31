import { Modal } from 'react-modal';
import { useParams } from 'react-router';
import { Link } from "react-router-dom";
import '../../css/usersProfile.css';

const AddAdventureActionForm = ({modalIsOpen, setModalIsOpen}) => {
    const {adventureId} = useParams();
    return(
        <div>
            <Modal className="fullscreen" isOpen={modalIsOpen}
            shouldCloseOnEsc={true}
            onRequestClose={() => setModalIsOpen(false)} 
            ariaHideApp={false}>
                <div id="addLodgeAction" className="adding-wrapper">
                    <div className="right">
                        <div className="info">
                            <h3>ADD NEW ACTION</h3>
                            <div className="info_data">
                                <div className="data">
                                    <h4>Reservation start:</h4>
                                    <input type="date"/>
                                </div>
                                <div className="data">
                                    <h4>Reservation end:</h4>
                                    <input type="date"/>
                                </div>
                                <div className="data">
                                    <h4>Action start:</h4>
                                    <input type="date"/>
                                </div>
                                <div className="data">
                                    <h4>Action end:</h4>
                                    <input type="date"/>
                                </div>
                                <div className="data">
                                    <h4>Number of persons:</h4>
                                    <input type="number" min="1" step="1" />
                                </div>
                                <div className="data">
                                    <h4>Additional services:</h4>
                                    <input type="text" />
                                </div>
                                <div className="data">
                                    <h4>Price:</h4>
                                    <input type="text"/>
                                </div>
                                <Link to={"/adventureActions/" + adventureId} onClick={() => setModalIsOpen(false)}>
                                    <button >
                                        Add
                                    </button>
                                </Link>
                            </div> <br/> <br/>
                        </div>
                    </div>
                </div>
            </Modal>
        </div>
    );
     
}
export default AddAdventureActionForm;