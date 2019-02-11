package com.example.weekend5homeassignment.managers;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

public class PermisstionsManager {

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 1;

    Context context;
    IPermissionManager iPermissionManager;

    public PermisstionsManager(Context context, IPermissionManager iPermissionManager) {
        this.context = context;
        this.iPermissionManager = iPermissionManager;
    }

    public void checkPermissionForContacts() {

        if(ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

            if(ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_CONTACTS)) {

            } else {
                ActivityCompat.requestPermissions((Activity) context,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            }

        } else {

            requestPermissionForCotacts();

        }

    }

    public void requestPermissionForCotacts() {
        ActivityCompat.requestPermissions((Activity) context,
                new String[]{Manifest.permission.READ_CONTACTS},
                MY_PERMISSIONS_REQUEST_READ_CONTACTS);
    }

    public void checkResultForContacts(int requestCode, String permission[], int[] grantResult) {

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                if(grantResult.length > 0 && grantResult[0] == PackageManager.PERMISSION_GRANTED) {

                    Log.d("TAG", "checkResult: ");

                    iPermissionManager.onPermissionResult(true);

                } else {

                    iPermissionManager.onPermissionResult(false);

                    Log.d("TAG", "checkResult: ");
                }
            }
        }

    }




    public void checkPermissionForLocation() {

        if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if(ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.ACCESS_FINE_LOCATION)) {

            } else {
                ActivityCompat.requestPermissions((Activity) context,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }

        } else {

            requestPermissionForLocation();

        }

    }

    public void requestPermissionForLocation() {
        ActivityCompat.requestPermissions((Activity) context,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                MY_PERMISSIONS_REQUEST_LOCATION);
    }

    public void checkResultForLocation(int requestCode, String permission[], int[] grantResult) {

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                if(grantResult.length > 0 && grantResult[0] == PackageManager.PERMISSION_GRANTED) {

                    Log.d("TAG", "checkResultForLocation: ");

                    iPermissionManager.onPermissionResult(true);

                } else {

                    iPermissionManager.onPermissionResult(false);

                    Log.d("TAG", "checkResultForLocation: ");
                }
            }
        }

    }

    public interface IPermissionManager {
        void onPermissionResult(boolean isGranted);
    }
}
