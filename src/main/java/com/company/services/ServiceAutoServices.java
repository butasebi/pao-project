package com.company.services;

import com.company.entities.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import static com.company.services.Reader.cinInt;
import static com.company.services.Reader.cinText;

public class ServiceAutoServices {
    private static ServiceAutoServices singleton = null;
    private static String status = "Not created";
    private List services;

    private ServiceAutoServices(List<AutoService> services) {
        this.services = services;
    }

    //Singleton object getter
    public static ServiceAutoServices getInstance(List<AutoService> services)
    {
        status = "Created";
        if(singleton == null)
            singleton = new ServiceAutoServices(services);
        return singleton;
    }

    public List<AutoService> getServices() {
        return services;
    }

    public void setServices(List<AutoService> services) {
        this.services = services;
    }

    public static String getStatus() {
        return status;
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
        for (Object o : this.services) {
            AutoService service = (AutoService) o;
            mesaj += "  " + service.getName() + "\n";
            mesaj += "  The employees of " + service.getName() + " are:\n";
            for (Employee employee : service.getEmployees())
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

    public void SortListOfAutoServices()
    {
        Collections.sort(services);
    }

    public static List<AutoService> readAutoServices()
    {
        List<AutoService> auxVec = new ArrayList<AutoService>();
        System.out.println("Introduce the number of autoservices the database will have:");

        int nrAutoServices = cinInt();

        for(int i = 1; i <= nrAutoServices; i ++)
        {
            String name, address;
            int nrEmployees, nrWorkspaces;
            System.out.println("Introduce the name of the autoservice " + i + ":");
            name = cinText();

            System.out.println("Introduce the address of " + name + ":");
            address = cinText();

            System.out.println("Introduce how many employees will " + name + " have:");

            nrEmployees = cinInt();
            List <Employee> listEmp = new ArrayList <Employee>();
            for(int j = 1; j <= nrEmployees; j ++)
            {
                String firstName;
                String lastName;
                String nrTel;
                int salary;
                String type;

                System.out.println("Introduce the firstname of the employee " + j + ":");
                firstName = cinText();

                System.out.println("Introduce the lastname of the employee " + j + ":");
                lastName = cinText();

                System.out.println("Introduce the telephone number of " + firstName + " " + lastName + ":");
                nrTel = cinText();

                System.out.println("Introduce the salary of " + firstName + " " + lastName + ":");
                salary = cinInt();

                System.out.println("Introduce the function of " + firstName + " " + lastName + "(Manager/Enginner/Mecanic):");
                type = cinText();
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
                    university = cinText();

                    System.out.println("Introduce the specialization at the university:");
                    specialization = cinText();

                    Engineer auxEmp = new Engineer(firstName, lastName, nrTel, salary, university, specialization);
                    listEmp.add(auxEmp);
                }
                else if(type.equals("Mecanic"))
                {
                    String specialization;

                    System.out.println("Introduce the specialization at the university:");
                    specialization = cinText();

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
            List <Workspace> listWorkspaces = new ArrayList <Workspace>();
            for(int j = 1; j <= nrWorkspaces; j ++)
            {
                String type;

                System.out.println("Introduce the type of the workspace " + j + "(Tunnel/Elevator):");
                type = cinText();
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

    public static List<AutoService> readAutoServicesFromCSV() {

        List <Employee> listEmployees = new ArrayList<>();
        List <Manager> listManager = CSVReader.read("src/main/java/com/company/data/manager.csv", Manager.class);
        List <Mecanic> listMecanic = CSVReader.read("src/main/java/com/company/data/mecanic.csv", Mecanic.class);
        List <Engineer> listEngineer = CSVReader.read("src/main/java/com/company/data/engineer.csv", Engineer.class);

        listEmployees.addAll(listMecanic);
        listEmployees.addAll(listEngineer);
        listEmployees.addAll(listManager);

        List <Workspace> listWorkspaces = new ArrayList <Workspace>();

        List<Elevator> listElevator = CSVReader.read("src/main/java/com/company/data/elevator.csv", Elevator.class);

        listWorkspaces.addAll(listElevator);

        List<Tunnel> listTunnel = CSVReader.read("src/main/java/com/company/data/tunnel.csv", Tunnel.class);
        listWorkspaces.addAll(listTunnel);

        List<AutoService> auxVec = CSVReader.read("src/main/java/com/company/data/autoService.csv", AutoService.class);

        for(int i = 0; i < auxVec.size(); i ++)
        {
            auxVec.get(i).setEmployees(listEmployees.subList(i * (listEmployees.size() / auxVec.size()), (i + 1) * (listEmployees.size() / auxVec.size())));
            auxVec.get(i).setWorkspaces(listWorkspaces.subList(i * (listWorkspaces.size() / auxVec.size()), (i + 1) * (listWorkspaces.size() / auxVec.size())));
        }

        return auxVec;
    }
    public void AddServiceAuto()
    {
        String name, address;
        int nrEmployees, nrWorkspaces;
        System.out.println("Introduce the name of the autoservice:");
        name = cinText();

        System.out.println("Introduce the address of " + name + ":");
        address = cinText();

        System.out.println("Introduce how many employees will " + name + " have:");

        nrEmployees = cinInt();
        List <Employee> listEmp = new ArrayList <Employee>();
        for(int j = 1; j <= nrEmployees; j ++)
        {
            String firstName;
            String lastName;
            String nrTel;
            int salary;
            String type;

            System.out.println("Introduce the firstname of the employee " + j + ":");
            firstName = cinText();

            System.out.println("Introduce the lastname of the employee " + j + ":");
            lastName = cinText();

            System.out.println("Introduce the telephone number of " + firstName + " " + lastName + ":");
            nrTel = cinText();

            System.out.println("Introduce the salary of " + firstName + " " + lastName + ":");
            salary = cinInt();

            System.out.println("Introduce the function of " + firstName + " " + lastName + "(Manager/Enginner/Mecanic):");
            type = cinText();
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
                university = cinText();

                System.out.println("Introduce the specialization at the university:");
                specialization = cinText();

                Engineer auxEmp = new Engineer(firstName, lastName, nrTel, salary, university, specialization);
                listEmp.add(auxEmp);
            }
            else if(type.equals("Mecanic"))
            {
                String specialization;

                System.out.println("Introduce the specialization:");
                specialization = cinText();

                Mecanic auxEmp = new Mecanic(firstName, lastName, nrTel, salary, specialization);
                listEmp.add(auxEmp);
            }
        }

        System.out.println("Introduce how many workspaces will " + name + " have:");
        nrWorkspaces = cinInt();
        List <Workspace> listWorkspaces = new ArrayList <Workspace>();
        for(int j = 1; j <= nrWorkspaces; j ++)
        {
            String type;

            System.out.println("Introduce the type of the workspace " + j + "(Tunnel/Elevator):");
            type = cinText();
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
        this.getServices().add(auxAutoService);
    }
}
