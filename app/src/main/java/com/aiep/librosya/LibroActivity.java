package com.aiep.librosya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.aiep.librosya.entidades.Libro;
import com.aiep.librosya.entidades.PersonaLibros;
import com.bumptech.glide.Glide;

public class LibroActivity extends AppCompatActivity {

    TextView titulo, author, sinopsis, valoracion;
    ImageView imagen;
    Button leer, reservar;
    int user_id;
    Libro libro;
    PersonaLibros personaLibros;
    //RatingBar bar;

    int idLibro;
    DBHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.libro);



        Bundle extras = getIntent().getExtras();
        user_id = extras.getInt("user_id");


        titulo = findViewById(R.id.book_reserva_titulo);
        author = findViewById(R.id.book_reserva_author);
        sinopsis = findViewById(R.id.book_reserva_sinopsis);
        valoracion = findViewById(R.id.book_reserva_valoracion);
        imagen = findViewById(R.id.book_reserva_imagen);

        leer = findViewById(R.id.btn_leer);
        reservar = findViewById(R.id.btn_reservar);


        try {
            libro = (Libro) getIntent().getSerializableExtra("Libro");
            idLibro = libro.getId();
            titulo.setText(libro.getNombre());
            author.setText(libro.getAuthor());
            sinopsis.setText(libro.getSinopsis());
            valoracion.setText(""+libro.getValoracion());
            Glide.with(this)
                    .asBitmap()
                    .load(libro.getImagen())
                    .into(imagen);

        }catch (Exception e){

            personaLibros = (PersonaLibros) getIntent().getSerializableExtra("Libro");
            idLibro = personaLibros.getId();
            titulo.setText(personaLibros.getNombre());
            author.setText(personaLibros.getAuthor());
            sinopsis.setText(personaLibros.getSinopsis());
            valoracion.setText(""+personaLibros.getValoracion());Glide.with(this)
                    .asBitmap()
                    .load(personaLibros.getImagen())
                    .into(imagen);

        }



        check_book();

        //bar = findViewById(R.id.book_reserva_stars);


    }

    public void Reservar(View view){
        Intent i = new Intent(this, ReservaActivity.class);
        i.putExtra("user_id", user_id);
        i.putExtra("libro_id", libro.getId());
        startActivity(i);
        finish();


    }

    private void check_book(){

        DBHelper conn=new DBHelper(this,"LibrosYa",null,1);
        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT 1 FROM personaslibros WHERE idPersona = "+ user_id +" AND idLibro = "+ idLibro , null);
        cursor.moveToFirst();
        if ((cursor != null) && (cursor.getCount() > 0)){
            reservar.setVisibility(View.GONE);
            leer.setVisibility(View.VISIBLE);
        }else {
            reservar.setVisibility(View.VISIBLE);
            leer.setVisibility(View.GONE);
        }

    }

    public void volver(View view){
        finish();

    }

}