import type { Profile } from "../interfaces/Typer";

const baseURL = "http://localhost:8080";
const mapping = "/profile";

export async function getProfile(profileId: String): Promise<Profile | undefined> {
    try {
        const response = await fetch(`${baseURL}${mapping}/getProfile?profileId=${profileId}`, {
            method: "Get",
            headers: { "Content-Type": "application/json" }
        });

        const data = await response.json();

        if (response.ok) {
            console.log("Get profile success:", data);
            return data;
        } else {
            console.error("Get profile failed:", data);
            return data;
        }


    } catch (error) {
        console.error("Network error:", error);
        return undefined;
    }
}