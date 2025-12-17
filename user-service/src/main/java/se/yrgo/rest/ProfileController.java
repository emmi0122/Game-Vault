package se.yrgo.rest;

import java.util.*;

import org.springframework.http.*;
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
            
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "status", "Major ERROR",
                    "message", "Could not found the profile connected to the user?"));
        }catch(Exception error){
            System.err.println("An error occurred, why?:");
            error.printStackTrace();
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "failed",
                    "message", "Network error"));
        }

        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO = ProfileDTO.toDTO(profile); 

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(Map.of(
                "status", "success",
                "message", "Profile found",
                "profile", profileDTO));

    }
}
