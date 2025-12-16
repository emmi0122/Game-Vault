import like from "../assets/like.png";
import {useState} from "react";
import {addLike, removeLike} from "../endpoints/ReviewLikeEndpoints.ts";

export interface ReviewButtonProps {
    amountOfLikes: number,
    reviewId: string
}

export default function ReviewButtonComponent({amountOfLikes, reviewId}: ReviewButtonProps) {
    const [liked, setLiked] = useState(false);
    const [likeCount, setLikeCount] = useState(amountOfLikes);
    const [loading, setLoading] = useState(false);


    async function handleLike() {
        const profileIdStr = localStorage.getItem("profileId");
        if (!profileIdStr || loading) return;

        setLoading(true);

        if (liked) {
            await handleUnlike(reviewId, profileIdStr);
            return;
        }
        await handleAddLike(reviewId, profileIdStr)
    }

    async function handleUnlike(reviewIdStr: string, profileIdStr: string) {
        const success = await removeLike(reviewIdStr, profileIdStr);

        if (success) {
            setLiked(false);
            setLikeCount(prev => prev - 1);
        } else {
            console.error("Failed to remove like");
        }
        setLoading(false);
    }

    async function handleAddLike(reviewIdStr: string, profileIdStr: string) {
        const success = await addLike(reviewIdStr, profileIdStr);

        if (success) {
            setLiked(true);
            setLikeCount(prev => prev + 1);
        } else {
            console.error("Failed to add like");
        }
        setLoading(false);
    }

    return (
        <div className="review-button">
            <button
                onClick={handleLike}
                style={{ backgroundColor: liked ? "deepskyblue" : "white"}}>
                <img className="icon-size" src={like} alt="Like icom" />{likeCount}</button>
        </div>
    )
}