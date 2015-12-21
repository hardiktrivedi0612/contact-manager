package com.utdallas.hpt150030.mycontactmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by Hardik on 11/2/2015.
 */

/*
* This is the first activity that the user will see when the app is started.
* It contains the list of the contacts that the user has created.
*/
public class ContactDisplayActivity extends BaseActivity {

    private ListView listView;
    private MyContactsAdapter contactsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_display);

        initFields();
        setListeners();

    }

    /*
    * Override the function onRestart so that everytime the activity restarts, the contact list is updated
    * so as to reflect the changes made to the contact list at the manage contact screen
    */
    @Override
    protected void onRestart() {
        super.onRestart();
        contactsAdapter.notifyDataSetChanged();
    }

    /*
    * This function is defined to have a grouping of all the initializing of the fields that are required in the activity
    */
    private void initFields() {
        listView = (ListView) findViewById(R.id.listView);
        contactsAdapter = new MyContactsAdapter(this, R.id.listView, ContactList.list);
        listView.setAdapter(contactsAdapter);
    }

    /*
    * This function is defied to group all the listeners that have to be added to the various views in the activity
    */
    private void setListeners() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //start new activity in edit mode
                ContactBean bean = ContactList.list.get(position);
                Intent i = new Intent(getApplicationContext(), ManageContactActivity.class);
                i.putExtra(Constants.manageMode, Constants.ManageMode.EDIT);
                i.putExtra(Constants.firstName, bean.getFirstName());
                i.putExtra(Constants.lastName, bean.getLastName());
                i.putExtra(Constants.phoneNumber, bean.getPhoneNumber());
                i.putExtra(Constants.emailId, bean.getEmailAddress());
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contact_display, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.addContact) {
            //start new activity in add mode
            Intent i = new Intent(this, ManageContactActivity.class);
            i.putExtra(Constants.manageMode, Constants.ManageMode.ADD);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}
