import type { RegistrationRequestDTO } from "../interfaces/Typer";

const baseURL = "http://localhost:8080/";



export async function registerUser(registrationData: RegistrationRequestDTO): Promise<{[key: string]: string} | undefined> {
  try {
    console.log(registrationData)
    const response = await fetch(baseURL + "register", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(registrationData)
    });
    console.log("hej")
    
    const data = await response.json(); // vanliga objekt
    if (response.ok) {
      console.log("Registration success:", data);
      return data;
    } else {
      console.error("Registration failed:", data);
      return data;
    }
  } catch (error) {
    console.error("Network error:", error);
    return undefined;
  }
}