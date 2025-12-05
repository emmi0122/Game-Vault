package se.yrgo.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import se.yrgo.data.UserRepository;
import se.yrgo.domain.Profile;
import se.yrgo.domain.User;

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
        // if user excist
        if (ur.findUserByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Username already exists"); //custom exception in futer
        }

        String encoderPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encoderPassword);
        user.setCreatedAt(LocalDateTime.now());

        user.setUserProfile(profile);

        return ur.save(user);
    }

    public Optional<User> findUserByEmail(String email){
        Optional<User> user = ur.findUserByEmail(email);
        return user;
    }
    
}
