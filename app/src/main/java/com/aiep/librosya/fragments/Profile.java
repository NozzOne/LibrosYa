package com.aiep.librosya.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.aiep.librosya.admin.AddBook;
import com.aiep.librosya.DBHelper;
import com.aiep.librosya.HomeActivity;
import com.aiep.librosya.MainActivity;
import com.aiep.librosya.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile extends Fragment implements View.OnClickListener {
    DBHelper conn;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Profile() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static Profile newInstance(String param1, String param2) {
        Profile fragment = new Profile();
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
        DBHelper conn = new DBHelper(getContext(),"LibrosYa",null,1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        TextView nombre = (TextView) view.findViewById(R.id.nombre_usuario);
        TextView email = (TextView) view.findViewById(R.id.correo_txt);
        conn = new DBHelper(getContext(),"LibrosYa",null,1);

        HomeActivity activity = (HomeActivity) getActivity();
        int user_id =  activity.getUser_id();

        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT nombre, email FROM persona WHERE id = "+ user_id, null);

        if (cursor.moveToFirst()){
            nombre.setText(cursor.getString(0));
            email.setText(cursor.getString(1));
        }else {
            nombre.setText("No se puedo cargar");
            email.setText("No se puedo cargar");
        }

        Button agregarLibro = (Button) view.findViewById(R.id.add_book);
        agregarLibro.setOnClickListener((View.OnClickListener) this);
        Button agregarCategoria = (Button) view.findViewById(R.id.add_category);
        agregarCategoria.setOnClickListener((View.OnClickListener) this);
        Button cerrar = (Button) view.findViewById(R.id.cerrar_sesion);
        cerrar.setOnClickListener((View.OnClickListener) this);

        Cursor cursor1 = db.rawQuery("SELECT 1 FROM persona WHERE persona.id = "+ user_id +" AND persona.id = 1", null);
        cursor1.moveToFirst();

        if (cursor1.getCount() != 0){
            agregarLibro.setVisibility(view.VISIBLE);
            agregarCategoria.setVisibility(view.VISIBLE);

        }else {
            agregarLibro.setVisibility(view.GONE);
            agregarCategoria.setVisibility(view.GONE);

        }

        return view;
    }

    @Override
    public void onClick(View v){
        if (v.getId() == R.id.cerrar_sesion){
            Intent salir = new Intent(v.getContext(), MainActivity.class);
            startActivity( salir);
            getActivity().finish();
        } else if (v.getId() == R.id.add_book){
            Intent add_book = new Intent(v.getContext(), AddBook.class);
            startActivity(add_book);
        }
    }


}