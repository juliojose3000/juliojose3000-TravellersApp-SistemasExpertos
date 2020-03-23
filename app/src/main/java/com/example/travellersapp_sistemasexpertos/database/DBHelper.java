package com.example.travellersapp_sistemasexpertos.database;

import android.os.AsyncTask;

import com.example.travellersapp_sistemasexpertos.domain.Airport;
import com.example.travellersapp_sistemasexpertos.domain.Hotel;
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
    public static ArrayList<Airport> AIRPORTS;
    public static ArrayList<Hotel> HOTELS;
    public static ArrayList<ReservationPackage> RESERVATIONS;
    public static ArrayList<TouristCompany> TOURIST_COMPANIES;
    public static ArrayList<TravelPackage> TRAVEL_PACKAGES;

    public static String apiUrl = "https://loaiza4ever.000webhostapp.com/TravellersApi/api/";

    public DBHelper(){

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    getAllUsers();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

        }.execute();

    }

    public static void getAllUsers() throws JSONException {

        USERS = new ArrayList<>();

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

            USERS.add(user);

        }
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
    public static void getAllTravelPackage() throws JSONException, ParseException {

        TRAVEL_PACKAGES = new ArrayList<>();

        Map<String, String> params = new HashMap<>();

        HttpJsonParser httpJsonParser = new HttpJsonParser();

        JSONArray jsonArray =  httpJsonParser.getJson(DBTravelPackage.URLRead(), params);

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
            String imageURL= jsonObject.getString("imageURL");
            String touristType= jsonObject.getString("touristType");
            String typeOfRoute= jsonObject.getString("typeOfRoute");
            Hotel hotel=  getHotelByID(idHotel);
            Airport airport=getAirportById(idAirport);
            Date startDateS=new SimpleDateFormat("dd/MM/yyyy").parse(startDate);
            Date endDateS=new SimpleDateFormat("dd/MM/yyyy").parse(endDate);


            TravelPackage travelPackage=new TravelPackage(id,startDateS,endDateS,cost,duration,name,
                    description,hotel,airport,imageURL,touristType,typeOfRoute);
         TRAVEL_PACKAGES.add(travelPackage);

        }
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
            String imageURL= jsonObject.getString("imageURL");
            String touristType= jsonObject.getString("touristType");
            String typeOfRoute= jsonObject.getString("typeOfRoute");
            Hotel hotel=  getHotelByID(idHotel);
            Airport airport=getAirportById(idAirport);
            Date startDateS=new SimpleDateFormat("dd/MM/yyyy").parse(startDate);
            Date endDateS=new SimpleDateFormat("dd/MM/yyyy").parse(endDate);


             travelPackage=new TravelPackage(id,startDateS,endDateS,cost,duration,name,
                    description,hotel,airport,imageURL,touristType,typeOfRoute);


        }
        return travelPackage;
    }
    public static Hotel getHotelByID(int idHotel) throws JSONException {

        USERS = new ArrayList<>();

        Map<String, String> params = new HashMap<>();

        String idHotelS= ""+idHotel;

        params.put("idHotel",idHotelS);


        HttpJsonParser httpJsonParser = new HttpJsonParser();

        JSONArray jsonArray =  httpJsonParser.getJson(DBHotel.URLReadSingle(), params);

        Hotel hotel=new Hotel();

        for(int i=0; i<jsonArray.length(); i++)
        {
            JSONObject jsonObject=jsonArray.getJSONObject(i);
            int id = jsonObject.getInt("idHotel");
            String name = jsonObject.getString("name");
            String email = jsonObject.getString("email");
            String phone = jsonObject.getString("phone");
            String offers = jsonObject.getString("offers");

             hotel.setIdHotel(id);
             hotel.setName(name);
             hotel.setEmail(email);
             hotel.setPhone(phone);
             hotel.setOffers(offers);

        }
        return hotel;
    }
    public static Airport getAirportById(int idAriport) throws JSONException {

        USERS = new ArrayList<>();

        Map<String, String> params = new HashMap<>();

        String idAirportS= ""+idAriport;

        params.put("idAirport",idAirportS);


        HttpJsonParser httpJsonParser = new HttpJsonParser();

        JSONArray jsonArray =  httpJsonParser.getJson(DBAirport.URLReadSingle(), params);

        Airport airport=new Airport();
        for(int i=0; i<jsonArray.length(); i++)
        {
            JSONObject jsonObject=jsonArray.getJSONObject(i);
            int id = jsonObject.getInt("idAirport");
            String name = jsonObject.getString("name");
            String email = jsonObject.getString("email");
            String address = jsonObject.getString("address");

            airport.setIdAirport(id);
            airport.setName(name);
            airport.setEmail(email);
            airport.setAddress(address);

        }
        return airport;

    }
    public static void getAllReservations() throws JSONException, ParseException {

        USERS = new ArrayList<>();

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

}