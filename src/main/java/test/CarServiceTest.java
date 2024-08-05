package test;

import data.repositories.CarRepository;
import domain.entities.Car;
import domain.entities.CarCondition;
import domain.utils.AuditService;
import org.junit.Before;
import org.junit.Test;
import service.CarService;
import service.impl.CarServiceImpl;

import static org.junit.Assert.*;

public class CarServiceTest {
    private CarService carService;

    @Before
    public void setUp() {
        carService = new CarServiceImpl(new CarRepository(), new AuditService());
    }

    @Test
    public void testAddCar() {
        Car car = new Car("BMW", "M5", 2020, 50000, CarCondition.NEW);
        carService.addCar(car);
        assertNotNull(carService.getCar(car.getCarId()));
    }

    @Test
    public void testUpdateCar() {
        Car car = new Car("BMW", "M5", 2020, 50000, CarCondition.NEW);
        carService.addCar(car);
        car.setPrice(55000);
        carService.updateCar(car);
        assertEquals(55000, carService.getCar(car.getCarId()).getPrice());
    }

    @Test
    public void testDeleteCar() {
        Car car = new Car("BMW", "M5", 2020, 50000, CarCondition.NEW);
        carService.addCar(car);
        carService.deleteCar(car.getCarId());
        assertNull(carService.getCar(car.getCarId()));
    }
}