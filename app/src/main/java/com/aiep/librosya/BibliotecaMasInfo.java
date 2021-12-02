package com.aiep.librosya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aiep.librosya.entidades.Biblioteca;
import com.aiep.librosya.entidades.Libro;
import com.aiep.librosya.entidades.PersonaLibros;
import com.aiep.librosya.utils.GPS;
import com.bumptech.glide.Glide;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class BibliotecaMasInfo extends AppCompatActivity{

    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;



    Biblioteca biblioteca;
    TextView nombre, direccion, zipcode, telefono;
    ImageView imagen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biblioteca_mas_info);

        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googlemaps);
        nombre = findViewById(R.id.biblioteca_nombre);
        direccion = findViewById(R.id.biblioteca_direccion);
        zipcode = findViewById(R.id.biblioteca_zipcode);
        telefono = findViewById(R.id.biblioteca_telefono);
        imagen = findViewById(R.id.Biblioteca_imagen);


        biblioteca = (Biblioteca) getIntent().getSerializableExtra("Biblioteca");
        nombre.setText(biblioteca.getNombre());
        direccion.setText(biblioteca.getDireccion());
        zipcode.setText(biblioteca.getZipcode());
        telefono.setText(biblioteca.getTelefono());

        Glide.with(this)
                .asBitmap()
                .load(biblioteca.getImagen())
                .into(imagen);


        GPS gps = new GPS(this);
        if (gps.getIsGPSTrackingEnabled()){
            String stringLongitude = String.valueOf(gps.getLongitude());
            Toast.makeText(this, stringLongitude, Toast.LENGTH_LONG).show();
            supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(@NonNull GoogleMap googleMap) {
                    LatLng latLng = getLocationFromAddress(getApplicationContext(), direccion.getText().toString());

                    MarkerOptions options = new MarkerOptions().position(latLng).title(nombre.getText().toString());
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                    googleMap.addMarker(options);

                }
            });

        }
        else {
            //GPS NO ESTA ACTIVADO, PREGUNTA POR SI QUIERE ACTIVARLO
            gps.showSettingsAlert();
        }

    }


    public void Volver(View View) {
        finish();

    }

    public LatLng getLocationFromAddress(Context context,String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }

            Address location = address.get(0);
            p1 = new LatLng(location.getLatitude(), location.getLongitude() );

        } catch (IOException ex) {

            ex.printStackTrace();
        }

        return p1;
    }

}