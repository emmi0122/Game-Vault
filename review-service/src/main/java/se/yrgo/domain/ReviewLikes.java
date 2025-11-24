package se.yrgo.domain;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
public class ReviewLikes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private Instant likedAt;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

    public ReviewLikes() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Instant getLikedAt() {
        return likedAt;
    }

    public void setLikedAt(Instant likedAt) {
        this.likedAt = likedAt;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }
}
