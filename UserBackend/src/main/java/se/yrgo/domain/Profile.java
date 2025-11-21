package se.yrgo.domain;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "Profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // auth like (user, admin)
    @ElementCollection
    @CollectionTable(name = "profile_roles", joinColumns = @JoinColumn(name = "profile_id"))
    @Column(name = "role")
    private List<String> roles;

    @Column(unique = true)
    private String profileName;
    private String avatarURL; // image
    // Later when games has genger
    // private List<String> favoriteGenres;
    
    
    public Profile() {
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public List<String> getRoles() {
        return roles;
    }
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
    public String getProfileName() {
        return profileName;
    }
    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }
    public String getAvatarURL() {
        return avatarURL;
    }
    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }


    
}
