package com.company.Entities;

import java.util.Vector;

public class AutoService implements Comparable<AutoService> {
    String address;
    String name;
    Vector<Employee> employees;
    Vector<Workspace> workspaces;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vector<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Vector<Employee> employees) {
        this.employees = employees;
    }

    public Vector<Workspace> getWorkspaces() {
        return workspaces;
    }

    public void setWorkspaces(Vector<Workspace> workspaces) {
        this.workspaces = workspaces;
    }

    public AutoService(String address, String name, Vector<Employee> employees, Vector<Workspace> workspaces) {
        this.address = address;
        this.name = name;
        this.employees = employees;
        this.workspaces = workspaces;
    }


    @Override
    public int compareTo(AutoService o) {
        if(this.name.equals(o.name))
            return this.address.compareTo(o.address);
        return this.name.compareTo(o.name);
    }
}
