package com.nepaliappstudio.cleanarchitecturemvvm.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.nepaliappstudio.cleanarchitecturemvvm.data.repositories.UserRepository
import com.nepaliappstudio.cleanarchitecturemvvm.util.ApiException
import com.nepaliappstudio.cleanarchitecturemvvm.util.Coroutines
import com.nepaliappstudio.cleanarchitecturemvvm.util.NoInternetException

class AuthViewModel(
    private val repository: UserRepository
) : ViewModel() {

    var name: String? = null
    var email: String? = null
    var password: String? = null
    var passwordConfirm: String? = null

    var authListener: AuthListener? = null

    fun getLoggedInUser() = repository.getUser()

    fun onLoginButtonClick(view: View) {
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invalid email or password")
            return
        }

        Coroutines.main {

            try {
                val authResponse = repository.userLogin(email!!, password!!)
                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            } catch (e: ApiException) {
                authListener?.onFailure("Error Code: ${e.message}")
            } catch (e: NoInternetException) {
                authListener?.onFailure(e.message!!)
            }
        }

    }

    fun onSignupButtonClick(view: View) {
        authListener?.onStarted()

        if (name.isNullOrEmpty()) {
            authListener?.onFailure("Name is required")
            return
        }

        if (email.isNullOrEmpty()) {
            authListener?.onFailure("Email is required")
            return
        }

        if (password.isNullOrEmpty()) {
            authListener?.onFailure("Password is required")
            return
        }

        if (passwordConfirm.isNullOrEmpty()) {
            authListener?.onFailure("Password confirm is required")
            return
        }

        if (!password.equals(passwordConfirm)) {
            authListener?.onFailure("Both password and password confirm must be exact same")
            return
        }

        Coroutines.main {

            try {
                val authResponse = repository.userSignup(name!!, email!!, password!!)
                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            } catch (e: ApiException) {
                authListener?.onFailure("Error Code: ${e.message}")
            } catch (e: NoInternetException) {
                authListener?.onFailure(e.message!!)
            }
        }

    }

    fun gotoSignup(view: View) {
        Intent(view.context, SignupActivity::class.java).also {
            //it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            view.context.startActivity(it)
        }
    }

    fun gotoLogin(view: View) {
        Intent(view.context, LoginActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            view.context.startActivity(it)
        }
    }


}