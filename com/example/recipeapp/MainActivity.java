package com.example.recipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler handler = new Handler(Looper.getMainLooper());
               handler.postDelayed(
                        new Runnable() {
                            @Override
                            public void run() {
                                startActivity(new Intent(MainActivity.this,Home_Activity.class));
                                finish();
                            }
                        }, 2000);


    }
}