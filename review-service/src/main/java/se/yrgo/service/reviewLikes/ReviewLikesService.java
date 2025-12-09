package se.yrgo.service.reviewLikes;

import se.yrgo.domain.ReviewLikes;

public interface ReviewLikesService {
    void addLike(ReviewLikes reviewLikes);
    void deleteLike(ReviewLikes reviewLikes);
    void updateLike(ReviewLikes updatedLikes);
}
