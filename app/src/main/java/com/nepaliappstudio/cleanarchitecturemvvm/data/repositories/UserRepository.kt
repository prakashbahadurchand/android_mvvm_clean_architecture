package com.nepaliappstudio.cleanarchitecturemvvm.data.repositories

import com.nepaliappstudio.cleanarchitecturemvvm.data.db.AppDatabase
import com.nepaliappstudio.cleanarchitecturemvvm.data.db.entities.User
import com.nepaliappstudio.cleanarchitecturemvvm.data.network.MyApi
import com.nepaliappstudio.cleanarchitecturemvvm.data.network.SafeApiRequest
import com.nepaliappstudio.cleanarchitecturemvvm.data.network.responses.AuthResponse

class UserRepository(
    private val api: MyApi,
    private val db: AppDatabase
) : SafeApiRequest() {

    suspend fun userLogin(email: String, password: String): AuthResponse {
        return apiRequest { api.userLogin(email, password) }
    }

    suspend fun userSignup(name: String, email: String, password: String) : AuthResponse {
        return apiRequest { api.userSignup(name, email, password) }
    }

    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getUser()

}