package com.example.travellersapp_sistemasexpertos.database;

import com.example.travellersapp_sistemasexpertos.domain.Airport;
import com.example.travellersapp_sistemasexpertos.domain.Hotel;
import com.example.travellersapp_sistemasexpertos.domain.TravelPackage;
import com.example.travellersapp_sistemasexpertos.domain.User;

import java.sql.Date;
import java.util.ArrayList;

public class Data {

    public static boolean areValidCredentials(String username, String password){

        if(username.equals("admin") && password.equals("admin")){return true;}

        for (User user: DBHelper.USERS) {

            if(user.getPassword().equals(password) && user.getUsername().equals(username)){
                return true;
            }

        }

        return false;

    }


    public static boolean doesThisUsernameExists(String username){

        for (User user: DBHelper.USERS) {

            if(user.getUsername().equals(username)){
                return true;
            }

        }

        return false;

    }


    public static ArrayList<TravelPackage> getArrayItems(String search, float maxPrice, String category, String userType){

        ArrayList<TravelPackage> listItems = travelToSearch(search, DBHelper.TRAVEL_PACKAGES);

        if(maxPrice>0.0){
            listItems = searchTravelByMaxPrice(maxPrice, listItems);
        }

        if(!category.equals("Cualquier tipo de viaje")){
            listItems = searchTravelByCategory(category, listItems);
        }

        if(!userType.equals("Cualquier tipo de usuario")){
            listItems = searchTravelByUsertype(userType, listItems);
        }


        return listItems;

    }


    private static ArrayList<TravelPackage> travelToSearch(String travelToSearch, ArrayList<TravelPackage> travelPackages){

        ArrayList<TravelPackage> travels = new ArrayList<>();

        for (TravelPackage currentTravel:
                travelPackages) {

            if(containsIgnoreCase(currentTravel.getName(), travelToSearch)){
                travels.add(currentTravel);
            }

        }

        return travels;

    }

    private static ArrayList<TravelPackage> searchTravelByMaxPrice(float maxPrice, ArrayList<TravelPackage> travelPackages){

        ArrayList<TravelPackage> travels = new ArrayList<>();

        for (TravelPackage currentTravel:
                travelPackages) {

            if(currentTravel.getCost()<= maxPrice){
                travels.add(currentTravel);
            }

        }

        return travels;

    }

    private static ArrayList<TravelPackage> searchTravelByCategory(String category, ArrayList<TravelPackage> travelPackages){

        ArrayList<TravelPackage> travels = new ArrayList<>();

        for (TravelPackage currentTravel:
                travelPackages) {

            if(currentTravel.getTypeOfRoute().equals(category)){
                travels.add(currentTravel);
            }

        }

        return travels;

    }


    private static ArrayList<TravelPackage> searchTravelByUsertype(String usertype, ArrayList<TravelPackage> travelPackages){

        ArrayList<TravelPackage> travels = new ArrayList<>();

        for (TravelPackage currentTravel:
                travelPackages) {

            if(currentTravel.getTouristType().equals(usertype)){
                travels.add(currentTravel);
            }

        }

        return travels;

    }


    public static boolean containsIgnoreCase(String src, String what) {
        final int length = what.length();
        if (length == 0)
            return true; // Empty string is contained

        final char firstLo = Character.toLowerCase(what.charAt(0));
        final char firstUp = Character.toUpperCase(what.charAt(0));

        for (int i = src.length() - length; i >= 0; i--) {
            // Quick check before calling the more expensive regionMatches() method:
            final char ch = src.charAt(i);
            if (ch != firstLo && ch != firstUp)
                continue;

            if (src.regionMatches(true, i, what, 0, length))
                return true;
        }

        return false;
    }


    public static void fillList(){

        String str="2015-03-31";
        Date date= Date.valueOf(str);//converting string into sql date

        Airport airport = new Airport(1, "Juan S", "juan@gmail.com", "Alajuela");

        Hotel hotel = new Hotel(1,"Hiltom","hilton@gmail.com","777777","");

        TravelPackage travelPackage = new TravelPackage(1, date, date, 50, "5 dias", "Manuel Antonio",
                "Viaje turistico para una familia promedio", hotel, airport,
                "https://www.larepublica.net/storage/images/2019/03/13/20190313144831.manu.jpg",
                "Loquillo","4x4");

        TravelPackage travelPackage2 = new TravelPackage(1, date, date, 80, "5 dias", "Goku SSJ Blue",
                "Viaje turistico para una familia promedio", hotel, airport,
                "https://i.pinimg.com/originals/d8/9d/79/d89d79aad3ee2dc980ffd261aa8dbb00.jpg",
                "Aventurero","4x4");

        DBHelper.TRAVEL_PACKAGES.add(travelPackage);

        DBHelper.TRAVEL_PACKAGES.add(travelPackage2);

    }











}
