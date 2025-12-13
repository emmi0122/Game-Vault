import { useState } from "react";
import { Link, useNavigate } from "react-router";
import './../style/SignUp.css';
import type { RegistrationRequestDTO } from "../interfaces/Typer";
import { registerUser } from "../endpoints/UserEndpoints";

export default function CreateAccount() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [username, setUsername] = useState("");
    const [realName, setRealName] = useState("");
    const [avatar, setAvatar] = useState<string>(generateAvatar);
    const [message, setMessage] = useState<string>("");

    const navigate = useNavigate();

    const toHomePage = () => {
        navigate("/");
    };


    function generateAvatar(): string {
        const avatarURL = `https://robohash.org/${generateUUID()}?set=set4`; // Skapa URL för avatar
        return avatarURL;
    }

    function generateUUID(): string {
        // Skapa ett slumpmässigt tal för varje segment i UUID:n på en gång
        const randomValues = new Uint8Array(16);
        window.crypto.getRandomValues(randomValues); // Fyller arrayen med kryptografiskt säkra slumpvärden

        // Bygg UUID-strängen från de slumpmässiga värdena
        randomValues[6] = (randomValues[6] & 0x0f) | 0x40;
        randomValues[8] = (randomValues[8] & 0x3f) | 0x80;

        // Bygg UUID-strängen
        return Array.from(randomValues)
            .map((byte, i) => {
                const hex = byte.toString(16).padStart(2, "0");
                return [4, 6, 8, 10].includes(i) ? "-" + hex : hex;
            })
            .join("");
    }

    async function handleSubmit(event: React.FormEvent) {
        event.preventDefault();
        setMessage("waiting");
        console.log("Submitting form...");

        if (password !== confirmPassword) {
            setMessage("Passwords do not match");
            console.log("Passwords do not match");
            return;
        }

        const registrationData: RegistrationRequestDTO = {
            user: {
                email: email,
                password: password
            },
            profile: {
                profileName: username,
                realName: realName,
                avatarURL: avatar,
                roles: ["USER"]
            }
        };

        try {
            const user = await registerUser(registrationData);
            console.log("Response from registerUser:", user);

            if (user?.profileId) {
                localStorage.setItem("profileId", String(user.profileId));
                setMessage("Registration successful!");
                toHomePage();

            } else if (user?.message) {
                setMessage(`Registration failed: ${user.message}`);
            } else {
                setMessage("Network error");
            }
        } catch (error) {
            console.error("Error in handleSubmit:", error);
            setMessage("Network error");
        }
    }


    return (<>
        <form onSubmit={handleSubmit}>
            <div className="container">
                <h2>Sign Up</h2>

                <label htmlFor="email"><b>Email</b></label>
                <input
                    type="email"
                    placeholder="Enter Email"
                    name="email"
                    value={email}
                    onChange={e => setEmail(e.target.value)}
                    required
                />
                
                <label htmlFor="real-name"><b>Your real name</b></label>
                <input
                    type="text"
                    placeholder="Enter Name"
                    name="real-name"
                    value={realName}
                    onChange={e => setRealName(e.target.value)}
                    required
                />

                <label htmlFor="psw"><b>Password</b></label>
                <input
                    type="password"
                    placeholder="Enter Password"
                    name="psw"
                    value={password}
                    onChange={e => setPassword(e.target.value)}
                    required
                />

                <label htmlFor="psw-repeat"><b>Confirm Password</b></label>
                <input
                    type="password"
                    placeholder="Confirm Password"
                    name="psw-repeat"
                    value={confirmPassword}
                    onChange={e => setConfirmPassword(e.target.value)}
                    required
                />

                <div className="avatar-container">
                    <h2>Your Avatar</h2>
                    <img id="avatar-img" src={avatar} alt="Avatar" />
                    <button type="button" onClick={() => setAvatar(generateAvatar())}>Generate New Avatar</button>
                </div>

                <label htmlFor="username"><b>Username</b></label>
                <input
                    type="text"
                    placeholder="Enter Username"
                    name="username"
                    value={username}
                    onChange={e => setUsername(e.target.value)}
                    required
                />

                <p>{message}</p>
                <button type="submit">Sign Up</button>
            </div>

            <div className="container">
                <button
                    type="button"
                    className="cancelbtn"
                    onClick={toHomePage}
                >
                    Cancel
                </button>

                <span className="psw">Already have an account? <Link to={'/login'} >Log in</Link></span>
            </div>
        </form>
    </>);
}