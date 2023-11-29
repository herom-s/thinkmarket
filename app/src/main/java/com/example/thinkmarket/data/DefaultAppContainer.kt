package com.example.thinkmarket.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DefaultAppContainer : AppContainer {
    private var auth : FirebaseAuth = Firebase.auth
    private var db : FirebaseFirestore = Firebase.firestore

    override val userRepository: UserRepository by lazy {
       UserRepository(UserDataSource(auth, db))
    }

}