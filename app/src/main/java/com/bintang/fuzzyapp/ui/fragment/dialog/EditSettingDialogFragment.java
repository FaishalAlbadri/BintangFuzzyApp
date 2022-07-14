package com.bintang.fuzzyapp.ui.fragment.dialog;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.bintang.fuzzyapp.R;
import com.bintang.fuzzyapp.ui.fragment.SettingFragment;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditSettingDialogFragment extends DialogFragment {

    @BindView(R.id.txt_toolbar)
    TextView txtToolbar;
    @BindView(R.id.edt_edit)
    MaterialEditText edtEdit;
    @BindView(R.id.btn_edit)
    Button btnEdit;

    private String judul, value;
    private SettingFragment settingFragment;

    public EditSettingDialogFragment(SettingFragment settingFragment) {
        this.settingFragment = settingFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_setting_dialog, container, false);
        ButterKnife.bind(this, view);

        getDataIntent();

        setView();
        return view;
    }

    private void setView() {
        txtToolbar.setText("Edit " + judul);
        edtEdit.setHint(judul);
        edtEdit.setText(value);
    }

    private void getDataIntent() {
        Bundle bundle = getArguments();
        judul = bundle.getString("judul");
        value = bundle.getString("value");
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @OnClick(R.id.btn_edit)
    public void onClick() {
        if (edtEdit.getText().toString().isEmpty()) {
            edtEdit.setError("Data tidak boleh kosong");
            edtEdit.requestFocus();
        } else {

            if (judul.equals("Barang Rusak Minimum")) {
                settingFragment.editMinBarangRusak(edtEdit.getText().toString());
                dismiss();
            } else if (judul.equals("Barang Rusak Maximum")) {
                settingFragment.editMaxBarangRusak(edtEdit.getText().toString());
                dismiss();
            } else if (judul.equals("Persediaan Minimum")) {
                settingFragment.editMinPersediaan(edtEdit.getText().toString());
                dismiss();
            } else if (judul.equals("Persediaan Maximum")) {
                settingFragment.editMaxPersediaan(edtEdit.getText().toString());
                dismiss();
            } else if (judul.equals("Permintaan Minimum")) {
                settingFragment.editMinPermintaan(edtEdit.getText().toString());
                dismiss();
            } else if (judul.equals("Permintaan Maximum")) {
                settingFragment.editMaxPermintaan(edtEdit.getText().toString());
                dismiss();
            } else if (judul.equals("Produksi Minimum")) {
                settingFragment.editMinProduksi(edtEdit.getText().toString());
                dismiss();
            } else if (judul.equals("Produksi Maximum")) {
                settingFragment.editMaxProduksi(edtEdit.getText().toString());
                dismiss();
            }
        }
    }
}