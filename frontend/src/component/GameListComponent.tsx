import './../style/Home.css'
import { type ReactNode } from 'react';

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