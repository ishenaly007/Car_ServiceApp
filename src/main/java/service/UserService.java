package service;

import domain.entities.Role;
import domain.entities.User;

import java.util.List;

public interface UserService {

    void registerUser(User user);
    User authenticate(String username, String password);

    void addUser(User user);

    void deleteUser(int id);

    User getUser(int id);
    User getUser(String username);

    void updateUser(User user);

    List<User> getAllUsers();

    List<User> filterUsers(String name, String username, Role role);
}
