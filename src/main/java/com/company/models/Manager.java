package com.company.models;

public class Manager {
    private String firstName;
    private String lastName;
    private String nrTel;
    private int salary;

    private Boolean founder;

    public Manager(String firstName, String lastName, String nrTel, int salary, Boolean founder) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nrTel = nrTel;
        this.salary = salary;
        this.founder = founder;
    }

    public Boolean getFounder() {
        return founder;
    }

    public void setFounder(Boolean founder) {
        this.founder = founder;
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

    public String getNrTel() {
        return nrTel;
    }

    public void setNrTel(String nrTel) {
        this.nrTel = nrTel;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
