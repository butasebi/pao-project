package com.company.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class ServiceAudit {
    private static int lineNumber = 1;

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
