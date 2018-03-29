package com.example.gamee.mycontacts;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ContactListActivity extends AppCompatActivity {
    public ArrayList<Contact> mContactSingleton;
    public ContactAdapter mAdapter;
    public static final String CLA_EXTRA = "EXTRA";
    public static final String TAG = "MainActive";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        mContactSingleton = ContactList.getInstance();

        for (int i = 0; i < 20; i++) {
            Contact testContact = new Contact();
            if (i <= 10) {
                testContact.setName("Stephen Kandeh");
                testContact.setNameID(0);
            }
            else
            {
                testContact.setName("Gloria Kandeh");
                testContact.setNameID(1);
            }
                mContactSingleton.add(testContact);

        }

        mAdapter = new ContactAdapter(mContactSingleton);

        ListView list = (ListView) findViewById(R.id.list_view);
        list.setAdapter(mAdapter);

        list.setOnScrollListener(new AbsListView.OnScrollListener() {
            int previousVisibleItem = 0;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                if (firstVisibleItem > previousVisibleItem){
                    getSupportActionBar().hide();
                }
                else if (firstVisibleItem < previousVisibleItem){
                    getSupportActionBar().show();
                }
                previousVisibleItem = firstVisibleItem ;


            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ContactListActivity.this,ContactViewActivity.class);
                i.putExtra(CLA_EXTRA,position);
                startActivity(i);
            }
        });

    }


    public class ContactAdapter extends ArrayAdapter{
        public ContactAdapter(ArrayList<Contact> contacts){
            super(ContactListActivity.this,R.layout.contact_list_row,R.id.text_row2,contacts);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = super.getView(position, convertView, parent);
            return convertView;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contact_list,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }



}
