package com.example.gamee.mycontacts;

/**
 * Created by gamee on 2/7/2018.
 */

public class PhoneListCount {
    private PhoneListCount(){}
    static PhoneListCount mPhonesInList = null;
    private int mListCount;

    public static PhoneListCount getInstance(){
        if (mPhonesInList == null){
            mPhonesInList = new PhoneListCount();
        }
        return mPhonesInList;
    }
    public void setPhoneCount(int size){
        mListCount = size;
    }
    public int getPhoneCount(){
        return mListCount;
    }
}