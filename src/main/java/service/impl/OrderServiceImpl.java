package service.impl;

import data.repositories.CarRepository;
import data.repositories.OrderRepository;
import domain.entities.Car;
import domain.entities.Order;
import domain.entities.OrderStatus;
import domain.entities.User;
import domain.utils.AuditService;
import service.OrderService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the OrderService interface that provides methods for managing orders,
 * including creating, updating, canceling, and filtering orders.
 */
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CarRepository carRepository;
    private final AuditService auditService;

    /**
     * Constructs a new OrderServiceImpl instance.
     *
     * @param orderRepository the repository used for order data storage and retrieval
     * @param carRepository the repository used for car data storage and retrieval
     * @param auditService the service used for logging actions
     */
    public OrderServiceImpl(OrderRepository orderRepository, CarRepository carRepository, AuditService auditService) {
        this.orderRepository = orderRepository;
        this.carRepository = carRepository;
        this.auditService = auditService;
    }

    /**
     * Creates a new order and logs the action.
     *
     * @param order the order to be created
     * @throws IllegalArgumentException if the car associated with the order is not found
     */
    @Override
    public void createOrder(Order order) {
        Car car = carRepository.get(order.getCar().getCarId());
        if (car == null) {
            throw new IllegalArgumentException("Car not found");
        }
        orderRepository.add(order);
        auditService.logAction(order.getClient().getUsername(), "Created order for car ID: " + car.getCarId());
    }

    /**
     * Updates the status of an existing order and logs the action.
     *
     * @param orderId the ID of the order to be updated
     * @param status the new status of the order
     * @throws IllegalArgumentException if the order with the specified ID does not exist
     */
    @Override
    public void updateOrderStatus(int orderId, OrderStatus status) {
        Order order = orderRepository.get(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Order with ID " + orderId + " does not exist.");
        }

        order.setStatus(status);
        orderRepository.update(order);
        auditService.logAction(order.getClient().getUsername(), "Updated order status to " + status);
    }

    /**
     * Cancels an existing order and logs the action.
     *
     * @param orderId the ID of the order to be canceled
     * @throws IllegalArgumentException if the order with the specified ID does not exist
     */
    @Override
    public void cancelOrder(int orderId) {
        Order order = orderRepository.get(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Order with ID " + orderId + " does not exist.");
        }

        orderRepository.delete(orderId);
        auditService.logAction(order.getClient().getUsername(), "Canceled order with ID " + orderId);
    }

    /**
     * Adds a new order to the repository.
     *
     * @param order the order to be added
     */
    @Override
    public void addOrder(Order order) {
        orderRepository.add(order);
    }

    /**
     * Deletes an existing order by its ID.
     *
     * @param id the ID of the order to be deleted
     * @throws IllegalArgumentException if the order with the specified ID does not exist
     */
    @Override
    public void deleteOrder(int id) {
        if (orderRepository.get(id) == null) {
            throw new IllegalArgumentException("Order with ID " + id + " does not exist.");
        }
        orderRepository.delete(id);
    }

    /**
     * Retrieves an order by its ID.
     *
     * @param id the ID of the order to be retrieved
     * @return the order with the specified ID
     * @throws IllegalArgumentException if the order with the specified ID does not exist
     */
    @Override
    public Order getOrder(int id) {
        Order order = orderRepository.get(id);
        if (order == null) {
            throw new IllegalArgumentException("Order with ID " + id + " does not exist.");
        }
        return order;
    }

    /**
     * Updates the details of an existing order.
     *
     * @param order the order with updated details
     * @throws IllegalArgumentException if the order with the specified ID does not exist
     */
    @Override
    public void updateOrder(Order order) {
        if (orderRepository.get(order.getOrderId()) == null) {
            throw new IllegalArgumentException("Order with ID " + order.getOrderId() + " does not exist.");
        }
        orderRepository.update(order);
    }

    /**
     * Retrieves all orders from the repository.
     *
     * @return a list of all orders
     */
    @Override
    public List<Order> getAllOrders() {
        return new ArrayList<>(orderRepository.getAll());
    }

    /**
     * Filters orders based on various criteria.
     *
     * @param startDate the start date of the order (can be null to ignore)
     * @param endDate the end date of the order (can be null to ignore)
     * @param client the client associated with the order (can be null to ignore)
     * @param status the status of the order (can be null to ignore)
     * @param car the car associated with the order (can be null to ignore)
     * @return a list of orders matching the criteria
     */
    @Override
    public List<Order> filterOrders(LocalDate startDate, LocalDate endDate, User client, OrderStatus status, Car car) {
        return orderRepository.getAll().stream()
                .filter(order -> (startDate == null || !order.getOrderDate().isBefore(startDate)) &&
                                 (endDate == null || !order.getOrderDate().isAfter(endDate)) &&
                                 (client == null || order.getClient().equals(client)) &&
                                 (status == null || order.getStatus().equals(status)) &&
                                 (car == null || order.getCar().equals(car)))
                .collect(Collectors.toList());
    }
}