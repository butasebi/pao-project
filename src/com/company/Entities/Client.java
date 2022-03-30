package com.company.Entities;

public class Client {
    String firstname;
    String lastname;
    Car car;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Client() {
    }

    public Client(String firstname, String lastname, Car car) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.car = car;
    }
}
