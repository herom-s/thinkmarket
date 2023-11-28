package com.example.thinkmarket.ui.register

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thinkmarket.R
import com.example.thinkmarket.data.Result
import com.example.thinkmarket.data.UserRepository

class CadastroUsuarioViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _cadastroForm = MutableLiveData<CadastroUsuarioFormState>()
    val cadastroFormState: LiveData<CadastroUsuarioFormState> = _cadastroForm

    private val _cadastroResult = MutableLiveData<CadastroUsuarioResult>()
    val cadastroResult: LiveData<CadastroUsuarioResult> = _cadastroResult

    fun cadastro(
        nome: String,
        cpfCnpj: String,
        endereco: String,
        telefone: String,
        email: String,
        senha: String,
    ) {
        // can be launched in a separate asynchronous job
        val result = userRepository.create(nome, cpfCnpj, endereco, telefone, email, senha)

        if (result is Result.Success) {
            _cadastroResult.value =
                CadastroUsuarioResult(success = CadastradoUserView(displayName = result.data.displayName!!))
        } else {
            _cadastroResult.value = CadastroUsuarioResult(error = R.string.falha_cadastro)
        }
    }

    fun cadastroDataChanged(
        nome: String,
        cpfCnpj: String,
        endereco: String,
        telefone: String,
        email: String,
        senha: String,
    ) {
        if (!isNomeValid(nome)) {
            _cadastroForm.value = CadastroUsuarioFormState(nomeError = R.string.error_nome)
        } else if (!isCpfCnpjValid(cpfCnpj)) {
            _cadastroForm.value = CadastroUsuarioFormState(cpfCnpjError = R.string.error_cpfCnpj)
        } else if (!isEnderecoValid(endereco)) {
            _cadastroForm.value = CadastroUsuarioFormState(enderecoError = R.string.error_endereco)
        } else if (!isTelefoneValid(telefone)) {
            _cadastroForm.value = CadastroUsuarioFormState(telefoneError = R.string.error_telefone)
        } else if (!isEmailValid(email)) {
            _cadastroForm.value = CadastroUsuarioFormState(emailError = R.string.error_email)
        } else if (!isSenhaValid(senha)) {
            _cadastroForm.value = CadastroUsuarioFormState(senhaError = R.string.error_senha)
        } else {
            _cadastroForm.value = CadastroUsuarioFormState(isDataValid = true)
        }
    }

    private fun isNomeValid(nome: String): Boolean {
        return nome.isNotEmpty()
    }

    private fun isCpfCnpjValid(cpfCnpj: String): Boolean {
        return cpfCnpj.isNotEmpty()
    }

    private fun isEnderecoValid(endereco: String): Boolean {
        return endereco.isNotEmpty()
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