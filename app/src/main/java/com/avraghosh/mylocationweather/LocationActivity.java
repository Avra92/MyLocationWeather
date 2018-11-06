package com.avraghosh.mylocationweather;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.Manifest;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class LocationActivity extends AppCompatActivity  {

    TextView tvLatitude, tvLongitude, tvDisplay, tvComma;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        imageView = (ImageView) findViewById(R.id.img_latlong);
        tvLatitude = (TextView) findViewById(R.id.latitude);
        tvLongitude = (TextView) findViewById(R.id.longitude);
        tvDisplay = (TextView) findViewById(R.id.display);
        tvComma = (TextView) findViewById(R.id.textView4);

        Bundle extras = getIntent().getExtras();
        double latitude = extras.getDouble("Latitude");
        double longitude = extras.getDouble("Longitude");

        tvLatitude.setText(String.valueOf(latitude));
        tvLongitude.setText(String.valueOf(longitude));

    }
}