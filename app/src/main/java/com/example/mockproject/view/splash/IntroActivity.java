package com.example.mockproject.view.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mockproject.R;
import com.example.mockproject.databinding.ActivityIntroBinding;
import com.example.mockproject.view.main.MainActivity;


public class IntroActivity extends AppCompatActivity {
    public static final int SPLASH_TIME_OUT = 3500;
    ActivityIntroBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent introIntent = new Intent(IntroActivity.this, MainActivity.class);
                startActivity(introIntent);
                finish();

            }
        }, SPLASH_TIME_OUT);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.fadein);
        mBinding.iconFlag.startAnimation(animation);
        mBinding.txtIntro.startAnimation(animation);
//        hideActionBar();


    }

    public void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
}