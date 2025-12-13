package se.yrgo.service.reviewLikes;

import se.yrgo.domain.ReviewLikes;

public interface ReviewLikesService {
    void addLike(Long reviewId, Long profileId);
    void deleteLike(Long id, Long profileId);
}
