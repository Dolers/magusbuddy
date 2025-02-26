package com.lazyfools.magusbuddy;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;


public class HomeActivity extends AppCompatActivity {
    private NavController navController;
    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        bottomNavigation = findViewById(R.id.navigation);
        NavigationUI.setupWithNavController(bottomNavigation,navController);

        // Other views not used yet, keep it hidden
        bottomNavigation.setVisibility(View.GONE);
    }

    public void setBottomNavigationVisibility(int value){
        // Other views not used yet, keep it hidden
        //bottomNavigation.setVisibility(value);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                navController.navigateUp();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
