package com.example.travellersapp_sistemasexpertos.utilities;

import com.example.travellersapp_sistemasexpertos.MainActivity;
import com.example.travellersapp_sistemasexpertos.database.DBHelper;
import com.example.travellersapp_sistemasexpertos.domain.Airport;
import com.example.travellersapp_sistemasexpertos.domain.Hotel;
import com.example.travellersapp_sistemasexpertos.domain.Image;
import com.example.travellersapp_sistemasexpertos.domain.TravelPackage;
import com.example.travellersapp_sistemasexpertos.domain.User;

import org.json.JSONException;
import java.util.ArrayList;
import java.util.Random;

public class Data {

    public static User loggedUser = new User();

    public static boolean areValidCredentials(String username, String password){

        if(username.equals("admin") && password.equals("admin")){return true;}

        for (User user: MainActivity.USERS) {

            if(user.getPassword().equals(password) && user.getUsername().equals(username)){
                loggedUser = user;
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

    public static ArrayList<TravelPackage> getTravells(String search, float maxPrice, String category, String userType){

        ArrayList<TravelPackage> listItems = travelToSearch(search, MainActivity.TRAVEL_PACKAGES);

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

    public static TravelPackage getTravelPackageById(int id){

        for (TravelPackage travelPackage:
             MainActivity.TRAVEL_PACKAGES) {

            if(travelPackage.getIdTravelPackage()==id){
                return travelPackage;
            }

        }

        return null;

    }

    public static ArrayList<Image> getAllImagesByIDPackage(int idTravelPackage){

        ArrayList<Image> listImagePackageTravel = new ArrayList<>();

        for (Image image:
                MainActivity.IMAGES) {

            if(image.getIdTravelPackage()==idTravelPackage){
                listImagePackageTravel.add(image);
            }

        }

        return listImagePackageTravel;

    }

    public static ArrayList<TravelPackage> generateOffers(){

        ArrayList<TravelPackage> list = new ArrayList<>();

        int i = 2;

        for (TravelPackage travelPackage: MainActivity.TRAVEL_PACKAGES) {

            if(i%2==0){

                list.add(travelPackage);

            }

            i = getRandomNumberInRange(0, 10);

        }

        return list;

    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static int getLastIDUser(){

        return MainActivity.USERS.get(MainActivity.USERS.size()-1).getId()+1;

    }

    public static Hotel getHotelByID(int idHotel) throws JSONException {
        for(Hotel hotel: MainActivity.HOTELS){
            if(hotel.getIdHotel()==idHotel){
                return hotel;
            }
        }
        return null;
    }

    public static Airport getAirportById(int idAriport) throws JSONException {
        for(Airport airport: MainActivity.AIRPORTS){
            if(airport.getIdAirport()==idAriport){
                return airport;
            }
        }
        return null;
    }

    public static User getUserByID(int id){

        for (User user: MainActivity.USERS) {

            if(user.getId()==id){
                return user;
            }

        }

        return null;
    }













}
