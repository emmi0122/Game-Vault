import { Link, useNavigate } from 'react-router-dom';
import './../style/Home.css'
import { useEffect, useState } from 'react';
import { getProfile } from '../endpoints/ProfileEndpoints';
import HeaderComponent from "../component/HeaderComponent.tsx";
import type { Game } from '../interfaces/GameTypes.ts';
import { getAllGames } from '../endpoints/GameEndpoints.ts';
import type { Profile } from "../interfaces/UserTypes.ts";

export default function HomePage() {
    const [profile, setProfile] = useState<Profile | undefined>(undefined);

    const [allGames, setAllGames] = useState<Game[]>([])

    useEffect(() => {
        const fetchGames = async () => {
            const foundGames = await getAllGames()
            setAllGames(foundGames)
        }

        fetchGames()
    }, [])

    /*useEffect(() =>{
        const stordeUserId = localStorage.getItem("profileId");
        if(!stordeUserId){
            return
        }
        getProfile(stordeUserId).then(profile => setProfile(profile));

    }, []);*/

    async function consoleProfile() {
        const storedUserId = localStorage.getItem("profileId");
        if (storedUserId) {
            const profile = await getProfile(storedUserId);
            if (profile) {
                console.log("Hej:", profile);
            } else {
                console.log("Error fetching profile:");
            }
        } else {
            console.log("no profile")
        }
    }

    return (
        <>
            <HeaderComponent />
            <main>
                <p>Hello: {profile?.profileName}</p>
                <button onClick={consoleProfile} >print profile</button>
                <ul>
                    {allGames.map(game => <li key={game.id}><Link to={`/game/${game.id}`}>{game.title}</Link></li>)}
                </ul>
            </main>
        </>
    );
}