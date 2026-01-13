package com.example.intentpraktikum;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.util.Locale;

public class LuasJajarGenjangActivity extends AppCompatActivity {

    private TextInputEditText etAlas, etTinggi;
    private MaterialTextView tvHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luas_jajar_genjang);

        etAlas = findViewById(R.id.etAlas);
        etTinggi = findViewById(R.id.etTinggi);
        tvHasil = findViewById(R.id.tvHasil);

        Button btnHitung = findViewById(R.id.btnHitung);
        Button btnReset = findViewById(R.id.btnReset);

        btnHitung.setOnClickListener(v -> hitung());
        btnReset.setOnClickListener(v -> reset());
    }

    private void hitung() {
        String alasStr = etAlas.getText() != null ? etAlas.getText().toString().trim() : "";
        String tinggiStr = etTinggi.getText() != null ? etTinggi.getText().toString().trim() : "";

        if (TextUtils.isEmpty(alasStr)) {
            etAlas.setError("Alas wajib diisi");
            etAlas.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(tinggiStr)) {
            etTinggi.setError("Tinggi wajib diisi");
            etTinggi.requestFocus();
            return;
        }

        try {
            double alas = Double.parseDouble(alasStr);
            double tinggi = Double.parseDouble(tinggiStr);

            if (alas <= 0 || tinggi <= 0) {
                Toast.makeText(this, "Nilai harus > 0", Toast.LENGTH_SHORT).show();
                return;
            }

            double luas = alas * tinggi;
            tvHasil.setText(String.format(Locale.getDefault(), "Luas = %.2f", luas));

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Input tidak valid. Gunakan angka.", Toast.LENGTH_SHORT).show();
        }
    }

    private void reset() {
        etAlas.setText("");
        etTinggi.setText("");
        tvHasil.setText("Luas = -");
        etAlas.requestFocus();
    }
}
