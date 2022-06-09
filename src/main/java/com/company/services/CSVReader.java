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

    public static <T> List<T> read(String fileName, Class<T> classToRead)
    {
        BufferedReader fileBuffer = null;
        try
        {
            fileBuffer = new BufferedReader(new FileReader(fileName));
            List result = new ArrayList<T>();

            String line;
            List<String> rowNames = new ArrayList<String>();

            line = fileBuffer.readLine();

            if (line == null) {
                return result;
            }
            String[] row = line.strip().split(",");
            for(int i = 0; i < row.length; i ++)
            {
                row[i] = row[i].strip();
                rowNames.add(row[i]);
            }

            while ((line = fileBuffer.readLine()) != null)
            {
                row = line.strip().split(",");
                var instanceOfClass = classToRead.getConstructor().newInstance();

                for (int i = 0; i < rowNames.size(); i++)
                {
                    Class classInstance = instanceOfClass.getClass();
                    Field fieldToRead = null;

                    try
                    {
                        fieldToRead = classInstance.getDeclaredField(rowNames.get(i));
                    }
                    catch (Exception e)
                    {

                    }

                    if (fieldToRead == null)
                    {
                        fieldToRead = classInstance.getSuperclass().getDeclaredField(rowNames.get(i));
                    }

                    fieldToRead.setAccessible(true);

                    if (fieldToRead.getType() == String.class)
                    {
                        fieldToRead.set(instanceOfClass, row[i].strip());
                    }
                    else if (fieldToRead.getType() == int.class)
                    {
                        fieldToRead.set(instanceOfClass, Integer.parseInt(row[i].strip()));
                    }
                    else if (fieldToRead.getType() == float.class)
                    {
                        fieldToRead.set(instanceOfClass, Float.parseFloat(row[i].strip()));
                    }
                    else if (fieldToRead.getType() == boolean.class)
                    {
                        fieldToRead.set(instanceOfClass, Boolean.parseBoolean(row[i].strip()));
                    }
                }
                result.add(instanceOfClass);
            }
            return result;

        } catch (Exception e)
        {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
