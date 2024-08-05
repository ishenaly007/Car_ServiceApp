package service;

import domain.entities.Car;
import domain.entities.CarCondition;

import java.util.List;

public interface CarService {
    void addCar(Car car);

    void deleteCar(int id);

    Car getCar(int id);

    void updateCar(Car car);

    List<Car> getAllCars();

    List<Car> searchCars(String brand, String model, Integer year, Double price, CarCondition condition);


    List<Car> searchCarsByBrand(String brand);

    List<Car> searchCarsByModel(String model);

    List<Car> filterCarsByCondition(CarCondition condition);

    List<Car> filterCarsByPriceRange(int minPrice, int maxPrice);

    List<Car> filterCarsByYearRange(int minYear, int maxYear);

}