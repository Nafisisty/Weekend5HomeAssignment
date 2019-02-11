package com.example.weekend5homeassignment.managers;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import com.example.weekend5homeassignment.model.contact.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactsManager {

    Context context;
    IContactsManager iContactsManager;

    public ContactsManager(Context context) {
        this.context = context;
        this.iContactsManager = (IContactsManager) context;
    }

    public void getContacts() {

        Uri contactUri = ContactsContract.Contacts.CONTENT_URI;

        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
        String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;

        Cursor contactCursor = context.getContentResolver().query(
                contactUri, null, null, null, null
        );

        List<Contact> contactList = new ArrayList<>();

        while (contactCursor.moveToNext()) {

            String contactName = contactCursor.getString(contactCursor.getColumnIndex(DISPLAY_NAME));
            int hasPhoneColumnIndex = contactCursor.getColumnIndex(HAS_PHONE_NUMBER);
            int has_phone = contactCursor.getInt(hasPhoneColumnIndex);

            if(has_phone > 0) {

                String contactAddress = "";

                Uri addressUri = ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI;
                String ADDRESS = ContactsContract.CommonDataKinds.StructuredPostal.FORMATTED_ADDRESS;
                String contactId = contactCursor.getString(contactCursor.getColumnIndex(ContactsContract.Contacts._ID));

                Cursor addressCursor = context.getContentResolver().query(
                        addressUri,
                        null,
                        ContactsContract.CommonDataKinds.StructuredPostal.CONTACT_ID
                        + " = " + contactId,
                        null,
                        null
                );

                if(addressCursor.moveToNext()) {
                    String address = addressCursor.getString(addressCursor.getColumnIndex(ADDRESS));
                    contactAddress = address;

                    Contact contact = new Contact(contactName, contactAddress);
                    contactList.add(contact);
                }

            }

        }

        iContactsManager.getContacts(contactList);
    }

    public interface IContactsManager {
        void getContacts(List<Contact> contacts);
    }
}
