package se.yrgo.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import se.yrgo.domain.User;
import se.yrgo.repository.UserRepository;

@Service
public class UserService {
    private UserRepository ur;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository ur, PasswordEncoder passwordEncoder) {
        this.ur = ur;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User registerUser(User user) {
        // if user excist
        if (ur.findUserByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Username already exists"); //custom exception in futer
        }

        String encoderPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encoderPassword);
        user.setCreatedAt(LocalDateTime.now());

        return ur.save(user);
    }

    public Optional<User> findUserByEmail(String email){
        Optional<User> user = ur.findUserByEmail(email);
        return user;
    }
    
}
