package com.nepaliappstudio.cleanarchitecturemvvm.data.network.responses

import com.nepaliappstudio.cleanarchitecturemvvm.data.db.entities.User

data class AuthResponse(
    val isSuccessful: Boolean?,
    val message: String?,
    val user: User?
) {



}