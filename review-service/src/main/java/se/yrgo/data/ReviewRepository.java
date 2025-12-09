package se.yrgo.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import se.yrgo.domain.Review;
import se.yrgo.domain.ReviewLikes;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT r FROM Review as r WHERE r.gameId = :gameId")
    List<Review> findAllReviewsForGame(Long gameId);

}
