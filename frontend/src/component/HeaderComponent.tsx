import {Link} from "react-router-dom";
import {useEffect, useState} from "react";

export default function HeaderComponent({logOut} ) {
    const [isLoggedIn, setLoggedIn] = useState(false);

    /*function loggedIn() {

    }*/
    useEffect(() => {
        if (localStorage.getItem("profileId")) {
            setLoggedIn(true);
        } else {
            setLoggedIn(false);
        }
    }, []);

    return (
        <header>
        <h1>Game Vault</h1>
        <nav>
            { isLoggedIn ?
                <span className="link" onClick={logOut}><Link to={'/login'} >Log out</Link></span> :
                <>
                <span className="link"><Link to={'/login'} >Log In</Link></span>
                <span className="link"><Link to={'/create'} >Create account</Link></span>
                </>
            }
            <span className="link"><Link to={'/allGames'}>All games</Link></span>
        </nav>
        </header>
    )
}