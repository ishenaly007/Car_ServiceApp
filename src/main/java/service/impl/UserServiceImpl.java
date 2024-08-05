package service.impl;

import data.repositories.UserRepository;
import domain.entities.Role;
import domain.entities.User;
import domain.utils.AuditService;
import service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the UserService interface that provides methods for managing users,
 * including registration, authentication, and updating user information.
 */
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuditService auditService;

    /**
     * Constructs a new UserServiceImpl instance.
     *
     * @param userRepository the repository used for user data storage and retrieval
     * @param auditService the service used for logging actions
     */
    public UserServiceImpl(UserRepository userRepository, AuditService auditService) {
        this.userRepository = userRepository;
        this.auditService = auditService;
    }

    /**
     * Registers a new user if the username is not already taken and logs the action.
     *
     * @param user the user to be registered
     * @throws IllegalArgumentException if the username is already taken
     */
    @Override
    public void registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("Username " + user.getUsername() + " is already taken.");
        }
        userRepository.add(user);
        auditService.logAction("Registered user: " + user.getUsername());
    }

    /**
     * Authenticates a user by username and password and logs the login action.
     *
     * @param username the username of the user
     * @param password the password of the user
     * @return the authenticated user
     * @throws IllegalArgumentException if the username or password is invalid
     */
    @Override
    public User authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid username or password.");
        }
        auditService.logAction("User logged in: " + username);
        return user;
    }

    /**
     * Adds a new user if the username does not already exist and logs the action.
     *
     * @param user the user to be added
     * @throws IllegalArgumentException if the username is already taken
     */
    @Override
    public void addUser(User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null) {
            throw new IllegalArgumentException("User with this username already exists.");
        }
        userRepository.add(user);
        auditService.logAction("Added user: " + user.getUsername());
    }

    /**
     * Deletes a user by ID and logs the action.
     *
     * @param id the ID of the user to be deleted
     */
    @Override
    public void deleteUser(int id) {
        userRepository.delete(id);
        auditService.logAction("Deleted user with ID: " + id);
    }

    /**
     * Retrieves a user by ID.
     *
     * @param id the ID of the user to be retrieved
     * @return the user with the specified ID
     */
    @Override
    public User getUser(int id) {
        return userRepository.get(id);
    }

    /**
     * Retrieves a user by username.
     *
     * @param username the username of the user to be retrieved
     * @return the user with the specified username
     */
    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Updates an existing user and logs the action.
     *
     * @param user the user with updated information
     * @throws IllegalArgumentException if the username is already taken by another user
     */
    @Override
    public void updateUser(User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null && existingUser.getId() != user.getId()) {
            throw new IllegalArgumentException("User with this username already exists.");
        }
        userRepository.update(user);
        auditService.logAction("Updated user: " + user.getUsername());
    }

    /**
     * Retrieves all users from the repository.
     *
     * @return a list of all users
     */
    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(userRepository.getAll());
    }

    /**
     * Filters users based on various criteria.
     *
     * @param name the name of the user (can be null to ignore)
     * @param username the username of the user (can be null to ignore)
     * @param role the role of the user (can be null to ignore)
     * @return a list of users matching the criteria
     */
    @Override
    public List<User> filterUsers(String name, String username, Role role) {
        return userRepository.getAll().stream()
                .filter(user -> (name == null || user.getName().equalsIgnoreCase(name)) &&
                                (username == null || user.getUsername().equals(username)) &&
                                (role == null || user.getRole().equals(role)))
                .collect(Collectors.toList());
    }
}