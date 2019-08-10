package service.services;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class GetDate {
    public String getDate(){
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year  = localDate.getYear();
        int month = localDate.getMonthValue();
        int day   = localDate.getDayOfMonth();
        return String.valueOf(day)+"."+String.valueOf(month)+"."+String.valueOf(year);
    }
}
