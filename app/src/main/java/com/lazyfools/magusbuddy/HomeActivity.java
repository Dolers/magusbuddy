package com.lazyfools.magusbuddy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import android.arch.lifecycle.ViewModelProviders;

import com.lazyfools.magusbuddy.battle.BattleFragment;
import com.lazyfools.magusbuddy.character.CharacterListFragment;
import com.lazyfools.magusbuddy.dreonar.DreonarCharacterHandler;


public class HomeActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private ViewPager viewPager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.navigation_home:{
                    viewPager.setCurrentItem(0);
                    break;
                }
                case R.id.navigation_battle:{
                    viewPager.setCurrentItem(1);
                    break;
                }
                case R.id.navigation_skills:{

                }
            }
            return true;
        }
    };
    private CharacterListFragment characterListFragment;
    private BottomNavigationView bottomNavigation;
    private DatabaseViewModel mViewModel;
    private DreonarCharacterHandler mDreonarCharacterHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mViewModel = ViewModelProviders.of(this).get(DatabaseViewModel.class);
        mDreonarCharacterHandler = new DreonarCharacterHandler(getApplication());

        mTextMessage = (TextView) findViewById(R.id.message);
        viewPager = findViewById(R.id.mainViewPager);
        setupViewPager();
        bottomNavigation = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigation.getMenu().getItem(0).setChecked(false);
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void setupViewPager()
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        characterListFragment= CharacterListFragment.newInstance(mViewModel);
        BattleFragment battleFragment = BattleFragment.newInstance(mViewModel);
        adapter.addFragment(characterListFragment);
        adapter.addFragment(battleFragment);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                switch (bottomNavigation.getSelectedItemId()){
                    case R.id.navigation_home:{
                        bottomNavigation.getMenu().getItem(0).setChecked(false);
                        break;
                    }
                    case R.id.navigation_battle:{
                        bottomNavigation.getMenu().getItem(1).setChecked(false);
                        break;
                    }
                    case R.id.navigation_skills:{
                        //bottomNavigation.getMenu().getItem(2).setChecked(false);
                    }
                }
                bottomNavigation.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }
}
