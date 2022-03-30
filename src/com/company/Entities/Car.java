package com.company.Entities;

public class Car {
    String brand;
    String model;
    String car_plate;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCar_plate() {
        return car_plate;
    }

    public void setCar_plate(String car_plate) {
        this.car_plate = car_plate;
    }

    public Car() {
    }

    public Car(String brand, String model, String car_plate) {
        this.brand = brand;
        this.model = model;
        this.car_plate = car_plate;
    }
}
