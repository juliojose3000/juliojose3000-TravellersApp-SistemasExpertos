package com.example.travellersapp_sistemasexpertos.database;

import com.example.travellersapp_sistemasexpertos.domain.User;

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

    public static void insertUser(User user){



    }







}
