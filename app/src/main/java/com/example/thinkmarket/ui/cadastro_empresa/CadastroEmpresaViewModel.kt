package com.example.thinkmarket.ui.cadastro_empresa

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thinkmarket.R
import com.example.thinkmarket.data.Result
import com.example.thinkmarket.data.UserRepository

class CadastroEmpresaViewModel(private val userRepository: UserRepository): ViewModel() {

    private val _cadastroForm = MutableLiveData<CadastroEmpresaFormState>()
    val cadastroFormState: LiveData<CadastroEmpresaFormState> = _cadastroForm

    private val _cadastroResult = MutableLiveData<CadastroEmpresaResult>()
    val cadastroResult: LiveData<CadastroEmpresaResult> = _cadastroResult

    fun cadastro(
        nome: String,
        cnpj: String,
        telefone: String,
        email: String,
        senha: String,
    ) {
        // can be launched in a separate asynchronous job
        val result = userRepository.createEmpresa(nome, cnpj, telefone, email, senha)

        if (result is Result.Success) {
            _cadastroResult.value =
                CadastroEmpresaResult(success = true)
        } else {
            _cadastroResult.value = CadastroEmpresaResult(error = R.string.falha_cadastro)
        }
    }

    fun cadastroDataChanged(
        nome: String,
        cnpj: String,
        telefone: String,
        email: String,
        senha: String,
    ) {
        if (!isNomeValid(nome)) {
            _cadastroForm.value = CadastroEmpresaFormState(nomeError = R.string.error_nome)
        } else if (!isCnpjValid(cnpj)) {
            _cadastroForm.value = CadastroEmpresaFormState(cnpjError = R.string.error_cpfCnpj)
        } else if (!isTelefoneValid(telefone)) {
            _cadastroForm.value = CadastroEmpresaFormState(telefoneError = R.string.error_telefone)
        }else if (!isEmailValid(email)) {
            _cadastroForm.value = CadastroEmpresaFormState(emailError = R.string.error_email)
        } else if (!isSenhaValid(senha)) {
            _cadastroForm.value = CadastroEmpresaFormState(senhaError = R.string.error_senha)
        } else {
            _cadastroForm.value = CadastroEmpresaFormState(isDataValid = true)
        }
    }

    private fun isNomeValid(nome: String): Boolean {
        return nome.isNotEmpty()
    }

    private fun isCnpjValid(cnpj: String): Boolean {
        return cnpj.isNotEmpty()
    }

    private fun isTelefoneValid(telefone: String): Boolean {
        return telefone.isNotEmpty()
    }

    private fun isEmailValid(email: String): Boolean {
        return if (email.contains("@")) {
            Patterns.EMAIL_ADDRESS.matcher(email).matches()
        } else {
            email.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isSenhaValid(senha: String): Boolean {
        return senha.length > 5
    }
}