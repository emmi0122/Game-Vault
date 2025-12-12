import type {Review, ReviewDTO} from "../interfaces/ReviewTypers.ts";

const baseUrl = "http://localhost:8081";
const mapping = "/reviews"

export async function getReviewList(gameId: string): Promise<Review | undefined> {
    try {
        const response = await fetch(`${baseUrl}${mapping}/all-reviews?gameId=${gameId}`, {
            method: "Get",
            headers: { "Content-Type": "application/json" }
        });

        const data = await response.json();

        if (response.ok) {
            console.log("Found reviews: ", data);
            return data;
        } else {
            console.error("Failed to get reviews:", data);
            return data;
        }
    } catch (error){
        console.error("Network error: ", error);
        return undefined;
    }
}

export async function createReview(newReview: ReviewDTO): Promise<ReviewDTO | undefined> {
    try {
        const response = await fetch(`${baseUrl}${mapping}/review` , {
            method: "Post",
            headers: {"Content-type": "application/json"},
            body: JSON.stringify(newReview)
        });

        const data = await response.json();

        if (response.ok) {
            console.log("Review successfully created: ", data);
            return data;
        } else {
            console.error("Failed to add review:", data);
            return data;
        }

    } catch (error) {
        console.error("Network error: ", error);
        return undefined;
    }
}