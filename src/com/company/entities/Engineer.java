package com.company.entities;

public class Engineer extends Employee {

    private String university;
    private String specialization;

    public Engineer() {
    }

    public Engineer(String firstName, String lastName, String nrTel, int salary, String university, String specialization) {
        super(firstName, lastName, nrTel, salary);
        this.university = university;
        this.specialization = specialization;
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


    @Override
    public int getSalaryWithCommission() {
        return this.getsalary();
    }
}
