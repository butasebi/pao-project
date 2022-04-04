package com.company.entities;

import java.util.ArrayList;

public class AutoService implements Comparable<AutoService> {
    private String address;
    private String name;
    private ArrayList employees;
    private ArrayList workspaces;

    public AutoService(String address, String name, ArrayList employees, ArrayList workspaces) {
        this.address = address;
        this.name = name;
        this.employees = employees;
        this.workspaces = workspaces;
    }

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

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList employees) {
        this.employees = employees;
    }

    public ArrayList getWorkspaces() {
        return workspaces;
    }

    public void setWorkspaces(ArrayList workspaces) {
        this.workspaces = workspaces;
    }


    @Override
    public int compareTo(AutoService o) {
        if(this.name.equals(o.name))
            return this.address.compareTo(o.address);
        return this.name.compareTo(o.name);
    }
}
