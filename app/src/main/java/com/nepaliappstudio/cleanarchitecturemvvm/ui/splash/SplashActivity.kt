package com.nepaliappstudio.cleanarchitecturemvvm.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nepaliappstudio.cleanarchitecturemvvm.R
import com.nepaliappstudio.cleanarchitecturemvvm.ui.home.HomeActivity

//______________________________________|
//| Auth0r: (C) 7/29/2022 with <3 :)    |
//| >> Prakash Bahadur Chand (Thakuri)  |
//|    prakashbahadurchand@gmail.com    |
//--------------------------------------|


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Intent(this, HomeActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(it)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

    }
}