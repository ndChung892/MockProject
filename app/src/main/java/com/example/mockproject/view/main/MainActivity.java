package com.example.mockproject.view.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.mockproject.databinding.ActivityMainBinding;
import com.example.mockproject.view.OpenNavListener;
import com.example.mockproject.R;
import com.example.mockproject.view.main.fragmentelement.song.Song;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements OpenNavListener{
    private static final String TAG = "MainActivity";
    private List<Song> songList = new ArrayList<>();
    ActivityMainBinding mBinding;
    private List<MenuModel> mMenuModelList= new ArrayList<>();
    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        Log.i("123", "onCreate: ");

        //Setup ViewPager
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        mBinding.viewPager.setAdapter(viewPagerAdapter);

        //Create navHost and add BottomNav to navController
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavController navController = Objects.requireNonNull(navHostFragment).getNavController();
        setUpBottomNav(navController);

//        //check permission
//        boolean hasPermission = hasPermission();
//        if(hasPermission){
//            LoaderManager.getInstance(this).initLoader(1, null, this);
//
//        }else{
//            ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                    1);
//        }
//
//        setUpViewPager();
//        setUpRcvNav();
    }

    private boolean hasPermission() {
        return (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }

    private void setUpRcvNav() {
//        initDataMenu();

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this);

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(this,layoutManager.VERTICAL);
        mBinding.rcvMenu.addItemDecoration(dividerItemDecoration);
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        mainActivityViewModel.getListMenuModelLiveData().observe(this, new Observer<List<MenuModel>>() {
            @Override
            public void onChanged(List<MenuModel> menuModelList) {
                Log.d(TAG, "onChanged: "+ menuModelList);

                mBinding.rcvMenu.setLayoutManager(layoutManager);
                MenuAdapter menuAdapter = new MenuAdapter(menuModelList);
                mBinding.rcvMenu.setAdapter(menuAdapter);
            }
        });

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