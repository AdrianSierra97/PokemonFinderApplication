package com.example.pokemonfinderapplication.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pokemonfinderapplication.R
import com.example.pokemonfinderapplication.presentation.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}