package se.yrgo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.yrgo.domain.Review;
import se.yrgo.exception.ReviewNotFoundException;
import se.yrgo.rest.ReviewRestController;
import se.yrgo.service.review.ReviewService;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

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
    void updateReviewTest() {
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

    @Test
    void couldNotUpdateTest() {
        Long id = 10L;

        Review newReview = new Review();
        newReview.setProfileId(1L);
        newReview.setText("Bad game");
        newReview.setRating(2);
        newReview.setGameId(1L);

        doThrow(new ReviewNotFoundException("Couldn't find review"))
                .when(service)
                .updateReview(id, newReview);

        ReviewRestController controller = new ReviewRestController(service);
        assertThrows(
                ReviewNotFoundException.class,
                () -> controller.updateReview(id, newReview)
        );
    }

}
