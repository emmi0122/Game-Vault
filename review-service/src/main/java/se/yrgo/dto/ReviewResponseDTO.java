package se.yrgo.dto;

import se.yrgo.domain.Review;
import se.yrgo.domain.ReviewLikes;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewResponseDTO {
        private Long reviewId;
        private int rating;
        private String text;
        private Instant createdAt;
        private String profileUsername;
        private String avatarUrl;
        private List<ReviewLikeDTO> likes = new ArrayList<>();

        public ReviewResponseDTO() {};

        public ReviewResponseDTO(Long reviewId, int rating, String text, Instant createdAt, String profileUsername, String avatarUrl, List<ReviewLikeDTO> likes) {
                this.reviewId = reviewId;
                this.rating = rating;
                this.text = text;
                this.createdAt = createdAt;
                this.profileUsername = profileUsername;
                this.avatarUrl = avatarUrl;
                this.likes = likes;
        }

        public ReviewResponseDTO toDto(Review review, ProfileDTO profileDTO) {
                return new ReviewResponseDTO(
                        review.getId(),
                        review.getRating(),
                        review.getText(),
                        review.getCreatedAt(),
                        profileDTO.getProfileName(),
                        profileDTO.getAvatarURL(),
                        review.getLikes().stream()
                                .map(like -> new ReviewLikeDTO(like.getId(), profileDTO.getProfileName(), like.getLikedAt(), like.getReview().getId())).collect(Collectors.toList())
                );
        }

        public Long getReviewId() {
                return reviewId;
        }

        public int getRating() {
                return rating;
        }

        public String getText() {
                return text;
        }

        public Instant getCreatedAt() {
                return createdAt;
        }

        public String getProfileUsername() {
                return profileUsername;
        }

        public String getAvatarUrl() {return avatarUrl; }

        public List<ReviewLikeDTO> getLikes() {
                return likes;
        }
};
