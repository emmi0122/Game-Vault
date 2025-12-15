package se.yrgo.data;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import se.yrgo.domain.Profile;
import se.yrgo.domain.User;

@DataJpaTest
public class ProfileRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldGetProfileWhenProfileExist() {
        Profile newProfile = new Profile();
        newProfile.setAvatarURL("123");
        newProfile.setProfileName("bar");

        User user = new User();
        user.setEmail("Foo@Bar.gmail.com");
        user.setPassword("mySecretPassword123");
        user.setUserProfile(newProfile);

        userRepository.save(user);

        assertThat(userRepository.findAll())
                .first()
                .extracting(User::getUserProfile)
                .isNotNull()
                .extracting(Profile::getProfileName)
                .isEqualTo("bar");
    }
}
