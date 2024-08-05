package service.impl;

import data.repositories.CarRepository;
import domain.entities.Car;
import domain.entities.CarCondition;
import domain.utils.AuditService;
import service.CarService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the CarService interface that provides methods for managing cars,
 * including adding, updating, deleting, and searching for cars.
 */
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final AuditService auditService;

    /**
     * Constructs a new CarServiceImpl instance.
     *
     * @param carRepository the repository used for car data storage and retrieval
     * @param auditService the service used for logging actions
     */
    public CarServiceImpl(CarRepository carRepository, AuditService auditService) {
        this.carRepository = carRepository;
        this.auditService = auditService;
    }

    /**
     * Retrieves a car by its ID.
     *
     * @param id the ID of the car
     * @return the car with the specified ID, or null if not found
     */
    @Override
    public Car getCar(int id) {
        return carRepository.get(id);
    }

    /**
     * Adds a new car to the repository and logs the action.
     *
     * @param car the car to be added
     */
    @Override
    public void addCar(Car car) {
        carRepository.add(car);
        auditService.logAction("Admin", "Added car: " + car);
    }

    /**
     * Deletes a car by its ID from the repository and logs the action.
     *
     * @param id the ID of the car to be deleted
     * @throws IllegalArgumentException if the car with the specified ID does not exist
     */
    @Override
    public void deleteCar(int id) {
        Car car = carRepository.get(id);
        if (car == null) {
            throw new IllegalArgumentException("Car with ID " + id + " does not exist.");
        }
        carRepository.delete(id);
        auditService.logAction("Deleted car with ID: " + id);
    }

    /**
     * Updates the details of an existing car and logs the action.
     *
     * @param car the car with updated details
     * @throws IllegalArgumentException if the car with the specified ID does not exist
     */
    @Override
    public void updateCar(Car car) {
        if (carRepository.get(car.getCarId()) == null) {
            throw new IllegalArgumentException("Car with ID " + car.getCarId() + " does not exist.");
        }
        carRepository.update(car);
        auditService.logAction("Updated car with ID: " + car.getCarId());
    }

    /**
     * Retrieves all cars from the repository.
     *
     * @return a list of all cars
     */
    @Override
    public List<Car> getAllCars() {
        return new ArrayList<>(carRepository.getAll());
    }

    /**
     * Searches for cars based on various criteria.
     *
     * @param brand the brand of the car (can be null to ignore)
     * @param model the model of the car (can be null to ignore)
     * @param year the year of the car (can be null to ignore)
     * @param price the maximum price of the car (can be null to ignore)
     * @param condition the condition of the car (can be null to ignore)
     * @return a list of cars matching the criteria
     */
    @Override
    public List<Car> searchCars(String brand, String model, Integer year, Double price, CarCondition condition) {
        return carRepository.getAll().stream()
                .filter(car -> (brand == null || car.getBrand().equalsIgnoreCase(brand)) &&
                               (model == null || car.getModel().equalsIgnoreCase(model)) &&
                               (year == null || car.getYear() == year) &&
                               (price == null || car.getPrice() <= price) &&
                               (condition == null || car.getCondition().equals(condition)))
                .collect(Collectors.toList());
    }

    /**
     * Searches for cars by brand.
     *
     * @param brand the brand of the car
     * @return a list of cars with the specified brand
     */
    @Override
    public List<Car> searchCarsByBrand(String brand) {
        return carRepository.findByBrand(brand);
    }

    /**
     * Searches for cars by model.
     *
     * @param model the model of the car
     * @return a list of cars with the specified model
     */
    @Override
    public List<Car> searchCarsByModel(String model) {
        return carRepository.findByModel(model);
    }

    /**
     * Filters cars based on their condition.
     *
     * @param condition the condition of the car
     * @return a list of cars with the specified condition
     */
    @Override
    public List<Car> filterCarsByCondition(CarCondition condition) {
        return carRepository.findByCondition(condition);
    }

    /**
     * Filters cars based on a price range.
     *
     * @param minPrice the minimum price of the car
     * @param maxPrice the maximum price of the car
     * @return a list of cars within the specified price range
     */
    @Override
    public List<Car> filterCarsByPriceRange(int minPrice, int maxPrice) {
        return carRepository.findByPriceRange(minPrice, maxPrice);
    }

    /**
     * Filters cars based on a year range.
     *
     * @param minYear the minimum year of the car
     * @param maxYear the maximum year of the car
     * @return a list of cars within the specified year range
     */
    @Override
    public List<Car> filterCarsByYearRange(int minYear, int maxYear) {
        return carRepository.findByYearRange(minYear, maxYear);
    }
}