package com.lazyfools.magusbuddy;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;

import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;


public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNavigation = findViewById(R.id.navigation);
        NavigationUI.setupWithNavController(bottomNavigation,
                Navigation.findNavController(this, R.id.nav_host_fragment));
    }
    /*
    @Override public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search_menu, menu);
        return true;
    }
/*
    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_icon:
                onSearchRequested();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/
}
