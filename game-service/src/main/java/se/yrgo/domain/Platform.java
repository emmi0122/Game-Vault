package se.yrgo.domain;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Platform {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name; //"Xbox", "PlayStation", "Nintendo", "PC"
    
    @ManyToMany(mappedBy = "platforms")
    @JsonIgnore
    private List<Game> games = new ArrayList<>();
    
    public Platform() {}
    
    public Platform(String name) {
        this.name = name;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<Game> getGames() {
        return games;
    }
    
    public void setGames(List<Game> games) {
        this.games = games;
    }
}