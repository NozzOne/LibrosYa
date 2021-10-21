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

import com.aiep.librosya.DBHelper;
import com.aiep.librosya.HomeActivity;
import com.aiep.librosya.LibroActivity;
import com.aiep.librosya.R;
import com.aiep.librosya.adapters.LibraryAdapter;
import com.aiep.librosya.adapters.RecentAdapter;
import com.aiep.librosya.entidades.PersonaLibros;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Library#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Library extends Fragment {
    DBHelper conn;
    List<PersonaLibros> lista_libros;
    int user_id;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Library() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Library.
     */
    // TODO: Rename and change types and number of parameters
    public static Library newInstance(String param1, String param2) {
        Library fragment = new Library();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        View view = inflater.inflate(R.layout.fragment_library, container, false);
        HomeActivity activity = (HomeActivity) getActivity();
        user_id = activity.getUser_id();
        StartLibrary(view);

        return view;
    }

    private void StartLibrary(View view){
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

        LibraryAdapter libraryAdapter = new LibraryAdapter(lista_libros, getContext(), new LibraryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(PersonaLibros item) {
                MoveToRecentBook(item);
            }
        });

        RecyclerView libraryView = view.findViewById(R.id.library_recycler);
        libraryView.setHasFixedSize(false);
        libraryView.setLayoutManager(new LinearLayoutManager(getContext()));
        libraryView.setAdapter(libraryAdapter);
    }

    public void MoveToRecentBook(PersonaLibros item){
        Intent intent = new Intent(getContext(), LibroActivity.class);
        intent.putExtra("Libro", item);
        intent.putExtra("user_id", user_id);
        startActivity(intent);
    }

}