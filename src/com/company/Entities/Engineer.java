package com.company.Entities;

public class Engineer extends Employee {

    String university;
    String specialization;

    public Engineer() {
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Engineer(String firstName, String lastName, String nrTel, int salary, String university, String specialization) {
        super(firstName, lastName, nrTel, salary);
        this.university = university;
        this.specialization = specialization;
    }

    @Override
    public int getSalaryWithCommission() {
        return this.salary;
    }
}
