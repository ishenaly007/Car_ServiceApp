package service;

import domain.entities.Car;
import domain.entities.Order;
import domain.entities.OrderStatus;
import domain.entities.User;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    void addOrder(Order order);

    void deleteOrder(int id);

    Order getOrder(int id);

    void updateOrder(Order order);

    List<Order> getAllOrders();

    List<Order> filterOrders(LocalDate startDate, LocalDate endDate, User client, OrderStatus status, Car car);

    void createOrder(Order order);
    void updateOrderStatus(int orderId, OrderStatus status);
    void cancelOrder(int orderId);
}