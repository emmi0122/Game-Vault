package se.yrgo.data;

import org.springframework.data.jpa.repository.JpaRepository;
import se.yrgo.domain.ReviewLikes;

import java.util.List;

public interface ReviewLikesRepository extends JpaRepository<ReviewLikes, Long> {
    List<Object[]> findAllLikesForCertainReview();
}
