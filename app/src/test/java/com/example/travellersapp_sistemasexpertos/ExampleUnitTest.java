package com.example.travellersapp_sistemasexpertos;

import com.example.travellersapp_sistemasexpertos.domain.User;
import com.example.travellersapp_sistemasexpertos.utilities.Data;

import org.junit.Test;

import java.util.ArrayList;

import androidx.core.widget.TextViewCompat;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {


    @Test
    public void isAValidUser(){

        loadUsers();

        String username = "juliojose3000";
        String password = "123";

        boolean isValid = Data.areValidCredentials(username, password);

        assertEquals(true, isValid);

    }

    public void loadUsers(){

        int id = 1;
        String username = "juliojose3000";
        String password = "123";
        String name = "Julio";
        String lastName = "Segura";
        String phone = "87349630";
        String mail = "juliojose3000@gmail.com";

        MainActivity.USERS = new ArrayList<>();
        MainActivity.USERS.add(new User(id, username, password, name, lastName, phone, mail));
    }


}