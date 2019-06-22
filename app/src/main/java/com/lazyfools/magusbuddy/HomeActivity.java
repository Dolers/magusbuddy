package com.lazyfools.magusbuddy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import android.arch.lifecycle.ViewModelProviders;

import com.lazyfools.magusbuddy.page.battle.BattleFragment;
import com.lazyfools.magusbuddy.page.character.CharacterListFragment;
import com.lazyfools.magusbuddy.dreonar.DreonarCharacterHandler;
import com.lazyfools.magusbuddy.page.skill.QualificationCategoryListFragment;


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
                    viewPager.setCurrentItem(2);
                }
            }
            return true;
        }
    };
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

        CharacterListFragment characterListFragment= CharacterListFragment.newInstance(mViewModel);
        BattleFragment battleFragment = BattleFragment.newInstance(mViewModel);
        QualificationCategoryListFragment qualificationCategoryListFragment = QualificationCategoryListFragment.newInstance(mViewModel);

        adapter.addFragment(characterListFragment);
        adapter.addFragment(battleFragment);
        adapter.addFragment(qualificationCategoryListFragment);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                for (int i =0; i<viewPager.getAdapter().getCount();i++)
                    bottomNavigation.getMenu().getItem(i).setChecked(false);

                bottomNavigation.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }
}
