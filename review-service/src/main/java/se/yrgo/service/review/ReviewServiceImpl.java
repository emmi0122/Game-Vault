package se.yrgo.service.review;

import org.springframework.stereotype.Service;
import se.yrgo.data.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepo;

    public ReviewServiceImpl(ReviewRepository reviewRepo) {
        this.reviewRepo = reviewRepo;
    }
}
