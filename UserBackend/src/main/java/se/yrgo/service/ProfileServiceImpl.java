package se.yrgo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import se.yrgo.data.ProfileRepository;
import se.yrgo.domain.Profile;
import se.yrgo.exception.ProfileNotFoundException;

@Service
public class ProfileServiceImpl implements ProfileService{
    private ProfileRepository pr;

    public ProfileServiceImpl(ProfileRepository pr) {
        this.pr = pr;
    }

    // @Override
    // public Optional<Profile> getProfile(Long profileId) {
    //     Optional<Profile> profile = pr.findById(profileId);
    //     return profile;
    // }

    @Override
    public Profile getProfile(Long profileId) throws ProfileNotFoundException {
        Optional<Profile> profile = pr.findById(profileId);

        if(profile.isEmpty()){
            throw new ProfileNotFoundException("Could not found the profile connected to the user?", new NullPointerException());
        }

        return profile.get();
    }
    
}
