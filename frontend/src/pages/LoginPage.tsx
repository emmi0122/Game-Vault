import { Link, useNavigate } from 'react-router-dom';
import './../style/Login.css'
import { useState } from 'react';
import type { User } from '../interfaces/Typer';
import { login } from '../endpoints/UserEndpoints';

export default function LoginPage() {
    const [email, setEmail] = useState<string>("")
    const [password, setPassword] = useState<string>("")
    const [message, setMessage] = useState<string>("")



    const navigate = useNavigate();
    const toHomePage = () => {
        navigate("/");
    };

    async function handleSubmit(event: React.FormEvent) {
        event.preventDefault();
        setMessage("");
        console.log("LogIn form...");

        if (!email || !password) {
            setMessage("You need an email and a password");
            return;
        }

        const user: User = {
            email: email,
            password: password
        }


        try {
            const data = await login(user);
            console.log("Response from login:", data);

            if (data?.profileId) {
                localStorage.setItem("profileId", String(data.profileId));
                setMessage("Login successful!");
            } else {
                setMessage(`Login failed: ${data.message}`);
            }

        } catch (error) {
            console.error("Error in handleSubmit:", error);
            setMessage("Network error");
        }

    }

    return (
        <>
            <form onSubmit={handleSubmit}>
                <div className="container">
                    <h2>Sign In</h2>
                    <label htmlFor="Email">Email</label>
                    <input type="email" placeholder="Enter Email" name="email" value={email} onChange={e => setEmail(e.target.value)} required />

                    <label htmlFor="psw">Password</label>
                    <input type="password" placeholder="Enter Password" name="psw" value={password} onChange={e => setPassword(e.target.value)} required />

                    <button type="submit">Sign In</button>
                    <p>{message}</p>
                </div>

                <div className="container">
                    <button
                        type="button"
                        className="cancelbtn"
                        onClick={toHomePage}
                    >
                        Cancel
                    </button>

                    <span className="psw">Forgot <a href="#">password?</a></span>
                    <span className="psw">Do not have an account? <Link to={'/Create'} >Create account</Link></span>
                </div>
            </form>
        </>
    );
}