package com.example.gamee.mycontacts;

import java.util.ArrayList;

/**
 * Created by gamee on 7/17/2017.
 */

public class ContactList {
    public static ArrayList<Contact> mContacts = null;
    private ContactList(){}

    public static ArrayList<Contact> getInstance(){
        if (mContacts == null){
            mContacts = new ArrayList<Contact>();
        }
        return mContacts;
    }
}
