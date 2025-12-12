package se.yrgo.dto;

import se.yrgo.domain.Review;

import java.time.Instant;

public class ReviewResponseDTO {
        private Long reviewId;
        private int rating;
        private String text;
        private Instant createdAt;
        private String profileUsername;
        private String avatarUrl;

        public ReviewResponseDTO() {};

        public ReviewResponseDTO(Long reviewId, int rating, String text, Instant createdAt, String profileUsername, String avatarUrl) {
                this.reviewId = reviewId;
                this.rating = rating;
                this.text = text;
                this.createdAt = createdAt;
                this.profileUsername = profileUsername;
                this.avatarUrl = avatarUrl;
        }

        public ReviewResponseDTO toDto(Review review, ProfileDTO profileDTO) {
                return new ReviewResponseDTO(
                        review.getId(),
                        review.getRating(),
                        review.getText(),
                        review.getCreatedAt(),
                        profileDTO.getProfileName(),
                        profileDTO.getAvatarURL()
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
};
