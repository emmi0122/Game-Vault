package se.yrgo.dto;

import java.time.LocalDateTime;

public record UserDTO(
    Long id,
    String email,
    String password,
    Long profileId,  
    LocalDateTime createdAt
) {
}
