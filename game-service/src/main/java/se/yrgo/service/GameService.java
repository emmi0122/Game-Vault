package se.yrgo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.yrgo.domain.Game;
import se.yrgo.domain.Platform;
import se.yrgo.repository.GameRepository;
import se.yrgo.repository.PlatformRepository;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlatformRepository platformRepository;

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Game getGameById(Long id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Game not found with id: " + id));
    }

    public Game createGame(Game game) {
        return gameRepository.save(game);
    }

    public Game updateGame(Long id, Game gameDetails) {
        Game game = getGameById(id);
        game.setTitle(gameDetails.getTitle());
        game.setGenre(gameDetails.getGenre());
        game.setPegi(gameDetails.getPegi());
        game.setReleaseDate(gameDetails.getReleaseDate());
        game.setDevelopedBy(gameDetails.getDevelopedBy());
        return gameRepository.save(game);
    }

    public void deleteGame(Long id) {
        Game game = getGameById(id);
        gameRepository.delete(game);
    }

    public List<Game> getGamesByGenre(String genre) {
        return gameRepository.findByGenre(genre);
    }

    public List<Game> getGamesByMaxPegi(int maxPegi) {
        return gameRepository.findByPegiLessThanEqual(maxPegi);
    }

    public List<Game> getGamesByTitle(String title) {
        return gameRepository.findByTitle(title);
    }

    public List<Game> getGamesFromYear(int year) {
        return gameRepository.findByReleaseDateGreaterThanEqual(year);
    }

    public Game addPlatformToGame(Long gameId, Long platformId) {
        Game game = getGameById(gameId);
        Platform platform = platformRepository.findById(platformId)
                .orElseThrow(() -> new RuntimeException("Platform not found with id: " + platformId));
        game.addPlatform(platform);
        return gameRepository.save(game);
    }

    public Game removePlatformFromGame(Long gameId, Long platformId) {
        Game game = getGameById(gameId);
        Platform platform = platformRepository.findById(platformId)
                .orElseThrow(() -> new RuntimeException("Platform not found with id: " + platformId));
        game.removePlatform(platform);
        return gameRepository.save(game);
    }
}