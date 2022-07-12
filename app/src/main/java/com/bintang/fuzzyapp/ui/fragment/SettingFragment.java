package com.bintang.fuzzyapp.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bintang.fuzzyapp.R;
import com.bintang.fuzzyapp.Util.SessionManager;
import com.bintang.fuzzyapp.ui.fragment.dialog.EditSettingDialogFragment;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingFragment extends Fragment {


    @BindView(R.id.txt_min_permintaan_value)
    TextView txtMinPermintaanValue;
    @BindView(R.id.txt_max_permintaan_value)
    TextView txtMaxPermintaanValue;
    @BindView(R.id.txt_min_persediaan_value)
    TextView txtMinPersediaanValue;
    @BindView(R.id.txt_max_persediaan_value)
    TextView txtMaxPersediaanValue;
    @BindView(R.id.txt_min_produksi_value)
    TextView txtMinProduksiValue;
    @BindView(R.id.txt_max_produksi_value)
    TextView txtMaxProduksiValue;

    private static SessionManager sessionManager;
    private static HashMap<String, String> hashMap;
    private FragmentManager fragmentManager;

    public SettingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this, view);

        fragmentManager = getActivity().getSupportFragmentManager();

        setData();

        return view;
    }

    private void setData() {
        sessionManager = new SessionManager(getActivity());
        hashMap = sessionManager.getData();
        txtMinPersediaanValue.setText(hashMap.get(SessionManager.key_persediaan_min));
        txtMaxPersediaanValue.setText(hashMap.get(SessionManager.key_persediaan_max));
        txtMinPermintaanValue.setText(hashMap.get(SessionManager.key_permintaan_min));
        txtMaxPermintaanValue.setText(hashMap.get(SessionManager.key_permintaan_max));
        txtMinProduksiValue.setText(hashMap.get(SessionManager.key_produksi_min));
        txtMaxProduksiValue.setText(hashMap.get(SessionManager.key_produksi_max));
    }


    @OnClick(R.id.txt_min_persediaan_value)
    public void onClickMinPersediaan() {
        Bundle args = new Bundle();
        args.putString("judul", "Persediaan Minimum");
        args.putString("value", hashMap.get(SessionManager.key_persediaan_min));
        EditSettingDialogFragment alert = new EditSettingDialogFragment(this);
        alert.setArguments(args);
        alert.show(fragmentManager, "");
    }

    @OnClick(R.id.txt_max_persediaan_value)
    public void onClickMaxPersediaan() {
        Bundle args = new Bundle();
        args.putString("judul", "Persediaan Maximum");
        args.putString("value", hashMap.get(SessionManager.key_persediaan_max));
        EditSettingDialogFragment alert = new EditSettingDialogFragment(this);
        alert.setArguments(args);
        alert.show(fragmentManager, "");
    }

    @OnClick(R.id.txt_min_permintaan_value)
    public void onClickMinpermintaan() {
        Bundle args = new Bundle();
        args.putString("judul", "Permintaan Minimum");
        args.putString("value", hashMap.get(SessionManager.key_permintaan_min));
        EditSettingDialogFragment alert = new EditSettingDialogFragment(this);
        alert.setArguments(args);
        alert.show(fragmentManager, "");
    }

    @OnClick(R.id.txt_max_permintaan_value)
    public void onClickMaxpermintaan() {
        Bundle args = new Bundle();
        args.putString("judul", "Permintaan Maximum");
        args.putString("value", hashMap.get(SessionManager.key_permintaan_max));
        EditSettingDialogFragment alert = new EditSettingDialogFragment(this);
        alert.setArguments(args);
        alert.show(fragmentManager, "");
    }

    @OnClick(R.id.txt_min_produksi_value)
    public void onClickMinproduksi() {
        Bundle args = new Bundle();
        args.putString("judul", "Produksi Minimum");
        args.putString("value", hashMap.get(SessionManager.key_produksi_min));
        EditSettingDialogFragment alert = new EditSettingDialogFragment(this);
        alert.setArguments(args);
        alert.show(fragmentManager, "");
    }

    @OnClick(R.id.txt_max_produksi_value)
    public void onClickMaxproduksi() {
        Bundle args = new Bundle();
        args.putString("judul", "Produksi Maximum");
        args.putString("value", hashMap.get(SessionManager.key_produksi_max));
        EditSettingDialogFragment alert = new EditSettingDialogFragment(this);
        alert.setArguments(args);
        alert.show(fragmentManager, "");
    }

    public void editMinPersediaan(String value) {
        sessionManager.persediaanMin(value);
        setData();
    }

    public void editMaxPersediaan(String value) {
        sessionManager.persediaanMax(value);
        setData();
    }

    public void editMinPermintaan(String value) {
        sessionManager.permintaanMin(value);
        setData();
    }

    public void editMaxPermintaan(String value) {
        sessionManager.permintaanMax(value);
        setData();
    }

    public void editMinProduksi(String value) {
        sessionManager.produksiMin(value);
        setData();
    }

    public void editMaxProduksi(String value) {
        sessionManager.produksiMax(value);
        setData();
    }

}