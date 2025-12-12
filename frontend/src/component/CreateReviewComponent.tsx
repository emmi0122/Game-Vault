import '../style/GameDetail.css';
import {useState} from "react";
import type {ReviewDTO} from "../interfaces/ReviewTypers.ts";
import {createReview} from "../endpoints/ReviewEndpoints.ts";

export default function CreateReviewComponent({gameId}: { gameId: string }) {
    const [text, setText] = useState("");
    const [rating, setRating] = useState("");

    function handleSubmit() {
        const idOfProfile = localStorage.getItem("profileId")
        if (idOfProfile != null) {
            const reviewDto: ReviewDTO = {
                text: text,
                rating: rating,
                gameId: gameId,
                profileId: idOfProfile
            }
            createReview(reviewDto).then(r => console.log(r));
        }
    }

    return (
        <form className="create-review-container" onSubmit={handleSubmit}>
            <div>
                {[1, 2, 3, 4, 5].map((num) => (
                    <label key={num}>
                        {num}
                        <input
                            type="radio"
                            value={num.toString()}
                            name="rating"
                            checked={rating === num.toString()}
                            onChange={(e) => setRating(e.target.value)}
                        />
                    </label>
                ))}
                <input type="text" placeholder="What did you think about the game"
                       onChange={(e) => setText(e.target.value)}/>
            </div>
            <button type="submit">Save</button>
        </form>
    )
}