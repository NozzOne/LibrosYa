package com.aiep.librosya.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aiep.librosya.DBHelper;
import com.aiep.librosya.HomeActivity;
import com.aiep.librosya.LibroActivity;
import com.aiep.librosya.R;
import com.aiep.librosya.adapters.BooksAdapter;
import com.aiep.librosya.adapters.RecentAdapter;
import com.aiep.librosya.entidades.Libro;
import com.aiep.librosya.entidades.PersonaLibros;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Start#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class Start extends Fragment {
    DBHelper conn;
    List<Libro> elements;
    List<PersonaLibros> lista_libros;
    int user_id;



    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;



    public static Start newInstance(String param1, String param2) {
        Start fragment = new Start();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Start() {
        // Required empty public constructor
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_start, container, false);
        HomeActivity activity = (HomeActivity) getActivity();
        user_id = activity.getUser_id();



        StartRecent(view);
        StartPopular(view);



        return view;
    }



    public void StartRecent(View view){

        TextView empty = (TextView) view.findViewById(R.id.cominza_leer);

        DBHelper conn = new DBHelper(getContext(),"LibrosYa",null,1);
        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT personaslibros.idLibro, libro.nombre, libro.author, libro.sinopsis, libro.paginas, libro.valoracion, libro.imagen, personaslibros.porcentaje  FROM personaslibros INNER JOIN libro ON personaslibros.idLibro = libro.id INNER JOIN persona ON personaslibros.idPersona = persona.id WHERE persona.id = "+ user_id +" ORDER BY personaslibros.porcentaje", null);
        lista_libros = new ArrayList<>();

        if (cursor!= null && cursor.getCount() > 0){
            if (cursor.moveToFirst()){
                do {
                    lista_libros.add(new PersonaLibros(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getFloat(5), cursor.getString(6), cursor.getInt(7)));

                } while (cursor.moveToNext());
            }
        }

        RecentAdapter recentAdapter = new RecentAdapter(lista_libros, getContext(), new RecentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(PersonaLibros item) {
                MoveToRecentBook(item);
            }
        });

        RecyclerView recentview = view.findViewById(R.id.recent_book);
        recentview.setHasFixedSize(false);
        LinearLayoutManager mlayout = new LinearLayoutManager(getContext(), recentview.HORIZONTAL, false);
        recentview.setLayoutManager(mlayout);
        recentview.setAdapter(recentAdapter);

        if (mlayout.getItemCount()== 0){
            empty.setVisibility(View.VISIBLE);

        }else {
            empty.setVisibility(view.GONE);
        }



    }

    public void StartPopular(View view){
        DBHelper conn = new DBHelper(getContext(),"LibrosYa",null,1);
        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM libro order by valoracion DESC", null);
        elements = new ArrayList<>();


        if (cursor!=null) {
            if (cursor.moveToFirst()) {
                do {

                    elements.add(new Libro(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getFloat(5), cursor.getString(6)));

                } while (cursor.moveToNext());
            }
        }
        BooksAdapter booksAdapter = new BooksAdapter(elements, getContext(), new BooksAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Libro item) {
                MoveToBook(item);

            }
        });

        RecyclerView popularView =   view.findViewById(R.id.bookRecycler);
        popularView.setHasFixedSize(false);
        popularView.setLayoutManager(new LinearLayoutManager(getContext()));
        popularView.setAdapter(booksAdapter);
    }


    public void MoveToRecentBook(PersonaLibros item){
        Intent intent = new Intent(getContext(), LibroActivity.class);
        intent.putExtra("Libro", item);
        intent.putExtra("user_id", user_id);
        startActivity(intent);
    }

    public void MoveToBook(Libro item){
        Intent intent = new Intent(getContext(), LibroActivity.class);
        intent.putExtra("Libro", item);
        intent.putExtra("user_id", user_id);
        startActivity(intent);
    }






}