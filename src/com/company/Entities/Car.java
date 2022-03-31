package com.company.Entities;

public class Car {
    String brand;
    String model;
    String carPlate;

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

    public Car() {
    }

    public Car(String brand, String model, String carPlate) {
        this.brand = brand;
        this.model = model;
        this.carPlate = carPlate;
    }
}
