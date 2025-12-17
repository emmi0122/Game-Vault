package se.yrgo.service;


import se.yrgo.domain.*;
import se.yrgo.exception.UserNotFoundException;

public interface UserService {
    User registerUserWithProfile(User user, Profile profile);

    User findUserByEmail(String email) throws UserNotFoundException;

    void validatePassword(User findUser, User user);
}
