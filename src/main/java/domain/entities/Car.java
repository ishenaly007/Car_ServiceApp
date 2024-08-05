package domain.entities;

public class Car {
    private static int idCounter = 0;
    private final int carId;
    private String brand;
    private String model;
    private int price;
    private int year;
    private CarCondition condition;

    public Car(String brand, String model, int price, int year, CarCondition condition) {
        this.carId = ++idCounter;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.year = year;
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "Car{" +
               "carId=" + carId +
               ", brand='" + brand + '\'' +
               ", model='" + model + '\'' +
               ", price=" + price +
               ", year=" + year +
               ", condition=" + condition +
               '}';
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCarId() {
        return carId;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setCondition(CarCondition condition) {
        this.condition = condition;
    }

    public CarCondition getCondition() {
        return condition;
    }

    public String getBrand() {
        return brand;
    }

    public int getPrice() {
        return price;
    }

    public int getYear() {
        return year;
    }
}