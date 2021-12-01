package com.minhien.musicappasm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.minhien.musicappasm.MainActivity2;
import com.minhien.musicappasm.R;

public class SplashActivity extends AppCompatActivity {
    private static final long SPLASH_TIME_OUT = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                Intent i = new Intent(SplashActivity.this, MainActivity2.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}