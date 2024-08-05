package presentation;

import data.repositories.CarRepository;
import data.repositories.OrderRepository;
import data.repositories.UserRepository;
import domain.entities.*;
import domain.utils.AuditService;
import service.CarService;
import service.OrderService;
import service.UserService;
import service.impl.CarServiceImpl;
import service.impl.OrderServiceImpl;
import service.impl.UserServiceImpl;

import java.util.Scanner;

/**
 * The main console application class that handles user interactions and navigates through the different menus
 * based on the user's role (ADMIN, MANAGER, CLIENT).
 */
public class ConsoleApp {
    private final UserManagement userManagement;
    private final CarService carService;
    private final UserService userService;
    private final OrderService orderService;
    private final AuditService auditService;
    private User currentUser;
    private final Scanner scanner;

    /**
     * Constructs a new ConsoleApp instance.
     *
     * @param userManagement the user management service
     * @param carService the car service
     * @param userService the user service
     * @param orderService the order service
     * @param auditService the audit service
     */
    public ConsoleApp(UserManagement userManagement, CarService carService, UserService userService,
                      OrderService orderService, AuditService auditService) {
        this.userManagement = userManagement;
        this.carService = carService;
        this.userService = userService;
        this.orderService = orderService;
        this.auditService = auditService;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts the console application and displays the main menu.
     * It handles user registration, login, and exiting the application.
     */
    public void start() {
        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    userManagement.register();
                    break;
                case 2:
                    currentUser = userManagement.login();
                    if (currentUser != null) {
                        displayUserOptions(currentUser);
                    }
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    /**
     * Displays user-specific options based on the user's role.
     * Allows users to navigate through their respective menus (AdminMenu, ManagerMenu, ClientMenu)
     * and provides options to logout or exit the application.
     *
     * @param user the current user
     */
    private void displayUserOptions(User user) {
        while (true) {
            if (user.getRole() == Role.ADMIN) {
                AdminMenu adminMenu = new AdminMenu(carService, userService, orderService, auditService);
                adminMenu.showMenu();
            } else if (user.getRole() == Role.MANAGER) {
                ManagerMenu managerMenu = new ManagerMenu(carService, orderService, userService);
                managerMenu.showMenu();
            } else if (user.getRole() == Role.CLIENT) {
                ClientMenu clientMenu = new ClientMenu(carService, orderService, userService);
                clientMenu.showMenu();
            }

            System.out.println("1. Logout");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    currentUser = null;
                    return; // Return to the main menu
                case 2:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    /**
     * The entry point of the application. Initializes the required services and starts the console application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        final UserRepository userRepository = new UserRepository();
        final OrderRepository orderRepository = new OrderRepository();
        final CarRepository carRepository = new CarRepository();

        AuditService auditService = new AuditService();
        UserService userService = new UserServiceImpl(userRepository, auditService);
        CarService carService = new CarServiceImpl(carRepository, auditService);
        OrderService orderService = new OrderServiceImpl(orderRepository, carRepository, auditService);

        UserManagement userManagement = new UserManagement(userService);
        ConsoleApp app = new ConsoleApp(userManagement, carService, userService, orderService, auditService);
        app.start();
    }
}