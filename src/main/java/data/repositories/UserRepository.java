package data.repositories;

import data.CrudRepository;
import domain.entities.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Repository for managing user entities.
 * This class provides methods for adding, updating, deleting, and querying users.
 */
public class UserRepository implements CrudRepository<User> {
    private Map<Integer, User> users = new HashMap<>();

    /**
     * Finds a user by their username.
     *
     * @param username the username of the user to find.
     * @return the user with the specified username, or null if not found.
     */
    public User findByUsername(String username) {
        return users.values().stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    /**
     * Adds a new user to the repository.
     *
     * @param item the user to add.
     */
    @Override
    public void add(User item) {
        users.put(item.getId(), item);
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user to retrieve.
     * @return the user with the specified ID, or null if not found.
     */
    @Override
    public User get(int id) {
        return users.get(id);
    }

    /**
     * Updates an existing user in the repository.
     *
     * @param item the user to update.
     */
    @Override
    public void update(User item) {
        if (users.containsKey(item.getId())) {
            users.replace(item.getId(), item);
        } else {
            System.out.println("User with ID " + item.getId() + " does not exist.");
        }
    }

    /**
     * Deletes a user from the repository by their ID.
     *
     * @param id the ID of the user to delete.
     */
    @Override
    public void delete(int id) {
        users.remove(id);
    }

    /**
     * Retrieves all users in the repository.
     *
     * @return a collection of all users.
     */
    @Override
    public Collection<User> getAll() {
        return users.values();
    }
}