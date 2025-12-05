package se.yrgo.service;

import java.util.Optional;

import se.yrgo.domain.Profile;


public interface ProfileService {
    Optional<Profile> getProfile(Long profileId);
}
