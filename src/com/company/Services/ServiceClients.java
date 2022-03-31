package com.company.Services;

import com.company.Entities.AutoService;
import com.company.Entities.Client;

import java.util.Collections;
import java.util.Vector;

public class ServiceClients {
    static ServiceClients singleton = null;
    Vector<Client> clients;

    private ServiceClients(Vector<Client> clients) {
        this.clients = clients;
    }

    public static ServiceClients getInstance(Vector<Client> clients)
    {
        if(singleton == null)
            singleton = new ServiceClients(clients);
        return singleton;
    }

    public Vector<Client> getClients() {
        return clients;
    }

    public void setClients(Vector<Client> clients) {
        this.clients = clients;
    }

    public void sortClientsAlphabetically()
    {
        Collections.sort(clients);
    }

    //Create
    public void addClient(Client client)
    {
        clients.add(client);
    }

    //Read
    @Override
    public String toString() {
        String mesaj = "The list of services is made of:";
        for(Client client : this.clients)
            mesaj += "  " + client.getFirstName() + "\n";
        return mesaj;
    }

    //Update
    public void updateClient(Client client1, Client client2)
    {
        clients.remove(client1);
        clients.add(client2);
    }

    //Delete
    public void removeClient(Client client)
    {
        clients.remove(client);
    }

}
