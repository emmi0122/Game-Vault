package se.yrgo.rest;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import se.yrgo.domain.Profile;
import se.yrgo.dto.ProfileDTO;
import se.yrgo.exception.ProfileNotFoundException;
import se.yrgo.service.ProfileService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/profile")
public class ProfileController {
    private ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }
    
    @GetMapping("/getProfile")
    public ResponseEntity<?> getProfile(@RequestParam Long profileId) {
        Profile profile;
        try{
            profile = profileService.getProfile(profileId);
        }catch(ProfileNotFoundException error){
            System.err.println("An error occurred, why?:");
            error.printStackTrace();
            
            return ResponseEntity.ok(Map.of(
                    "status", "Major ERROR",
                    "message", "Could not found the profile connected to the user?"));
        }

        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO = ProfileDTO.toDTO(profile); 

        return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "Profile found",
                "profile", profileDTO));

    }
}
