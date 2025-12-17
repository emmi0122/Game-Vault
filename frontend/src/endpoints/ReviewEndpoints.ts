import type {Review, ReviewDTO} from "../interfaces/ReviewTypes.ts";

const baseUrl = "http://localhost:8081";
const mapping = "/reviews"

export async function getReviewList(gameId: string): Promise<Review[]> {
    try {
        const response = await fetch(`${baseUrl}${mapping}/all-reviews?gameId=${gameId}`, {
            method: "Get",
            headers: { "Content-Type": "application/json" }
        });

        const data = await response.json();

        return data ?? []

    } catch (error){
        console.error("Network error: ", error);
        return [];
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

export async function deleteReview(reviewId: string): Promise<boolean> {
    try {
        const response = await fetch(`${baseUrl}${mapping}/delete-review?reviewId=${reviewId}`, {
            method: "POST",
            headers: {"Content-Type": "application/json"}
        });

        if(!response.ok) {
            const text = await response.text();
            console.error("Delete failed: " + text)
            return false;
        }
        const text = response.text();
        console.log("Review deleted: ", text);
        return true;
    } catch(error) {
        console.error("Network error: ", error);
        return false;
    }
}