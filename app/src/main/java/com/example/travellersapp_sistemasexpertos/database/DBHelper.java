package com.example.travellersapp_sistemasexpertos.database;

import com.example.travellersapp_sistemasexpertos.MainActivity;
import com.example.travellersapp_sistemasexpertos.domain.Airport;
import com.example.travellersapp_sistemasexpertos.domain.Hotel;
import com.example.travellersapp_sistemasexpertos.domain.Image;
import com.example.travellersapp_sistemasexpertos.domain.ReservationPackage;
import com.example.travellersapp_sistemasexpertos.domain.TouristCompany;
import com.example.travellersapp_sistemasexpertos.domain.TravelPackage;
import com.example.travellersapp_sistemasexpertos.domain.User;

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
    //public static ArrayList<Airport> AIRPORTS;
    //public static ArrayList<Hotel> HOTELS;
    public static ArrayList<ReservationPackage> RESERVATIONS;
    //public static ArrayList<TouristCompany> TOURIST_COMPANIES;
    public static ArrayList<TravelPackage> TRAVEL_PACKAGES;
    public static ArrayList<Image> IMAGES;
    public static ArrayList<Hotel> HOTELS;
    public static ArrayList<Airport> AIRPORTS;

    public static String apiUrl = "https://loaiza4ever.000webhostapp.com/TravellersApi/api/";

    public DBHelper(){

        try {
            getAllUsers();
            getAllImages();
            getAllAirports();
            getAllHotels();
            getAllTravelPackage();
            getAllReservations();
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

            User user = new User(id, username, password, name, lastname, email, phone);

            USERS.add(user);

        }

        return USERS;

    }
    public static User  getUserByID(int idUser) throws JSONException {



        Map<String, String> params = new HashMap<>();

        String idUserS= ""+idUser;

        params.put("idUser",idUserS);


        HttpJsonParser httpJsonParser = new HttpJsonParser();

        JSONArray jsonArray =  httpJsonParser.getJson(DBUsers.URLReadSingle(), params);

        User user=new User();

        for(int i=0; i<jsonArray.length(); i++)
        {
            JSONObject jsonObject=jsonArray.getJSONObject(i);
            int id = jsonObject.getInt("idUser");
            String username = jsonObject.getString("username");
            String password = jsonObject.getString("password");
            String name = jsonObject.getString("name");
            String lastname = jsonObject.getString("lastname");
            String email = jsonObject.getString("email");
            String phone = jsonObject.getString("phone");

            user.setId(id);
            user.setName(name);
            user.setLastName(lastname);
            user.setMail(email);
            user.setPhone(phone);
            user.setUsername(username);
            user.setPassword(password);
        }
        return user;
    }
    public static boolean logInUser(String userName,String passwordU) throws JSONException {

        boolean flag=false;
        Map<String, String> params = new HashMap<>();

        HttpJsonParser httpJsonParser = new HttpJsonParser();

        JSONArray jsonArray =  httpJsonParser.getJson(DBUsers.URLRead(), params);

        for(int i=0; i<jsonArray.length(); i++)
        {
            JSONObject jsonObject=jsonArray.getJSONObject(i);
            int id = jsonObject.getInt("idUser");
            String username = jsonObject.getString("username");
            String password = jsonObject.getString("password");
            String name = jsonObject.getString("name");
            String lastname = jsonObject.getString("lastname");
            String email = jsonObject.getString("email");
            String phone = jsonObject.getString("phone");

            User user = new User(id, username, password, name, lastname, email, phone);

            if(user.getUsername().equals(userName)&&user.getPassword().equals(passwordU)){
                flag=true;
            }


        }
        return flag;
    }
    public static int insertUser(String name, String lastname, String email, String phone, String username, String password){

        int code = 0;

        Map<String, String> params = new HashMap<>();

        params.put("name",name);
        params.put("lastname",lastname);
        params.put("email",email);
        params.put("phone",phone);
        params.put("username",username);
        params.put("password",password);

        HttpJsonParser httpJsonParser = new HttpJsonParser();

        try {
            code = httpJsonParser.sendJson(DBUsers.URLCreate(), params);
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
            String touristType= jsonObject.getString("touristType");
            String typeOfRoute= jsonObject.getString("typeOfRoute");
            Hotel hotel=  getHotelByID(idHotel);
            Airport airport=getAirportById(idAirport);
            /*Date startDateS=new SimpleDateFormat("dd/MM/yyyy").parse(startDate);
            Date endDateS=new SimpleDateFormat("dd/MM/yyyy").parse(endDate);*/
            ArrayList<Image> listImages = Data.getAllImagesByIDPackage(id);


            TravelPackage travelPackage=new TravelPackage(id, startDate, endDate, cost, duration, name,
                    description, hotel, airport, touristType, typeOfRoute, listImages, videoURL);

         TRAVEL_PACKAGES.add(travelPackage);

        }

        return TRAVEL_PACKAGES;
    }
    public static TravelPackage getTravelPackageById(int idTravelPackage) throws JSONException, ParseException {

        TRAVEL_PACKAGES = new ArrayList<>();

        Map<String, String> params = new HashMap<>();

        String idTravelPackageS= ""+idTravelPackage;

        params.put("idTravelPackage",idTravelPackageS);
        HttpJsonParser httpJsonParser = new HttpJsonParser();

        JSONArray jsonArray =  httpJsonParser.getJson(DBTravelPackage.URLReadSingle(), params);
        TravelPackage travelPackage=null;
        for(int i=0; i<jsonArray.length(); i++)
        {
            JSONObject jsonObject=jsonArray.getJSONObject(i);
            int id = jsonObject.getInt("idTravelPackage");
            String startDate = jsonObject.getString("startDate");
            String endDate = jsonObject.getString("endDate");
            float cost = jsonObject.getLong("cost");
            String duration = jsonObject.getString("duration");
            String name = jsonObject.getString("name");
            String description = jsonObject.getString("description");
            int idHotel = jsonObject.getInt("idHotel");
            int idAirport = jsonObject.getInt("idAirport");
            String touristType= jsonObject.getString("touristType");
            String typeOfRoute= jsonObject.getString("typeOfRoute");
            Hotel hotel=  getHotelByID(idHotel);
            Airport airport=getAirportById(idAirport);
            /*Date startDateS=new SimpleDateFormat("dd/MM/yyyy").parse(startDate);
            Date endDateS=new SimpleDateFormat("dd/MM/yyyy").parse(endDate);*/
            ArrayList<Image> listImages = Data.getAllImagesByIDPackage(id);
            String videoURL = jsonObject.getString("videoURL");


            travelPackage=new TravelPackage(id, startDate, endDate, cost, duration, name,
                    description, hotel, airport, touristType, typeOfRoute, listImages, videoURL);


        }
        return travelPackage;
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
            User user= getUserByID(idUser);
            TravelPackage travelPackage= getTravelPackageById(idTrip);

            ReservationPackage reservationPackage = new ReservationPackage(idReservacionPaquete,
                    user,travelPackage,reservationDateS);

            RESERVATIONS.add(reservationPackage);

        }

        return RESERVATIONS;

    }
    public static ReservationPackage  getReservationById(int idReservation) throws JSONException, ParseException {



        Map<String, String> params = new HashMap<>();

        String idReservationS= ""+idReservation;

        params.put("idReservacionPaquete",idReservationS);


        HttpJsonParser httpJsonParser = new HttpJsonParser();

        JSONArray jsonArray =  httpJsonParser.getJson(DBTravelPackageReservation.URLReadSingle(), params);


        ReservationPackage reservationPackage=null;
        for(int i=0; i<jsonArray.length(); i++)
        {
            JSONObject jsonObject=jsonArray.getJSONObject(i);
            int idReservacionPaquete = jsonObject.getInt("idReservacionPaquete");
            int idUser = jsonObject.getInt("idUser");
            int idTrip = jsonObject.getInt("idTrip");
            String reservationDate = jsonObject.getString("reservationDate");
            Date reservationDateS=new SimpleDateFormat("dd/MM/yyyy").parse(reservationDate);
            User user= getUserByID(idUser);
            TravelPackage travelPackage= getTravelPackageById(idTrip);

             reservationPackage = new ReservationPackage(idReservacionPaquete,
                    user,travelPackage,reservationDateS);
        }
        return reservationPackage;
    }
    public static int insertReservationPackage(User user, TravelPackage travelPackage,String reservationDate){

        int code = 0;

        Map<String, String> params = new HashMap<>();

        String idUser=""+user.getId();
        String idTravelPackage=""+travelPackage.getIdTravelPackage();
        params.put("idUser",idUser);
        params.put("idTravelPackage",idTravelPackage);
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

    public static ArrayList<Image> getAllImages() throws JSONException {

        IMAGES = new ArrayList();

        Map<String, String> params = new HashMap<>();

        HttpJsonParser httpJsonParser = new HttpJsonParser();

        JSONArray jsonArray =  httpJsonParser.getJson(DBImage.URLRead(), params);

        Image image;

        for(int i=0; i<jsonArray.length(); i++)
        {
            JSONObject jsonObject=jsonArray.getJSONObject(i);
            int idImage = jsonObject.getInt("idImage");
            int idTravelPackage = jsonObject.getInt("idTravelPackage");
            String imageURL = jsonObject.getString("imageURL");

            image = new Image(idImage, idTravelPackage, imageURL);

            IMAGES.add(image);

        }

        return IMAGES;

    }

}