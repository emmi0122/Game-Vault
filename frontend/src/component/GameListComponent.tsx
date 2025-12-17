import './../style/Home.css'
import HeaderComponent from "../component/HeaderComponent.tsx";
import type { Game } from '../interfaces/GameTypes.ts';
import { getAllGames } from '../endpoints/GameEndpoints.ts';
import ProfileComponent from '../component/ProfileComponent.tsx';
import { useEffect, useState, type ReactElement, type ReactNode } from 'react';
import GameComponent from './GameComponent.tsx';

interface Props {
    children: ReactNode;
}

export default function GameListComponent({ children }: Props) {




    return (
        <>
            <div className="games-section">
                <h3>🎮 Games</h3>
                <ul>{children}</ul>
            </div>
        </>
    )
}