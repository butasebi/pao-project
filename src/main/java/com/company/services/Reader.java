package com.company.services;

import java.util.Scanner;

public class Reader {
    public static int cinInt()
    {
        int n;
        Scanner cin = new Scanner(System.in);
        while(true)
        {
            if (cin.hasNextInt())
            {
                n = cin.nextInt();
                break;
            }
            else {
                cin.nextLine();
                System.out.println("Invalid type of introduced data, expected type: Int");
            }
        }
        return n;
    }

    public static String cinText()
    {
        String n;
        Scanner cin = new Scanner(System.in);

        n = cin.nextLine();
        return n;
    }
}
