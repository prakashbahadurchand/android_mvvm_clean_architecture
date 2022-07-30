package com.nepaliappstudio.cleanarchitecturemvvm.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nepaliappstudio.cleanarchitecturemvvm.R
import com.nepaliappstudio.cleanarchitecturemvvm.data.db.entities.User
import com.nepaliappstudio.cleanarchitecturemvvm.databinding.ActivitySignupBinding
import com.nepaliappstudio.cleanarchitecturemvvm.ui.home.HomeActivity
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

//______________________________________|
//| Auth0r: (C) 7/29/2022 with <3 :)    |
//| >> Prakash Bahadur Chand (Thakuri)  |
//|    prakashbahadurchand@gmail.com    |
//--------------------------------------|


class SignupActivity : AppCompatActivity(), AuthListener, KodeinAware {

    override val kodein by kodein()

    private val factory: AuthViewModelFactory by instance()
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        val viewModel = ViewModelProvider(this, factory)[AuthViewModel::class.java]
        binding.viewmodel = viewModel
        viewModel.authListener = this
        setContentView(binding.root)

        viewModel.getLoggedInUser().observe(this, Observer { user ->
            if (user != null) {
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })

    }

    override fun onStarted() {
    }

    override fun onSuccess(user: User) {
    }

    override fun onFailure(message: String) {
    }


}