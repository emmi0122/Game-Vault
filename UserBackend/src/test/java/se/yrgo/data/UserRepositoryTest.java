package se.yrgo.data;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import se.yrgo.domain.User;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldFindUserByEmail() {
        User user = new User();
        user.setEmail("foo@bar.com");
        user.setPassword("secret");

        userRepository.save(user);
        
        Optional<User> result = userRepository.findUserByEmail("foo@bar.com");

        assertThat(result)
                .isPresent()
                .get()
                .extracting(User::getEmail)
                .isEqualTo("foo@bar.com");
    }

    @Test
    void shouldReturnEmptyWhenEmailDoesNotExist() {
        Optional<User> result = userRepository.findUserByEmail("doesnotexist@bar.com");

        assertThat(result).isEmpty();
    }
}
