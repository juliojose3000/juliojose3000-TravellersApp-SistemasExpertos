package com.example.travellersapp_sistemasexpertos.utilities;

import android.provider.ContactsContract;

import com.example.travellersapp_sistemasexpertos.MainActivity;
import com.example.travellersapp_sistemasexpertos.database.DBHelper;
import com.example.travellersapp_sistemasexpertos.domain.Airport;
import com.example.travellersapp_sistemasexpertos.domain.Hotel;
import com.example.travellersapp_sistemasexpertos.domain.Image;
import com.example.travellersapp_sistemasexpertos.domain.TouristDestination;
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

    public static ArrayList<TravelPackage> getPackages(String amountOfPeople, float maxPrice, String category, String userType){

        ArrayList<TravelPackage> listItems = MainActivity.TRAVEL_PACKAGES;

        if(!amountOfPeople.equals("Cantidad de personas")){
            listItems = searchTravelByAmountOfPeople(Integer.parseInt(amountOfPeople), listItems);
        }

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

    private static ArrayList<TravelPackage> searchTravelByAmountOfPeople(int amountOfPeople, ArrayList<TravelPackage> listItems) {

        ArrayList<TravelPackage> travels = new ArrayList<>();

        for (TravelPackage currentTravel:
                travels) {

            if(currentTravel.getCost() == amountOfPeople){
                travels.add(currentTravel);
            }

        }

        return travels;


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

    public static ArrayList<Image> getAllImagesByIdTouristDestination(int idTouristDdestination){

        ArrayList<Image> listImagePackageTravel = new ArrayList<>();

        for (Image image:
                MainActivity.IMAGES) {

            if(image.getIdTouristDestination()==idTouristDdestination){
                listImagePackageTravel.add(image);
            }

        }

        return listImagePackageTravel;

    }

    public static ArrayList<TouristDestination> getAllTouristDestinationsByIdPackage(int idTravelPackage){

        ArrayList<TouristDestination> touristDestinations = new ArrayList<>();

        for (TouristDestination touristDestination:
                MainActivity.TOURISTDESTINATIONS) {

            if(touristDestination.getIdTravelPackage()==idTravelPackage){
                touristDestinations.add(touristDestination);
            }

        }

        return touristDestinations;

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

    public static TouristDestination getTouristDestinationById(int idTouristDestination) {

        for (TouristDestination touristDestination: MainActivity.TOURISTDESTINATIONS) {

            if(touristDestination.getIdTouristDestination()==idTouristDestination){
                return touristDestination;
            }

        }

        return null;
    }

    public static void fillList(){

        Airport airport = new Airport(1,"Juan Santa Maria", "", "");

        Hotel hotel = new Hotel(1, "Hilton", "", "", "");

        Image image = new Image(1,1,"https://loaiza4ever.000webhostapp.com/images/chirripo.jpg");
        Image image2 = new Image(2,1,"https://loaiza4ever.000webhostapp.com/images/chirripo2.jpg");
        Image image3 = new Image(3,1,"https://loaiza4ever.000webhostapp.com/images/chirripo3.jpg");
        Image image4 = new Image(4,1,"https://loaiza4ever.000webhostapp.com/images/chirripo4.jpg");
        Image image5 = new Image(5,1,"https://loaiza4ever.000webhostapp.com/images/chirripo5.jpg");

        ArrayList listImages = new ArrayList();

        listImages.add(image);
        listImages.add(image2);
        listImages.add(image3);
        listImages.add(image4);
        listImages.add(image5);

        TouristDestination touristDestination = new TouristDestination(1, "Alla por Loaiza", "Chirripo",
                "https://loaiza4ever.000webhostapp.com/videos/Chirripo.mp4", listImages,1 );

        ArrayList<TouristDestination> listaDestinos = new ArrayList<>();
        listaDestinos.add(touristDestination);

        //----------------------------------------------------------------------------------------//

        image = new Image(1,2,"https://loaiza4ever.000webhostapp.com/images/manuelantonio.jpeg");
        image2 = new Image(2,2,"https://loaiza4ever.000webhostapp.com/images/manuelantonio2.jpg");
        image3 = new Image(3,2,"https://loaiza4ever.000webhostapp.com/images/manuelantonio3.jpeg");
        image4 = new Image(4,2,"https://loaiza4ever.000webhostapp.com/images/manuelantonio4.jpg");
        image5 = new Image(5,2,"https://loaiza4ever.000webhostapp.com/images/manuelantonio5.jpg");

        listImages = new ArrayList();

        listImages.add(image);
        listImages.add(image2);
        listImages.add(image3);
        listImages.add(image4);
        listImages.add(image5);

        TouristDestination touristDestination2 = new TouristDestination(2, "En el sur", "Manuel Antonio",
                "https://loaiza4ever.000webhostapp.com/videos/manuelAntonio.mp4", listImages,1 );

        listaDestinos.add(touristDestination2);

        //----------------------------------------------------------------------------------------//

        TravelPackage travelPackage = new TravelPackage(
                1, "10 de enero 2020", "22 de enero 2020", 50, "3 dias",
                "Tour sudamericano", "Un tour por las montañSA DE costa rica", hotel, airport,
                "Cientifico", "4x4", 5, listaDestinos);

        User user = new User(1, "juliojose3000", "123", "Julio",
                "Segura", "87349630", "juliojose3000@gmail.com");


        MainActivity.TRAVEL_PACKAGES = new ArrayList<>();

        MainActivity.TOURISTDESTINATIONS = new ArrayList<>();

        MainActivity.IMAGES = new ArrayList<>();

        MainActivity.USERS = new ArrayList<>();

        MainActivity.AIRPORTS = new ArrayList<>();

        MainActivity.HOTELS = new ArrayList<>();


        MainActivity.TRAVEL_PACKAGES.add(travelPackage);

        MainActivity.TOURISTDESTINATIONS = listaDestinos;

        MainActivity.IMAGES = listImages;

        MainActivity.USERS.add(user);

        MainActivity.AIRPORTS.add(airport);

        MainActivity.HOTELS.add(hotel);

    }

}
