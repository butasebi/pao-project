package com.company;

import com.company.entities.AutoService;
import com.company.services.ServiceAutoServices;

import java.util.ArrayList;

import static com.company.services.Reader.cinInt;

public class Main {

    public static void main(String[] args) {

        ServiceAutoServices database = null;
        int op;
        while(true)
        {
            if(ServiceAutoServices.getStatus().equals("Not created"))
            {
                //options until we create the database
                System.out.println("Press 1 to create the autoservices database!");
                System.out.println("Press 10 to quit!");

                op = cinInt();
                if(op == 1)
                {
                    database = ServiceAutoServices.getInstance(ServiceAutoServices.readAutoServices());
                }
                else if(op == 10)
                {
                    break;
                }
                else
                {
                    System.out.println("Invalid command, try again!");
                    continue;
                }
            }
            else
            {
                //options after we created the database
                System.out.println("Press 1 to replace the autoservices database!");
                System.out.println("Press 2 to print the autoservices database details!");
                System.out.println("Press 3 to add an autoservice in the autoservices database!");
                System.out.println("Press 4 sort the existing autoservices database by name (at equality of names, it's sorted by address)!");
                System.out.println("Press 10 to quit!");

                op = cinInt();
                if(op == 1)
                {
                    ArrayList<AutoService> aux = ServiceAutoServices.readAutoServices();
                    database.setServices(aux);
                }
                else if(op == 2)
                {
                    System.out.println(database.toString());
                }
                else if(op == 3)
                {
                    database.AddServiceAuto();
                }
                else if(op == 4)
                {
                    database.SortListOfAutoServices();
                    System.out.println("List sorted successfully!");
                }
                else if(op == 10)
                {
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
}
