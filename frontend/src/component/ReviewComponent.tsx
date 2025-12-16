import star from "../assets/star.png";
import deleteIcon from '../assets/delete.png';
import type { Review } from "../interfaces/ReviewTypes.ts";
import './../style/GameDetail.css'
import {useEffect, useState} from "react";
import ReviewButtonComponent from "./ReviewButtonComponent.tsx";
import {deleteReview} from "../endpoints/ReviewEndpoints.ts";


export default function ReviewComponent({review}: {review: Review} ) {
    const [liked, setLiked] = useState(false);
    const [likeCount, setLikeCount] = useState(review.likes.length);
    const [isLoading, setIsLoading] = useState(false);

    useEffect(() => {
        const profileIdStr = localStorage.getItem("profileId");
        if (!profileIdStr) return;

        const profileIdNum = parseInt(profileIdStr);

        const alreadyLiked = review.likes.some(
            like => like.profileId === profileIdNum
        );

        setLiked(alreadyLiked);
        setLikeCount(review.likes.length);
    }, [review.likes]);

    async function handleDeleteReview() {
        setIsLoading(true);
        const success = await deleteReview(review.reviewId.toString())
        if(success) {
            setIsLoading(false)
            console.log("Review deleted")
        } else {
            console.log("Review couldn't be deleted")
        }
    }

    function renderStars(amount: number) {
        const row = [];
        for (let i = 0; i < amount; i++) {
            row.push(<img key={i} src={star} alt="Star icon" />)
        }
        return row;
    }

    return (
        <div className="review-container">
            <div className="review-header">
                <div>
                    <img className="avatar-img" src={review.avatarUrl} alt="Avatar icon" />
                    <h5>{review.profileUsername}</h5>
                </div>
                <div>
                    <span>{review.createdAt}</span>
                    <img className="icon-size" src={deleteIcon} alt="Delete icon" title="Delete" onClick={handleDeleteReview}/>
                </div>
            </div>
            {renderStars(review.rating)}
            <p>{review.text}</p>
            <ReviewButtonComponent amountOfLikes={review.likes.length} reviewId={review.reviewId.toString()} />
        </div>
    )
}