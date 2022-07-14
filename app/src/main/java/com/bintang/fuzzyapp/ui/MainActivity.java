package com.bintang.fuzzyapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.bintang.fuzzyapp.R;
import com.bintang.fuzzyapp.Util.ActivityUtil;
import com.bintang.fuzzyapp.ui.fragment.HomeFragment;
import com.bintang.fuzzyapp.ui.fragment.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.frame_home)
    FrameLayout frameHome;
    @BindView(R.id.frame_setting)
    FrameLayout frameSetting;
    @BindView(R.id.nav_bot_home)
    BottomNavigationView navBotHome;

    private HomeFragment homeFragment;
    private SettingFragment settingFragment;
    private ActivityUtil activityUtil;

    private AlertDialog.Builder alertbuild;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        alertbuild = new AlertDialog.Builder(this);

        setView();

        navBotHome.setOnNavigationItemSelectedListener(this);
    }

    private void setView() {
        homeFragment = new HomeFragment();
        settingFragment = new SettingFragment();

        activityUtil = ActivityUtil.getInstance(getApplicationContext());
        activityUtil.addFragment(getSupportFragmentManager(), R.id.frame_home, homeFragment);
        activityUtil.addFragment(getSupportFragmentManager(), R.id.frame_setting, settingFragment);

        frameHome.setVisibility(View.VISIBLE);
        frameSetting.setVisibility(View.GONE);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.home_menu:
                frameHome.setVisibility(View.VISIBLE);
                frameSetting.setVisibility(View.GONE);
                homeFragment.setData();
                break;
            case R.id.setting_menu:
                frameHome.setVisibility(View.GONE);
                frameSetting.setVisibility(View.VISIBLE);
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        alertbuild.setTitle("Keluar Aplikasi")
                .setMessage("Yakinkan anda ingin keluar dari aplikasi Fuzzy Kapasitas Produksi Sepeda?")
                .setPositiveButton("Keluar", (dialog, which) -> {
                    finish();
                }).setNegativeButton("Batal", ((dialog, which) -> {
            dialog.cancel();
        }));
        alertDialog = alertbuild.create();
        alertDialog.show();
    }
}