import Modal from "react-modal"
import { Rating } from "react-simple-star-rating"
import { useState } from "react"

const ReservationRating = ({modalIsOpen, setModalIsOpen}) => {
    const [rating, setRating] = useState(0)
    const [grade, setGrade] = useState(1)

    const handleRating = (rate) => {
        setRating(rate / 20)
        console.log(rate / 20)
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
                    <textarea className="comment-area" placeholder="Write a comment"></textarea>
                    <a className="reservation-link" >submit</a>
                    <a className="cancel-link" >cancel</a>
                </div>
                
            </Modal>
        </div>
    )
}

export default ReservationRating;