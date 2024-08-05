package data.repositories;

import data.CrudRepository;
import domain.entities.Car;
import domain.entities.CarCondition;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Repository for managing car entities.
 * This class provides methods for adding, updating, deleting, and querying cars.
 */
public class CarRepository implements CrudRepository<Car> {
    private final Map<Integer, Car> cars = new HashMap<>();

    /**
     * Retrieves a car by ID.
     *
     * @param id the ID of the car to retrieve.
     * @return the car with the ID, or null if not found.
     */
    @Override
    public Car get(int id) {
        return cars.get(id);
    }

    /**
     * Finds cars by model.
     *
     * @param model the model of the cars to find.
     * @return a list of cars with the model.
     */
    public List<Car> findByModel(String model) {
        return cars.values().stream()
                .filter(car -> car.getModel().equalsIgnoreCase(model))
                .collect(Collectors.toList());
    }

    /**
     * Adds a new car.
     *
     * @param item the car to add.
     */
    @Override
    public void add(Car item) {
        cars.put(item.getCarId(), item);
    }

    /**
     * Updates an existing car.
     *
     * @param item the car to update.
     */
    @Override
    public void update(Car item) {
        if (cars.containsKey(item.getCarId())) {
            cars.replace(item.getCarId(), item);
        } else {
            System.out.println("Car with ID " + item.getCarId() + " does not exist.");
        }
    }

    /**
     * Deletes a car from the repository by ID.
     *
     * @param id the ID of the car to delete.
     */
    @Override
    public void delete(int id) {
        cars.remove(id);
    }

    /**
     * Retrieves all cars.
     *
     * @return a collection of all cars.
     */
    @Override
    public Collection<Car> getAll() {
        return cars.values();
    }

    /**
     * Finds cars by brand.
     *
     * @param brand the brand of the cars to find.
     * @return a list of cars with the brand.
     */
    public List<Car> findByBrand(String brand) {
        return cars.values().stream()
                .filter(car -> car.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
    }

    /**
     * Finds cars by condition.
     *
     * @param condition the condition of the cars to find.
     * @return a list of cars with the condition.
     */
    public List<Car> findByCondition(CarCondition condition) {
        return cars.values().stream()
                .filter(car -> car.getCondition() == condition)
                .collect(Collectors.toList());
    }

    /**
     * Finds cars within a specific price range.
     *
     * @param minPrice the minimum price of the cars to find.
     * @param maxPrice the maximum price of the cars to find.
     * @return a list of cars within the price range.
     */
    public List<Car> findByPriceRange(int minPrice, int maxPrice) {
        return cars.values().stream()
                .filter(car -> car.getPrice() >= minPrice && car.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    /**
     * Finds cars within a specific year range.
     *
     * @param minYear the minimum year of the cars to find.
     * @param maxYear the maximum year of the cars to find.
     * @return a list of cars within the year range.
     */
    public List<Car> findByYearRange(int minYear, int maxYear) {
        return cars.values().stream()
                .filter(car -> car.getYear() >= minYear && car.getYear() <= maxYear)
                .collect(Collectors.toList());
    }
}