import star from "../assets/star.png";
import type { Review } from "../interfaces/ReviewTypers.ts";
import './../style/GameDetail.css'


export default function ReviewComponent({review}: {review: Review} ) {
    function renderStars(amount: number) {
        const row = [];
        for (let i = 0; i < amount; i++) {
            row.push(<img src={star} alt="Star icon" />)
        }
        return row;
    }

    return (
        <div className="review-container">
            <div className="review-header">
                <div>
                    <img src={review.avatarUrl} alt="Avatar icon" />
                    <h5>{review.profileUsername}</h5>
                </div>
                <span>{review.createdAt}</span>
            </div>
            {renderStars(review.rating)}
            <p>{review.text}</p>
        </div>
    )
}