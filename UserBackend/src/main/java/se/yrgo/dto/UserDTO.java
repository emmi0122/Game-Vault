package se.yrgo.dto;

import java.time.LocalDateTime;
import se.yrgo.domain.Profile;

public record UserDTO(
    Long id,
    String email,
    String password,
    Long profileId,  
    LocalDateTime createdAt
) {
}
