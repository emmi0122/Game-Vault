package se.yrgo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.yrgo.domain.Platform;
import se.yrgo.service.PlatformService;

@RestController
@RequestMapping("/api/platforms")
public class PlatformController {

    @Autowired
    private PlatformService platformService;

    @GetMapping
    public List<Platform> getAllPlatforms() {
        return platformService.getAllPlatforms();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Platform> getPlatform(@PathVariable Long id) {
        Platform platform = platformService.getPlatformById(id);
        return ResponseEntity.ok(platform);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Platform> getPlatformByName(@PathVariable String name) {
        Platform platform = platformService.getPlatformByName(name);
        return ResponseEntity.ok(platform);
    }

    @PostMapping
    public ResponseEntity<Platform> createPlatform(@RequestBody Platform platform) {
        Platform createdPlatform = platformService.createPlatform(platform);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPlatform);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Platform> updatePlatform(@PathVariable Long id, @RequestBody Platform platform) {
        Platform updatedPlatform = platformService.updatePlatform(id, platform);
        return ResponseEntity.ok(updatedPlatform);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlatform(@PathVariable Long id) {
        platformService.deletePlatform(id);
        return ResponseEntity.noContent().build();
    }
}