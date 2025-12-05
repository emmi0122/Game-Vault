package se.yrgo.service;

import java.util.Optional;

import se.yrgo.domain.Profile;
import se.yrgo.domain.User;

public interface UserService {
    User registerUserWithProfile(User user, Profile profile);

    Optional<User> findUserByEmail(String email);

}
