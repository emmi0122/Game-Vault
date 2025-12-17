import './../style/Home.css'
import { useEffect, useState } from 'react';
import HeaderComponent from "../component/HeaderComponent.tsx";
import type { Game } from '../interfaces/GameTypes.ts';
import { getAllGames } from '../endpoints/GameEndpoints.ts';
import ProfileComponent from '../component/ProfileComponent.tsx';
import GameListComponent from '../component/GameListComponent.tsx';
import GameComponent from '../component/GameComponent.tsx';

export default function HomePage() {
    const [allGames, setAllGames] = useState<Game[]>([])
    useEffect(() => {
        const fetchGames = async () => {
            const foundGames = await getAllGames()
            setAllGames(foundGames)
        }

        fetchGames()
    }, [])

    return (
        <>
            <HeaderComponent />

            <div className="home-layout">
                <ProfileComponent/>


                <GameListComponent>
                    {allGames.map((game) => {
                       return <GameComponent game={game}></GameComponent>
                    })}
                </GameListComponent>
            </div>
        </>
    );
}