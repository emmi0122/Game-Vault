package se.yrgo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.yrgo.domain.Game;
import se.yrgo.service.GameService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGame(@PathVariable Long id) {
        Game game = gameService.getGameById(id);
        return ResponseEntity.ok(game);
    }

    @GetMapping("/genre/{genre}")
    public List<Game> getGamesByGenre(@PathVariable String genre) {
        return gameService.getGamesByGenre(genre);
    }

    @GetMapping("/pegi/{maxPegi}")
    public List<Game> getGamesByMaxPegi(@PathVariable int maxPegi) {
        return gameService.getGamesByMaxPegi(maxPegi);
    }

    @GetMapping("/title/{title}")
    public List<Game> getGamesByTitle(@PathVariable String title) {
        return gameService.getGamesByTitle(title);
    }

    @GetMapping("/year/{year}")
    public List<Game> getGamesFromYear(@PathVariable int year) {
        return gameService.getGamesFromYear(year);
    }

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        Game createdGame = gameService.createGame(game);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGame);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable Long id, @RequestBody Game game) {
        Game updatedGame = gameService.updateGame(id, game);
        return ResponseEntity.ok(updatedGame);
    }

    @DeleteMapping("/{id}") 
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        gameService.deleteGame(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{gameId}/platforms/{platformId}")
    public ResponseEntity<Game> addPlatformToGame(@PathVariable Long gameId, @PathVariable Long platformId) {
        Game game = gameService.addPlatformToGame(gameId, platformId);
        return ResponseEntity.ok(game);
    }

    @DeleteMapping("/{gameId}/platforms/{platformId}")
    public ResponseEntity<Game> removePlatformFromGame(@PathVariable Long gameId, @PathVariable Long platformId) {
        Game game = gameService.removePlatformFromGame(gameId, platformId);
        return ResponseEntity.ok(game);
    }
}