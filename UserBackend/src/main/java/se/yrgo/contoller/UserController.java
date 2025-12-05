package se.yrgo.contoller;

import org.springframework.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import se.yrgo.service.UserServiceImpl;
import se.yrgo.domain.*;
import se.yrgo.dto.RegistrationRequestDTO;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/user")
public class UserController {
    private UserServiceImpl userService;
    private PasswordEncoder passwordEncoder;

    public UserController(UserServiceImpl userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody RegistrationRequestDTO requestDTO) {
        // if user excist
        try {
            //validate user
            //validate profile
            //if valid do transaction
            User user = userService.registerUserWithProfile(requestDTO.getUser(), requestDTO.getProfile());

            return ResponseEntity.ok(Map.of(
                    "status", "success",
                    "message", "User registered successfully",
                    "userId", user.getId().toString()));
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
        Optional<User> findUser = userService.findUserByEmail(user.getEmail());
        User dbUser = findUser.get();

        // Kontrollera om användaren finns och lösenordet matchar
        if (findUser.isEmpty() || !passwordEncoder.matches(user.getPassword(), findUser.get().getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "status", "faild",
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
                // "profile", dbUser.getUserProfile(),
                "proflieId", dbUser.getUserProfile().getId()));
    }

    @GetMapping("/csrf")
    public CsrfToken csrf(CsrfToken token) {
        return token;
    }

}
