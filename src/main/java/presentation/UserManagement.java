package presentation;

import domain.entities.Role;
import domain.entities.User;
import service.UserService;

import java.util.Scanner;

public class UserManagement {
    private final UserService userService;
    private final Scanner scanner;

    public UserManagement(UserService userService) {
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    public void register() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter role (ADMIN/MANAGER/CLIENT): ");
        Role role = Role.valueOf(scanner.nextLine().toUpperCase());

        User user = new User(name, username, password, role);
        try {
            userService.addUser(user);
            System.out.println("User registered successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public User login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            User user = userService.authenticate(username, password);
            System.out.println("Login successful. Welcome, " + user.getName() + "!");
            return user;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}