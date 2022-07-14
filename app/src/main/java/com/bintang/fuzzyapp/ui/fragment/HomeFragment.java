package com.bintang.fuzzyapp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bintang.fuzzyapp.R;
import com.bintang.fuzzyapp.Util.SessionManager;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment extends Fragment {

    @BindView(R.id.edt_persediaan)
    MaterialEditText edtPersediaan;
    @BindView(R.id.edt_permintaan)
    MaterialEditText edtPermintaan;
    @BindView(R.id.btn_prediksi)
    Button btnPrediksi;

    private static SessionManager sessionManager;
    private static HashMap<String, String> hashMap;
    @BindView(R.id.txt_rendah_kurva_persediaan_value)
    TextView txtRendahKurvaPersediaanValue;
    @BindView(R.id.txt_tinggi_kurva_persediaan_value)
    TextView txtTinggiKurvaPersediaanValue;
    @BindView(R.id.txt_rendah_kurva_permintaan_value)
    TextView txtRendahKurvaPermintaanValue;
    @BindView(R.id.txt_tinggi_kurva_permintaan_value)
    TextView txtTinggiKurvaPermintaanValue;
    @BindView(R.id.txt_a_rules1_value)
    TextView txtARules1Value;
    @BindView(R.id.txt_z_rules1_value)
    TextView txtZRules1Value;
    @BindView(R.id.txt_a_rules2_value)
    TextView txtARules2Value;
    @BindView(R.id.txt_z_rules2_value)
    TextView txtZRules2Value;
    @BindView(R.id.txt_a_rules3_value)
    TextView txtARules3Value;
    @BindView(R.id.txt_z_rules3_value)
    TextView txtZRules3Value;
    @BindView(R.id.txt_a_rules4_value)
    TextView txtARules4Value;
    @BindView(R.id.txt_z_rules4_value)
    TextView txtZRules4Value;
    @BindView(R.id.txt_hasil_value)
    TextView txtHasilValue;
    @BindView(R.id.edt_barang_rusak)
    MaterialEditText edtBarangRusak;
    @BindView(R.id.txt_rendah_kurva_barang_rusak_value)
    TextView txtRendahKurvaBarangRusakValue;
    @BindView(R.id.txt_tinggi_kurva_barang_rusak_value)
    TextView txtTinggiKurvaBarangRusakValue;
    @BindView(R.id.txt_a_rules5_value)
    TextView txtARules5Value;
    @BindView(R.id.txt_z_rules5_value)
    TextView txtZRules5Value;
    @BindView(R.id.txt_a_rules6_value)
    TextView txtARules6Value;
    @BindView(R.id.txt_z_rules6_value)
    TextView txtZRules6Value;
    @BindView(R.id.txt_a_rules7_value)
    TextView txtARules7Value;
    @BindView(R.id.txt_z_rules7_value)
    TextView txtZRules7Value;
    @BindView(R.id.txt_a_rules8_value)
    TextView txtARules8Value;
    @BindView(R.id.txt_z_rules8_value)
    TextView txtZRules8Value;

    private String barangRusakVal, persediaanVal, permintaanVal;

    private Double kurvaBarangRusakRendah, kurvaBarangRusakTinggi;
    private Double kurvaPersediaanSeragamRendah, kurvaPersediaanSeragamTinggi;
    private Double kurvaPermintaanSeragamRendah, kurvaPermintaanSeragamTinggi;
    private Double a1, z1, a2, z2, a3, z3, a4, z4, a5, z5, a6, z6, a7, z7, a8, z8;
    private int hasil;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        setData();
        return view;
    }

    @OnClick(R.id.btn_prediksi)
    public void onClick() {
        if (edtPersediaan.getText().toString().isEmpty() || edtPermintaan.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), "TIdak Bisa Prediksi Karena Masih Ada Data Yang Kosong", Toast.LENGTH_SHORT).show();
        } else {
            barangRusakVal = edtBarangRusak.getText().toString();
            permintaanVal = edtPermintaan.getText().toString();
            persediaanVal = edtPersediaan.getText().toString();
            hitungKurvaBarangRusak();
        }
    }

    private void hitungKurvaBarangRusak() {
        double barangRusakValInt = Double.parseDouble(barangRusakVal);
        double min = Double.parseDouble(hashMap.get(SessionManager.key_barang_rusak_min));
        double max = Double.parseDouble(hashMap.get(SessionManager.key_barang_rusak_max));

        if (barangRusakValInt <= min) {
            kurvaBarangRusakRendah = Double.valueOf(1);
            kurvaBarangRusakTinggi = Double.valueOf(0);
        } else if (barangRusakValInt >= min && barangRusakValInt <= max) {
            kurvaBarangRusakRendah = round((max - barangRusakValInt) / (max - min), 2);
            kurvaBarangRusakTinggi = round((barangRusakValInt - min) / (max - min), 2);
        } else if (barangRusakValInt >= max) {
            kurvaBarangRusakRendah = Double.valueOf(0);
            kurvaBarangRusakTinggi = Double.valueOf(1);
        }

        txtRendahKurvaBarangRusakValue.setText(kurvaBarangRusakRendah + "");
        txtTinggiKurvaBarangRusakValue.setText(kurvaBarangRusakTinggi + "");

        hitungKurvaPersediaanSeragam();
    }

    private void hitungKurvaPersediaanSeragam() {
        double persediaanValInt = Double.parseDouble(persediaanVal);
        double min = Double.parseDouble(hashMap.get(SessionManager.key_persediaan_min));
        double max = Double.parseDouble(hashMap.get(SessionManager.key_persediaan_max));

        if (persediaanValInt <= min) {
            kurvaPersediaanSeragamRendah = Double.valueOf(1);
            kurvaPersediaanSeragamTinggi = Double.valueOf(0);
        } else if (persediaanValInt >= min && persediaanValInt <= max) {
            kurvaPersediaanSeragamRendah = round((max - persediaanValInt) / (max - min), 2);
            kurvaPersediaanSeragamTinggi = round((persediaanValInt - min) / (max - min), 2);
        } else if (persediaanValInt >= max) {
            kurvaPersediaanSeragamRendah = Double.valueOf(0);
            kurvaPersediaanSeragamTinggi = Double.valueOf(1);
        }

        txtRendahKurvaPersediaanValue.setText(kurvaPersediaanSeragamRendah + "");
        txtTinggiKurvaPersediaanValue.setText(kurvaPersediaanSeragamTinggi + "");

        hitungKurvaPermintaanSeragam();
    }

    private void hitungKurvaPermintaanSeragam() {
        double permintaanValInt = Double.parseDouble(permintaanVal);
        double min = Double.parseDouble(hashMap.get(SessionManager.key_permintaan_min));
        double max = Double.parseDouble(hashMap.get(SessionManager.key_permintaan_max));

        if (permintaanValInt <= min) {
            kurvaPermintaanSeragamRendah = Double.valueOf(1);
            kurvaPermintaanSeragamTinggi = Double.valueOf(0);
        } else if (permintaanValInt >= min && permintaanValInt <= max) {
            kurvaPermintaanSeragamRendah = round((max - permintaanValInt) / (max - min), 2);
            kurvaPermintaanSeragamTinggi = round((permintaanValInt - min) / (max - min), 2);
        } else if (permintaanValInt >= max) {
            kurvaPermintaanSeragamRendah = Double.valueOf(0);
            kurvaPermintaanSeragamTinggi = Double.valueOf(1);
        }

        txtRendahKurvaPermintaanValue.setText(kurvaPermintaanSeragamRendah + "");
        txtTinggiKurvaPermintaanValue.setText(kurvaPermintaanSeragamTinggi + "");

        rules1();
    }

    private void rules1() {
        double min = Double.parseDouble(hashMap.get(SessionManager.key_produksi_min));
        double max = Double.parseDouble(hashMap.get(SessionManager.key_produksi_max));

        a1 = min3value(kurvaBarangRusakTinggi, kurvaPersediaanSeragamTinggi, kurvaPermintaanSeragamTinggi);
        z1 = round(max - (a1 * (max - min)), 2);

        txtARules1Value.setText(a1 + "");
        txtZRules1Value.setText(z1 + "");

        rules2();
    }

    private void rules2() {
        double min = Double.parseDouble(hashMap.get(SessionManager.key_produksi_min));
        double max = Double.parseDouble(hashMap.get(SessionManager.key_produksi_max));

        a2 = min3value(kurvaBarangRusakTinggi, kurvaPersediaanSeragamRendah, kurvaPermintaanSeragamTinggi);
        z2 = round(max - (a2 * (max - min)), 2);

        txtARules2Value.setText(a2 + "");
        txtZRules2Value.setText(z2 + "");

        rules3();
    }

    private void rules3() {
        double min = Double.parseDouble(hashMap.get(SessionManager.key_produksi_min));
        double max = Double.parseDouble(hashMap.get(SessionManager.key_produksi_max));

        a3 = min3value(kurvaBarangRusakTinggi, kurvaPersediaanSeragamTinggi, kurvaPermintaanSeragamRendah);
        z3 = round(max - (a3 * (max - min)), 2);

        txtARules3Value.setText(a3 + "");
        txtZRules3Value.setText(z3 + "");
        rules4();
    }

    private void rules4() {
        double min = Double.parseDouble(hashMap.get(SessionManager.key_produksi_min));
        double max = Double.parseDouble(hashMap.get(SessionManager.key_produksi_max));

        a4 = min3value(kurvaBarangRusakTinggi, kurvaPersediaanSeragamRendah, kurvaPermintaanSeragamRendah);
        z4 = round(max - (a4 * (max - min)), 2);

        txtARules4Value.setText(a4 + "");
        txtZRules4Value.setText(z4 + "");

        rules5();
    }

    private void rules5() {
        double min = Double.parseDouble(hashMap.get(SessionManager.key_produksi_min));
        double max = Double.parseDouble(hashMap.get(SessionManager.key_produksi_max));

        a5 = min3value(kurvaBarangRusakRendah, kurvaPersediaanSeragamRendah, kurvaPermintaanSeragamTinggi);
        z5 = round(max - (a5 * (max - min)), 2);

        txtARules5Value.setText(a5 + "");
        txtZRules5Value.setText(z5 + "");

        rules6();
    }

    private void rules6() {
        double min = Double.parseDouble(hashMap.get(SessionManager.key_produksi_min));
        double max = Double.parseDouble(hashMap.get(SessionManager.key_produksi_max));

        a6 = min3value(kurvaBarangRusakRendah, kurvaPersediaanSeragamTinggi, kurvaPermintaanSeragamTinggi);
        z6 = round(max - (a6 * (max - min)), 2);

        txtARules6Value.setText(a6 + "");
        txtZRules6Value.setText(z6 + "");

        rules7();
    }

    private void rules7() {
        double min = Double.parseDouble(hashMap.get(SessionManager.key_produksi_min));
        double max = Double.parseDouble(hashMap.get(SessionManager.key_produksi_max));

        a7 = min3value(kurvaBarangRusakRendah, kurvaPersediaanSeragamTinggi, kurvaPermintaanSeragamRendah);
        z7 = round(max - (a7 * (max - min)), 2);

        txtARules7Value.setText(a7 + "");
        txtZRules7Value.setText(z7 + "");

        rules8();
    }

    private void rules8() {
        double min = Double.parseDouble(hashMap.get(SessionManager.key_produksi_min));
        double max = Double.parseDouble(hashMap.get(SessionManager.key_produksi_max));

        a8 = min3value(kurvaBarangRusakRendah, kurvaPersediaanSeragamRendah, kurvaPermintaanSeragamRendah);
        z8 = round(max - (a8 * (max - min)), 2);

        txtARules8Value.setText(a8 + "");
        txtZRules8Value.setText(z8 + "");

        defuzzyfikasi();
    }

    private void defuzzyfikasi() {
        Double x = ((a1 * z1) + (a2 * z2) + (a3 * z3) + (a4 * z4) + (a5 * z5) + (a6 * z6) + (a7 * z7) + (a8 * z8));
        Double y = a1 + a2 + a3 + a4 + a5 + a6 + a7 + a8;
        Double xy = x / y;

        hasil = xy.intValue();

        txtHasilValue.setText(hasil + "");
    }

    public void setData() {
        sessionManager = new SessionManager(getActivity());
        hashMap = sessionManager.getData();
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public static double min3value(double a, double b, double c) {
        return Math.min(Math.min(a, b), c);
    }
}