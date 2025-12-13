package se.yrgo.domain;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;


@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private Long profileId;
    @Column(nullable = false)
    private Long gameId;
    private int rating;
    @Column(nullable = false)
    private String text;
    private Instant createdAt;

    @OneToMany(
            mappedBy = "review",
            cascade = CascadeType.ALL
    )
    private List<ReviewLikes> likes;

    public Review() {};

    public Long getId() {
        return id;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long userId) {
        this.profileId = userId;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public List<ReviewLikes> getLikes() {
        return likes;
    }
}
