import {useNavigate} from 'react-router-dom';
import './../style/Home.css'
import { useEffect, useState } from 'react';
import { getProfile } from '../endpoints/ProfileEndpoints';
import HeaderComponent from "../component/HeaderComponent.tsx";
import type {Profile} from "../interfaces/Typer.ts";

export default function HomePage() {
    const [profileId, setProfileId] = useState<string|null>("");
    const [profile, setProfile] = useState<Profile | undefined>(undefined);

    useEffect(() =>{
        const stordeUserId = localStorage.getItem("profileId");
        if(!stordeUserId){
            return
        }
        setProfileId(stordeUserId);
        getProfile(stordeUserId).then(profile => setProfile(profile));

    }, []);

    const navigate = useNavigate();
    const toLogInPage = () => {
        navigate("/login");
    };

    function logUt(){
        localStorage.setItem("profileId", "");
        setProfileId("")
        setProfile(undefined);
        toLogInPage();
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
            <HeaderComponent logOut={logUt}/>
            <p>Hello: {profile?.profileName}</p>
            <button onClick={logUt}>Log out</button>
            <button onClick={consoleProfile} >print profile</button>

        </>
    );
}