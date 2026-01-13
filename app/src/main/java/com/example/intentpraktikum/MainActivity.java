package com.example.intentpraktikum;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnOpenSecond;
    private Button btnOpenBrowser;
    private Button btnCircleArea;
    private Button btnParallelogramArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOpenSecond = findViewById(R.id.btnOpenSecond);
        btnOpenBrowser = findViewById(R.id.btnOpenBrowser);
        btnCircleArea = findViewById(R.id.btnCircleArea);

        btnOpenSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Explicit Intent ke SecondActivity
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        btnOpenBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Implicit Intent untuk membuka Browser
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.unipma.ac.id/"));
                startActivity(intent);
            }
        });

        btnCircleArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Explicit Intent ke CircleAreaActivity (Latihan Kalkulator Luas Lingkaran)
                Intent intent = new Intent(MainActivity.this, CircleAreaActivity.class);
                startActivity(intent);
            }
        });
        btnParallelogramArea = findViewById(R.id.btnParallelogramArea);

        btnParallelogramArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LuasJajarGenjangActivity.class);
                startActivity(intent);
            }
        });

    }
}
