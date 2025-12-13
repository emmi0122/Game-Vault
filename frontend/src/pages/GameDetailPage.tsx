import ReviewComponent from "../component/ReviewComponent.tsx";
import {useEffect, useState} from "react";
import {getReviewList} from "../endpoints/ReviewEndpoints.ts";
import type {Review} from "../interfaces/ReviewTypers.ts";
import CreateReviewComponent from "../component/CreateReviewComponent.tsx";
import HeaderComponent from "../component/HeaderComponent.tsx";

export default function GameDetailPage() {
    const [listOfReviews, setListOfReviews] = useState<Review[]>([]);

    useEffect(() => {
        getReviewList("1").then(data => setListOfReviews(data));
    }, []);

    return (
        <>
            <HeaderComponent />
            {listOfReviews.map(review =>
                <ReviewComponent key={review.reviewId} review={review}/>)}
            <CreateReviewComponent gameId="1"/>

        </>
    )
}