package com.example.thinkmarket.data.model

/**
 * Data class that captures user information for logged in users retrieved from UserRepository
 */
data class User(
    val nome: String,
    val cpfCnpj: String,
    val endereco: String,
    val telefone: String,
)