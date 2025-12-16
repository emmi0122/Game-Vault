import { useEffect, useState } from "react";
import HeaderComponent from "../component/HeaderComponent";
import { getAllGames } from "../endpoints/GameEndpoints";
import type { Game } from "../interfaces/GameTypes";

export default function AllGames() {
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
            <ul>
                {allGames.map(Game => <li key={Game.id}>{Game.title}</li>)}
            </ul>
        </>
    )
}