import { Link } from 'react-router-dom';
import './../style/Login.css'
import { useEffect, useState } from 'react';
import { getProfile } from '../endpoints/ProfileEndpoints';

export default function HomePage() {
    const [userId, setUserId] = useState<String|null>("");
    
    useEffect(() =>{
        const stordeUserId = localStorage.getItem("profileId");

        if(stordeUserId !== null){
            setUserId(stordeUserId);
        }

        return (
            localStorage.setItem("profileId", "")
        );

    }, []);

    function logUt(){
        localStorage.setItem("profileId", "");
        setUserId("")
    }

    function consoleProfile(){
        const stordeUserId = localStorage.getItem("profileId");
        if(stordeUserId){
            const profile = getProfile(stordeUserId);
            console.log(profile)
        } else{
            console.log("no profile")
        }
    }

    return (
        <>
            <p>Hello user: {userId}</p>

            <span className="psw"><Link to={'/login'} >Log In</Link></span>
            <span className="psw"><Link to={'/create'} >Sign in</Link></span>
            <button onClick={logUt}>Log out</button>
            <button onClick={consoleProfile}>print profile</button>

        </>
    );
}