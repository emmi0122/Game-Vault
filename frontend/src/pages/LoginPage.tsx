import { Link, useNavigate } from 'react-router-dom';
import { useState } from 'react';
import type { User } from '../interfaces/UserTypes';
import { login } from '../endpoints/UserEndpoints';
import styles from '../style/Login.module.css'

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
                toHomePage();
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
            <form className={styles.loginForm} onSubmit={handleSubmit}>
                <div className={styles.loginContainer}>
                    <h2>Log In</h2>
                    <label htmlFor="Email">Email</label>
                    <input className={styles.logInInout} type="email" placeholder="Enter Email" name="email" value={email} onChange={e => setEmail(e.target.value)} required />

                    <label htmlFor="psw">Password</label>
                    <input className={styles.logInInout} type="password" placeholder="Enter Password" name="psw" value={password} onChange={e => setPassword(e.target.value)} required />

                    <button className={styles.logInButton} type="submit">Sign In</button>
                    { message ? <p>{message}</p> : ""}
                </div>

                <div className={styles.loginContainer}>
                    <button
                        type="button"
                        className={styles.cancelButton}
                        onClick={toHomePage}
                    >
                        Cancel
                    </button>

                    <span className={styles.pws}>Forgot <a className={styles.pswAnchor} href="#">password?</a></span>
                    <span className={styles.pws}>Do not have an account? <Link className={styles.pswAnchor} to={'/Create'} >Create account</Link></span>
                </div>
            </form>
        </>
    );
}