package com.example.travellersapp_sistemasexpertos.database;

/**
 * Created by Jay on 06-06-2017.
 */

public class DBUsers {

    public static String SERVER_URL = DBHelper.apiUrl+"user/";

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
