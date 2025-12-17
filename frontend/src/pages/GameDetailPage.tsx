import ReviewComponent from "../component/ReviewComponent.tsx";
import {useEffect, useState} from "react";
import {getReviewList} from "../endpoints/ReviewEndpoints.ts";
import type {Review} from "../interfaces/ReviewTypes.ts";
import CreateReviewComponent from "../component/CreateReviewComponent.tsx";
import HeaderComponent from "../component/HeaderComponent.tsx";

export default function GameDetailPage() {
    const [listOfReviews, setListOfReviews] = useState<Review[]>([]);
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
            {listOfReviews.map(review =>
                <ReviewComponent key={review.reviewId} review={review}/>)}
            <CreateReviewComponent gameId="1"/>
            </main>
        </>
    )
}