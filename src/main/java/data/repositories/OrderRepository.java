package data.repositories;

import data.CrudRepository;
import domain.entities.Order;

import java.util.*;

/**
 * Repository for managing order entities.
 * This class provides methods for adding, updating, deleting, and querying orders.
 */
public class OrderRepository implements CrudRepository<Order> {
    private final Map<Integer, Order> orders = new HashMap<>();

    /**
     * Adds a new order to the repository.
     *
     * @param item the order to add.
     */
    @Override
    public void add(Order item) {
        orders.put(item.getOrderId(), item);
    }

    /**
     * Retrieves an order by its ID.
     *
     * @param id the ID of the order to retrieve.
     * @return the order with the specified ID, or null if not found.
     */
    @Override
    public Order get(int id) {
        return orders.get(id);
    }

    /**
     * Updates an existing order in the repository.
     *
     * @param order the order to update.
     */
    @Override
    public void update(Order order) {
        if (orders.containsKey(order.getOrderId())) {
            orders.replace(order.getOrderId(), order);
        } else {
            System.out.println("Order with ID " + order.getOrderId() + " does not exist.");
        }
    }

    /**
     * Deletes an order from the repository by its ID.
     *
     * @param id the ID of the order to delete.
     */
    @Override
    public void delete(int id) {
        orders.remove(id);
    }

    /**
     * Retrieves all orders in the repository.
     *
     * @return a collection of all orders.
     */
    @Override
    public Collection<Order> getAll() {
        return orders.values();
    }
}