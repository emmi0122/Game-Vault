import {useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";
import Button from "./ButtonComponent.tsx";

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
                <Button to={'/login'} title={'Log out'} onClick={logUt}/>
                :
                <>
                    <Button to={'/login'} title={'Log in'} />
                    <Button to={'/create'} title={'Create account'} />
                </>
            }
        </nav>
        </header>
    )
}