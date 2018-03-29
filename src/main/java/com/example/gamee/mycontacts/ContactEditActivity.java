package com.example.gamee.mycontacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactEditActivity extends AppCompatActivity {
    public static final String TAG = "MainActive";
    ArrayList<Contact> mContactSingleton;
    Contact mContact;
    int mPosition;
    LinearLayout layoutP;
    LinearLayout layoutE;
    TextView mEditContactName;
    Button mAddPhoneButton;
    Button mAddEmailButton;
    PhoneListCount mPhonesInList;
    EmailListCount mEmailsInList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_edit);
        mPhonesInList = PhoneListCount.getInstance();
        mEmailsInList = EmailListCount.getInstance();

        Toolbar toolbar = (Toolbar) findViewById(R.id.edit_contact_toolbar);
        layoutP = (LinearLayout) findViewById(R.id.phone_layout);
        layoutE = (LinearLayout) findViewById(R.id.email_layout);

        mContactSingleton = ContactList.getInstance();
        mPosition = getIntent().getIntExtra(ContactViewActivity.CVA_EXTRA, 0);
        mContact = mContactSingleton.get(mPosition);

        addContactInfoToSectionP(mContact.mPhoneNumbers, layoutP);
        addContactInfoToSectionE(mContact.mEmails, layoutE);


        toolbar.setNavigationIcon(R.drawable.ic_finish_white);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();

                if (layoutP.getChildCount() > 1) {
                    for (int j = mPhonesInList.getPhoneCount(); j < layoutP.getChildCount(); j++) {
                        mContact.mPhoneNumbers.add(getContactPhoneTextAt(j));
                    }
                }
                if (layoutE.getChildCount() > 1) {
                    for (int j = mEmailsInList.getEmailCount(); j < layoutE.getChildCount(); j++) {
                        mContact.mEmails.add(getContactEmailTextAt(j));
                    }
                }
                mContact.setName(mEditContactName.getText().toString());
                setResult(RESULT_OK, i);
                finish();
            }
        });

        mEditContactName = (TextView) findViewById(R.id.edit_contact_name);
        mEditContactName.setText(mContact.getName());

         mAddPhoneButton = (Button) findViewById(R.id.phone_number_add_button);
         mAddEmailButton = (Button) findViewById(R.id.email_add_button);


        View.OnClickListener addPhone = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout layout = (LinearLayout) findViewById(R.id.phone_layout);
                addToLinearSection(layout);
            }
        };

        View.OnClickListener addEmail = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout layout = (LinearLayout) findViewById(R.id.email_layout);
                addToLinearSection(layout);
            }
        };


        mAddPhoneButton.setOnClickListener(addPhone);
        mAddEmailButton.setOnClickListener(addEmail);
    }

    private void addToLinearSection(LinearLayout section) {
        EditText et = new EditText(ContactEditActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        et.setLayoutParams(lp);
        section.addView(et);
    }


    private void addContactInfoToSectionP(ArrayList<String> Plist, LinearLayout Psection) {
        for (int i = 0; i < Plist.size(); i++) {
            //Log.d(TAG,mContact.mPhoneNumbers.get(i));
            //Log.d(TAG,mContact.mPhoneNumbers.size()+"");
            String itemName = Plist.get(i);
            EditText et = new EditText(ContactEditActivity.this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            et.setLayoutParams(lp);
            et.setText(itemName);
            Psection.addView(et);
        }

    }

    private void addContactInfoToSectionE(ArrayList<String> Elist, LinearLayout Esection) {
        for (int i = 0; i < Elist.size(); i++) {
            String itemName = Elist.get(i);
            EditText et = new EditText(ContactEditActivity.this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            et.setLayoutParams(lp);
            et.setText(itemName);
            Esection.addView(et);
        }

    }

    public String getContactPhoneTextAt(int a) {
        EditText numberView = (EditText) layoutP.getChildAt(a);
        return numberView.getText().toString();
    }

    public String getContactEmailTextAt(int a) {
        EditText emailText = (EditText) layoutE.getChildAt(a);
        return emailText.getText().toString();
    }


}

  /* for (int a = 0; a <= layoutP.getChildCount()-1; a++){

                       for (int a = 1; a <= layoutP.getChildCount() - 1; a++) {
           EditText et = (EditText) layoutP.getChildAt(a);
           String number = et.getText().toString();
           mContact.mPhoneNumbers.add(number);
           Log.d(TAG, mContact.mPhoneNumbers + "");
           }
           for (int a = 1; a <= layoutE.getChildCount() - 1; a++) {
           EditText et = (EditText) layoutE.getChildAt(a);
           String number = et.getText().toString();
           mContact.mEmails.add(number);
           Log.d(TAG, mContact.mEmails + "");
           }

                }*/


