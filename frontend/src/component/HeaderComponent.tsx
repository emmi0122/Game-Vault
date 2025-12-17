import {Link, useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";

export default function HeaderComponent() {
    const [isLoggedIn, setLoggedIn] = useState(false);
    const [profileId, setProfileId] = useState<string|null>("");

    useEffect(() =>{
        const stordeUserId = localStorage.getItem("profileId");
        if(!stordeUserId){
            setLoggedIn(false)
            return
        }
        setProfileId(stordeUserId);
        setLoggedIn(true)
    }, []);

    const navigate = useNavigate();
    const toLogInPage = () => {
        navigate("/login");
    };

    function logUt(){
        localStorage.setItem("profileId", "");
        setProfileId("")
        toLogInPage();
    }

    return (
        <header>
        <h1>Game Vault</h1>
        <nav>
            { isLoggedIn ?
                <span className="link" onClick={logUt}><Link to={'/login'} >Log out</Link></span> :
                <>
                <span className="link"><Link to={'/login'} >Log In</Link></span>
                <span className="link"><Link to={'/create'} >Create account</Link></span>
                </>
            }
        </nav>
        </header>
    )
}