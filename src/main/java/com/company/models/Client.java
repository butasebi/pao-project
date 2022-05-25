package com.company.models;

public class Client {
    private String firstName;
    private String lastName;

    private String carPlate;

    public Client(String firstName, String lastName, String carPlate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.carPlate = carPlate;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }
}
