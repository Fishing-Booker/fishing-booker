import Modal from 'react-modal';
const ComplainInfo = ({modalIsOpen, setModalIsOpen, complainInfo}) => {
    return(
        <div>
            <Modal className="fullscreen" isOpen={modalIsOpen}
            shouldCloseOnEsc={true}
            onRequestClose={() => setModalIsOpen(false)}
            ariaHideApp={false}>
                <div className="card-res entity-details">
                    <h3 style={{marginLeft: '40px'}}>Complaint info </h3>
                    <div style={{borderBottom: '2px solid cadetblue', padding: '5px', width: '80%', marginLeft: '38px'}}></div>
                    <br></br>
                    <p style={{color: 'black', fontWeight: '700', fontSize: '15px', marginLeft: '55px', marginTop: '15px'}}>CLIENT DETAILS</p>
                    <div style={{borderBottom: '1px solid black', padding: '5px', width: '20%', marginLeft: '55px'}}></div>
                    <p className='modal-info-res'>Name and surname: {complainInfo.clientName} {complainInfo.clientSurname}</p>
                    <p className='modal-info-res'>Username: {complainInfo.clientUsername}</p>
                    <p className='modal-info-res'>E-mail: {complainInfo.clientEmail}</p>
                    <p className='modal-info-res'>Telephone: {complainInfo.clientPhone}</p>
                    <br />
                    <p style={{color: 'black', fontWeight: '700', fontSize: '15px', marginLeft: '55px', marginTop: '15px'}}>ENTITY DETAILS</p>
                    <div style={{borderBottom: '1px solid black', padding: '5px', width: '20%', marginLeft: '55px'}}></div>
                    <p className='modal-info-res'>Owner: {complainInfo.entityOwner}</p>
                    <p className='modal-info-res'>Name: {complainInfo.entityName}</p>
                    <p className='modal-info-res'>Average grade: {complainInfo.averageGrade}</p>
                    <br />
                    <p style={{color: 'black', fontWeight: '700', fontSize: '15px', marginLeft: '55px', marginTop: '15px'}}>CONTENT</p>
                    <div style={{borderBottom: '1px solid black', padding: '5px', width: '20%', marginLeft: '55px'}}></div>
                    <p style={{color: 'black', fontSize: '17px', marginLeft: '50px', marginTop: '20px'}}>{complainInfo.text} </p>


                    <a className="reservation-link2" onClick={() => setModalIsOpen(false)} >cancel</a>
                </div>
            </Modal>
        </div>
    )
}
export default ComplainInfo;