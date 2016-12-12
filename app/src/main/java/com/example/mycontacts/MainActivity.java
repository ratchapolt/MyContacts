package com.example.mycontacts;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mycontacts.adapter.ContactListAdapter;
import com.example.mycontacts.db.DatabaseHelper;
import com.example.mycontacts.model.AddContactActivity;
import com.example.mycontacts.model.Contact;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper mHelper;
    private SQLiteDatabase mDb;

    private ArrayList<Contact> mContactList = new ArrayList<>();
    private ListView mContactListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHelper = new DatabaseHelper(this);
        mDb = mHelper.getWritableDatabase();


    }

    @Override
    protected void onResume() {
        super.onResume();
        Cursor cursor = mDb.query(DatabaseHelper.TABLE_NAME, null, null, null, null, null, null);

        mContactList.clear();

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_NAME));
            String phone = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_PHONE));
            String image = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_IMAGE));
            Contact contact = new Contact(name, phone, image);
            mContactList.add(contact);


        }
        mContactListView = (ListView) findViewById(R.id.contact_list_view);

        ContactListAdapter adapter = new ContactListAdapter(
                this,
                R.layout.list_item,
                mContactList
        );

        mContactListView.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_add){
            Intent intent = new Intent(this, AddContactActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
