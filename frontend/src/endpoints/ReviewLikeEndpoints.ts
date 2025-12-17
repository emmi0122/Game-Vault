import type {Review} from "../interfaces/ReviewTypes.ts";

const baseUrl = "http://localhost:8081";
const mapping = "/likes"

export async function addLike(reviewId: string, profileId: string): Promise<Review | undefined> {
    try {
        const response = await fetch(`${baseUrl}${mapping}/add`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({reviewId: reviewId, profileId: profileId})
        });

        const data = await response.json();

        if (response.ok) {
            console.log("Found likes for reviews: ", data);
            return data;
        } else {
            console.error("Failed to get likes for review:", data);
            return data;
        }
    } catch (error) {
        console.error("Network error: ", error);
        return undefined;
    }
}

export async function removeLike(reviewId: string, profileId: string): Promise<String | undefined> {
    try {
        const response = await fetch(`${baseUrl}${mapping}/delete`, {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({reviewId: reviewId, profileId: profileId})
        });

        const data = await response.json();

        if (response.ok) {
            console.log("Like removed from review: ", data);
            return data;
        } else {
            console.error("Couldn't remove like from review:", data);
            return data;
        }
    } catch (error) {
        console.error("Network error: ", error);
        return undefined;
    }
}
