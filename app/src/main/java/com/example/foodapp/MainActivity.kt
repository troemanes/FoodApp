package com.example.foodapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //bottomnav and controller
        val bottomNav =  findViewById<BottomNavigationView>(R.id.bottom_nav)
        val navController = Navigation.findNavController(this,R.id.hostfragment)
        NavigationUI.setupWithNavController(bottomNav,navController)
    }
}