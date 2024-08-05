package presentation;

import domain.entities.*;
import service.CarService;
import service.OrderService;
import service.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class SearchAll {
    public void searchOrders(Scanner scanner, OrderService orderService, UserService userService, CarService carService) {
        System.out.println("Search Orders");

        System.out.print("Enter start date (yyyy-mm-dd) (or leave empty): ");
        String startDateInput = scanner.nextLine();
        LocalDate startDate = startDateInput.isEmpty() ? null : LocalDate.parse(startDateInput);

        System.out.print("Enter end date (yyyy-mm-dd) (or leave empty): ");
        String endDateInput = scanner.nextLine();
        LocalDate endDate = endDateInput.isEmpty() ? null : LocalDate.parse(endDateInput);

        System.out.print("Enter client username (or leave empty): ");
        String clientUsername = scanner.nextLine();
        User client = clientUsername.isEmpty() ? null : userService.getUser(clientUsername);

        System.out.print("Enter status (NEW/IN_PROGRESS/COMPLETED/CANCELLED) (or leave empty): ");
        String statusInput = scanner.nextLine();
        OrderStatus status = statusInput.isEmpty() ? null : OrderStatus.valueOf(statusInput.toUpperCase());

        System.out.print("Enter car ID (or leave empty): ");
        String carIdInput = scanner.nextLine();
        Car car = carIdInput.isEmpty() ? null : carService.getCar(Integer.parseInt(carIdInput));

        List<Order> results = orderService.filterOrders(startDate, endDate, client, status, car);
        displayOrders(results);
    }

    private void displayOrders(List<Order> orders) {
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            for (Order order : orders) {
                System.out.println(order);
            }
        }
    }

    public void searchCars(Scanner scanner, CarService carService) {
        System.out.println("Search Cars");
        System.out.print("Enter brand (or leave empty): ");
        String brand = scanner.nextLine();
        brand = brand.isEmpty() ? null : brand;

        System.out.print("Enter model (or leave empty): ");
        String model = scanner.nextLine();
        model = model.isEmpty() ? null : model;

        System.out.print("Enter year (or leave empty): ");
        String yearInput = scanner.nextLine();
        Integer year = yearInput.isEmpty() ? null : Integer.parseInt(yearInput);

        System.out.print("Enter maximum price (or leave empty): ");
        String priceInput = scanner.nextLine();
        Double price = priceInput.isEmpty() ? null : Double.parseDouble(priceInput);

        System.out.print("Enter condition (NEW/USED) (or leave empty): ");
        String conditionInput = scanner.nextLine();
        CarCondition condition = conditionInput.isEmpty() ? null : CarCondition.valueOf(conditionInput.toUpperCase());

        List<Car> results = carService.searchCars(brand, model, year, price, condition);
        displayCars(results);
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
}