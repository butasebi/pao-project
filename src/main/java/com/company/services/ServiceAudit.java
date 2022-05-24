package com.company.services;

import com.company.entities.AutoService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;

public class ServiceAudit {

    private static ServiceAudit singleton = null;
    private static int lineNumber = 1;

    private ServiceAudit() {

    }

    //Singleton object getter
    public static ServiceAudit getInstance()
    {
        if(singleton == null)
            singleton = new ServiceAudit();
        return singleton;
    }

    public static int getLineNumber() {
        return lineNumber;
    }

    public static void setLineNumber(int lineNumber) {
        ServiceAudit.lineNumber = lineNumber;
    }

    public static void writeAudit(String fileName, String message, Boolean notBuffer)
    {
        if(notBuffer)
        {
            message = Integer.toString(ServiceAudit.getLineNumber()) + ") " + message;
            ServiceAudit.setLineNumber(ServiceAudit.getLineNumber() + 1);
        }
        try
        {
            File csvFile = new File(fileName);
            PrintWriter out = new PrintWriter(new FileOutputStream(csvFile, true));
            out.println(message);
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
