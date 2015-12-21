package com.utdallas.hpt150030.mycontactmanager;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Hardik on 11/2/2015.
 */

/*This class will act as a class that will hold the list of contacts and the operations related to the lists.
* One of the operations is that to override the add function of the list so that the list is automatically sorted each time a new entry is added to the contact list*/
public class ContactList {

    public static ArrayList<ContactBean> list;

    public ContactList() {
        list = new ArrayList<ContactBean>() {
            public boolean add(ContactBean mt) {
                super.add(mt);
                Collections.sort(list, new Comparator<ContactBean>() {
                    @Override
                    public int compare(ContactBean lhs, ContactBean rhs) {
                        return lhs.getFirstName().compareTo(rhs.getFirstName());
                    }
                });
                return true;
            }
        };
    }
}
