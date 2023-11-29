package com.example.thinkmarket.ui.home

import androidx.lifecycle.ViewModel
import com.example.thinkmarket.data.UserRepository

class HomeViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun logout() {
        userRepository.logout()
    }
}