package com.nepaliappstudio.cleanarchitecturemvvm.ui.home.profile

import androidx.lifecycle.ViewModel
import com.nepaliappstudio.cleanarchitecturemvvm.data.repositories.UserRepository

class ProfileViewModel(
    userRepository: UserRepository
) : ViewModel() {

    val user = userRepository.getUser()

}