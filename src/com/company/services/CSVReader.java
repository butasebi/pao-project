package com.company.services;

import com.company.entities.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.List;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;

public class CSVReader {

    //Autoservice reader
    public static List<AutoService> readAutoServiceCSV(String fileName, List<Employee> listEmployees, List<Workspace>listWorkspaces)
    {
        List<AutoService> result = new ArrayList<AutoService>();
        BufferedReader reader = null;
        String line = "";
        String separator = ",";

        try {
            reader = new BufferedReader(new FileReader(fileName));
            boolean header = true;
            while((line = reader.readLine()) != null)
            {
                if(header == true)
                {
                    header = false;
                    continue;
                }

                if(line.startsWith("sep="))
                {
                    separator = line.substring(4);
                    continue;
                }
                String [] row = line.split(separator);

                AutoService newObject = new AutoService(row[0].strip(), row[1].strip(), listEmployees, listWorkspaces);
                result.add(newObject);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally{
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    //Client reader
    public static List<Client> readClientCSV(String fileName)
    {
        List<Client> result = new ArrayList<Client>();
        BufferedReader reader = null;
        String line = "";
        String separator = ",";

        try {
            reader = new BufferedReader(new FileReader(fileName));
            boolean header = true;
            while((line = reader.readLine()) != null)
            {
                if(header == true)
                {
                    header = false;
                    continue;
                }

                if(line.startsWith("sep="))
                {
                    separator = line.substring(4);
                    continue;
                }
                String [] row = line.split(separator);

                Client newObject = new Client(row[0].strip(), row[1].strip(), new Car(row[2].strip(), row[3].strip(), row[4].strip()));
                result.add(newObject);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally{
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    //Elevator reader
    public static List<Elevator> readElevatorCSV(String fileName)
    {
        List<Elevator> result = new ArrayList<Elevator>();

        BufferedReader reader = null;
        String line = "";
        String separator = ",";

        try {
            reader = new BufferedReader(new FileReader(fileName));
            boolean header = true;
            while((line = reader.readLine()) != null)
            {
                if(header == true)
                {
                    header = false;
                    continue;
                }

                if(line.startsWith("sep="))
                {
                    separator = line.substring(4);
                    continue;
                }
                String [] row = line.split(separator);

                Elevator newObject = new Elevator(parseInt(row[0].strip()), parseInt(row[1].strip()), parseInt(row[2].strip()));
                result.add(newObject);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally{
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    //Employee reader
    public static List<Employee> readEmployeeCSV(String fileName)
    {
        List<Employee> result = new ArrayList<Employee>();

        BufferedReader reader = null;
        String line = "";
        String separator = ",";
        try {
            reader = new BufferedReader(new FileReader(fileName));
            boolean header = true;
            while((line = reader.readLine()) != null)
            {
                if(header == true)
                {
                    header = false;
                    continue;
                }

                if(line.startsWith("sep="))
                {
                    separator = line.substring(4);
                    continue;
                }
                String [] row = line.split(separator);

                Employee newObject;
                if(row[4].strip().equals("Engineer"))
                {
                    newObject = new Engineer(row[0].strip(), row[1].strip(), row[2].strip(), parseInt(row[3].strip()), row[5].strip(), row[6].strip());
                }
                else if(row[4].strip().equals("Manager"))
                {
                    newObject = new Manager(row[0].strip(), row[1].strip(), row[2].strip(), parseInt(row[3].strip()), parseBoolean(row[5].strip()));
                }
                else
                {
                    newObject = new Mecanic(row[0].strip(), row[1].strip(), row[2].strip(), parseInt(row[3].strip()), row[5].strip());
                }

                result.add(newObject);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally{
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    //Tunnel reader
    public static List<Tunnel> readTunnelCSV(String fileName)
    {
        List<Tunnel> result = new ArrayList<Tunnel>();

        BufferedReader reader = null;
        String line = "";
        String separator = ",";

        try {
            reader = new BufferedReader(new FileReader(fileName));
            boolean header = true;
            while((line = reader.readLine()) != null)
            {
                if(header == true)
                {
                    header = false;
                    continue;
                }
                if(line.startsWith("sep="))
                {
                    separator = line.substring(4);
                    continue;
                }
                String [] row = line.split(separator);

                Tunnel newObject = new Tunnel(parseInt(row[0].strip()), parseInt(row[1].strip()), parseInt(row[2].strip()), parseBoolean(row[3].strip()));

                result.add(newObject);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally{
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
