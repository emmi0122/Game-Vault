package se.yrgo.domain;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Game {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String title;
    private String genre;
    private int pegi;
    private int releaseDate;
    private String developedBy;
    
    @ManyToMany
    @JoinTable(
        name = "game_platform",
        joinColumns = @JoinColumn(name = "game_id"),
        inverseJoinColumns = @JoinColumn(name = "platform_id")
    )
    private List<Platform> platforms = new ArrayList<>();
    
    public Game() {}
    
    public Game(String title, String genre, int pegi, int releaseDate, String developedBy) {
        this.title = title;
        this.genre = genre;
        this.pegi = pegi;
        this.releaseDate = releaseDate;
        this.developedBy = developedBy;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getGenre() {
        return genre;
    }
    
    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    public int getPegi() {
        return pegi;
    }
    
    public void setPegi(int pegi) {
        this.pegi = pegi;
    }
    
    public int getReleaseDate() {
        return releaseDate;
    }
    
    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }
    
    public String getDevelopedBy() {
        return developedBy;
    }
    
    public void setDevelopedBy(String developedBy) {
        this.developedBy = developedBy;
    }
    
    public List<Platform> getPlatforms() {
        return platforms;
    }
    
    public void setPlatforms(List<Platform> platforms) {
        this.platforms = platforms;
    }
    
    public void addPlatform(Platform platform) {
        this.platforms.add(platform);
        platform.getGames().add(this);
    }
    
    public void removePlatform(Platform platform) {
        this.platforms.remove(platform);
        platform.getGames().remove(this);
    }
}