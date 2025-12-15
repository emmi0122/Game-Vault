import star from "../assets/star.png";
import like from '../assets/like.png';
import type { Review } from "../interfaces/ReviewTypers.ts";
import './../style/GameDetail.css'
import {addLike} from "../endpoints/ReviewLikeEndpoints.ts";
import {useEffect, useState} from "react";


export default function ReviewComponent({review}: {review: Review} ) {
    const [liked, setLiked] = useState(false);
    const [likeCount, setLikeCount] = useState(review.likes.length);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        const profileId = localStorage.getItem("profileId");
        if (!profileId) return;

        const alreadyLiked = review.likes.some(
            like => like.profileId === profileId
        );

        setLiked(alreadyLiked);
    }, [review.likes]);

    function renderStars(amount: number) {
        const row = [];
        for (let i = 0; i < amount; i++) {
            row.push(<img src={star} alt="Star icon" />)
        }
        return row;
    }

    async function handleLike() {
        if (liked || loading) return;

        const profileId = localStorage.getItem("profileId");
        if (!profileId) {
            alert("You need to log in");
            return;
        }

        setLiked(true);
        setLikeCount(prev => prev + 1);
        setLoading(true);

        try {
            await addLike(review.reviewId.toString(), profileId);
        } catch (err) {
            setLiked(false);
            setLikeCount(prev => prev - 1);
            alert("Couldn't like at the moment" + err);
        } finally {
            setLoading(false);
        }
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
            <div className="review-button">
                <button
                    onClick={handleLike}
                    disabled={liked || loading}
                    style={{ color: liked ? "deepskyblue" : "black"}}>
                    <img src={like} alt="Like icom" />{likeCount}</button>
            </div>
        </div>
    )
}