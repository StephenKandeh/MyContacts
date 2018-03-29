package com.example.gamee.mycontacts;

/**
 * Created by gamee on 2/7/2018.
 */

public class EmailListCount {
    private EmailListCount(){}
    public static EmailListCount mEmailsInList = null;

    private int mEmailCount;

    public static EmailListCount getInstance(){
        if (mEmailsInList == null){
            mEmailsInList = new EmailListCount();
        }
        return mEmailsInList;
    }

    public void setEmailCount(int emailCount) {
        mEmailCount = emailCount;
    }

    public int getEmailCount() {
        return mEmailCount;
    }
}
