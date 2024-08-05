package test;

import data.repositories.CarRepository;
import data.repositories.OrderRepository;
import data.repositories.UserRepository;
import domain.entities.*;
import domain.utils.AuditService;
import org.junit.Before;
import org.junit.Test;
import service.CarService;
import service.OrderService;
import service.UserService;
import service.impl.CarServiceImpl;
import service.impl.OrderServiceImpl;
import service.impl.UserServiceImpl;

import static org.junit.Assert.*;

public class OrderServiceTest {

    final UserRepository userRepository = new UserRepository();
    final OrderRepository orderRepository = new OrderRepository();
    final CarRepository carRepository = new CarRepository();

    AuditService auditService = new AuditService();
    UserService userService;
    CarService carService;
    OrderService orderService;

    @Before
    public void setUp() {
        carService = new CarServiceImpl(carRepository, auditService);
        userService = new UserServiceImpl(userRepository, auditService);
        orderService = new OrderServiceImpl(orderRepository, carRepository, auditService);
    }

    @Test
    public void testCreateOrder() {
        Car car = new Car("BMW", "M5", 2020, 50000, CarCondition.NEW);
        carService.addCar(car);
        User user = new User("Ishen", "ishen123", "123", Role.CLIENT);
        userService.addUser(user);
        Order order = new Order(car, user);
        orderService.createOrder(order);
        assertNotNull(orderService.getOrder(order.getOrderId()));
    }
}