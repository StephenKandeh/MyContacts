package com.example.gamee.mycontacts;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by gamee on 2/7/2017.
 */

public class Contact implements Serializable{
    private String mName;
    private int mNameID;
    public ArrayList<String> mEmails;
    public ArrayList<String> mPhoneNumbers;

    public String getName() {
        return mName;
    }
    public void setName(String name) {
        mName = name;
    }

    public int getNameID() {
        return mNameID;
    }

    public void setNameID(int nameID) {
        mNameID = nameID;
    }

    public String toString(){
        return mName;
    }

}
