package se.yrgo.dto;

import java.time.Instant;

public record ReviewResponseDTO (
        Long reviewId,
        int rating,
        String text,
        Instant createdAt,
        String profileUsername
        ) {};
