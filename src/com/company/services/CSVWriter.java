package com.company.services;

import com.company.entities.*;

import java.io.*;
import java.util.List;

public class CSVWriter {

    //AutoService writer
    public static void writeAutoServiceCSV(List<AutoService> autoServices, String fileName) {

        try
        {
            File csvFile = new File(fileName);
            PrintWriter out = new PrintWriter(csvFile);

            //PrintWriter out = new PrintWriter(new FileOutputStream(csvFile, true));
            out.println("adresa, nume");
            for(AutoService autoService : autoServices)
            {
                out.println(autoService.getAddress() + ", " + autoService.getName());
            }
            try
            {
                out.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    //Elevator writer
    public static void writeElevatorCSV(List<Elevator> elevators, String fileName) {

        try
        {
            File csvFile = new File(fileName);
            PrintWriter out = new PrintWriter(csvFile);
            out.println("maxHeight, length, width");
            for(Elevator elevator : elevators)
            {
                out.println(elevator.getMaxHeight() + ", " + elevator.getLength() + ", " +  elevator.getWidth());
            }
            try
            {
                out.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    //Employee writer
    public static void writeEmployeeCSV(List<Employee> employees, String fileName) {

        try
        {
            File csvFile = new File(fileName);
            PrintWriter out = new PrintWriter(csvFile);
            out.println("firstName, lastName, nrTel, salary, type, engineerUniversity/managerFounder/mecanicSpecialization, engineerSpecialization");
            for(Employee employee : employees)
            {
                if(employee.getClass() == Mecanic.class)
                {
                    out.println(employee.getFirstName() + ", " + employee.getLastName() + ", " + employee.getNrTel() + ", " +
                            Integer.toString(employee.getsalary()) + ", Mecanic, " + ((Mecanic) employee).getSpecialization());
                }
                else if(employee.getClass() == Engineer.class)
                {
                    out.println(employee.getFirstName() + ", " + employee.getLastName() + ", " + employee.getNrTel() + ", " +
                            Integer.toString(employee.getsalary()) + ", Engineer, " + ((Engineer) employee).getUniversity() + ", " + ((Engineer) employee).getSpecialization());
                }
                else
                {
                    out.println(employee.getFirstName() + ", " + employee.getLastName() + ", " + employee.getNrTel() + ", " +
                            Integer.toString(employee.getsalary()) + ", Manager, " + ((Manager) employee).getFounder());
                }
            }
            try
            {
                out.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    //Tunnel writer
    public static void writeClientCSV(List<Client> clients, String fileName) {

        try
        {
            File csvFile = new File(fileName);
            PrintWriter out = new PrintWriter(csvFile);
            out.println("firstName, lastName, carBrand, carModel, carPlate");
            for(Client client : clients)
            {
                out.println(client.getFirstName() + ", " + client.getLastName() + ", " +
                        client.getCar().getBrand() + ", " + client.getCar().getModel() + ", " + client.getCar().getCarPlate());
            }
            try
            {
                out.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    //Tunnel writer
    public static void writeTunnelCSV(List<Tunnel> tunnels, String fileName) {

        try
        {
            File csvFile = new File(fileName);
            PrintWriter out = new PrintWriter(csvFile);
            out.println("depth, width, length, covered");
            for(Tunnel tunnel : tunnels)
            {
                out.println(Integer.toString(tunnel.getDepth()) + ", " + Integer.toString(tunnel.getWidth()) + ", " +
                        Integer.toString(tunnel.getLength()) + ", " + Boolean.toString(tunnel.getCovered()));
            }
            try
            {
                out.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
