package com.utdallas.hpt150030.mycontactmanager;

/**
 * Created by Hardik on 11/2/2015.
 */

/*
* This class is a helper class that stores all the constants that are through out the app.
* This is done so that all the constants are at one place and are accessed from the same place.
* Changing of any of the constants will take place at one place only.
*/
public class Constants {
    public static final String fileName = "hpt150030.txt";
    public static final String manageMode = "manageMode";
    public static final String firstName = "firstName";
    public static final String lastName = "lastName";
    public static final String phoneNumber = "phoneNumber";
    public static final String emailId = "emailId";

    /*This enum is used to define the current state of the manage contact activity*/
    public enum ManageMode {
        ADD, EDIT;
    }
}
