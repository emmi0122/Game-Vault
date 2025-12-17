package se.yrgo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import se.yrgo.domain.Review;
import se.yrgo.dto.ProfileDTO;
import se.yrgo.dto.ReviewLikeDTO;
import se.yrgo.dto.ReviewResponseDTO;
import se.yrgo.exception.ProfileNotFound;
import se.yrgo.exception.ReviewNotFoundException;
import se.yrgo.rest.ReviewRestController;
import se.yrgo.service.review.ReviewService;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReviewControllerTest {
    @Mock
    private ReviewService service;

    @Test
    void createReviewTest() {
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
    void reviewNotCreatedTest() {
        Review newReview = new Review();
        newReview.setText("Bad game");
        newReview.setRating(2);
        newReview.setGameId(1L);
        ReviewRestController controller = new ReviewRestController(service);
        controller.createReview(newReview);
        doThrow(new ProfileNotFound("Couldnät find profile"))
                .when(service).createReview(newReview);
        assertThrows(ProfileNotFound.class, () -> controller.createReview(newReview));

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

    @Test
    void getAllReviewsTest() {
        List<ReviewLikeDTO> likes = List.of(new ReviewLikeDTO(1L, 2L, "Bert", Instant.now(), 1L));
        ReviewResponseDTO dto = new ReviewResponseDTO(1L, 2, "Bad game", Instant.now(), "Amanda", "somepic.png", likes);
        ReviewResponseDTO dto2 = new ReviewResponseDTO(2L, 1, "Hated it", Instant.now(), "Bert", "somepic.png", new ArrayList<>());

        List<ReviewResponseDTO> expected = List.of(dto, dto2);

        ReviewRestController controller = new ReviewRestController(service);
        when(service.findAllReviewsForGame(1L)).thenReturn(expected);
        controller.getAllReviewForGame(1L);
        verify(service).findAllReviewsForGame(1L);
    }

    @Test
    void noReviewsFoundTest() {
        List<ReviewResponseDTO> expected = new ArrayList<>();
        ReviewRestController controller = new ReviewRestController(service);
        when(service.findAllReviewsForGame(2L)).thenReturn(expected);
        controller.getAllReviewForGame(2L);
        verify(service).findAllReviewsForGame(2L);
    }

    @Test
    void deleteReviewTest() {
        Long reviewId = 1L;
        doNothing().when(service).deleteReview(reviewId);
        ReviewRestController controller = new ReviewRestController(service);
        ResponseEntity<String> response = controller.removeReview(reviewId);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Review deleted", response.getBody());
        verify(service, times(1)).deleteReview(reviewId);
    }

    @Test
    void findAllReviesForProfile() {
        Review r1 = new Review();
        r1.setProfileId(1L);
        r1.setText("Pretty okej");
        r1.setRating(3);
        r1.setGameId(2L);
        Review r2 = new Review();
        r2.setProfileId(1L);
        r2.setText("Bad game");
        r2.setRating(2);
        r2.setGameId(1L);
        List<Review> reviews = List.of(r1, r2);
        ReviewRestController controller = new ReviewRestController(service);
        when(service.findAllReviewsForProfile(1L)).thenReturn(reviews);
        controller.getAllReviewsForProfile(1L);
        verify(service).findAllReviewsForProfile(1L);
    }
}
