package com.example.thinkmarket.data

import com.example.thinkmarket.data.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import java.io.IOException


/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class UserDataSource(private var auth: FirebaseAuth, private var db: FirebaseFirestore) {
    fun login(email: String, senha: String): Result<FirebaseUser> {
        return try {
            auth.signInWithEmailAndPassword(email, senha)
            Result.Success(auth.currentUser!!)
        } catch (e: Throwable) {
            Result.Error(IOException("Error logging in", e))
        }
    }

    fun createUser(nome: String, cpfCnpj: String, endereco: String, telefone: String, email: String, senha: String): Result<FirebaseUser> {
        return try {
            val user = User(nome = nome, cpfCnpj = cpfCnpj, endereco = endereco, telefone = telefone)
            auth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener{task ->
                if(task.isSuccessful) {
                    db.collection("users").document(task.result.user!!.uid).set(user)
                }else{
                    throw Exception()
                }
            }
            Result.Success(auth.currentUser!!)
        } catch (e: Throwable) {
            Result.Error(IOException("Error cadastro in", e))
        }
    }
    fun logout() {

    }
}