package com.example.weekend5homeassignment.view.activities.mainactivity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.weekend5homeassignment.R;
import com.example.weekend5homeassignment.managers.ContactsManager;
import com.example.weekend5homeassignment.managers.PermisstionsManager;
import com.example.weekend5homeassignment.model.contact.Contact;
import com.example.weekend5homeassignment.view.adapters.RecyclerViewAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements PermisstionsManager.IPermissionManager, ContactsManager.IContactsManager {

    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;

    PermisstionsManager permisstionsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.contactListRecyclerViewId);

        permisstionsManager = new PermisstionsManager(this, this);
        permisstionsManager.checkPermissionForContacts();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        permisstionsManager.checkResultForContacts(requestCode, permissions, grantResults);

    }

    @Override
    public void getContacts(List<Contact> contacts) {

        for (Contact contact :
                contacts) {
            Log.d("TAG", "getContacts: " + contact.getName());
        }


        if(contacts != null) {

            recyclerViewAdapter = new RecyclerViewAdapter(contacts);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(recyclerViewAdapter);

        }else {
            Toast.makeText(this, "No data received", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onPermissionResult(boolean isGranted) {
        if(isGranted) {
            getContacts();
        }

    }

    public void getContacts() {

        ContactsManager contactsManager = new ContactsManager(this);
        contactsManager.getContacts();

    }
}
