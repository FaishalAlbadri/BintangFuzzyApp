package com.bintang.fuzzyapp.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

    private String persediaanVal, permintaanVal;

    private Double kurvaPersediaanSeragamRendah, kurvaPersediaanSeragamTinggi;
    private Double kurvaPermintaanSeragamRendah, kurvaPermintaanSeragamTinggi;
    private Double a1, z1, a2, z2, a3, z3, a4, z4;
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
        if (edtPersediaan.getText().toString().isEmpty() && edtPermintaan.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), "TIdak Bisa Prediksi Karena Masih Ada Data Yang Kosong", Toast.LENGTH_SHORT).show();
        } else {
            permintaanVal = edtPermintaan.getText().toString();
            persediaanVal = edtPersediaan.getText().toString();
            hitungKurvaPersediaanSeragam();
        }
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

        a1 = Math.min(kurvaPersediaanSeragamTinggi, kurvaPermintaanSeragamTinggi);
        z1 = round(max - (a1 * (max - min)), 2);

        txtARules1Value.setText(a1 + "");
        txtZRules1Value.setText(z1 + "");

        rules2();
    }

    private void rules2() {
        double min = Double.parseDouble(hashMap.get(SessionManager.key_produksi_min));
        double max = Double.parseDouble(hashMap.get(SessionManager.key_produksi_max));

        a2 = Math.min(kurvaPersediaanSeragamTinggi, kurvaPermintaanSeragamRendah);
        z2 = round(max - (a2 * (max - min)), 2);

        txtARules2Value.setText(a2 + "");
        txtZRules2Value.setText(z2 + "");

        rules3();
    }

    private void rules3() {
        double min = Double.parseDouble(hashMap.get(SessionManager.key_produksi_min));
        double max = Double.parseDouble(hashMap.get(SessionManager.key_produksi_max));

        a3 = Math.min(kurvaPersediaanSeragamRendah, kurvaPermintaanSeragamTinggi);
        z3 = round(max - (a3 * (max - min)), 2);

        txtARules3Value.setText(a3 + "");
        txtZRules3Value.setText(z3 + "");
        rules4();
    }

    private void rules4() {
        double min = Double.parseDouble(hashMap.get(SessionManager.key_produksi_min));
        double max = Double.parseDouble(hashMap.get(SessionManager.key_produksi_max));

        a4 = Math.min(kurvaPersediaanSeragamRendah, kurvaPermintaanSeragamRendah);
        z4 = round(max - (a4 * (max - min)), 2);

        txtARules4Value.setText(a4 + "");
        txtZRules4Value.setText(z4 + "");

        defuzzyfikasi();
    }

    private void defuzzyfikasi() {
        Double x = ((a1 * z1) + (a2 * z2) + (a3 * z3) + (a4 * z4));
        Double y = a1 + a2 + a3 + a4;
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
}