import './../style/Home.css'
<<<<<<< HEAD
import { type ReactNode } from 'react';
=======
import HeaderComponent from "../component/HeaderComponent.tsx";
import type { Game } from '../interfaces/GameTypes.ts';
import { getAllGames } from '../endpoints/GameEndpoints.ts';
import ProfileComponent from '../component/ProfileComponent.tsx';
import { useEffect, useState, type ReactElement, type ReactNode } from 'react';
import GameComponent from './GameComponent.tsx';
>>>>>>> 0ece7151436105327de940bd52ad269bb87303a7

interface Props {
    children: ReactNode;
}

export default function GameListComponent({ children }: Props) {
<<<<<<< HEAD
=======




>>>>>>> 0ece7151436105327de940bd52ad269bb87303a7
    return (
        <>
            <div className="games-section">
                <h3>🎮 Games</h3>
                <ul>{children}</ul>
            </div>
        </>
    )
}