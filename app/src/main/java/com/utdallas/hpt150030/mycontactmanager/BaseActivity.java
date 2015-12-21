package com.utdallas.hpt150030.mycontactmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * Created by Hardik on 11/2/2015.
 */

/*
* This class will be extended by all the activities that are present in the application.
* This base class will have all the functions that have to be done in all the activities.
*/
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /*Override the onDestroy method so that everytime the app is closed, the contact list is written back to the text file*/
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this instanceof ContactDisplayActivity) {
            try {
                PrintWriter printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(openFileOutput(Constants.fileName, MODE_PRIVATE))));
                for (ContactBean bean : ContactList.list) {
                    printWriter.println(bean.toString());
                }
                printWriter.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
