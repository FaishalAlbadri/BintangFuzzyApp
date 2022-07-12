package com.bintang.fuzzyapp.Util;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ActivityUtil {


    private static ActivityUtil INSTANCE;
    private Context context;


    public ActivityUtil(Context context) {
        this.context = context;
    }

    public static ActivityUtil getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new ActivityUtil(context);
        }

        return INSTANCE;
    }

    public void addFragment(FragmentManager fm, int frame_layout, Fragment fragment) {
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(frame_layout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}

