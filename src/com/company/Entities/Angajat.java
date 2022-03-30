package com.company.Entities;

public abstract class Angajat {

    String firstname;
    String lastname;
    String nr_tel;
    int salary;

    public Angajat(String firstname, String lastname, String nr_tel, int salary) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.nr_tel = nr_tel;
        this.salary = salary;
    }

    public Angajat() {
    }

    public String getfirstname() {
        return firstname;
    }

    public void setfirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getlastname() {
        return lastname;
    }

    public void setlastname(String lastname) {
        this.lastname = lastname;
    }

    public String getNr_tel() {
        return nr_tel;
    }

    public void setNr_tel(String nr_tel) {
        this.nr_tel = nr_tel;
    }

    public int getsalary() {
        return salary;
    }

    public void setsalary(int salary) {
        this.salary = salary;
    }

    public abstract int getSalaryWithCommission();
}
