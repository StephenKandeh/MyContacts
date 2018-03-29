package com.example.gamee.mycontacts;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactViewActivity extends AppCompatActivity {
    public static final String TAG = "MainActive";
    public static final String CVA_EXTRA = "EXTRA#2";
    public int mNameID;
    ArrayList<Contact> mContactSingleton;
    FieldsAdapter mAdapter;
    int mPosition;
    Contact mContact;
    TextView mTextView;
    public int mColor;
    public static final int REQUEST_CODE = 0;
    PhoneListCount mPhonesInList;
    EmailListCount mEmailsInList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_view);

        mContactSingleton = ContactList.getInstance();
        mPosition = getIntent().getIntExtra(ContactListActivity.CLA_EXTRA, 0);
        mContact = mContactSingleton.get(mPosition);
        mNameID = mContact.getNameID();

        mTextView = (TextView) findViewById(R.id.text_row2);
        mContact.mPhoneNumbers = new ArrayList<String>();
        mContact.mEmails = new ArrayList<String>();

        mAdapter = new FieldsAdapter(mContact.mPhoneNumbers, mContact.mEmails);

        if (mNameID == 0) {
            mContact.mPhoneNumbers.add("669-262-7405");
            mContact.mPhoneNumbers.add("915-525-2010");
            mContact.mEmails.add("skandeh234@gmail.com");
        } else if (mNameID == 1) {
            mContact.mPhoneNumbers.add("405-320-0974");
            mContact.mEmails.add("gkandeh321@gmail.com");
        } else {
            mContact.mPhoneNumbers.add("null");
            mContact.mEmails.add("null");
        }
        mPhonesInList = PhoneListCount.getInstance();
        mPhonesInList.setPhoneCount(mContact.mPhoneNumbers.size());
        mEmailsInList = EmailListCount.getInstance();
        mEmailsInList.setEmailCount(mContact.mEmails.size());

        updateUi();

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.layout_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        ListView contactInfoList = (ListView) findViewById(R.id.contact_info_list);
        contactInfoList.setAdapter(mAdapter);

        Display display = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        layout.setLayoutParams(new RelativeLayout.LayoutParams(width, width * 9 / 16));
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.images);
        Palette palette = Palette.generate(bitmap);
        mColor = palette.getDarkMutedSwatch().getRgb();


        toolbar.inflateMenu(R.menu.menu_contact_view);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.edit_contact) {
                    Intent i = new Intent(ContactViewActivity.this, ContactEditActivity.class);
                    i.putExtra(CVA_EXTRA, mPosition);
                    startActivity(i);

                }
                return false;
            }
        });

    }

    public void updateUi() {
        mTextView.setText(mContact.getName());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUi();
        mPhonesInList.setPhoneCount(mContact.mPhoneNumbers.size());
        mEmailsInList.setEmailCount(mContact.mEmails.size());
    }

    public class FieldsAdapter extends BaseAdapter {
        ArrayList<String> phoneNumber;
        ArrayList<String> emails;
        private boolean isEmail;

        public FieldsAdapter(ArrayList<String> phoneNumber, ArrayList<String> emails) {
            this.phoneNumber = phoneNumber;
            this.emails = emails;
        }

        @Override
        public int getCount() {
            return emails.size() + phoneNumber.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = ContactViewActivity.this.getLayoutInflater().inflate(R.layout.contact_view_row, parent, false);
            }
            TextView rowValue = (TextView) convertView.findViewById(R.id.contact_view_row_value);
            String value = getItem(position);
            rowValue.setText(value);

            ImageView fieldImage = (ImageView) convertView.findViewById(R.id.field_icon);

            if (isEmail) {
                fieldImage.setImageResource(R.mipmap.ic_email);
            } else {
                fieldImage.setImageResource(R.mipmap.ic_phone);

            }

            fieldImage.setColorFilter(mColor);

            return convertView;
        }


        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public String getItem(int position) {
            if (position > phoneNumber.size() - 1) {
                isEmail = true;
                return emails.get(position - phoneNumber.size());
            } else {
                isEmail = false;
                return phoneNumber.get(position);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
}
