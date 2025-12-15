package se.yrgo.rest;

import org.springframework.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import se.yrgo.service.UserServiceImpl;
import se.yrgo.domain.*;
import se.yrgo.dto.RegistrationRequestDTO;
import se.yrgo.exception.InvalidLoginException;
import se.yrgo.exception.UserNotFoundException;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/user")
public class UserController {
    private UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody RegistrationRequestDTO requestDTO) {
        try {    
            User user = userService.registerUserWithProfile(requestDTO.getUser(), requestDTO.getProfile());

            return ResponseEntity.ok(Map.of(
                    "status", "success",
                    "message", "User registered successfully",
                    "profileId", user.getUserProfile().getId().toString()));
        } catch (Exception e) {

            System.err.print(e.getMessage());
            System.err.println(e.getCause());
            e.printStackTrace();

            return ResponseEntity.badRequest().body(Map.of(
                    "status", "error",
                    "message", "User with that email already exists"));
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User dbUser;

        try {
            dbUser = userService.findUserByEmail(user.getEmail());
            userService.validatePassword(dbUser, user);

        } catch (UserNotFoundException | InvalidLoginException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "status", "failed",
                    "message", "Invalid email or password"));
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
                // "profile", dbUser.getUserProfile(),
                "profileId", dbUser.getUserProfile().getId()));
    }

    @GetMapping("/csrf")
    public CsrfToken csrf(CsrfToken token) {
        return token;
    }

}
