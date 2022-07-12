package com.bintang.fuzzyapp.Util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {

    public static final String key_persediaan_min = "key_persediaan_min";
    public static final String key_persediaan_max = "key_persediaan_max";
    public static final String key_permintaan_min = "key_permintaan_min";
    public static final String key_permintaan_max = "key_permintaan_max";
    public static final String key_produksi_min = "key_produksi_min";
    public static final String key_produksi_max = "key_produksi_max";

    public static final String pref_persediaan_min = "pref_persediaan_min";
    public static final String pref_persediaan_max = "pref_persediaan_max";
    public static final String pref_permintaan_min = "pref_permintaan_min";
    public static final String pref_permintaan_max = "pref_permintaan_max";
    public static final String pref_produksi_min = "pref_produksi_min";
    public static final String pref_produksi_max = "pref_produksi_max";

    private Context context;
    private int mode;

    private SharedPreferences persediaan_minPref, persediaan_maxPref, permintaan_minPref, permintaan_maxPref, produksi_minPref, produksi_maxPref;
    private SharedPreferences.Editor persediaan_minEditor, persediaan_maxEditor, permintaan_minEditor, permintaan_maxEditor, produksi_minEditor, produksi_maxEditor;

    public SessionManager(Context context) {
        mode = 0;
        this.context = context;
        persediaan_minPref = context.getSharedPreferences(pref_persediaan_min, mode);
        persediaan_maxPref = context.getSharedPreferences(pref_persediaan_max, mode);
        permintaan_minPref = context.getSharedPreferences(pref_permintaan_min, mode);
        permintaan_maxPref = context.getSharedPreferences(pref_permintaan_max, mode);
        produksi_minPref = context.getSharedPreferences(pref_produksi_min, mode);
        produksi_maxPref = context.getSharedPreferences(pref_produksi_max, mode);

        persediaan_minEditor = persediaan_minPref.edit();
        persediaan_maxEditor = persediaan_maxPref.edit();
        permintaan_minEditor = permintaan_minPref.edit();
        permintaan_maxEditor = permintaan_maxPref.edit();
        produksi_minEditor = produksi_minPref.edit();
        produksi_maxEditor = produksi_maxPref.edit();
    }

    public void persediaanMin(String data) {
        persediaan_minEditor.clear();
        persediaan_minEditor.putString(key_persediaan_min, data);
        persediaan_minEditor.commit();
    }

    public void persediaanMax(String data) {
        persediaan_maxEditor.clear();
        persediaan_maxEditor.putString(key_persediaan_max, data);
        persediaan_maxEditor.commit();
    }

    public void permintaanMin(String data) {
        permintaan_minEditor.clear();
        permintaan_minEditor.putString(key_permintaan_min, data);
        permintaan_minEditor.commit();
    }

    public void permintaanMax(String data) {
        permintaan_maxEditor.clear();
        permintaan_maxEditor.putString(key_permintaan_max, data);
        permintaan_maxEditor.commit();
    }

    public void produksiMin(String data) {
        produksi_minEditor.clear();
        produksi_minEditor.putString(key_produksi_min, data);
        produksi_minEditor.commit();
    }

    public void produksiMax(String data) {
        produksi_maxEditor.clear();
        produksi_maxEditor.putString(key_produksi_max, data);
        produksi_maxEditor.commit();
    }

    public HashMap<String, String> getData() {
        HashMap<String, String> data = new HashMap<>();
        data.put(key_persediaan_min, persediaan_minPref.getString(key_persediaan_min, null));
        data.put(key_persediaan_max, persediaan_maxPref.getString(key_persediaan_max, null));
        data.put(key_permintaan_min, permintaan_minPref.getString(key_permintaan_min, null));
        data.put(key_permintaan_max, permintaan_maxPref.getString(key_permintaan_max, null));
        data.put(key_produksi_min, produksi_minPref.getString(key_produksi_min, null));
        data.put(key_produksi_max, produksi_maxPref.getString(key_produksi_max, null));
        return data;
    }
}
