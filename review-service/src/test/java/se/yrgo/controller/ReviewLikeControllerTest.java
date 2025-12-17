package se.yrgo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import se.yrgo.dto.LikeRequestDTO;
import se.yrgo.rest.ReviewLikesRestController;
import se.yrgo.service.reviewLikes.ReviewLikesService;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReviewLikeControllerTest {
    @Mock
    private ReviewLikesService service;

    private ReviewLikesRestController controller;

    @BeforeEach
    void setUp() {
        controller = new ReviewLikesRestController(service);
    }

    @Test
    void testAddLike() {
         LikeRequestDTO dto = new LikeRequestDTO(1L, 1L);
         doNothing().when(service)
                 .addLike(dto.reviewId(), dto.profileId());
         ResponseEntity<Map<String, String>> response = controller.addLike(dto);

         assertEquals(200, response.getStatusCodeValue());
         assertNotNull(response.getBody());
         assertEquals("Like added", response.getBody().get("message"));

         verify(service, times(1))
                 .addLike(dto.reviewId(), dto.profileId());
    }

    @Test
    void deleteALike() {
        ReviewLikesRestController controller = new ReviewLikesRestController(service);
        LikeRequestDTO dto = new LikeRequestDTO(1L, 1L);

        when(service.deleteLike(1L, 1L)).thenReturn(true);

        ResponseEntity<?> response = controller.deleteLike(dto);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());

        verify(service).deleteLike(1L, 1L);
    }


}
