package se.yrgo.dto;

import java.util.List;
import se.yrgo.domain.Profile;

public class ProfileDTO {

    private Long id;
    private List<String> roles;
    private String profileName;
    private String realName;
    private String avatarURL;
    private Long userId;

    public ProfileDTO() {}

    public ProfileDTO(Long id, List<String> roles, String profileName,
                      String realName, String avatarURL, Long userId) {
        this.id = id;
        this.roles = roles;
        this.profileName = profileName;
        this.realName = realName;
        this.avatarURL = avatarURL;
        this.userId = userId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public List<String> getRoles() { return roles; }
    public void setRoles(List<String> roles) { this.roles = roles; }

    public String getProfileName() { return profileName; }
    public void setProfileName(String profileName) { this.profileName = profileName; }

    public String getRealName() { return realName; }
    public void setRealName(String realName) { this.realName = realName; }

    public String getAvatarURL() { return avatarURL; }
    public void setAvatarURL(String avatarURL) { this.avatarURL = avatarURL; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public static ProfileDTO toDTO(Profile profile) {
        if (profile == null) return null;

        return new ProfileDTO(
                profile.getId(),
                profile.getRoles(),
                profile.getProfileName(),
                profile.getRealName(),
                profile.getAvatarURL(),
                profile.getUser() != null ? profile.getUser().getId() : null
        );
    }
}
