package se.yrgo.service;

import se.yrgo.domain.Profile;
import se.yrgo.exception.ProfileNotFoundException;

/**
 * Service interface for managing user profiles.
 * Defines the contract for how profiles should be retrieved.
 */
public interface ProfileService {

    /**
     * Retrieves a profile based on the profile ID.
     *
     * @param profileId the ID of the profile to retrieve
     * @return the Profile object associated with the given ID
     * @throws ProfileNotFoundException if the profile does not exist
     */
    Profile getProfile(Long profileId) throws ProfileNotFoundException;
}
