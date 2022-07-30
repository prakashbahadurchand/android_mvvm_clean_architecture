package com.nepaliappstudio.cleanarchitecturemvvm.ui.auth

import com.nepaliappstudio.cleanarchitecturemvvm.data.db.entities.User

interface AuthListener {

    fun onStarted()
    fun onSuccess(user: User)
    fun onFailure(message: String)

}