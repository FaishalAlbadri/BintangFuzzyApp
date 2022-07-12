package com.bintang.fuzzyapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.bintang.fuzzyapp.R;
import com.bintang.fuzzyapp.Util.SessionManager;

import java.util.HashMap;

public class SplashScreen extends AppCompatActivity {

    private static SessionManager sessionManager;
    private static HashMap<String, String> hashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        sessionManager = new SessionManager(getApplicationContext());
        hashMap = sessionManager.getData();

        if (hashMap.get(SessionManager.key_permintaan_min) == null) {
            sessionManager.permintaanMin("45000");
            sessionManager.permintaanMax("75000");
            sessionManager.persediaanMin("5000");
            sessionManager.persediaanMax("13000");
            sessionManager.produksiMin("25000");
            sessionManager.produksiMax("75000");
        }

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }, 3000);
    }
}