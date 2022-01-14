import Modal from "react-modal"
import { Rating } from "react-simple-star-rating"
import { useState } from "react"
import axios from "axios"

const ReservationRating = ({modalIsOpen, setModalIsOpen, entityId}) => {
    const [rating, setRating] = useState(0)
    const [comment, setComment] = useState('')
    const SERVER_URL = process.env.REACT_APP_API;
    const [grade, setGrade] = useState('')

    const dto = {
        entityId: entityId,
        grade,
        comment: comment
    }

    const handleRating = (rate) => {
        setRating(rate / 20)
        //console.log(rate / 20)
        setGrade(rate / 20)
        console.log(dto.grade)
    }

    const handleSubmit = () => {
        axios.post(SERVER_URL + "/rating/add", dto)
            .then(response => console.log(response.data))
    }

    return (
        <div>
            <Modal isOpen={modalIsOpen}
            shouldCloseOnEsc={true}
            onRequestClose={() => setModalIsOpen(false)}>
                <div>
                    <h3 style={{marginLeft: '40px'}}>Add a comment </h3>
                    <div style={{borderBottom: '2px solid cadetblue', padding: '5px', width: '400px', marginLeft: '38px'}}></div>
                    <br></br>
                    <Rating onClick={handleRating} ratingValue={5} size={25} style={{marginLeft: '50px'}} /> <br></br>
                    <textarea className="comment-area" placeholder="Write a comment" value={comment} onChange={(e) => setComment(e.target.value)}></textarea>
                    <a className="reservation-link" onClick={handleSubmit} >submit</a>
                    <a className="cancel-link" onClick={() => {setComment(''); setModalIsOpen(false);}}>cancel</a>
                </div>
                
            </Modal>
        </div>
    )
}

export default ReservationRating;