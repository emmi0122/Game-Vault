import star from "../assets/star.png";
import like from '../assets/like.png';
import type { Review } from "../interfaces/ReviewTypers.ts";
import './../style/GameDetail.css'
import {addLike, removeLike} from "../endpoints/ReviewLikeEndpoints.ts";
import {useEffect, useState} from "react";
import ReviewButtonComponent from "./ReviewButtonComponent.tsx";


export default function ReviewComponent({review}: {review: Review} ) {
    const [liked, setLiked] = useState(false);
    const [likeCount, setLikeCount] = useState(review.likes.length);

    useEffect(() => {
        const profileIdStr = localStorage.getItem("profileId");
        if (!profileIdStr) return;

        const profileIdNum = parseInt(profileIdStr);  // ← STRING → NUMBER!

        const alreadyLiked = review.likes.some(
            like => like.profileId === profileIdNum  // ← Nu matchar typerna!
        );

        setLiked(alreadyLiked);
        setLikeCount(review.likes.length);
    }, [review.likes]);


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
                    <img src={review.avatarUrl} alt="Avatar icon" />
                    <h5>{review.profileUsername}</h5>
                </div>
                <span>{review.createdAt}</span>
            </div>
            {renderStars(review.rating)}
            <p>{review.text}</p>
            <ReviewButtonComponent amountOfLikes={review.likes.length} reviewId={review.reviewId.toString()} />
        </div>
    )
}