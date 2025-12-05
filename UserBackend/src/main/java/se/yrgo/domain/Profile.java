package se.yrgo.domain;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "Profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // auth like (user, admin)
    @ElementCollection
    @CollectionTable(name = "profile_roles", joinColumns = @JoinColumn(name = "profile_id"))
    @Column(name = "role")
    private List<String> roles;

    @Column(unique = true)
    private String profileName;
    private String realName;

    private String avatarURL; // image
    // Later when games has genger
    // private List<String> favoriteGenres;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        if (user != null && user.getUserProfile() != this) {
            user.setUserProfile(this);
        }
    }

}
