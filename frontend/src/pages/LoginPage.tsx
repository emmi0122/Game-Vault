import { Link, useNavigate } from 'react-router-dom';
import './../style/Login.css'

export default function LoginPage() {

    const navigate = useNavigate();

    const handleCancel = () => {
        navigate("/");
    };

    return (
        <>
            <form action="##" method="post">
                <div className="container">
                    <h2>Sign In</h2>
                    <label htmlFor="Email">Email</label>
                    <input type="email" placeholder="Enter Email" name="email" required />

                    <label htmlFor="psw">Password</label>
                    <input type="password" placeholder="Enter Password" name="psw" required />

                    <button type="submit">Sign In</button>
                </div>

                <div className="container">
                    <button
                        type="button"
                        className="cancelbtn"
                        onClick={handleCancel}
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