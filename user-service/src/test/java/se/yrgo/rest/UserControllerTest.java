package se.yrgo.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.*;

import se.yrgo.domain.Profile;
import se.yrgo.domain.User;
import se.yrgo.dto.RegistrationRequestDTO;
import se.yrgo.exception.UserNotFoundException;
import se.yrgo.service.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private UserController userController;

    @Test
    void register_shouldReturnCreated_whenUserRegistered() throws Exception {
        User user = new User();
        Profile profile = new Profile();
        profile.setId(1L);
        user.setUserProfile(profile);

        RegistrationRequestDTO dto = new RegistrationRequestDTO(user, profile);

        when(userService.registerUserWithProfile(user, profile)).thenReturn(user);

        ResponseEntity<Map<String, String>> response = userController.register(dto);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Map<String, String> body = response.getBody();
        assertThat(body.get("status")).isEqualTo("success");
        assertThat(body.get("message")).isEqualTo("User registered successfully");
        assertThat(body.get("profileId")).isEqualTo(profile.getId().toString());
    }

    @Test
    void register_shouldReturnBadRequest_whenUserAlreadyExists() throws Exception {
        User user = new User();
        Profile profile = new Profile();

        RegistrationRequestDTO dto = new RegistrationRequestDTO(user, profile);

        when(userService.registerUserWithProfile(user, profile))
                .thenThrow(new RuntimeException("User exists"));

        ResponseEntity<Map<String, String>> response = userController.register(dto);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        Map<String, String> body = response.getBody();
        assertThat(body.get("status")).isEqualTo("error");
        assertThat(body.get("message")).isEqualTo("User with that email already exists");
    }

    // ===== LOGIN TESTS =====
    @Test
    void login_shouldReturnAccepted_whenLoginSuccessful() throws Exception {
        User user = new User();
        Profile profile = new Profile();
        profile.setId(2L);
        User dbUser = new User();
        dbUser.setUserProfile(profile);
        user.setEmail("test@test.com");

        when(userService.findUserByEmail("test@test.com")).thenReturn(dbUser);
        // Mocka validatePassword så att den inte kastar exception
        // Om metoden är void så kan vi bara låta den göra ingenting

        ResponseEntity<?> response = userController.login(user);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
        Map<String, Object> body = (Map<String, Object>) response.getBody();
        assertThat(body.get("status")).isEqualTo("success");
        assertThat(body.get("message")).isEqualTo("User logged in successfully");
        assertThat(body.get("profileId")).isEqualTo(2L);
    }

    @Test
    void login_shouldReturnUnauthorized_whenUserNotFoundOrInvalidLogin() throws Exception {
        User user = new User();
        user.setEmail("notfound@test.com");

        when(userService.findUserByEmail("notfound@test.com"))
                .thenThrow(new UserNotFoundException("no user found", new IllegalArgumentException()));

        ResponseEntity<?> response = userController.login(user);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
        Map<String, Object> body = (Map<String, Object>) response.getBody();
        assertThat(body.get("status")).isEqualTo("failed");
        assertThat(body.get("message")).isEqualTo("Invalid email or password");
    }

    @Test
    void login_shouldReturnInternalServerError_whenOtherException() throws Exception {
        User user = new User();
        user.setEmail("error@test.com");

        when(userService.findUserByEmail("error@test.com"))
                .thenThrow(new RuntimeException("DB down"));

        ResponseEntity<?> response = userController.login(user);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        Map<String, Object> body = (Map<String, Object>) response.getBody();
        assertThat(body.get("status")).isEqualTo("failed");
        assertThat(body.get("message")).isEqualTo("Network error");
    }
}

