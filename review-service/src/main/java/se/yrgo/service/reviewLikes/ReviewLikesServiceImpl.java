package se.yrgo.service.reviewLikes;

import org.springframework.stereotype.Service;
import se.yrgo.data.ReviewLikesRepository;
import se.yrgo.domain.ReviewLikes;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewLikesServiceImpl implements ReviewLikesService {
    private final ReviewLikesRepository rlr;

    public ReviewLikesServiceImpl(ReviewLikesRepository rlr) {
        this.rlr = rlr;
    }

    public void addLike(ReviewLikes reviewLikes) {
        rlr.save(reviewLikes);
    }

    public void deleteLike(ReviewLikes reviewLikes) {
        rlr.delete(reviewLikes);
    }

    public void updateLike(ReviewLikes updatedLikes) {
        Optional<ReviewLikes> newLike = rlr.findById(updatedLikes.getId());
        newLike.ifPresent(rlr::save);
    }
}
