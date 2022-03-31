package com.company.Services;

import com.company.Entities.*;

import java.util.Collections;
import java.util.Vector;

import static com.company.Entities.Reader.cinInt;
import static com.company.Entities.Reader.cinString;

public class ServiceAutoServices {
    static ServiceAutoServices singleton = null;
    static String status = "Not created";
    Vector<AutoService> services;

    public Vector<AutoService> getServices() {
        return services;
    }

    public void setServices(Vector<AutoService> services) {
        this.services = services;
    }

    public static String getStatus() {
        return status;
    }

    public static ServiceAutoServices getInstance(Vector<AutoService> services)
    {
        status = "Created";
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
        for(AutoService service : this.services) {
            mesaj += "  " + service.getName() + "\n";
            mesaj += "  The employees of " + service.getName() + " are:\n";
            for(Employee employee : service.getEmployees())
                mesaj += "      " + employee.getFirstName() + " " + employee.getLastName() + "\n";
        }
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

    public void sortAutoServicesAlphabetically()
    {
        Collections.sort(services);
    }

    public static Vector<AutoService> readAutoServices()
    {
        Vector<AutoService> auxVec = new Vector<AutoService> ();
        System.out.println("Introduce the number of autoservices the database will have:");

        int nrAutoServices = cinInt();

        for(int i = 1; i <= nrAutoServices; i ++)
        {
            String name, address;
            int nrEmployees, nrWorkspaces;
            System.out.println("Introduce the name of the autoservice " + i + ":");
            name = cinString();

            System.out.println("Introduce the address of " + name + ":");
            address = cinString();

            System.out.println("Introduce how many employees will " + name + " have:");

            nrEmployees = cinInt();
            Vector <Employee> listEmp = new Vector <Employee>();
            for(int j = 1; j <= nrEmployees; j ++)
            {
                String firstName;
                String lastName;
                String nrTel;
                int salary;
                String type;

                System.out.println("Introduce the firstname of the employee " + j + ":");
                firstName = cinString();

                System.out.println("Introduce the lastname of the employee " + j + ":");
                lastName = cinString();

                System.out.println("Introduce the telephone number of " + firstName + " " + lastName + ":");
                nrTel = cinString();

                System.out.println("Introduce the salary of " + firstName + " " + lastName + ":");
                salary = cinInt();

                System.out.println("Introduce the function of " + firstName + " " + lastName + "(Manager/Enginner/Mecanic):");
                type = cinString();
                System.out.println(type);
                if(type.equals("Manager"))
                {
                    int founder;
                    System.out.println("Is he/she the founder of the company? (1 for yes, 0 for no):");
                    founder = cinInt();
                    Boolean founderBool;
                    if(founder == 0)founderBool = false;
                    else founderBool = true;

                    Manager auxEmp = new Manager(firstName, lastName, nrTel, salary, founderBool);
                    listEmp.add(auxEmp);
                }
                else if(type.equals("Enginner"))
                {
                    String university;
                    String specialization;

                    System.out.println("Introduce the university the employee pursued:");
                    university = cinString();

                    System.out.println("Introduce the specialization at the university:");
                    specialization = cinString();

                    Engineer auxEmp = new Engineer(firstName, lastName, nrTel, salary, university, specialization);
                    listEmp.add(auxEmp);
                }
                else if(type.equals("Mecanic"))
                {
                    String specialization;

                    System.out.println("Introduce the specialization at the university:");
                    specialization = cinString();

                    Mecanic auxEmp = new Mecanic(firstName, lastName, nrTel, salary, specialization);
                    listEmp.add(auxEmp);
                }
                else
                {
                    System.out.println("Introduce a valid choice!");
                    i --;
                }
            }

            System.out.println("Introduce how many workspaces will " + name + " have:");
            nrWorkspaces = cinInt();
            Vector <Workspace> listWorkspaces = new Vector <Workspace>();
            for(int j = 1; j <= nrWorkspaces; j ++)
            {
                String type;

                System.out.println("Introduce the type of the workspace " + j + "(Tunnel/Elevator):");
                type = cinString();
                if(type.equals("Tunnel"))
                {
                    int depth;
                    int width;
                    int length;
                    int covered;
                    Boolean coveredBool;

                    System.out.println("Introduce the depth of the tunnel " + j + ":");
                    depth = cinInt();

                    System.out.println("Introduce the width of the tunnel " + j + ":");
                    width = cinInt();

                    System.out.println("Introduce the length of the tunnel " + j + ":");
                    length = cinInt();

                    System.out.println("Is he the tunnel covered? (1 for yes, 0 for no):");
                    covered = cinInt();
                    if(covered == 0)coveredBool = false;
                    else coveredBool = true;

                    Tunnel auxWorkspace = new Tunnel(depth, width, length, coveredBool);
                    listWorkspaces.add(auxWorkspace);
                }
                else if(type.equals("Elevator"))
                {
                    int maxHeight;
                    int width;
                    int length;

                    System.out.println("Introduce the depth of the tunnel " + j + ":");
                    maxHeight = cinInt();

                    System.out.println("Introduce the width of the tunnel " + j + ":");
                    width = cinInt();

                    System.out.println("Introduce the length of the tunnel " + j + ":");
                    length = cinInt();


                    Elevator auxWorkspace = new Elevator(maxHeight, width, length);
                    listWorkspaces.add(auxWorkspace);
                }
                else
                {
                    System.out.println("Introduce a valid choice!");
                    j --;
                }
            }

            AutoService auxAutoService = new AutoService(address, name, listEmp, listWorkspaces);
            auxVec.add(auxAutoService);
        }
        return auxVec;
    }
}
