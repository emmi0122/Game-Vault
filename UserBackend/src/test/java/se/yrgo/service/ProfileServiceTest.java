package se.yrgo.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import se.yrgo.domain.Profile;
import se.yrgo.domain.User;
import se.yrgo.rest.*;
import se.yrgo.dto.RegistrationRequestDTO;

public class ProfileServiceTest {
    private ProfileService profileService;

   



    @Test
    void shouldGetProfileWhenProfileExcist(){
        Profile newProfile = new Profile();
        newProfile.setAvatarURL("123");
        newProfile.setProfileName("bar");

        User user = new User();
        user.setEmail("Foo@Bar.gmail.com");
        user.setPassword("mySecretPassword123");
        user.setUserProfile(newProfile);

        Profile profile = profileService.getProfile(1L);


    }
}
