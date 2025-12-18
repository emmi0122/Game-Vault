package se.yrgo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import se.yrgo.data.ProfileRepository;
import se.yrgo.domain.Profile;
import se.yrgo.exception.ProfileNotFoundException;

/**
 * Implementation of {@link ProfileService} that retrieves profiles from the database.
 * Uses {@link ProfileRepository} for CRUD operations on profiles.
 */
@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository pr;

    /**
     * Creates a new {@code ProfileServiceImpl} with the given repository.
     *
     * @param pr the repository used for profile operations
     */
    public ProfileServiceImpl(ProfileRepository pr) {
        this.pr = pr;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Retrieves the profile from the database using {@link ProfileRepository#findById}.
     * Throws {@link ProfileNotFoundException} if the profile does not exist.
     */
    @Override
    public Profile getProfile(Long profileId) throws ProfileNotFoundException {
        Optional<Profile> profile = pr.findById(profileId);

        if (profile.isEmpty()) {
            throw new ProfileNotFoundException(
                "Could not find the profile connected to the user?", 
                new NullPointerException()
            );
        }

        return profile.get();
    }
}
