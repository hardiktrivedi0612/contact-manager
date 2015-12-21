package com.utdallas.hpt150030.mycontactmanager;

import android.app.Application;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Hardik on 11/2/2015.
 */

/*
* This class has been created so that everytime the application starts, we will initialize the list of contacts and fill them up from the text file
* */
public class MyApplication extends Application{

    private ContactList contactList;

    /*Override the onCreate function so that everytime the app is started, all the contacts are read from the text file and added to the list for use in the app*/
    @Override
    public void onCreate() {
        super.onCreate();

        contactList = new ContactList();

        try {
            InputStream inputStream = openFileInput(Constants.fileName);
            if(inputStream!=null)
            {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String contact = bufferedReader.readLine();
                while(contact!=null)
                {
                    String data[] = contact.split("\t",-2);
                    ContactBean bean = new ContactBean(data[0],data[1],data[2],data[3]);
                    contactList.list.add(bean);
                    contact = bufferedReader.readLine();
                }
                bufferedReader.close();
            }
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
