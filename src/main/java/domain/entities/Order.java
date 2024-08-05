package domain.entities;

import java.time.LocalDate;

public class Order {
    private static int idCounter = 0;
    private final int orderId;
    private User client;
    private Car car;
    private OrderStatus status;
    private LocalDate orderDate;
    private ServiceType serviceType;

    @Override
    public String toString() {
        return "Order{" +
               "orderId=" + orderId +
               ", client=" + client +
               ", car=" + car +
               ", status=" + status +
               ", orderDate=" + orderDate +
               ", serviceType=" + serviceType +
               '}';
    }

    public Order(Car car, User client) {
        this.orderId = ++idCounter;
        this.car = car;
        this.client = client;
        this.status = OrderStatus.PENDING; //статус по умолчанию
        this.orderDate = LocalDate.now();
        this.serviceType = null;
    }

    public Order(Car car, User client, ServiceType serviceType) {
        this(car, client);
        this.serviceType = serviceType;
    }

    public int getOrderId() {
        return orderId;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }
}