package com.example.travellersapp_sistemasexpertos.database;

public class DBHotel {
    public static String SERVER_URL = DBHelper.apiUrl+"hotel/";

    public static String URLReadSingle(){
        return SERVER_URL+"read_single.php";
    }

    public static String URLRead(){
        return SERVER_URL+"read.php";
    }

    public static String URLCreate(){

        return SERVER_URL+"create.php";

    }
}
