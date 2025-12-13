package se.yrgo.service.reviewLikes;

import se.yrgo.domain.ReviewLikes;

public interface ReviewLikesService {
    void addLike(Long id);
    void deleteLike(Long id, Long profileId);
}
