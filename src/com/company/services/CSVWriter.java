package com.company.services;

import com.company.entities.*;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CSVWriter {

    public static <T> void write(List<T> listToWrite, String fileName, Class<T> classToWrite) {
        try
        {
            new File(fileName).createNewFile();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        FileWriter f = null;
        try
        {
            f = new FileWriter(fileName);
            var rowFields = classToWrite.getDeclaredFields();

            List<String> rowNames = new ArrayList<>();

            for (var field : rowFields)
            {
                rowNames.add(field.toString().replaceAll(".*\\.", ""));
            }

            //Writing the names of each row
            for (int j = 0; j < rowNames.size(); j ++)
            {
                Field columnName = null;
                Class cls = listToWrite.get(0).getClass();

                try
                {
                    columnName = cls.getDeclaredField(rowNames.get(j));
                }
                catch (Exception e)
                {
                }

                if(!columnName.getType().getName().startsWith("java.lang")){
                    rowNames.remove(j);
                    j --;
                    continue;
                }

                if(j != 0)f.write(", ");
                f.write(rowNames.get(j));
            }
            f.write('\n');


            //Writing the rows themselves
            for (var i : listToWrite)
            {
                for (int j = 0; j < rowNames.size(); j ++)
                {
                    Field fieldToWrite = null;
                    Class cls = i.getClass();

                    try
                    {
                        fieldToWrite = cls.getDeclaredField(rowNames.get(j));
                    }
                    catch (Exception e)
                    {
                    }

                    if(!fieldToWrite.getType().getName().startsWith("java.lang"))
                        continue;

                    if (fieldToWrite == null)
                    {
                        fieldToWrite = cls.getSuperclass().getDeclaredField(rowNames.get(j));
                    }

                    fieldToWrite.setAccessible(true);

                    if(Collection.class.isAssignableFrom(fieldToWrite.getType()))
                    {
                        f.write("[]");  //if a column is a list we will just leave the 2 brackets as symbol
                    }
                    else
                    {
                        f.write(fieldToWrite.get(i).toString());
                    }
                    if (j != rowNames.size() - 1)
                    {
                        f.write(", ");
                    }

                }
                f.write('\n');
            }


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            f.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

}
