package com.company.Entities;

public class Mecanic extends Employee {

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    String specialization;

    public Mecanic() {
    }

    public Mecanic(String firstname, String lastname, String nr_tel, int salary, String specialization) {
        super(firstname, lastname, nr_tel, salary);
        this.specialization = specialization;
    }

    @Override
    public int getSalaryWithCommission() {
        return this.salary;
    }
}
