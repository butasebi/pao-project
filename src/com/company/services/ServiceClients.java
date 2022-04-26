package com.company.services;

import com.company.entities.Client;

import java.util.ArrayList;
import java.util.Collections;

public class ServiceClients {
    private static ServiceClients singleton = null;
    private ArrayList clients;


    private ServiceClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public static ServiceClients getInstance(ArrayList<Client> clients)
    {
        if(singleton == null)
            singleton = new ServiceClients(clients);
        return singleton;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
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
        for(Object o : this.clients) {
            Client client = (Client) o;
            mesaj += "  " + client.getFirstName() + "\n";
        }
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
