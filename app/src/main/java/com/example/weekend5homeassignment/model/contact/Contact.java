package com.example.weekend5homeassignment.model.contact;

import android.os.Parcel;
import android.os.Parcelable;

public class Contact implements Parcelable {

    String name;
    String location;

    public Contact() {
    }

    public Contact(String name, String location) {
        this.name = name;
        this.location = location;
    }

    protected Contact(Parcel in) {
        name = in.readString();
        location = in.readString();
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(location);
    }
}
