import type { Profile } from "../interfaces/UserTypes";

const baseURL = "http://localhost:8080";
const mapping = "/profile";

export async function getProfile(profileId: string): Promise<Profile | undefined> {
    try {
        const response = await fetch(`${baseURL}${mapping}/getProfile?profileId=${profileId}`, {
            method: "Get",
            headers: { "Content-Type": "application/json" }
        });

        const data = await response.json();

        if (response.ok) {
            console.log("Get profile success:", data);
            return data.profile;
        } else {
            console.error("Get profile failed:", data);
            return undefined;
        }


    } catch (error) {
        console.error("Network error:", error);
        return undefined;
    }
}