package se.yrgo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import se.yrgo.data.UserRepository;
import se.yrgo.domain.User;
import se.yrgo.exception.*;

@DataJpaTest
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
    void shouldFindUserByEmailWhenUserExcist(){
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
    void shouldThrowUserNotFoundExceptionIfUserNotExcist(){
        String email = "Foo@Bar.gmail.com";

         assertThrows(
                UserNotFoundException.class,
                () -> userService.findUserByEmail(email));

    }



}
