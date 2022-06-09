package com.company.entities;

public class Car {
    private String brand;
    private String model;
    private String carPlate;

    public Car() {
    }

    public Car(String brand, String model, String carPlate) {
        this.brand = brand;
        this.model = model;
        this.carPlate = carPlate;
    }

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

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    @Override
    public String toString() {
        return brand + ", " + model + ", " + carPlate;
    }
}
