package com.company.entities;

import java.util.List;

public class AutoService implements Comparable<AutoService> {
    private String address;
    private String name;
    private List employees;
    private List workspaces;

    public AutoService(String address, String name, List employees, List workspaces) {
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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List employees) {
        this.employees = employees;
    }

    public List getWorkspaces() {
        return workspaces;
    }

    public void setWorkspaces(List workspaces) {
        this.workspaces = workspaces;
    }


    @Override
    public int compareTo(AutoService o) {
        if(this.name.equals(o.name))
            return this.address.compareTo(o.address);
        return this.name.compareTo(o.name);
    }
}
