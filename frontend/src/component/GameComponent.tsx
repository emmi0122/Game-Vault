import type { Game } from "../interfaces/GameTypes";
import {Link} from "react-router-dom";
import style from '../style/Game.module.css'

interface Props {
    game: Game;
}

export default function GameComponent({ game }: Props) {
    return (
        <>
        <span className={style.gameContainer}><Link className={style.linkText} to={`/game/${game.id}`}>{game.title}</Link></span>
        </>
    )
}