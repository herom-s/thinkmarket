package com.example.thinkmarket.ui.cadastro_usuario

data class CadastroUsuarioFormState(
    val nomeError: Int? = null,
    val cpfCnpjError: Int? = null,
    val enderecoError: Int? = null,
    val telefoneError: Int? = null,
    val emailError: Int? = null,
    val senhaError: Int? = null,
    val isDataValid: Boolean = false,
)
