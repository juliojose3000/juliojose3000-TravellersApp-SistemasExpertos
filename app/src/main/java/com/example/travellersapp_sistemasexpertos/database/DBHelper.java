package com.example.travellersapp_sistemasexpertos.database;

import android.util.ArraySet;

import com.example.travellersapp_sistemasexpertos.MainActivity;
import com.example.travellersapp_sistemasexpertos.domain.Airport;
import com.example.travellersapp_sistemasexpertos.domain.Hotel;
import com.example.travellersapp_sistemasexpertos.domain.Image;
import com.example.travellersapp_sistemasexpertos.domain.ReservationPackage;
import com.example.travellersapp_sistemasexpertos.domain.TouristCompany;
import com.example.travellersapp_sistemasexpertos.domain.TouristDestination;
import com.example.travellersapp_sistemasexpertos.domain.TravelPackage;
import com.example.travellersapp_sistemasexpertos.domain.User;
import com.example.travellersapp_sistemasexpertos.utilities.Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;
public class DBHelper  {

    public static ArrayList<User> USERS;
    public static ArrayList<ReservationPackage> RESERVATIONS;
    public static ArrayList<TravelPackage> TRAVEL_PACKAGES;
    public static ArrayList<Image> IMAGES;
    public static ArrayList<Hotel> HOTELS;
    public static ArrayList<Airport> AIRPORTS;
    public static ArrayList<TouristCompany> TOURISTCOMPANIES;
    public static ArrayList<TouristDestination> TOURISTDESTINATIONS;

    public static String apiUrl = "https://loaiza4ever.000webhostapp.com/TravellersApi/api/";

