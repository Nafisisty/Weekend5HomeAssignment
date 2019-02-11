package com.example.weekend5homeassignment.view.activities.contactdetailsactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weekend5homeassignment.R;
import com.example.weekend5homeassignment.model.contact.Contact;
import com.example.weekend5homeassignment.view.activities.mapactivity.MapsActivity;

public class ContactDetailsActivity extends AppCompatActivity {

    TextView contactAddressTextView;
    Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {

            contact = bundle.getParcelable("contact");

        }

        contactAddressTextView = findViewById(R.id.contactAddressTextViewId);
        contactAddressTextView.setText(contact.getLocation());
    }

    public void onClick(View view) {

        Toast.makeText(this, "Address is: " + contact.getLocation(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("contact", contact);
        startActivity(intent);

    }
}
