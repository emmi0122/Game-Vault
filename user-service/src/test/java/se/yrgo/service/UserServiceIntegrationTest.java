package se.yrgo.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import se.yrgo.data.UserRepository;
import se.yrgo.domain.*;
import se.yrgo.exception.*;

@DataJpaTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
public class UserServiceIntegrationTest {
    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository, passwordEncoder);
    }

    @Test
    void shouldRegisterUserWithProfileWhenUserIsNew() {
        User user = new User();
        String email = "Foo@Bar.com";
        user.setEmail(email);
        user.setPassword("password123");

        Profile profile = new Profile();
        profile.setProfileName("Foo");

        User savedUser = userService.registerUserWithProfile(user, profile);

        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getEmail()).isEqualTo(email);
        assertThat(savedUser.getPassword()).isNotEqualTo("password123");
        assertThat(savedUser.getUserProfile()).isEqualTo(profile);
        assertThat(savedUser.getCreatedAt()).isNotNull();

        User fromDb = userRepository.findById(savedUser.getId()).orElseThrow();
        assertThat(fromDb.getEmail()).isEqualTo(email);
    }

    @Test
    void shouldThrowExceptionWhenRegisteringUserThatAlreadyExists() {
        User user = new User();
        String email = "Foo@Bar.com";
        user.setEmail(email);
        user.setPassword("password123");

        Profile profile = new Profile();
        profile.setProfileName("Foo");

        userService.registerUserWithProfile(user, profile);

        assertThatThrownBy(() -> {
            userService.registerUserWithProfile(user, profile);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void shouldFindUserByEmailWhenUserExcist() {
        String email = "Foo@Bar.gmail.com";

        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword("mySecretPassword");

        userRepository.save(newUser);

        User foundUser = userService.findUserByEmail(email);
        assertThat(foundUser)
                .isEqualTo(newUser);

    }

    @Test
    void shouldThrowUserNotFoundExceptionIfUserNotExcist() {
        String email = "Foo@Bar.gmail.com";

        assertThatThrownBy(() -> userService.findUserByEmail(email))
                .isInstanceOf(UserNotFoundException.class);

    }

    // validatePassword
    @Test
    void shouldNotThrowException_whenPasswordIsValid() {
        String password = "secret";
        String email = "Foo@Bar.gmail.com";
        User user = new User();
        user.setPassword(password);
        user.setEmail(email);

        Profile profile = new Profile();
        userService.registerUserWithProfile(user, profile);

        User foundUser = userService.findUserByEmail(email);

        User loginUser = new User();
        loginUser.setEmail(email);
        loginUser.setPassword(password);

        assertThatCode(() -> userService.validatePassword(foundUser, loginUser))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = { "wrongPassword" })
    @NullAndEmptySource
    void shouldThrowException_whenPasswordIsInvalid(String inLogPassword) {
        String password = "secret";
        String email = "Foo@Bar.gmail.com";
        User user = new User();
        user.setPassword(password);
        user.setEmail(email);

        Profile profile = new Profile();
        userService.registerUserWithProfile(user, profile);

        User loginUser = new User();
        loginUser.setEmail(email);
        loginUser.setPassword(inLogPassword);

        User foundUser = userService.findUserByEmail(email);

        assertThatThrownBy(() -> userService.validatePassword(foundUser, loginUser))
                .isInstanceOf(InvalidLoginException.class);
    }

}
