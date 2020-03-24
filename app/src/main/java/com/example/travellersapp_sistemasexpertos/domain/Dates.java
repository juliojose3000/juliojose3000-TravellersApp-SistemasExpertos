package com.example.travellersapp_sistemasexpertos.domain;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Dates {

    private int year, month, dayOfMonth;

    private Calendar calendar;

    public Dates(){

        calendar = Calendar.getInstance();

        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH)+1;

        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

    }

    public String getDateOfToday(){
        return year+"-"+month+"-"+dayOfMonth;
    }

    public static String getDateForShowUser(String date){

        String dayName = getDayName(date);

        String[] dateParts = date.split("-");

        String day = dateParts[2];

        String month = getMonthByNum(Integer.parseInt(dateParts[1])-1);

        String year = dateParts[0];

        return dayName+", "+day+" "+month+" "+year;

    }

    public static String getDateOfFirstDayInTheWeek(){

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());

        calendar.add(Calendar.DAY_OF_YEAR, 1);

        int year = calendar.get(Calendar.YEAR);

        int month = calendar.get(Calendar.MONTH)+1;

        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        String date = year+"-"+month+"-"+dayOfMonth;

        String dayName = getDayName(date);

        String monthName = getMonthByNum(month-1);

        return dayName+", "+dayOfMonth+" "+monthName+" "+year;

    }

    public static String getDateOfLastDayInTheWeek(){

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());

        calendar.add(Calendar.DAY_OF_YEAR, 7);

        int year = calendar.get(Calendar.YEAR);

        int month = calendar.get(Calendar.MONTH)+1;

        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        String date = year+"-"+month+"-"+dayOfMonth;

        String dayName = getDayName(date);

        String monthName = getMonthByNum(month-1);

        return dayName+", "+dayOfMonth+" "+monthName+" "+year;

    }


    public static String getDateInAWeek(){

        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.DAY_OF_YEAR, 7);//Lunes

        int year = calendar.get(Calendar.YEAR);

        int month = calendar.get(Calendar.MONTH)+1;

        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        String date = year+"-"+month+"-"+dayOfMonth;

        String dayName = getDayName(date);

        String monthName = getMonthByNum(month-1);

        return dayName+", "+dayOfMonth+" "+monthName+" "+year;

    }

    public static String getDateInAWeek(String dateX){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date dat = null;
        try {
            dat = sdf.parse(dateX);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar. getInstance();

        calendar. setTime(dat);

        calendar.add(Calendar.DAY_OF_YEAR, 7);//Lunes



        int year = calendar.get(Calendar.YEAR);

        int month = calendar.get(Calendar.MONTH)+1;

        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        String date = year+"-"+month+"-"+dayOfMonth;

        String dayName = getDayName(date);

        String monthName = getMonthByNum(month-1);

        return dayName+", "+dayOfMonth+" "+monthName+" "+year;

    }

    public static String getDateInAMonth(String dateX){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date dat = null;
        try {
            dat = sdf.parse(dateX);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar. getInstance();

        calendar. setTime(dat);

        calendar.add(Calendar.MONTH, 1);//Lunes

        int year = calendar.get(Calendar.YEAR);

        int month = calendar.get(Calendar.MONTH)+1;

        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        String date = year+"-"+month+"-"+dayOfMonth;

        String dayName = getDayName(date);

        String monthName = getMonthByNum(month-1);

        return dayName+", "+dayOfMonth+" "+monthName+" "+year;

    }

    public static String getDateInAMonth(){

        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.MONTH, 1);//Lunes

        int year = calendar.get(Calendar.YEAR);

        int month = calendar.get(Calendar.MONTH)+1;

        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        String date = year+"-"+month+"-"+dayOfMonth;

        String dayName = getDayName(date);

        String monthName = getMonthByNum(month-1);

        return dayName+", "+dayOfMonth+" "+monthName+" "+year;

    }


    public static String getMonthByNum(int i){
        String[] months = {"Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dec"};
        return months[i];
    }

    public static String getDateForDB(String date){

        if(date.contains(",")){
            String[] newDate = date.split(",");
            date = newDate[1].substring(1);
        }

        String[] dateParts = date.split(" ");

        String day = dateParts[0];

        String month = getNumByMonth(dateParts[1]);

        String year = dateParts[2];

        return year+"-"+month+"-"+day;
    }

    private static String getNumByMonth(String month){

        String[] months = {"Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dec"};

        for(int i = 0; i< months.length; i++){
            if(months[i].equals(month)){
                return ""+(++i);
            }
        }

        return "";
    }

    public static String getDayName(String inputDate){

        String[] dayNames = {"Lunes","Martes","Miércoles","Jueves", "Viernes", "Sábado", "Domingo"};

        java.util.Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String day = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date);

        if(day.equals("Monday")){
            return dayNames[0];
        }else if(day.equals("Tuesday")){
            return dayNames[1];
        }else if(day.equals("Wednesday")){
            return dayNames[2];
        }else if(day.equals("Thursday")){
            return dayNames[3];
        }else if(day.equals("Friday")){
            return dayNames[4];
        }else if(day.equals("Saturday")){
            return dayNames[5];
        }else if(day.equals("Sunday")){
            return dayNames[6];
        }

        return "";
    }

    public static String getDateOfTheLastDayInTheCurrentMonth(){

        Calendar calendar = Calendar.getInstance();

        //calendar.add(Calendar.DAY_OF_YEAR, noOfDays);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

        int year = calendar.get(Calendar.YEAR);

        int month = calendar.get(Calendar.MONTH)+1;

        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        String date = year+"-"+month+"-"+dayOfMonth;

        String dayName = getDayName(date);

        String monthName = getMonthByNum(month-1);

        return dayName+", "+dayOfMonth+" "+monthName+" "+year;

    }

    public static String getDateOfTheFirstDayInTheCurrentMonth(){

        Calendar calendar = Calendar.getInstance();

        //calendar.add(Calendar.DAY_OF_YEAR, noOfDays);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

        int year = calendar.get(Calendar.YEAR);

        int month = calendar.get(Calendar.MONTH)+1;

        int dayOfMonth = 1;

        String date = year+"-"+month+"-"+dayOfMonth;

        String dayName = getDayName(date);

        String monthName = getMonthByNum(month-1);

        return dayName+", "+dayOfMonth+" "+monthName+" "+year;

    }




}
