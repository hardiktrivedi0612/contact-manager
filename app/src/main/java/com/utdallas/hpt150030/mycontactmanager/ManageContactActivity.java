package com.utdallas.hpt150030.mycontactmanager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Hardik on 11/2/2015.
 */


/*
* This is the activity where the user can add, edit or delete a contact.
*/
public class ManageContactActivity extends BaseActivity {

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText phoneNumberEditText;
    private EditText emailIdEditText;
    private Button saveButton;
    private Button deleteButton;
    private Constants.ManageMode manageMode = Constants.ManageMode.ADD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_contact);

        initFields();
        setListeners();

        if (!getIntent().getExtras().isEmpty()) {
            this.manageMode = (Constants.ManageMode) getIntent().getExtras().get(Constants.manageMode);

            if ((Constants.ManageMode) getIntent().getExtras().get(Constants.manageMode) == Constants.ManageMode.EDIT) {
                firstNameEditText.setText(getIntent().getStringExtra(Constants.firstName));
                lastNameEditText.setText(getIntent().getStringExtra(Constants.lastName));
                phoneNumberEditText.setText(getIntent().getStringExtra(Constants.phoneNumber));
                emailIdEditText.setText(getIntent().getStringExtra(Constants.emailId));
                setTitle(R.string.edit_contact);
            } else {
                deleteButton.setVisibility(View.GONE);
                setTitle(R.string.add_contact);
            }
        }

    }

    /*
    * This function is defined to have a grouping of all the initializing of the fields that are required in the activity
    */
    private void initFields() {
        firstNameEditText = (EditText) findViewById(R.id.firstNameEditText);
        lastNameEditText = (EditText) findViewById(R.id.lastNameEditText);
        phoneNumberEditText = (EditText) findViewById(R.id.phoneNumberEditText);
        emailIdEditText = (EditText) findViewById(R.id.emailIdEditText);
        saveButton = (Button) findViewById(R.id.saveButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);
    }

    /*
    * This function is defied to group all the listeners that have to be added to the various views in the activity
    */
    private void setListeners() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstNameText = firstNameEditText.getText().toString();
                String lastNameText = lastNameEditText.getText().toString();
                String phoneNumberText = phoneNumberEditText.getText().toString();
                String emailIdText = emailIdEditText.getText().toString();

                if (firstNameText.equals("") && lastNameText.equals("") && phoneNumberText.equals("") && emailIdText.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please enter something", Toast.LENGTH_LONG).show();
                    return;
                }

                if (firstNameText.equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), "Please enter the first name", Toast.LENGTH_LONG).show();
                    return;
                }

                if (manageMode == Constants.ManageMode.ADD) {
                    ContactBean bean = new ContactBean(firstNameText, lastNameText, phoneNumberText, emailIdText);
                    ContactList.list.add(bean);
                    Toast.makeText(getApplicationContext(), "New Contact Successfully Added", Toast.LENGTH_LONG).show();

                } else {
                    for (ContactBean bean : ContactList.list) {
                        if (bean.getFirstName().equalsIgnoreCase(getIntent().getStringExtra(Constants.firstName)) && bean.getLastName().equalsIgnoreCase(getIntent().getStringExtra(Constants.lastName)) && bean.getPhoneNumber().equalsIgnoreCase(getIntent().getStringExtra(Constants.phoneNumber)) && bean.getEmailAddress().equalsIgnoreCase(getIntent().getStringExtra(Constants.emailId))) {
                            ContactList.list.remove(bean);
                            break;
                        }
                    }
                    ContactBean bean = new ContactBean(firstNameText, lastNameText, phoneNumberText, emailIdText);
                    ContactList.list.add(bean);

                    Toast.makeText(getApplicationContext(), "Contact Successfully Edited", Toast.LENGTH_LONG).show();
                }
                finish();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (manageMode == Constants.ManageMode.EDIT) {
                    /*
                    * Confirm dialog to be shown before deleting the contact.
                    * */
                    AlertDialog.Builder builder = new AlertDialog.Builder(ManageContactActivity.this);
                    builder.setMessage("Are you sure that you want to delete this contact?");
                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            for (ContactBean bean : ContactList.list) {
                                if (bean.getFirstName().equalsIgnoreCase(getIntent().getStringExtra(Constants.firstName)) && bean.getLastName().equalsIgnoreCase(getIntent().getStringExtra(Constants.lastName)) && bean.getPhoneNumber().equalsIgnoreCase(getIntent().getStringExtra(Constants.phoneNumber)) && bean.getEmailAddress().equalsIgnoreCase(getIntent().getStringExtra(Constants.emailId))) {
                                    ContactList.list.remove(bean);
                                    Toast.makeText(getApplicationContext(), "Contact Successfully deleted", Toast.LENGTH_LONG).show();
                                    break;
                                }
                            }
                            finish();
                            dialog.dismiss();
                        }

                    });
                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }
        });
    }


    /*
    * Override the onBackPressed() so that we can have an alert dialog show up when the user presses back key with unsaved changes
    */
    @Override
    public void onBackPressed() {

        String firstNameText = firstNameEditText.getText().toString();
        String lastNameText = lastNameEditText.getText().toString();
        String phoneNumberText = phoneNumberEditText.getText().toString();
        String emailIdText = emailIdEditText.getText().toString();

        if (manageMode == Constants.ManageMode.EDIT) {
            /*
            * If something is changed then create a confirm dialog box to make sure that back wasn't pressed by mistake
            */
            if (!firstNameText.equalsIgnoreCase(getIntent().getStringExtra(Constants.firstName)) || !lastNameText.equalsIgnoreCase(getIntent().getStringExtra(Constants.lastName)) || !phoneNumberText.equalsIgnoreCase(getIntent().getStringExtra(Constants.phoneNumber)) || !emailIdText.equalsIgnoreCase(getIntent().getStringExtra(Constants.emailId))) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("You seem to have some unsaved changes. Do you want to discard changes?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        ManageContactActivity.super.onBackPressed();
                        dialog.dismiss();
                    }

                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            } else {
                super.onBackPressed();
            }
        } else {
            /*
            * If nothing was entered and back was pressed then do nothing
            * else show a confirm dialog box for saving changes
            */
            if (firstNameText.equalsIgnoreCase("") && lastNameText.equalsIgnoreCase("") && phoneNumberText.equalsIgnoreCase("") && emailIdText.equalsIgnoreCase("")) {
                super.onBackPressed();
                return;
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("You seem to have some unsaved changes. Do you want to discard changes?");
            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    ManageContactActivity.super.onBackPressed();
                    dialog.dismiss();
                }

            });
            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_manage_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}
