package com.aiep.librosya.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aiep.librosya.R;

public class ManageBiblioteca extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_biblioteca);

    }

    public void Agregar(View v){
        Intent agregar = new Intent(v.getContext(), AddLibrary.class);
        startActivity(agregar);
    }

    public void Mostrar(View v){
        Intent mostrar = new Intent(v.getContext(), FetchLibrary.class);
        startActivity(mostrar);
    }


    public void Volver(View v){
        finish();
    }


}