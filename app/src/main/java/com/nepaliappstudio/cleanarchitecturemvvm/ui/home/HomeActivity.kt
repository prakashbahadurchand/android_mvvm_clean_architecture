package com.nepaliappstudio.cleanarchitecturemvvm.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.nepaliappstudio.cleanarchitecturemvvm.R

//______________________________________|
//| Auth0r: (C) 7/29/2022 with <3 :)    |
//| >> Prakash Bahadur Chand (Thakuri)  |
//|    prakashbahadurchand@gmail.com    |
//--------------------------------------|


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        setSupportActionBar(findViewById(R.id.toolbar))

        val navController = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupActionBarWithNavController(findViewById(R.id.nav_view), navController)
        NavigationUI.setupActionBarWithNavController(this, findViewById(R.id.drawer_layout))

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            Navigation.findNavController(this, R.id.fragment),
            findViewById(R.id.drawer_layout)
        )
    }

}





