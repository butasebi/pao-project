package com.company.entities;

public class Client implements Comparable<Client>{
    private String firstName;
    private String lastName;
    private Car car;

    public Client() {
    }

    public Client(String firstName, String lastName, Car car) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.car = car;
    }

    @Override
    public int compareTo(Client o) {
        if(this.firstName.equals(firstName))
            return this.lastName.compareTo(lastName);
        return this.firstName.compareTo(firstName);
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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
