package se.yrgo.rest;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.*;

import se.yrgo.domain.Profile;
import se.yrgo.dto.ProfileDTO;
import se.yrgo.exception.ProfileNotFoundException;
import se.yrgo.service.ProfileService;

@ExtendWith(MockitoExtension.class)
class ProfileControllerTest {

    @Mock
    private ProfileService profileService;

    @InjectMocks
    private ProfileController profileController;

    @Test
    void getProfile_shouldReturnAccepted_whenProfileExists() throws Exception {
        Long profileId = 1L;
        Profile profile = new Profile();

        when(profileService.getProfile(profileId)).thenReturn(profile);
        ResponseEntity<?> response = profileController.getProfile(profileId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);

        Map<String, Object> body = (Map<String, Object>) response.getBody();
        assertThat(body.get("status")).isEqualTo("success");
        assertThat(body.get("message")).isEqualTo("Profile found");
        assertThat(body.get("profile")).isInstanceOf(ProfileDTO.class);
    }

    @Test
    void getProfile_shouldReturnNotFound_whenProfileNotFoundExceptionThrown() throws Exception {
        Long profileId = 2L;
        when(profileService.getProfile(profileId)).thenThrow(new ProfileNotFoundException("profile not found"));

        ResponseEntity<?> response = profileController.getProfile(profileId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

        Map<String, Object> body = (Map<String, Object>) response.getBody();
        assertThat(body.get("status")).isEqualTo("Major ERROR");
        assertThat(body.get("message")).isEqualTo("Could not found the profile connected to the user?");
    }

    @Test
    void getProfile_shouldReturnInternalServerError_whenOtherExceptionThrown() throws Exception {
        Long profileId = 3L;
        when(profileService.getProfile(profileId)).thenThrow(new RuntimeException("DB down"));

        ResponseEntity<?> response = profileController.getProfile(profileId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);

        Map<String, Object> body = (Map<String, Object>) response.getBody();
        assertThat(body.get("status")).isEqualTo("failed");
        assertThat(body.get("message")).isEqualTo("Network error");
    }
}
