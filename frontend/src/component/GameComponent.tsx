import type { Game } from "../interfaces/GameTypes";

interface Props {
    game: Game;
}

export default function GameComponent({ game }: Props) {
    return <li>{game.title}</li>;
}