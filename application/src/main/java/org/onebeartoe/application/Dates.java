package org.onebeartoe.application;

import java.util.Date;

public class Dates 
{
    public static String shortFilesystemDate(Date date) 
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append(date.getYear());
        buffer.append("-");

        int month = date.getMonth() + 1;
        String monthStr;
        if (month < 10) {
            monthStr = "0" + month;
        } else {
            monthStr = String.valueOf(month);
        }
        buffer.append(monthStr);
        buffer.append("-");

        int dateInt = date.getDate();
        String dateStr;
        if (dateInt < 10) {
            dateStr = "0" + dateInt;
        } else {
            dateStr = String.valueOf(dateInt);
        }
        buffer.append(dateStr);

        return buffer.toString();
    }
}
