import type { LoginResponse, RegistrationRequestDTO, User } from "../interfaces/Typer";



const baseURL = "http://localhost:8080";
const mapping = "/user";

export async function registerUser(registrationData: RegistrationRequestDTO): Promise<{[key: string]: string} | undefined> {
  try {
    console.log(registrationData)
    const response = await fetch(baseURL + mapping +"/register", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(registrationData)
    });
    
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

export async function login(user: User): Promise<LoginResponse> {
  const response = await fetch("http://localhost:8080/login", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(user)
  });

  if (!response.ok) {
    // Extract backend error message
    const errorData = await response.json();
    return {
      status: "failed",
      message: errorData.message || "Login failed"
    };
  }

  return await response.json(); // returns LoginResponse
}
