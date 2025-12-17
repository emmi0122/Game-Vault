<<<<<<< HEAD
=======
import type { ReactNode } from "react";
>>>>>>> 0ece7151436105327de940bd52ad269bb87303a7
import type { Game } from "../interfaces/GameTypes";

interface Props {
    game: Game;
}

export default function GameComponent({ game }: Props) {
    return <li>{game.title}</li>;
}