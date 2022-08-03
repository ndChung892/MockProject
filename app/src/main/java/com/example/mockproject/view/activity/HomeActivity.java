package com.example.mockproject.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.mockproject.R;
import com.example.mockproject.view.adapter.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import javax.xml.transform.Transformer;

public class HomeActivity extends AppCompatActivity {
    private BottomNavigationView mNavigationView;
    private ViewPager2 mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        hideActionBar();
        mViewPager = findViewById(R.id.viewPager);

        mNavigationView = findViewById(R.id.bottom_nav);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        mViewPager.setAdapter(viewPagerAdapter);
        mNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.action_song:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.action_setting:
                        mViewPager.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });
        setUpViewPager();
    }

    private void setUpViewPager() {
        mViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        mNavigationView.getMenu().findItem(R.id.action_home).setChecked(true);
                        break;
                    case 1:
                        mNavigationView.getMenu().findItem(R.id.action_song).setChecked(true);
                        break;
                    case 2:
                        mNavigationView.getMenu().findItem(R.id.action_setting).setChecked(true);
                        break;

                }
            }
        });

    }


    public void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
}