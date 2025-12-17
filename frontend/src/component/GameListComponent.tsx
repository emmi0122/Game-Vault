import type { ReactNode } from 'react';

interface Props {
    children: ReactNode;
}

export default function GameListComponent({ children }: Props) {
    return (
        <>
            <div className="games-section">
                <h2>🎮 Games</h2>
                <ul>{children}</ul>
            </div>
        </>
    )
}