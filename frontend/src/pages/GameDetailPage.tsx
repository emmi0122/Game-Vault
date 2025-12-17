import ReviewComponent from "../component/ReviewComponent.tsx";
import { useEffect, useState } from "react";
import { getReviewList } from "../endpoints/ReviewEndpoints.ts";
import type { Review } from "../interfaces/ReviewTypes.ts";
import CreateReviewComponent from "../component/CreateReviewComponent.tsx";
import HeaderComponent from "../component/HeaderComponent.tsx";
import type { Game } from "../interfaces/GameTypes.ts";
import { useParams } from "react-router-dom";
import { getGamesById } from "../endpoints/GameEndpoints.ts";

export default function GameDetailPage() {
    const { id } = useParams();
    const [game, setGame] = useState<Game>();
    const [listOfReviews, setListOfReviews] = useState<Review[]>([]);

    console.log(game)

    useEffect(() => {
        if (!id) {
            return
        }

        const response = async () => {
            const foundGame = await getGamesById(id)
            setGame(foundGame)
        }

        response()
    }, [id])

    useEffect(() => {
        const review = async () => {
            const foundReviews = await getReviewList("1");
            setListOfReviews(foundReviews)
        }

        review()
    }, []);

    return (
        <>
            <HeaderComponent />
            <main>
                <h1>{game?.title}</h1>
                <p>Genre: {game?.genre}</p>
                <p>PEGI: {game?.pegi}</p>
                <p>Release Date: {game?.releaseDate}</p>
                <p>Developed By: {game?.developedBy}</p>
                <p>
                    Platforms:{" "}
                    {game?.platforms?.map((platform) => (
                        <span key={platform.id}>{platform.name} </span>
                    )) ?? "Unknown"}
                </p>
                {listOfReviews.map(review =>
                    <ReviewComponent key={review.reviewId} review={review} />)}
                <CreateReviewComponent gameId="1" />
            </main>
        </>
    )
}