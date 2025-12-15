package se.yrgo.dto;

import se.yrgo.domain.Review;

import java.time.Instant;

public class ReviewLikeDTO {
    private Long reviewLikeId;
    private Long profileId;
    private String profileUsername;
    private Instant likedAt;
    private Long reviewId;

    public ReviewLikeDTO() {};

    public ReviewLikeDTO(Long reviewLikeId, Long profileId, String profileUsername, Instant likedAt, Long reviewId) {
        this.reviewLikeId = reviewLikeId;
        this.profileId = profileId;
        this.profileUsername = profileUsername;
        this.likedAt = likedAt;
        this.reviewId = reviewId;
    }


    public Long getReviewLikeId() {
        return reviewLikeId;
    }

    public void setReviewLikeId(Long reviewLikeId) {
        this.reviewLikeId = reviewLikeId;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public String getProfileUsername() {
        return profileUsername;
    }

    public void setProfileUsername(String profileUsername) {
        this.profileUsername = profileUsername;
    }

    public Instant getLikedAt() {
        return likedAt;
    }

    public void setLikedAt(Instant likedAt) {
        this.likedAt = likedAt;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReview(Long reviewId) {
        this.reviewId = reviewId;
    }
}
