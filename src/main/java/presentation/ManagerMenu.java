package presentation;

import domain.entities.Car;
import domain.entities.Order;
import domain.entities.OrderStatus;
import service.CarService;
import service.OrderService;
import service.UserService;

import java.util.List;
import java.util.Scanner;

public class ManagerMenu {
    private final UserService userService;
    private final CarService carService;
    private final OrderService orderService;
    private final Scanner scanner;
    private SearchAll searchAll;

    public ManagerMenu(CarService carService, OrderService orderService, UserService userService) {
        this.carService = carService;
        this.orderService = orderService;
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("1. View Cars");
            System.out.println("2. Search Cars by filters");
            System.out.println("3. Manage Orders");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    viewCars();
                    break;
                case 2:
                    searchAll.searchCars(scanner, carService);
                    break;
                case 3:
                    manageOrders();
                    break;
                case 4:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void viewCars() {
        List<Car> cars = carService.getAllCars();
        if (cars.isEmpty()) {
            System.out.println("No cars available.");
        } else {
            cars.forEach(System.out::println);
        }
    }

    private void manageOrders() {
        while (true) {
            System.out.println("1. View Orders");
            System.out.println("2. Search Orders");
            System.out.println("3. Update Order Status");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    viewOrders();
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

    private void viewOrders() {
        List<Order> orders = orderService.getAllOrders();
        if (orders.isEmpty()) {
            System.out.println("No orders available.");
        } else {
            orders.forEach(System.out::println);
        }
    }

    private void updateOrderStatus() {
        System.out.print("Enter order ID to update: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new status(PENDING/PROCESSING/COMPLETED/CANCELLED): ");
        String status = scanner.nextLine();

        try {
            orderService.updateOrderStatus(orderId, OrderStatus.valueOf(status));
            System.out.println("Order status updated successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}