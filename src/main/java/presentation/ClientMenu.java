package presentation;

import domain.entities.*;
import service.CarService;
import service.OrderService;
import service.UserService;

import java.util.List;
import java.util.Scanner;

public class ClientMenu {
    private final CarService carService;
    private final OrderService orderService;
    private final UserService userService;
    private final Scanner scanner;
    private SearchAll searchAll;

    public ClientMenu(CarService carService, OrderService orderService, UserService userService) {
        this.carService = carService;
        this.orderService = orderService;
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("1. View Cars");
            System.out.println("2. Search Cars by filters");
            System.out.println("3. Create Order");
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
                    createOrder();
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

    private void createOrder() {
        System.out.println("1. Create order to purchase a car");
        System.out.println("2. Create order for service");
        System.out.print("Choose an option: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter car ID: ");
        int carId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter your username: ");
        String customerName = scanner.nextLine();

        Car car = carService.getCar(carId);
        if (car == null) {
            System.out.println("Car not found.");
            return;
        }

        User client = userService.getUser(customerName);
        if (client == null) {
            System.out.println("Client not found.");
            return;
        }

        try {
            if (option == 1) {
                Order order = new Order(car, client);
                orderService.createOrder(order);
                System.out.println("Purchase order created successfully.");
            } else if (option == 2) {
                System.out.print("Enter service type (MAINTENANCE/REPAIR/DETAILING): ");
                ServiceType serviceType = ServiceType.valueOf(scanner.nextLine().toUpperCase());
                Order order = new Order(car, client, serviceType);
                orderService.createOrder(order);
                System.out.println("Service order created successfully.");
            } else {
                System.out.println("Invalid option.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}