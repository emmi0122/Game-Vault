package se.yrgo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.yrgo.domain.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByGenre(String genre);
    List<Game> findByPegiLessThanEqual(int pegi);
    List<Game> findByTitle(String title);
    List<Game> findByReleaseDateGreaterThanEqual(int year);
}