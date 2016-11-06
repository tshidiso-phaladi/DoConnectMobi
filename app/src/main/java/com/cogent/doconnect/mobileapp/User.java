package com.cogent.doconnect.mobileapp;

import java.util.Date;

/**
 * Created by Tumudi.P on 11/5/2016.
 */

public class User {
    private int ID;
    private String Password;
    private Date Last_Login;
    private int AccessLevel;

    public User()
    {

    }

    public int GetID()
    {
        return ID;
    }
    public void SetID(int id)
    {
        ID = id;
    }

    public String GetPassword()
    {
        return Password;
    }

    public void SetPassword(String password)
    {
        Password = password;
    }

    public Date GetLastLogin()
    {
        return Last_Login;
    }

    public void SetLastLogin(Date last_Login)
    {
        Last_Login = last_Login;
    }

    public int GetAccessLevel()
    {
        return  AccessLevel;
    }

    public void SetAccessLevel(int accessLevel)
    {
        AccessLevel = accessLevel;
    }


}
