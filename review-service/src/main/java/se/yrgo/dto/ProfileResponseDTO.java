package se.yrgo.dto;

public record ProfileResponseDTO (
    String message,
    String status,
    ProfileDTO profile
) {}
