package com.example.thinkmarket.ui.cadastro_empresa

data class CadastroEmpresaFormState(
    val nomeError: Int? = null,
    val cnpjError: Int? = null,
    val telefoneError: Int? = null,
    val emailError: Int? = null,
    val senhaError: Int? = null,
    val isDataValid: Boolean = false,
)
