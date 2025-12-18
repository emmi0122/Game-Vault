package se.yrgo.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import se.yrgo.data.UserRepository;
import se.yrgo.domain.*;
import se.yrgo.exception.*;

/**
 * Implementation of {@link UserService} that handles user persistence
 * and authentication logic.
 */
@Service
public class UserServiceImpl implements UserService {
    private UserRepository ur;
    private PasswordEncoder passwordEncoder;

    /**
     * Creates a new {@code UserServiceImpl} with the required dependencies.
     *
     * @param ur              the repository used for user persistence
     * @param passwordEncoder encoder used to hash and verify passwords
     */
    public UserServiceImpl(UserRepository ur, PasswordEncoder passwordEncoder) {
        this.ur = ur;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Encodes the user's password, sets metadata, associates the profile,
     * and persists the user in the database.
     */
    @Override
    @Transactional
    public User registerUserWithProfile(User user, Profile profile) {
        // validate user (in further implementation)

        if (ur.findUserByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        // validate profile (in further implementation)

        String encoderPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encoderPassword);
        user.setCreatedAt(LocalDateTime.now());

        user.setUserProfile(profile);

        return ur.save(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User findUserByEmail(String email) throws UserNotFoundException {
        Optional<User> user = ur.findUserByEmail(email);
        if (user.isEmpty()) {
            throw new UserNotFoundException("No user found with this email: " + email, null);
        }

        return user.get();
    }

    /**
     * {@inheritDoc}
     * <p>
     * Verifies that the provided password matches the stored encoded password.
     */
    @Override
    public void validatePassword(User foundUser, User loginUser) {
        if (foundUser == null || loginUser == null || loginUser.getPassword() == null) {
            throw new InvalidLoginException("Invalid username or password", null);
        }

        if (!passwordEncoder.matches(loginUser.getPassword(), foundUser.getPassword())) {
            throw new InvalidLoginException("Invalid username or password", null);
        }
    }

}
