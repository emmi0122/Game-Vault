import type { Game } from "../interfaces/GameTypes";
import {Link} from "react-router-dom";

interface Props {
    game: Game;
}

export default function GameComponent({ game }: Props) {
    return <li><Link to={`/game/${game.id}`}>{game.title}</Link></li>;
}