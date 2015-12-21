package com.utdallas.hpt150030.mycontactmanager;

/**
 * Created by Hardik on 11/2/2015.
 */

/*
* This class will act as the bean class for each of the contacts that are either in the list or in the text file
*/
public class ContactBean {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;

    public ContactBean(String firstName, String lastName, String phoneNumber, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return firstName + "\t" +
                lastName + "\t" +
                phoneNumber + "\t" +
                emailAddress + "\t";
    }
}
