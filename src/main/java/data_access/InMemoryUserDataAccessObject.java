package data_access;

import java.util.HashMap;
import java.util.Map;

import entity.User;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

/**
 * In-memory implementation of the DAO for storing user data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface {

    /**
     * A map to store users by their username.
     */
    private final Map<String, User> users = new HashMap<>();

    /**
     * The username of the current logged-in user. Null if no user is logged in.
     */
    private String currentUser;

    /**
     * Checks if a user exists in the data store by their username.
     *
     * @param identifier the username to check
     * @return true if the user exists, false otherwise
     */
    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
    }

    /**
     * Saves a user to the data store.
     *
     * @param user the user to save
     */
    @Override
    public void save(User user) {
        users.put(user.getName(), user);
    }

    /**
     * Retrieves a user from the data store by their username.
     *
     * @param username the username of the user to retrieve
     * @return the User object corresponding to the username, or null if not found
     */
    @Override
    public User get(String username) {
        return users.get(username);
    }

    /**
     * Changes the password for an existing user in the data store.
     *
     * @param user the user whose password is to be changed
     */
    @Override
    public void changePassword(User user) {
        // Replace the old entry with the updated user object containing the new password
        users.put(user.getName(), user);
    }

    /**
     * Sets the current logged-in user.
     *
     * @param name the username of the user who has logged in
     */
    @Override
    public void setCurrentUser(String name) {
        this.currentUser = name;
    }

    /**
     * Retrieves the current logged-in user.
     *
     * @return the username of the current logged-in user, or null if no user is logged in
     */
    public String getCurrentUser() {
        return currentUser;
    }
}
