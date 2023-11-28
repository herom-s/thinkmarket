package com.example.thinkmarket.data

import com.google.firebase.auth.FirebaseUser

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class UserRepository(val dataSource: UserDataSource) {

    // in-memory cache of the loggedInUser object
    var user: FirebaseUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout() {
        user = null
        dataSource.logout()
    }

    fun login(username: String, password: String): Result<FirebaseUser> {
        val result = dataSource.login(username, password)

        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }

        return result
    }

    fun createUser(nome: String, cpfCnpj: String, endereco: String, telefone: String, email: String, senha: String): Result<FirebaseUser> {
        val result = dataSource.createUser(nome, cpfCnpj, endereco, telefone, email, senha)
        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }
        return result
    }

    fun createEmpresa(nome: String, cnpj: String, telefone: String, email: String, senha: String): Result<FirebaseUser> {
        val result = dataSource.createEmpresa(nome, cnpj, telefone, email, senha)
        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }
        return result
    }

    private fun setLoggedInUser(user: FirebaseUser) {
        this.user = user
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}