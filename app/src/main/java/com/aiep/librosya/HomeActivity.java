package com.aiep.librosya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.aiep.librosya.fragments.Library;
import com.aiep.librosya.fragments.Profile;
import com.aiep.librosya.fragments.Search;
import com.aiep.librosya.fragments.Start;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity{

    Start start = new Start();
    Search search = new Search();
    Library library = new Library();
    Profile profile = new Profile();

    RecyclerView Scroll;

    TextView user_name;

    int user_id;


    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        Bundle extras = getIntent().getExtras();
        user_id = extras.getInt("user_id");



        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.start:
                    loadFragment(start);

                    return true;
                case R.id.search:
                    loadFragment(search);

                    return true;
                case R.id.library:
                    loadFragment(library);

                    return true;
                case R.id.profile:
                    loadFragment(profile);

                    return true;

            }

            return false;
        });
        if (savedInstanceState == null){
            navigation.setSelectedItemId(R.id.start);
        }


    }

    public void loadFragment(Fragment fragment){

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.left_to_right, R.anim.right_to_left, R.anim.left_to_right, R.anim.right_to_left);
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }

    public int getUser_id(){
        return user_id;
    }



}