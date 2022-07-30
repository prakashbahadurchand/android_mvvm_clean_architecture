package com.nepaliappstudio.cleanarchitecturemvvm

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.nepaliappstudio.cleanarchitecturemvvm.data.db.AppDatabase
import com.nepaliappstudio.cleanarchitecturemvvm.data.network.MyApi
import com.nepaliappstudio.cleanarchitecturemvvm.data.network.NetworkConnectionInterceptor
import com.nepaliappstudio.cleanarchitecturemvvm.data.repositories.UserRepository
import com.nepaliappstudio.cleanarchitecturemvvm.ui.auth.AuthViewModelFactory
import com.nepaliappstudio.cleanarchitecturemvvm.ui.home.profile.ProfileViewModelFactory
import com.nepaliappstudio.cleanarchitecturemvvm.util.IS_DEBUG_MODE
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class App : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@App))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { UserRepository(instance(), instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { ProfileViewModelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        if (IS_DEBUG_MODE)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }


}