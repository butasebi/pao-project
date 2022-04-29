package com.company;

import com.company.entities.*;
import com.company.services.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.List;

import static com.company.services.Reader.cinInt;

public class Main {

    public static void main(String[] args) {

        //the auditFilePath keeps the location where we will keep a logbook of the program activity
        String auditFilePath = "src/com/company/data/logbook.csv";

        //writeAudit is a function that takes 3 arguments: the path where to write, the message to write and a boolean value
        //that retains weather our message is a separation message (example line 39) or a valid message like the one below
        //(the purpose is to print a counter correctly for each message)
        ServiceAudit.writeAudit(auditFilePath, "Program just started", true);

        ServiceAutoServices autoServicesDatabase = null;
        ServiceClients clientsDatabase = null;

        //Loading the autoservices database from the CSV file
        //The initial database will have 1 service with 5 employees and 10 workspaces: 5 tunnels and 5 elevators
        autoServicesDatabase = ServiceAutoServices.getInstance(ServiceAutoServices.readAutoServicesFromCSV());
        CSVWriter.writeAutoServiceCSV(autoServicesDatabase.getServices(), "src/com/company/data/autoServiceWrite.csv");
        ServiceAudit.writeAudit(auditFilePath, "Loaded the autoservices database from autoServiceWrite.csv", true);

        //Loading the client database from the CSV file
        //The initial client database will have 5 clients
        clientsDatabase = ServiceClients.getInstance(ServiceClients.readClientsFromCSV());
        CSVWriter.writeClientCSV(clientsDatabase.getClients(), "src/com/company/data/clientsWrite.csv");
        ServiceAudit.writeAudit(auditFilePath, "Loaded the client database from client.csv", true);
        ServiceAudit.writeAudit(auditFilePath, "--------------------------------------", false);

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
            if(op == 1)
            {
                ServiceAudit.writeAudit(auditFilePath, "Replacing autoservices database\n The old database:", true);
                ServiceAudit.writeAudit(auditFilePath, autoServicesDatabase.toString(), false);

                List<AutoService> aux = ServiceAutoServices.readAutoServices();
                autoServicesDatabase.setServices(aux);

                ServiceAudit.writeAudit(auditFilePath, "Autoservices database replaced successfully\n The new database:", false);
                ServiceAudit.writeAudit(auditFilePath, autoServicesDatabase.toString(), false);
                ServiceAudit.writeAudit(auditFilePath, "--------------------------------------", false);
            }
            else if(op == 2)
            {
                System.out.println(autoServicesDatabase.toString());

                ServiceAudit.writeAudit(auditFilePath, "Autoservices database printed to console\n Output:", true);
                ServiceAudit.writeAudit(auditFilePath, autoServicesDatabase.toString(), false);
                ServiceAudit.writeAudit(auditFilePath, "--------------------------------------", false);
            }
            else if(op == 3)
            {
                ServiceAudit.writeAudit(auditFilePath, "Adding a new autoservice to database\n The old database:\n", true);
                ServiceAudit.writeAudit(auditFilePath, autoServicesDatabase.toString() + "\n", false);

                autoServicesDatabase.AddServiceAuto();

                //keeping the actualised database in the autoServiceWrite.csv file
                CSVWriter.writeAutoServiceCSV(autoServicesDatabase.getServices(), "src/com/company/data/autoServiceWrite.csv");

                ServiceAudit.writeAudit(auditFilePath, "New service added successfully\n The new database:\n", false);
                ServiceAudit.writeAudit(auditFilePath, autoServicesDatabase.toString() + "\n", false);
                ServiceAudit.writeAudit(auditFilePath, "--------------------------------------", false);
            }
            else if(op == 4)
            {
                ServiceAudit.writeAudit(auditFilePath, "Sorting autoservice database\n The old database:", true);
                ServiceAudit.writeAudit(auditFilePath, autoServicesDatabase.toString() + "\n", false);

                autoServicesDatabase.SortListOfAutoServices();
                System.out.println("List sorted successfully!");

                ServiceAudit.writeAudit(auditFilePath, "Sort successful\n The new database:", false);
                ServiceAudit.writeAudit(auditFilePath, autoServicesDatabase.toString() + "\n", false);
                ServiceAudit.writeAudit(auditFilePath, "--------------------------------------", false);
            }
            else if(op == 5)
            {
                System.out.println(clientsDatabase.toString());

                ServiceAudit.writeAudit(auditFilePath, "Clients database printed to console\n Output:", true);
                ServiceAudit.writeAudit(auditFilePath, clientsDatabase.toString(), false);
                ServiceAudit.writeAudit(auditFilePath, "--------------------------------------", false);
            }
            else if(op == 6)
            {

                ServiceAudit.writeAudit(auditFilePath, "Adding a new client to database\n The old database:\n", true);
                ServiceAudit.writeAudit(auditFilePath, clientsDatabase.toString() + "\n", false);

                clientsDatabase.AddClient();

                //keeping the actualised database in the clientsWrite.csv file
                CSVWriter.writeClientCSV(clientsDatabase.getClients(), "src/com/company/data/clientsWrite.csv");

                ServiceAudit.writeAudit(auditFilePath, "New client added successfully\n The new database:\n", false);
                ServiceAudit.writeAudit(auditFilePath, clientsDatabase.toString() + "\n", false);
                ServiceAudit.writeAudit(auditFilePath, "--------------------------------------", false);
            }
            else if(op == 7)
            {
                List<String> listForLog = new ArrayList<String>();

                clientsDatabase.getClients().stream().
                        sorted((n1, n2) -> n1.getCar().getBrand().compareTo(n2.getCar().getBrand())).
                        forEach((n) -> listForLog.add(n.getCar().getBrand()));

                ServiceAudit.writeAudit(auditFilePath, "Priting the car brands of the clients from the database sorted alphabetically\n Output:\n", true);
                ServiceAudit.writeAudit(auditFilePath, listForLog.toString().replace("[", " ").
                        replace("]", "").replace(",", "\n") + "\n", false);

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

                ServiceAudit.writeAudit(auditFilePath, "Priting the names of the autoservices from the database sorted alphabetically\n Output:\n", true);
                ServiceAudit.writeAudit(auditFilePath, listForLog.toString().replace("[", " ").
                        replace("]", "").replace(",", "\n") + "\n", false);

                autoServicesDatabase.getServices().stream().
                        sorted((n1, n2) -> n1.getName().compareTo(n2.getName())).
                        forEach((n) -> System.out.println(n.getName()));
            }
            else if(op == 10)
            {
                ServiceAudit.writeAudit(auditFilePath, "Program finished running", true);
                ServiceAudit.writeAudit(auditFilePath, "\n--------------------------------------\n-----------------END------------------\n--------------------------------------\n", false);

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
