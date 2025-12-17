package se.yrgo.service;

import se.yrgo.domain.Profile;
import se.yrgo.exception.ProfileNotFoundException;


public interface ProfileService {
    Profile getProfile(Long profileId) throws ProfileNotFoundException;
}
