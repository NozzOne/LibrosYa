package com.aiep.librosya.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.aiep.librosya.DBHelper;
import com.aiep.librosya.R;
import com.aiep.librosya.entidades.Categorias;
import com.aiep.librosya.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class AddBook extends AppCompatActivity {

    DBHelper conn;
    List<Categorias> listCat;
    List<String> listaCategorias;
    Spinner categoria1, categoria2, categoria3, categoria4;
    EditText titulo, author, sinopsis, paginas, url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_book);

        categoria1 = findViewById(R.id.lista_categoria1);
        categoria2 = findViewById(R.id.lista_categoria2);
        categoria3 = findViewById(R.id.lista_categoria3);
        categoria4 = findViewById(R.id.lista_categoria4);

        titulo = findViewById(R.id.new_book_title);
        author = findViewById(R.id.new_book_author);
        sinopsis = findViewById(R.id.new_book_sinopsis);
        paginas = findViewById(R.id.new_book_paginas);
        url = findViewById(R.id.new_book_imagen);



        allCategorias();

        ArrayAdapter<CharSequence> adapter1 = new ArrayAdapter(this, R.layout.spinner_item, listaCategorias);
        categoria1.setAdapter(adapter1);
        ArrayAdapter<CharSequence> adapter2 = new ArrayAdapter(this, R.layout.spinner_item, listaCategorias);
        categoria2.setAdapter(adapter2);
        ArrayAdapter<CharSequence> adapter3 = new ArrayAdapter(this, R.layout.spinner_item, listaCategorias);
        categoria3.setAdapter(adapter3);
        ArrayAdapter<CharSequence> adapter4 = new ArrayAdapter(this, R.layout.spinner_item, listaCategorias);
        categoria4.setAdapter(adapter4);
    }

    private void allCategorias(){
        DBHelper conn = new DBHelper(this, "LibrosYa", null, 1);
        SQLiteDatabase db = conn.getReadableDatabase();
        listCat = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utils.TABLA_CATEGORIA, null);


        if (cursor.moveToFirst()){
            do{
                listCat.add(new Categorias(cursor.getInt(0), cursor.getString(1)));
            }while (cursor.moveToNext());
        }

        obtenerLista();

    }


    private void obtenerLista(){
        listaCategorias = new ArrayList<String>();
        listaCategorias.add("Selecccione");

        for (int i=0; i<listCat.size();i++){
            listaCategorias.add(listCat.get(i).getNombre());
        }
    }

    public void InsertarLibro(View view){
        DBHelper conn = new DBHelper(this, "LibrosYa", null, 1);

        SQLiteDatabase db = conn.getWritableDatabase();


        if (titulo.getText().toString().isEmpty()
                || author.getText().toString().isEmpty()
                || sinopsis.getText().toString().isEmpty()
                || paginas.getText().toString().isEmpty()
                || url.getText().toString().isEmpty()
                || categoria1.getSelectedItem().toString() == "Selecccione"
                || categoria2.getSelectedItem().toString() == "Selecccione"
                || categoria3.getSelectedItem().toString() == "Selecccione"
                || categoria4.getSelectedItem().toString() == "Selecccione"){

            Toast.makeText(this, "Hay uno o mas campos incorrectos", Toast.LENGTH_LONG).show();
        } else{

            ContentValues values = new ContentValues();

            values.put(Utils.CAMPO_LIBRO_NOMBRE, titulo.getText().toString());
            values.put(Utils.CAMPO_AUTHOR, author.getText().toString());
            values.put(Utils.CAMPO_SINOPSIS, sinopsis.getText().toString());
            values.put(Utils.CAMPO_PAGINAS, paginas.getText().toString());
            values.put(Utils.CAMPO_IMAGEN_URL, url.getText().toString());

            db.insert(Utils.TABLA_LIBROS, null, values);

            Toast.makeText(this, "Libro Registrado Correctamente", Toast.LENGTH_SHORT).show();
            db.close();

        }



    }



    public void volver(View view){
        finish();
    }

}