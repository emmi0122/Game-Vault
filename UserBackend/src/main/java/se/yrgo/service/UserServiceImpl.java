package se.yrgo.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import se.yrgo.data.UserRepository;
import se.yrgo.domain.Profile;
import se.yrgo.domain.User;
import se.yrgo.exception.InvalidLoginException;
import se.yrgo.exception.UserNotFoundException;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository ur;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository ur, PasswordEncoder passwordEncoder) {
        this.ur = ur;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User registerUserWithProfile(User user, Profile profile) {
        // validate user
        // if user excist
        if (ur.findUserByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }
        
        // validate profile 
        String encoderPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encoderPassword);
        user.setCreatedAt(LocalDateTime.now());

        user.setUserProfile(profile);

        return ur.save(user);
    }

    public User findUserByEmail(String email) throws UserNotFoundException {
        Optional<User> user = ur.findUserByEmail(email);
        if (user.isEmpty()) {
            throw new UserNotFoundException("No user found with this email: " + email, null);
        }

        return user.get();
    }

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
