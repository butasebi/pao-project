package com.company.Entities;

public abstract class Employee {

    String firstName;
    String lastName;
    String nrTel;
    int salary;

    public Employee(String firstName, String lastName, String nrTel, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nrTel = nrTel;
        this.salary = salary;
    }

    public Employee() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setfirstName(String firstName) {
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

    public void setNrTel(String nr_tel) {
        this.nrTel = nr_tel;
    }

    public int getsalary() {
        return salary;
    }

    public void setsalary(int salary) {
        this.salary = salary;
    }

    public abstract int getSalaryWithCommission();
}
