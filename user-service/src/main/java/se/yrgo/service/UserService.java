package se.yrgo.service;

import se.yrgo.domain.*;
import se.yrgo.exception.InvalidLoginException;
import se.yrgo.exception.UserNotFoundException;

/**
 * Service interface for managing users and authentication logic.
 * Defines the contract for user registration, lookup, and password validation.
 */
public interface UserService {
    /**
     * Registers a new user together with an associated profile.
     *
     * @param user the user to be registered
     * @param profile the profile associated with the user
     * @return the persisted User entity
     * @throws IllegalArgumentException if a user with the same email already exists
     */
    User registerUserWithProfile(User user, Profile profile);

    /**
     * Finds a user by email address.
     *
     * @param email the email of the user
     * @return the User associated with the given email
     * @throws UserNotFoundException if no user with the given email exists
     */
    User findUserByEmail(String email) throws UserNotFoundException;

    /**
     * Validates the password provided during login against the stored password.
     *
     * @param foundUser the user retrieved from the database
     * @param loginUser the user object containing the login credentials
     * @throws InvalidLoginException if the credentials are invalid
     */
    void validatePassword(User findUser, User user);
}
