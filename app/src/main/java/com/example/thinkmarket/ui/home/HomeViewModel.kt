package com.example.thinkmarket.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.thinkmarket.ThinkMarketApplication
import com.example.thinkmarket.data.UserRepository
import com.example.thinkmarket.ui.login.LoginViewModel

class HomeViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun logout() {
        userRepository.logout()
    }

    fun isAdmin(): Boolean {
        return if(userRepository.isLoggedIn){
            userRepository.user!!.email!!.contains("admin")
        }else{
            false
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ThinkMarketApplication)
                val userRepository = application.container.userRepository
                LoginViewModel(userRepository  = userRepository)
            }
        }
    }
}