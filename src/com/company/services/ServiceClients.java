package com.company.services;

import com.company.entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import static com.company.services.Reader.cinInt;
import static com.company.services.Reader.cinText;

public class ServiceClients {
    private static ServiceClients singleton = null;
    private static String status = "Not created";
    private List clients;

    //Constructor
    private ServiceClients(List<Client> clients) {
        this.clients = clients;
    }

    //Getters and setters
    public static String getStatus() {
        return status;
    }

    public static void setStatus(String status) {
        ServiceClients.status = status;
    }

    //Singleton object getter
    public static ServiceClients getInstance(List<Client> clients)
    {
        status = "Created";
        if(singleton == null)
            singleton = new ServiceClients(clients);
        return singleton;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
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
        String mesaj = "The list of clients is made of:";
        for(Object o : this.clients) {
            Client client = (Client) o;
            mesaj += "  " + client.getFirstName() + "\n";
        }
        return mesaj;
    }

    public static List<Client> readClientsFromCSV() {

        List<Client> auxClientList = CSVReader.read("src/com/company/data/client.csv", Client.class);
        List<Car> auxCarList = CSVReader.read("src/com/company/data/car.csv", Car.class);
        for(int i = 0; i < auxClientList.size(); i ++)
            auxClientList.get(i).setCar(auxCarList.get(i));
        return auxClientList;
    }

    public void AddClient()
    {
        String firstName, lastName, brand, model, carPlate;
        System.out.println("Introduce the firstname of the client");
        firstName = cinText();

        System.out.println("Introduce the lastname of the client");
        lastName = cinText();

        System.out.println("Information regarding client's car:");

        System.out.println("Introduce the brand of the car");
        brand = cinText();

        System.out.println("Introduce the model of the car");
        model = cinText();

        System.out.println("Introduce the car plate of the car");
        carPlate = cinText();

        Client auxClient = new Client(firstName, lastName, new Car(brand, model, carPlate));
        this.getClients().add(auxClient);
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
