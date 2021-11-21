package com.aiep.librosya.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.aiep.librosya.R;

public class ManageBooks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_books);
    }


    public void addBook(View v){
        Intent addbook = new Intent(v.getContext(), AddBook.class);
        startActivity(addbook);
    }

    public void Volver(View v){
        finish();
    }

}