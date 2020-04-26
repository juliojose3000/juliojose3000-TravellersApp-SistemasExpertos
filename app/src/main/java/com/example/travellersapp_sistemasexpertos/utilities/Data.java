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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class Data {

    public static User loggedUser;

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

        for (User user: MainActivity.USERS) {

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
                listImages,1,"https://loaiza4ever.000webhostapp.com/videos/Chirripo.mp4","Un lugar muy alto jaja", 9.383820, -84.144437);

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
                 listImages,1,"https://loaiza4ever.000webhostapp.com/videos/manuelAntonio.mp4", "playa playaaaa", 9.383820, -84.144437 );

        listaDestinos.add(touristDestination2);

        //----------------------------------------------------------------------------------------//

        TravelPackage travelPackage = new TravelPackage(
                1, "10 de enero 2020", "22 de enero 2020", 500000, "3 dias",
                "Tour sudamericano", "Un tour por las montañSA DE costa rica", hotel, airport,
                "Aventurero", "4x4", 5, listaDestinos, "Playa");

        User user = new User(1, "juliojose3000", "123", "Julio",
                "Segura", "87349630", "juliojose3000@gmail.com");


        MainActivity.TRAVEL_PACKAGES = new ArrayList<>();

        MainActivity.TOURISTDESTINATIONS = new ArrayList<>();

        MainActivity.IMAGES = new ArrayList<>();

        MainActivity.USERS = new ArrayList<>();

        MainActivity.AIRPORTS = new ArrayList<>();

        MainActivity.HOTELS = new ArrayList<>();


        MainActivity.TRAVEL_PACKAGES.add(travelPackage);
        MainActivity.TRAVEL_PACKAGES.add(travelPackage);
        MainActivity.TRAVEL_PACKAGES.add(travelPackage);
        MainActivity.TRAVEL_PACKAGES.add(travelPackage);

        MainActivity.TOURISTDESTINATIONS = listaDestinos;

        MainActivity.IMAGES = listImages;

        MainActivity.USERS.add(user);

        MainActivity.AIRPORTS.add(airport);

        MainActivity.HOTELS.add(hotel);

    }

    public static ArrayList<TravelPackage> getResults(ArrayList<TravelPackage> packageListStatic, double amountOfPeople,int a, double price, int b, double categoryPackage, int c, double userType, int d){

        ArrayList<TravelPackage> packageList = packageListStatic;

        //Lista que ira almacenando los paquetes de manera ordenada para mostrarlos
        ArrayList<TravelPackage> packagesListSort = new ArrayList<>();

        int cantidadDePaquetesAMostrar = 4;

        //en este caso, si todos los parametros están en cero, es porque al usuario le es indiferente
        //las caracteristicas que pueda tener un paquete, por lo que se le mostrarán todos
        if(a == 0 && b == 0 && c == 0 && d == 0){cantidadDePaquetesAMostrar = packageList.size();}

        //recorro la lista hasta que encuentre 4 paquetes semilares
        for(int i = 0; i<cantidadDePaquetesAMostrar; i++){

            System.out.println("Algoritmo de euclides, tamaño lista: "+MainActivity.TRAVEL_PACKAGES.size());

            //sobre esta variable aplico euclides, la inicializo en cualquier valor elevedo para evitar
            //errores de logica.
            double distanciaMinima = 10000;

            //paquete euclidiano: una vez que se encuentra el mas parecido, lo remuevo de la lista para
            //buscar los siguientes mas parecidos
            TravelPackage paqueteEuclidiano = null;

            //recorro la lista de paquetes en busca de los que son mas parecidos
            for (TravelPackage travelPackage: packageList) {

                double packagePrice = maxPriceValue(travelPackage.getCost());
                double people = travelPackage.getNumberOfPersons();
                double categoryValue = getCategoryValue((travelPackage.getTravelType()));
                double userTypePackage = getUserTypeValue(travelPackage.getTouristType());

                //aplico euclides
                //los parametros a,b,c y d se utilizan para darle importancia solo a los parametros
                //que el usuario escogió. Por ejemplo, si en tipo de paquete puso "cualquiera",
                //entonces el parametro c pa a estar en cero.
                double dist = Math.sqrt(
                        Math.pow((amountOfPeople-people), 2) * a +
                        Math.pow((price-packagePrice), 2) * b +
                        Math.pow((categoryPackage-categoryValue), 2) * c +
                        Math.pow((userType-userTypePackage), 2) * d
                );

                //pregunto si la distancia es menor que la distancia minima(el mas parecido), si es asi
                //establezco una nueva distancia minima y guardo el paquete que, por ahora, es el mas
                //similar a los gustos del usuario
                if(dist<distanciaMinima){

                    distanciaMinima = dist;

                    paqueteEuclidiano = travelPackage;

                }


            }
            //por lo tanto, el primer elemento de esta lista será el paquete que más concuerde con
            //los filtros establecidos por el usuario al menos parecido, osea el ultimo elemento de
            //la lista
            packagesListSort.add(paqueteEuclidiano);

            //remuevo el paquete que se acaba de agregar a la lista anterior para ir en busca de
            //los siguientes mas parecidos
            packageList.remove(paqueteEuclidiano);

        }


        return packagesListSort;


    }

    public static double maxPriceValue(double maxPrice){

        if(maxPrice==0.0) {
            return 20;
        }else if(maxPrice<10000){
            return 1;
        }else if(maxPrice<20000){
            return 2;
        }else if(maxPrice<30000){
            return 3;
        }else if(maxPrice<50000){
            return 5;
        }else if(maxPrice<80000){
            return 8;
        }else if(maxPrice<90000){
            return 9;
        }else if(maxPrice<100000){
            return 10;
        }else if(maxPrice<120000){
            return 12;
        }else if(maxPrice<200000){
            return 20;
        }else if(maxPrice<250000){
            return 25;
        }else if(maxPrice<300000){
            return 30;
        }else{
            return 40;
        }


    }

    public static double getCategoryValue(String category){

        Hashtable<String, Integer> categoryValues = new Hashtable<>();

        // Convierto a un valor numerico la categoria del viaje
        categoryValues.put("Playa", 1);
        categoryValues.put("Isla", 3);
        categoryValues.put("Playa y Montana", 5);
        categoryValues.put("Montana", 9);
        categoryValues.put("Ciudad", 17);

        return categoryValues.get(category);


    }

    public static double getUserTypeValue(String userType){

        Hashtable<String, Integer> userTypeValues = new Hashtable<>();

        // Convierto en un valor numerico el tipo de usuario
        userTypeValues.put("Relajado", 1);
        userTypeValues.put("Aventurero", 15);
        userTypeValues.put("Deportista", 10);

        return userTypeValues.get(userType);

    }


    public static ArrayList<TravelPackage> clonePackageTravelList(){

        ArrayList<TravelPackage> listPackages = new ArrayList<>();

        for(TravelPackage travelPackage: MainActivity.TRAVEL_PACKAGES){

            listPackages.add(travelPackage);

        }

        return listPackages;

    }


}
