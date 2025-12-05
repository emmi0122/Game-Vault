package se.yrgo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import se.yrgo.domain.*;

public record RegistrationRequestDTO(
    @JsonProperty("user") User getUser,
    @JsonProperty("profile") Profile getProfile
){}
