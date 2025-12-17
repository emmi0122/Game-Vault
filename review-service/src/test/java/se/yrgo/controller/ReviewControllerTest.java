package se.yrgo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.yrgo.domain.Review;
import se.yrgo.rest.ReviewRestController;
import se.yrgo.service.review.ReviewService;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ReviewControllerTest {
    @Mock
    private ReviewService service;

    @Test
    void createReview() {
        Review newReview = new Review();
        newReview.setProfileId(1L);
        newReview.setText("Bad game");
        newReview.setRating(2);
        newReview.setGameId(1L);

        Review savedReview = new Review();
        savedReview.setId(10L);

        ReviewRestController controller = new ReviewRestController(service);
        controller.createReview(newReview);

        verify(service).createReview(newReview);
    }

    @Test
    void updateReview() {
        Long id = 10L;

        Review newReview = new Review();
        newReview.setProfileId(1L);
        newReview.setText("Bad game");
        newReview.setRating(2);
        newReview.setGameId(1L);

        ReviewRestController controller = new ReviewRestController(service);
        controller.updateReview(id, newReview);

        verify(service).updateReview(10L, newReview);
    }

}
