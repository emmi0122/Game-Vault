package se.yrgo.rest;

import org.springframework.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;
import se.yrgo.service.UserService;
import se.yrgo.domain.User;

import java.util.*;

@RestController
public class UserController {
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody User user) {
        // if user excist
        try {
            userService.registerUser(user);
            return ResponseEntity.ok(Map.of(
                    "status", "success",
                    "message", "User registered successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "error",
                    "message", "User with that email already exists"));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Optional<User> findUser = userService.findUserByEmail(user.getEmail());
        User dbUser = findUser.get();

        // Kontrollera om användaren finns och lösenordet matchar
        if (findUser.isEmpty() || !passwordEncoder.matches(user.getPassword(), findUser.get().getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "status", "error",
                    "message", "Invalid username or password"));
        }


        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                dbUser.getId(),
                null, // no past-one password
                List.of() // authorities like User/Adimin...
        );

        SecurityContextHolder.getContext().setAuthentication(auth);

        // Returnera JSON till frontend
        return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "User logged in successfully",
            //     "profile", dbUser.getUserProfile(),
                "userId", dbUser.getId()));
    }

    @GetMapping("/protected")
    public String authController() {
        return "Du är autentiserad och har nått en skyddad resurs!";
    }

    @GetMapping("/csrf")
    public CsrfToken csrf(CsrfToken token) {
        return token;
    }

}
