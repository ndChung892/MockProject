package com.example.mockproject.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.mockproject.OpenNavListener;
import com.example.mockproject.R;
import com.example.mockproject.databinding.ActivityHomeBinding;
import com.example.mockproject.model.MenuModel;
import com.example.mockproject.view.adapter.MenuAdapter;
import com.example.mockproject.view.adapter.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.xml.transform.Transformer;

public class HomeActivity extends AppCompatActivity implements OpenNavListener {
    private static final String TAG = "HomeActivity";
    ActivityHomeBinding mBinding;
    private List<MenuModel> menuModelList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        Log.i("123", "onCreate: ");

        //Setup ViewPager
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        mBinding.viewPager.setAdapter(viewPagerAdapter);

        //Create navHost and add BottomNav to navController
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavController navController = Objects.requireNonNull(navHostFragment).getNavController();
        setUpBottomNav(navController);


        setUpViewPager();
        setUpRcvNav();
    }

    private void setUpRcvNav() {
        initDataMenu();
        Log.d(TAG, "setUpRcvNav: "+ menuModelList);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this);

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(this,layoutManager.VERTICAL);

        mBinding.rcvMenu.addItemDecoration(dividerItemDecoration);
        mBinding.rcvMenu.setLayoutManager(layoutManager);
        MenuAdapter menuAdapter = new MenuAdapter(menuModelList);
        mBinding.rcvMenu.setAdapter(menuAdapter);
    }

    private void setUpBottomNav(NavController navController) {
        NavigationUI.setupWithNavController(mBinding.bottomNav, navController);
        mBinding.bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        mBinding.viewPager.setCurrentItem(0);
                        break;
                    case R.id.action_song:
                        mBinding.viewPager.setCurrentItem(1);
                        break;
                    case R.id.action_setting:
                        mBinding.viewPager.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });
    }

    private void initDataMenu(){
        menuModelList.add(new MenuModel(getResources().getDrawable(R.drawable.ic_theme,null), "Theme"));
        menuModelList.add(new MenuModel(getResources().getDrawable(R.drawable.ic_cutter,null),"RingTone Cutter"));
        menuModelList.add(new MenuModel(getResources().getDrawable(R.drawable.ic_sleep_timer,null),"Sleep Timer"));
        menuModelList.add(new MenuModel(getResources().getDrawable(R.drawable.ic_quliser, null),"Equliser"));
        menuModelList.add(new MenuModel(getResources().getDrawable(R.drawable.ic_drive_mode, null),"Drive Mode"));
        menuModelList.add(new MenuModel(getResources().getDrawable(R.drawable.ic_hidden_folder, null),"Hidden Folder"));
        menuModelList.add(new MenuModel(getResources().getDrawable(R.drawable.ic_scan_media, null),"Scan Media"));
    }

    @Override
    public void onBackPressed() {
        if (mBinding.drawerlayout.isDrawerOpen(GravityCompat.START)) {
            mBinding.drawerlayout.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }

    //set Item in ViewPager2
    private void setUpViewPager() {
        mBinding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        mBinding.bottomNav.getMenu().findItem(R.id.action_home).setChecked(true);
                        break;
                    case 1:
                        mBinding.bottomNav.getMenu().findItem(R.id.action_song).setChecked(true);
                        break;
                    case 2:
                        mBinding.bottomNav.getMenu().findItem(R.id.action_setting).setChecked(true);
                        break;

                }
            }
        });
    }

    @Override
    public void navListener() {
        if (mBinding.drawerlayout.isDrawerOpen(GravityCompat.START)) {
            mBinding.drawerlayout.close();
        } else {
            mBinding.drawerlayout.openDrawer(GravityCompat.START);
        }
    }
}