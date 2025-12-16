package se.yrgo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.yrgo.domain.Platform;
import se.yrgo.repository.PlatformRepository;

@Service
public class PlatformService {

    @Autowired
    private PlatformRepository platformRepository;

    public List<Platform> getAllPlatforms() {
        return platformRepository.findAll();
    }

    public Platform getPlatformById(Long id) {
        return platformRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Platform not found with id: " + id));
    }

    public Platform getPlatformByName(String name) {
        return platformRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Platform not found with name: " + name));
    }

    public Platform createPlatform(Platform platform) {
        return platformRepository.save(platform);
    }

    public Platform updatePlatform(Long id, Platform platformDetails) {
        Platform platform = getPlatformById(id);
        platform.setName(platformDetails.getName());
        return platformRepository.save(platform);
    }

    public void deletePlatform(Long id) {
        Platform platform = getPlatformById(id);
        platformRepository.delete(platform);
    }
}