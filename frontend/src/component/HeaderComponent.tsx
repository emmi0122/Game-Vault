import {useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";
import ButtonLink from "./ButtonComponent.tsx";
import style from "../style/Header.module.css"

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
        <header className={style.headerContainer}>
        <h1 className={style.h1}>Game Vault</h1>
        <nav className={style.headerNav}>
            { isLoggedIn ?
                <ButtonLink to={'/login'} title={'Log out'} onClick={logUt}/>
                :
                <>
                    <ButtonLink to={'/login'} title={'Log in'} />
                    <ButtonLink to={'/create'} title={'Create account'} />
                </>
            }
        </nav>
        </header>
    )
}