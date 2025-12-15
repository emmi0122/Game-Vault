package se.yrgo.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import se.yrgo.data.*;
import se.yrgo.domain.*;
import se.yrgo.exception.ProfileNotFoundException;

@DataJpaTest
public class ProfileServiceIntegrationTest {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    private ProfileServiceImpl profileService;

    @BeforeEach
    void setUp() {
        profileService = new ProfileServiceImpl(profileRepository);
    }

    @Test
    void shouldReturnProfile() throws ProfileNotFoundException {
        Profile profile = new Profile();
        profile.setProfileName("bar");

        User user = new User();
        user.setEmail("foo@bar.com");
        user.setPassword("secret");

        user.setUserProfile(profile);
        profile.setUser(user);

        userRepository.save(user);

        Profile result = profileService.getProfile(profile.getId());
        assertThat(result.getProfileName()).isEqualTo("bar");
    }
}