    public DBHelper(){

        try {
            getAllUsers();
            getAllImages();
            getAllAirports();
            getAllHotels();
            getAllTravelPackage();
            getAllReservations();
            getAllTouristCompany();
            getAllTouristDestination();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    public static ArrayList<User> getAllUsers() throws JSONException {

        USERS = new ArrayList<>();

        Map<String, String> params = new HashMap<>();

        HttpJsonParser httpJsonParser = new HttpJsonParser();

        JSONArray jsonArray =  httpJsonParser.getJson(DBUsers.URLRead(), params);

        for(int i=0; i<jsonArray.length(); i++)
        {
            JSONObject jsonObject=jsonArray.getJSONObject(i);
            int id = jsonObject.getInt("idUser");
            String username = jsonObject.getString("userName");
            String password = jsonObject.getString("password");
            String name = jsonObject.getString("name");
            String lastname = jsonObject.getString("lastName");
            String email = jsonObject.getString("email");
            String phone = jsonObject.getString("phone");

            User user = new User(id, username, password, name, lastname, phone, email);

            USERS.add(user);

        }

        return USERS;

    }

    public static int insertUser(String name, String lastName, String email, String phone, String userName, String password){

        int code = 0;

        Map<String, String> params = new HashMap<>();

        params.put("name",name);
        params.put("lastName",lastName);
        params.put("email",email);
        params.put("phone",phone);
        params.put("userName",userName);
        params.put("password",password);

        HttpJsonParser httpJsonParser = new HttpJsonParser();

        try {
            code = httpJsonParser.sendJson(DBUsers.URLCreate(), params);
          //  Data.loggedUser=getActualUser( name,  lastName,  email,  phone,  userName,  password);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return code;
    }

    public static ArrayList<TravelPackage> getAllTravelPackage() throws JSONException, ParseException {

        TRAVEL_PACKAGES = new ArrayList<>();

        Map<String, String> params = new HashMap<>();

        HttpJsonParser httpJsonParser = new HttpJsonParser();

        JSONArray jsonArray =  httpJsonParser.getJson(DBTravelPackage.URLRead(), params);

        for(int i=0; i<jsonArray.length(); i++)
        {
            JSONObject jsonObject=jsonArray.getJSONObject(i);
            int id = jsonObject.getInt("idTravelPackage");
            String name = jsonObject.getString("name");
            String startDate = jsonObject.getString("startDate");
            String endDate = jsonObject.getString("endDate");
            float cost = jsonObject.getLong("cost");
            String duration = jsonObject.getString("duration");
            String description = jsonObject.getString("description");
            String videoURL = jsonObject.getString("videoURL");
            int idHotel = jsonObject.getInt("idHotel");
            int idAirport = jsonObject.getInt("idAirport");
            int numberOfPersons = jsonObject.getInt("numberOfPersons");
            String touristType= jsonObject.getString("touristType");
            String typeOfRoute= jsonObject.getString("typeOfRoute");
            Hotel hotel=  Data.getHotelByID(idHotel);
            Airport airport= Data.getAirportById(idAirport);
            ArrayList<TouristDestination> touristDestinations = Data.getAllTouristDestinationsByIdPackage(id);

            //todo descomentar
            /*TravelPackage travelPackage=new TravelPackage(id, startDate, endDate, cost, duration, name,
                    description, hotel, airport, touristType, typeOfRoute,numberOfPersons, videoURL,touristDestinations);*/
            TravelPackage travelPackage=new TravelPackage(id, startDate, endDate, cost, duration, name,
                    description, hotel, airport, touristType, typeOfRoute,numberOfPersons, touristDestinations);

         TRAVEL_PACKAGES.add(travelPackage);

        }

        return TRAVEL_PACKAGES;
    }

    public static ArrayList<ReservationPackage> getAllReservations() throws JSONException, ParseException {

        RESERVATIONS = new ArrayList<>();

        Map<String, String> params = new HashMap<>();

        HttpJsonParser httpJsonParser = new HttpJsonParser();

        JSONArray jsonArray =  httpJsonParser.getJson(DBTravelPackageReservation.URLRead(), params);

        for(int i=0; i<jsonArray.length(); i++)
        {
            JSONObject jsonObject=jsonArray.getJSONObject(i);
            int idReservacionPaquete = jsonObject.getInt("idReservacionPaquete");
            int idUser = jsonObject.getInt("idUser");
            int idTrip = jsonObject.getInt("idTrip");
            String reservationDate = jsonObject.getString("reservationDate");
            Date reservationDateS=new SimpleDateFormat("dd/MM/yyyy").parse(reservationDate);
            User user= Data.getUserByID(idUser);
            TravelPackage travelPackage= getTravelPackageById(idTrip);

            ReservationPackage reservationPackage = new ReservationPackage(idReservacionPaquete,
                    user,travelPackage,reservationDateS);

            RESERVATIONS.add(reservationPackage);

        }

        return RESERVATIONS;

    }

    public static TravelPackage  getTravelPackageById(int idReservation)  {
        for(TravelPackage travelPackage: MainActivity.TRAVEL_PACKAGES){
            if(travelPackage.getIdTravelPackage()==idReservation){
                return travelPackage;
            }
        }
        return null;
    }

    public static int insertReservationPackage(User user, int travelPackageID,String reservationDate){

        int code = 0;

        Map<String, String> params = new HashMap<>();

        String idUser=""+user.getId();
        String idTravelPackage=""+getTravelPackageById(travelPackageID).getIdTravelPackage();
        params.put("idUser",idUser);
        params.put("idTrip",idTravelPackage);
        params.put("reservationDate",reservationDate);


        HttpJsonParser httpJsonParser = new HttpJsonParser();

        try {
            code = httpJsonParser.sendJson(DBTravelPackageReservation.URLCreate(), params);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return code;
    }

    public static ArrayList<Hotel> getAllHotels() throws JSONException {

        HOTELS = new ArrayList<>();

        Map<String, String> params = new HashMap<>();

        HttpJsonParser httpJsonParser = new HttpJsonParser();

        JSONArray jsonArray =  httpJsonParser.getJson(DBHotel.URLRead(), params);

        for(int i=0; i<jsonArray.length(); i++)
        {
            JSONObject jsonObject=jsonArray.getJSONObject(i);
            int id = jsonObject.getInt("idHotel");
            String email = jsonObject.getString("email");
            String phone = jsonObject.getString("phone");
            String name = jsonObject.getString("name");
            String offers = jsonObject.getString("offers");


            Hotel hotel=new Hotel(id,name,email,phone,offers);

            HOTELS.add(hotel);

        }

        return HOTELS;

    }

    public static ArrayList<Airport> getAllAirports() throws JSONException {

        AIRPORTS = new ArrayList<>();

        Map<String, String> params = new HashMap<>();

        HttpJsonParser httpJsonParser = new HttpJsonParser();

        JSONArray jsonArray =  httpJsonParser.getJson(DBAirport.URLRead(), params);

        for(int i=0; i<jsonArray.length(); i++)
        {
            JSONObject jsonObject=jsonArray.getJSONObject(i);
            int id = jsonObject.getInt("idAirport");
            String name = jsonObject.getString("name");
            String email = jsonObject.getString("email");
            String address = jsonObject.getString("address");

            Airport airport=new Airport(id,name,email,address);

            AIRPORTS.add(airport);

        }

        return AIRPORTS;

    }

    public static ArrayList<TouristCompany> getAllTouristCompany() throws JSONException {

        AIRPORTS = new ArrayList<>();

        Map<String, String> params = new HashMap<>();

        HttpJsonParser httpJsonParser = new HttpJsonParser();

        JSONArray jsonArray =  httpJsonParser.getJson(DBTouristCompany.URLRead(), params);

        for(int i=0; i<jsonArray.length(); i++)
        {
            JSONObject jsonObject=jsonArray.getJSONObject(i);
            int idTouristCompany = jsonObject.getInt("idTouristCompany");
            String name = jsonObject.getString("name");
            String email = jsonObject.getString("email");
            String phone = jsonObject.getString("phone");

            TouristCompany touristCompany=new TouristCompany(idTouristCompany,name,email,phone);

            TOURISTCOMPANIES.add(touristCompany);

        }

        return TOURISTCOMPANIES;

    }

    public static ArrayList<TouristDestination> getAllTouristDestination() throws JSONException {

        AIRPORTS = new ArrayList<>();

        Map<String, String> params = new HashMap<>();

        HttpJsonParser httpJsonParser = new HttpJsonParser();

        JSONArray jsonArray =  httpJsonParser.getJson(DBTouristCompany.URLRead(), params);

        for(int i=0; i<jsonArray.length(); i++)
        {
            JSONObject jsonObject=jsonArray.getJSONObject(i);
            int idTouristDestination = jsonObject.getInt("idtouristdestination");
            String address = jsonObject.getString("address");
            String name = jsonObject.getString("name");
            String videoURL = jsonObject.getString("videoURL");
            int idTravelPackage=jsonObject.getInt("idTravelPackage");
            ArrayList<Image>imagesList= Data.getAllImagesByIdTouristDestination(idTouristDestination);
            TouristDestination touristDestinations=new TouristDestination(idTouristDestination
                    ,address,name,videoURL,imagesList,idTravelPackage);

            TOURISTDESTINATIONS.add(touristDestinations);

        }

        return TOURISTDESTINATIONS;

    }

    public static ArrayList<Image> getAllImages() throws JSONException {

        IMAGES = new ArrayList();

        Map<String, String> params = new HashMap<>();

        HttpJsonParser httpJsonParser = new HttpJsonParser();

        JSONArray jsonArray =  httpJsonParser.getJson(DBImage.URLRead(), params);

        Image image;

        for(int i=0; i<jsonArray.length(); i++)
        {
            JSONObject jsonObject=jsonArray.getJSONObject(i);
            int idImage = jsonObject.getInt("imagetouristdestination");
            int idTouristDestination = jsonObject.getInt("idTouristDestination");
            String imageURL = jsonObject.getString("imageURL");

            image = new Image(idImage, idTouristDestination, imageURL);

            IMAGES.add(image);

        }

        return IMAGES;

    }

}