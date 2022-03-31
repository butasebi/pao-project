package com.company;

import com.company.Entities.AutoService;
import com.company.Services.ServiceAutoServices;
import com.company.Services.ServiceClients;

import java.util.Scanner;

import static com.company.Entities.Reader.cinInt;

public class Main {

    public static void main(String[] args) {

        ServiceAutoServices database = null;
        int op, nrAutoServices;
        while(true)
        {
            if(ServiceAutoServices.getStatus().equals("Not created"))System.out.println("Press 1 to create the autoservices database!");
            else
            {
                System.out.println("Press 1 to replace the autoservices database!");
                System.out.println("Press 2 to print the autoservices database details!");
            }
            System.out.println("Press 10 to quit!");

            op = cinInt();
            if(op == 1)
            {
                database = ServiceAutoServices.getInstance(ServiceAutoServices.readAutoServices());
            }
            if(op == 2)
            {
                System.out.println(database.toString());
            }
            if(op == 10)
            {
                break;
            }
        }
    }
}
