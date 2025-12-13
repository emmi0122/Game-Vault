package se.yrgo.dto;

import se.yrgo.domain.Review;

import java.time.Instant;

public class ReviewLikeDTO {
    private Long reviewLikeId;
    private String profileUsername;
    private Instant likedAt;
    private Long reviewId;

    public ReviewLikeDTO() {};

    public ReviewLikeDTO(Long reviewLikeId, String profileUsername, Instant likedAt, Long reviewId) {
        this.reviewLikeId = reviewLikeId;
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

    public String getProfileId() {
        return profileUsername;
    }

    public void setProfileId(String profileUsername) {
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
