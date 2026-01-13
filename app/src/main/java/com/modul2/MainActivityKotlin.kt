package com.modul2

import android.os.Bundle
import androidx.activity.ComponentActivity

class MainActivityKotlin : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.relative_layout)
    }
}
