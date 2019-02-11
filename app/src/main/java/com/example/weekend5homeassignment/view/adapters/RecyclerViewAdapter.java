package com.example.weekend5homeassignment.view.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weekend5homeassignment.R;
import com.example.weekend5homeassignment.model.contact.Contact;
import com.example.weekend5homeassignment.view.activities.contactdetailsactivity.ContactDetailsActivity;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    List<Contact> contactList;

    public RecyclerViewAdapter(List<Contact> contactList) {
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_contact, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder viewHolder, int position) {

        Contact contact = contactList.get(position);

        if(contact != null) {

            viewHolder.setItemContact(contact);

            viewHolder.contactNameTextView.setText(contact.getName());
        }
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView contactNameTextView;
        Contact itemContact;

        public void setItemContact(Contact itemContact) {
            this.itemContact = itemContact;
        }

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            contactNameTextView = itemView.findViewById(R.id.contactNameTextViewId);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(itemView.getContext(), ContactDetailsActivity.class);
                    intent.putExtra("contact", itemContact);
                    itemView.getContext().startActivity(intent);

                }
            });
        }
    }
}
