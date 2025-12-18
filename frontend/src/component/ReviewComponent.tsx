import star from "../assets/star.png";
import deleteIcon from '../assets/delete.png';
import type { Review } from "../interfaces/ReviewTypes.ts";
import {useEffect, useState} from "react";
import ReviewButtonComponent from "./ReviewButtonComponent.tsx";
import {deleteReview} from "../endpoints/ReviewEndpoints.ts";
import style from '../style/Review.module.css'

export default function ReviewComponent({review, onDelete}: {review: Review; onDelete: (reviewId: number) => void;} ) {
    const [liked, setLiked] = useState(false);
    const [likeCount, setLikeCount] = useState(review.likes.length);
    const [isLoading, setIsLoading] = useState(false);

    console.log(review)
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
            onDelete(review.reviewId)
            console.log("Review deleted")
        } else {
            console.log("Review couldn't be deleted")
        }
    }

    function renderStars(amount: number) {
        const row = [];
        for (let i = 0; i < amount; i++) {
            row.push(<img className={style.reviewIcon} key={i} src={star} alt="Star icon" />)
        }
        return row;
    }

    return (
        <div className={style.reviewContainer}>
            <div className={style.reviewHeader}>
                <div className={style.reviewHeaderDiv}>
                    <img className={style.reviewAvatar} src={review.avatarUrl} alt="Avatar icon" />
                    <h5 className={style.reviewUsername}>{review.profileUsername}</h5>
                </div>
                <div className={style.reviewHeaderDiv}>
                    <span className={style.reviewCreated}>{review.createdAt}</span>
                    <button className={style.reviewDeleteButton}>
                        <img className={style.reviewIcon} src={deleteIcon} alt="Delete icon" title="Delete" onClick={handleDeleteReview}/>
                    </button>
                </div>
            </div>
            {renderStars(review.rating)}
            <p>{review.text}</p>
            <ReviewButtonComponent amountOfLikes={review.likes.length} reviewId={review.reviewId.toString()} />
        </div>
    )
}