package com.company;

import com.company.config.DatabaseConfiguration;
import com.company.entities.*;
import com.company.models.*;
import com.company.models.AutoService;
import com.company.models.Client;
import com.company.models.Manager;
import com.company.repository.*;
import com.company.services.*;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import static com.company.services.Reader.cinInt;

public class Main {

    public static void main(String[] args) {

//        CarRepositoryUsingPreparedStatement x = CarRepositoryUsingPreparedStatement.getInstance();
//        x.insertCar(new Car("Volvo", "XC90", "VL01VVV"));
//        CarRepositoryUsingStatement y = CarRepositoryUsingStatement.getInstance();
//        y.displayCar();
//
//        ClientRepositoryUsingStatement xcc = ClientRepositoryUsingStatement.getInstance();
//        xcc.createTable();
//
//        ClientRepositoryUsingPreparedStatement xc = ClientRepositoryUsingPreparedStatement.getInstance();
//        xc.insertClient(new Client("Florinela", "Andronescu", "VL01VVC"));
//
//        ClientRepositoryUsingStatement xccc = ClientRepositoryUsingStatement.getInstance();
//        xccc.displayClient();

//        AutoServiceRepository x = AutoServiceRepository.getInstance();
//        x.createTable();
//        x.insertAutoService(new AutoService("GIGELOMANIA", "adresa"));
//        x.displayAutoService();

//        ManagerRepository x = ManagerRepository.getInstance();
//        x.createTable();
//        x.insertManager(new Manager("Andreea", "Antonescu", "0752952948", 2500, Boolean.TRUE));
//        x.displayManager();

//        CarRepository x = CarRepository.getInstance();
//        x.createTable();
//        x.insertCar(new Car("Volvo", "XC90", "VL01VVF"));
//        x.displayCar();

//        ClientRepository x = ClientRepository.getInstance();
//        x.createTable();
//        x.insertClient(new Client("Florinela", "Andronescu", "VL01VEF"));
//        x.displayClient();

        //the auditFilePath keeps the location where we will keep a logbook of the program activity
        String auditFilePath = "src/main/java/com/company/data/logbook.csv";

        String timestamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());

        //writeAudit is a function that takes 3 arguments: the path where to write, the message to write and a boolean value
        //that retains weather our message is a separation message or a valid message like the one below
        //(the purpose is to print a counter correctly for each message)
        ServiceAudit.writeAudit(auditFilePath, timestamp + " Program just started", true);

        ServiceAutoServices autoServicesDatabase = null;
        ServiceClients clientsDatabase = null;

        //Loading the autoservices database from the CSV file
        //The initial database will have 1 service with 5 employees and 10 workspaces: 5 tunnels and 5 elevators
        autoServicesDatabase = ServiceAutoServices.getInstance(ServiceAutoServices.readAutoServicesFromCSV());

        //Loading the client database from the CSV file
        //The initial client database will have 5 clients
        clientsDatabase = ServiceClients.getInstance(ServiceClients.readClientsFromCSV());

        int op;
        while(true)
        {
            //options after we created the database
            System.out.println("Press 1 to replace the autoservices database!");
            System.out.println("Press 2 to print the autoservices database details!");
            System.out.println("Press 3 to add an autoservice in the autoservices database!");
            System.out.println("Press 4 sort the existing autoservices database by name (at equality of names, it's sorted by address)!");
            System.out.println("Press 5 to print the clients database details!");
            System.out.println("Press 6 to add an in the clients database!");
            System.out.println("Press 7 to print a list with the brands of the cars owned by the clients, sorted alphabetically!");
            System.out.println("Press 8 to print a list with the autoservices names, sorted alphabetically!!");
            System.out.println("Press 10 to quit the program!");

            op = cinInt();

            // current time for the logbook
            timestamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());

            if(op == 1)
            {
                ServiceAudit.writeAudit(auditFilePath, timestamp + " Replaced autoservices database\n", true);

                List<com.company.entities.AutoService> aux = ServiceAutoServices.readAutoServices();
                autoServicesDatabase.setServices(aux);

            }
            else if(op == 2)
            {
                System.out.println(autoServicesDatabase.toString());

                ServiceAudit.writeAudit(auditFilePath, timestamp + " Autoservices database printed to console\n", true);
            }
            else if(op == 3)
            {
                ServiceAudit.writeAudit(auditFilePath, timestamp + " Added a new autoservice to database\n", true);

                autoServicesDatabase.AddServiceAuto();

                //keeping the actualised database in the autoService.csv file
                CSVWriter.write(autoServicesDatabase.getServices(), "src/main/java/com/company/data/autoService.csv", com.company.entities.AutoService.class);
            }
            else if(op == 4)
            {
                ServiceAudit.writeAudit(auditFilePath, timestamp + " Sorted autoservice database\n The old database:", true);

                autoServicesDatabase.SortListOfAutoServices();
                System.out.println("List sorted successfully!");

            }
            else if(op == 5)
            {
                System.out.println(clientsDatabase.toString());

                ServiceAudit.writeAudit(auditFilePath, timestamp + " Clients database printed to console\n", true);
            }
            else if(op == 6)
            {

                ServiceAudit.writeAudit(auditFilePath, timestamp + " Added a new client to database\n", true);

                clientsDatabase.AddClient();

                //keeping the actualised database in the client.csv file
                CSVWriter.write(clientsDatabase.getClients(), "src/com/company/data/client.csv", com.company.entities.Client.class);

                //auxListofCars, used to extract the cars for adding them to the CSV
                List<Car> auxListOfCars = new ArrayList<Car>();
                clientsDatabase.getClients().stream().forEach((n) -> auxListOfCars.add(n.getCar()));

                CSVWriter.write(auxListOfCars, "src/com/company/data/car.csv", Car.class);

            }
            else if(op == 7)
            {
                List<String> listForLog = new ArrayList<String>();

                clientsDatabase.getClients().stream().
                        sorted((n1, n2) -> n1.getCar().getBrand().compareTo(n2.getCar().getBrand())).
                        forEach((n) -> listForLog.add(n.getCar().getBrand()));

                ServiceAudit.writeAudit(auditFilePath, timestamp + " Printed the car brands of the clients from the database sorted alphabetically\n", true);

                clientsDatabase.getClients().stream().
                        sorted((n1, n2) -> n1.getCar().getBrand().compareTo(n2.getCar().getBrand())).
                        forEach((n) -> System.out.println(n.getCar().getBrand()));
            }
            else if(op == 8)
            {
                List<String> listForLog = new ArrayList<String>();
                autoServicesDatabase.getServices().stream().
                        sorted((n1, n2) -> n1.getName().compareTo(n2.getName())).
                        forEach((n) -> listForLog.add(n.getName()));

                ServiceAudit.writeAudit(auditFilePath, timestamp + " Printed the names of the autoservices from the database sorted alphabetically\n", true);

                autoServicesDatabase.getServices().stream().
                        sorted((n1, n2) -> n1.getName().compareTo(n2.getName())).
                        forEach((n) -> System.out.println(n.getName()));
            }
            else if(op == 10)
            {
                ServiceAudit.writeAudit(auditFilePath, timestamp + " Program terminated", true);
                ServiceAudit.writeAudit(auditFilePath, "\n--------------------------------------\n-----------------END------------------\n--------------------------------------\n", false);

                DatabaseConfiguration.closeDatabaseConnection();
                break;
            }
            else
            {
                System.out.println("Invalid command, try again!");
                continue;
            }
        }
    }
}
