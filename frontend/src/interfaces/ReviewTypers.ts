export interface Review {
    reviewId: number,
    rating: number,
    text: string,
    createdAt: string,
    profileUsername: string,
    avatarUrl: string
    likes: ReviewLike[]
}

export interface ReviewLike {
    reviewLikeId: number,
    likedAt: string,
    reviewId: number,
    profileId: string,
    profileUsername: string
}

export interface ReviewDTO {
    profileId: string,
    gameId: string,
    rating: string,
    text: string
}

