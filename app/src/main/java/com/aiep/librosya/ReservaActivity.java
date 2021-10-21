package com.aiep.librosya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aiep.librosya.utils.Utils;

public class ReservaActivity extends AppCompatActivity {

    int user_id;
    int libro_id;
    Button reserva, fisica;
    LinearLayout ly;
    Animation anim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservar);


        anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);


        Bundle extras = getIntent().getExtras();
        user_id = extras.getInt("user_id");
        libro_id = extras.getInt("libro_id");

        reserva = (Button) findViewById(R.id.btn_lecturaDigital);
        fisica = (Button) findViewById(R.id.btn_reservarFisica);

        ly = (LinearLayout) findViewById(R.id.layout_check);
        ly.setVisibility(View.GONE);



    }

    public void ReservaDigital(View view){
        DBHelper conn=new DBHelper(this,"LibrosYa",null,1);
        SQLiteDatabase db = conn.getReadableDatabase();
        ly.setVisibility(View.VISIBLE);
        ly.startAnimation(anim);
        ContentValues values=new ContentValues();
        values.put(Utils.CAMPO_IDPERSONA,""+user_id);
        values.put(Utils.CAMPO_IDLIBRO,""+libro_id);
        values.put(Utils.CAMPO_PORCENTAJE,"0");

        db.insert(Utils.TABLA_PERSONASLIBROS,null,values);
        reserva.setVisibility(View.GONE);
        fisica.setVisibility(View.GONE);

        ly.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 1000);



    }




}