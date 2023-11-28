package com.example.thinkmarket.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.thinkmarket.data.UserDataSource
import com.example.thinkmarket.data.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class LoginViewModelFactory : ViewModelProvider.Factory {
    private var auth : FirebaseAuth = Firebase.auth
    private var db : FirebaseFirestore = Firebase.firestore
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                userRepository = UserRepository(
                    dataSource = UserDataSource(auth, db)
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}