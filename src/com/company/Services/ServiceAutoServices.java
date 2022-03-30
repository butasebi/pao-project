package com.company.Services;

import com.company.Entities.AutoService;

import java.util.Vector;

public class ServiceAutoServices {
    static ServiceAutoServices singleton = null;
    Vector<AutoService> services;

    public Vector<AutoService> getServices() {
        return services;
    }

    public void setServices(Vector<AutoService> services) {
        this.services = services;
    }

    public static ServiceAutoServices getInstance(Vector<AutoService> services)
    {
        if(singleton == null)
            singleton = new ServiceAutoServices(services);
        return singleton;
    }

    private ServiceAutoServices(Vector<AutoService> services) {
        this.services = services;
    }

    //Create
    public void AddAutoService(AutoService service)
    {
        this.services.add(service);
    }

    //Read
    @Override
    public String toString() {
        String mesaj = "The list of services is made of:";
        for(AutoService service : this.services)
            mesaj += "  " + service.getName() + "\n";
        return mesaj;
    }

    //Update
    public void UpdateAutoService(AutoService service1, AutoService service2)
    {
        this.services.remove(service1);
        this.services.add(service2);
    }

    //Delete
    public void RemoveAutoService(AutoService service)
    {
        this.services.remove(service);
    }
}
