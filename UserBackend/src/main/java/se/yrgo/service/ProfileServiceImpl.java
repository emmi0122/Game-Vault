package se.yrgo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import se.yrgo.data.ProfileRepository;
import se.yrgo.domain.Profile;

@Service
public class ProfileServiceImpl implements ProfileService{
    private ProfileRepository pr;

    public ProfileServiceImpl(ProfileRepository pr) {
        this.pr = pr;
    }

    @Override
    public Optional<Profile> getProfile(Long profileId) {
        Optional<Profile> profile = pr.findById(profileId);
        return profile;
    }
    
}
