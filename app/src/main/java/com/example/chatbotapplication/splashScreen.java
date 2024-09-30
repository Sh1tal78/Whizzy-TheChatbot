package com.example.chatbotapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class splashScreen extends AppCompatActivity {

    private static final int SPLASH_SCREEN_TIME_OUT = 3500; // Duration for splash screen (3 seconds)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        // Delay to show splash screen for a few seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // After the delay, launch the main activity
                Intent intent = new Intent(splashScreen.this, MainActivity.class);
                startActivity(intent);
                finish(); // Close the splash screen activity
            }
        }, SPLASH_SCREEN_TIME_OUT);
    }
}
