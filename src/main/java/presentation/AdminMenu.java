package presentation;

import domain.entities.*;
import domain.utils.AuditService;
import service.CarService;
import service.OrderService;
import service.UserService;

import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    private final CarService carService;
    private final UserService userService;
    private final OrderService orderService;
    private final AuditService auditService;
    private final Scanner scanner;
    private SearchAll searchAll;

    public AdminMenu(CarService carService, UserService userService, OrderService orderService, AuditService auditService) {
        this.carService = carService;
        this.userService = userService;
        this.orderService = orderService;
        this.auditService = auditService;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("Admin Menu:");
            System.out.println("1. Manage Cars");
            System.out.println("2. Manage Orders");
            System.out.println("3. Manage Users");
            System.out.println("4. View Audit Log");
            System.out.println("5. Export Audit Log");
            System.out.println("6. Logout");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    manageCars();
                    break;
                case 2:
                    manageOrders();
                    break;
                case 3:
                    manageUsers();
                    break;
                case 4:
                    viewAuditLog();
                    break;
                case 5:
                    exportAuditLog();
                    break;
                case 6:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void manageCars() {
        while (true) {
            System.out.println("Manage Cars:");
            System.out.println("1. Add Car");
            System.out.println("2. Delete Car");
            System.out.println("3. Update Car");
            System.out.println("4. Search Cars by filter");
            System.out.println("5. Search Cars by filters");
            System.out.println("6. View All Cars");
            System.out.println("7. Back");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    addCar();
                    break;
                case 2:
                    deleteCar();
                    break;
                case 3:
                    updateCar();
                    break;
                case 4:
                    searchAndFilterCars();
                    break;
                case 5:
                    searchAll.searchCars(scanner, carService);
                    break;
                case 6:
                    viewAllCars();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void manageOrders() {
        while (true) {
            System.out.println("Manage Orders:");
            System.out.println("1. View All Orders");
            System.out.println("2. Search Orders");
            System.out.println("3. Update Order Status");
            System.out.println("4. Back");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    viewAllOrders();
                    break;
                case 2:
                    updateOrderStatus();
                    break;
                case 3:
                    searchAll.searchOrders(scanner, orderService, userService, carService);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void manageUsers() {
        while (true) {
            System.out.println("Manage Users:");
            System.out.println("1. Add User");
            System.out.println("2. Delete User");
            System.out.println("3. Update User");
            System.out.println("4. Search Users");
            System.out.println("5. View All Users");
            System.out.println("6. Back");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    addUser();
                    break;
                case 2:
                    deleteUser();
                    break;
                case 3:
                    updateUser();
                    break;
                case 4:
                    searchUsers();
                    break;
                case 5:
                    viewAllUsers();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void viewAuditLog() {
        System.out.println("Audit Log:");
        for (String log : auditService.getAuditLog()) {
            System.out.println(log);
        }
    }

    private void exportAuditLog() {
        System.out.print("Enter filename to export audit log: ");
        String filename = scanner.nextLine();
        auditService.exportAuditLog(filename);
        System.out.println("Audit log exported successfully to " + filename);
    }

    private void searchUsers() {
        System.out.println("Search Users");
        System.out.print("Enter name (or leave empty): ");
        String name = scanner.nextLine();
        name = name.isEmpty() ? null : name;

        System.out.print("Enter username (or leave empty): ");
        String username = scanner.nextLine();
        username = username.isEmpty() ? null : username;

        System.out.print("Enter role (ADMIN/MANAGER/CLIENT) (or leave empty): ");
        String roleInput = scanner.nextLine();
        Role role = roleInput.isEmpty() ? null : Role.valueOf(roleInput.toUpperCase());

        List<User> results = userService.filterUsers(name, username, role);
        displayUsers(results);
    }

    private void displayUsers(List<User> users) {
        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            for (User user : users) {
                System.out.println(user);
            }
        }
    }

    private void searchAndFilterCars() {
        System.out.println("1. Search by brand");
        System.out.println("2. Search by model");
        System.out.println("3. Filter by condition (NEW/USED)");
        System.out.println("4. Filter by price range");
        System.out.println("5. Filter by year range");
        System.out.print("Choose an option: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                System.out.print("Enter brand: ");
                String brand = scanner.nextLine();
                List<Car> carsByBrand = carService.searchCarsByBrand(brand);
                displayCars(carsByBrand);
                break;
            case 2:
                System.out.print("Enter model: ");
                String model = scanner.nextLine();
                List<Car> carsByModel = carService.searchCarsByModel(model);
                displayCars(carsByModel);
                break;
            case 3:
                System.out.print("Enter condition (NEW/USED): ");
                CarCondition condition = CarCondition.valueOf(scanner.nextLine().toUpperCase());
                List<Car> carsByCondition = carService.filterCarsByCondition(condition);
                displayCars(carsByCondition);
                break;
            case 4:
                System.out.print("Enter min price: ");
                int minPrice = scanner.nextInt();
                System.out.print("Enter max price: ");
                int maxPrice = scanner.nextInt();
                scanner.nextLine();
                List<Car> carsByPriceRange = carService.filterCarsByPriceRange(minPrice, maxPrice);
                displayCars(carsByPriceRange);
                break;
            case 5:
                System.out.print("Enter min year: ");
                int minYear = scanner.nextInt();
                System.out.print("Enter max year: ");
                int maxYear = scanner.nextInt();
                scanner.nextLine();
                List<Car> carsByYearRange = carService.filterCarsByYearRange(minYear, maxYear);
                displayCars(carsByYearRange);
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private void displayCars(List<Car> cars) {
        if (cars.isEmpty()) {
            System.out.println("No cars found.");
        } else {
            for (Car car : cars) {
                System.out.println(car);
            }
        }
    }

    private void addCar() {
        System.out.print("Enter car brand: ");
        String brand = scanner.nextLine();
        System.out.print("Enter car model: ");
        String model = scanner.nextLine();
        System.out.print("Enter car year: ");
        int year = scanner.nextInt();
        System.out.print("Enter car price: ");
        int price = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter car condition (NEW/USED): ");
        CarCondition condition = CarCondition.valueOf(scanner.nextLine().toUpperCase());

        Car car = new Car(brand, model, year, price, condition);
        carService.addCar(car);
        //auditService.logAction("Admin", "Added car: " + car);
        System.out.println("Car added successfully.");
    }

    private void deleteCar() {
        System.out.print("Enter car ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        carService.deleteCar(id);
        auditService.logAction("Admin", "Deleted car with ID: " + id);
        System.out.println("Car deleted successfully.");
    }

    private void updateCar() {
        System.out.print("Enter car ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Car car = carService.getCar(id);
        if (car == null) {
            System.out.println("Car with ID " + id + " does not exist.");
            return;
        }

        System.out.print("Enter new car brand (leave empty to keep current): ");
        String brand = scanner.nextLine();
        System.out.print("Enter new car model (leave empty to keep current): ");
        String model = scanner.nextLine();
        System.out.print("Enter new car year (leave empty to keep current): ");
        String yearInput = scanner.nextLine();
        Integer year = yearInput.isEmpty() ? null : Integer.parseInt(yearInput);
        System.out.print("Enter new car price (leave empty to keep current): ");
        String priceInput = scanner.nextLine();
        Integer price = priceInput.isEmpty() ? null : Integer.parseInt(priceInput);
        System.out.print("Enter new car condition (NEW/USED, leave empty to keep current): ");
        String conditionInput = scanner.nextLine();
        CarCondition condition = conditionInput.isEmpty() ? null : CarCondition.valueOf(conditionInput.toUpperCase());

        if (!brand.isEmpty()) car.setBrand(brand);
        if (!model.isEmpty()) car.setModel(model);
        if (year != null) car.setYear(year);
        if (price != null) car.setPrice(price);
        if (condition != null) car.setCondition(condition);

        carService.updateCar(car);
        auditService.logAction("Admin", "Updated car: " + car);
        System.out.println("Car updated successfully.");
    }

    private void viewAllCars() {
        System.out.println("All Cars:");
        for (Car car : carService.getAllCars()) {
            System.out.println(car);
        }
    }

    private void viewAllOrders() {
        System.out.println("All Orders:");
        for (Order order : orderService.getAllOrders()) {
            System.out.println(order);
        }
    }

    private void updateOrderStatus() {
        System.out.print("Enter order ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Order order = orderService.getOrder(id);
        if (order == null) {
            System.out.println("Order with ID " + id + " does not exist.");
            return;
        }

        System.out.print("Enter new order status (PENDING/PROCESSING/COMPLETED/CANCELLED): ");
        OrderStatus status = OrderStatus.valueOf(scanner.nextLine().toUpperCase());

        order.setStatus(status);
        orderService.updateOrder(order);
        auditService.logAction("Admin", "Updated order status: " + order);
        System.out.println("Order status updated successfully.");
    }

    private void addUser() {
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
            auditService.logAction("Admin", "Added user: " + user);
            System.out.println("User added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteUser() {
        System.out.print("Enter user ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        userService.deleteUser(id);
        auditService.logAction("Admin", "Deleted user with ID: " + id);
        System.out.println("User deleted successfully.");
    }

    private void updateUser() {
        System.out.print("Enter user ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        User user = userService.getUser(id);
        if (user == null) {
            System.out.println("User with ID " + id + " does not exist.");
            return;
        }

        System.out.print("Enter new name (leave empty to keep current): ");
        String name = scanner.nextLine();
        System.out.print("Enter new username (leave empty to keep current): ");
        String username = scanner.nextLine();
        System.out.print("Enter new password (leave empty to keep current): ");
        String password = scanner.nextLine();
        System.out.print("Enter new role (ADMIN/MANAGER/CLIENT, leave empty to keep current): ");
        String roleInput = scanner.nextLine();
        Role role = roleInput.isEmpty() ? null : Role.valueOf(roleInput.toUpperCase());

        if (!name.isEmpty()) user.setName(name);
        if (!username.isEmpty()) user.setUsername(username);
        if (!password.isEmpty()) user.setPassword(password);
        if (role != null) user.setRole(role);

        userService.updateUser(user);
        auditService.logAction("Admin", "Updated user: " + user);
        System.out.println("User updated successfully.");
    }

    private void viewAllUsers() {
        System.out.println("All Users:");
        for (User user : userService.getAllUsers()) {
            System.out.println(user);
        }
    }
}