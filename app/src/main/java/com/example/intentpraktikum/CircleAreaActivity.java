package com.example.intentpraktikum;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CircleAreaActivity extends AppCompatActivity {

    private EditText inputRadius;
    private Button btnCalculate;
    private TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_area);

        inputRadius = findViewById(R.id.inputRadius);
        btnCalculate = findViewById(R.id.btnCalculate);
        textResult = findViewById(R.id.textResult);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String radiusStr = inputRadius.getText().toString().trim();

                if (!radiusStr.isEmpty()) {
                    try {
                        double r = Double.parseDouble(radiusStr);
                        double luas = Math.PI * r * r;
                        textResult.setText("Luas: " + String.format("%.2f", luas));
                    } catch (NumberFormatException e) {
                        inputRadius.setError("Masukkan nilai r yang valid");
                    }
                } else {
                    inputRadius.setError("Masukkan nilai r");
                }
            }
        });
    }
}
