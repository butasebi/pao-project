package com.company.Entities;

public class Manager extends Angajat{

    Boolean founder;

    public Boolean getFounder() {
        return founder;
    }

    public void setFounder(Boolean founder) {
        this.founder = founder;
    }

    public Manager() {
    }

    public Manager(String nume, String prenume, String nr_tel, int salariu, Boolean founder) {
        super(nume, prenume, nr_tel, salariu);
        this.founder = founder;
    }

    @Override
    public int getSalaryWithCommission() {
        if(this.founder == true)
            return this.salary;
        return this.salary;
    }
}
