package com.company.Entities;

public class Engineer extends Angajat{

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

    public Engineer(String firstname, String lastname, String nr_tel, int salary, String university, String specialization) {
        super(firstname, lastname, nr_tel, salary);
        this.university = university;
        this.specialization = specialization;
    }

    @Override
    public int getSalaryWithCommission() {
        return this.salary;
    }
}
