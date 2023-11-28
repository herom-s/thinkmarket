package com.example.thinkmarket.ui.register

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.thinkmarket.R
import com.example.thinkmarket.databinding.FragmentCadastroUsuarioBinding
import com.example.thinkmarket.ui.login.LoginFragment

class CadastroUsuarioFragment : Fragment() {

    private lateinit var cadastroUsuarioViewModel: CadastroUsuarioViewModel
    private var _binding: FragmentCadastroUsuarioBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCadastroUsuarioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cadastroUsuarioViewModel = ViewModelProvider(this, CadastroUsuarioViewModelFactory())[CadastroUsuarioViewModel::class.java]

        val nomeEditText = binding.txtfNome
        val cpfCnpjEditText = binding.txtfCpfCnpj
        val enderecoEditText = binding.txtfEndereco
        val telefoneEditText = binding.txtfTelefone
        val emailEditText = binding.txtfEmail
        val senhaEditText = binding.txtfSenha
        val cadastrarButton = binding.btnCadastrar
        val voltarButton = binding.btnVoltar

        cadastroUsuarioViewModel.cadastroFormState.observe(viewLifecycleOwner,
            Observer { cadastroFormState ->
                if (cadastroFormState == null) {
                    return@Observer
                }
                cadastrarButton.isEnabled = cadastroFormState.isDataValid

                cadastroFormState.nomeError?.let {
                    nomeEditText.error = getString(it)
                }

                cadastroFormState.cpfCnpjError?.let {
                    cpfCnpjEditText.error = getString(it)
                }

                cadastroFormState.enderecoError?.let {
                    enderecoEditText.error = getString(it)
                }

                cadastroFormState.telefoneError?.let {
                    telefoneEditText.error = getString(it)
                }

                cadastroFormState.emailError?.let {
                    emailEditText.error = getString(it)
                }
                cadastroFormState.senhaError?.let {
                    senhaEditText.error = getString(it)
                }
            })

        cadastroUsuarioViewModel.cadastroResult.observe(viewLifecycleOwner,
            Observer { cadastroResult ->
                cadastroResult ?: return@Observer
                cadastroResult.error?.let {
                    showCadastroFailed(it)
                }
                cadastroResult.success?.let {
                    updateUiWithUser(it)
                }
            })

        val afterTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // ignore
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // ignore
            }

            override fun afterTextChanged(s: Editable) {
                cadastroUsuarioViewModel.cadastroDataChanged(
                    nomeEditText.text.toString(),
                    cpfCnpjEditText.text.toString(),
                    enderecoEditText.text.toString(),
                    telefoneEditText.text.toString(),
                    emailEditText.text.toString(),
                    senhaEditText.text.toString()
                )
            }
        }
        emailEditText.addTextChangedListener(afterTextChangedListener)
        senhaEditText.addTextChangedListener(afterTextChangedListener)

        cadastrarButton.setOnClickListener {
            cadastroUsuarioViewModel.cadastro(
                nomeEditText.text.toString(),
                cpfCnpjEditText.text.toString(),
                enderecoEditText.text.toString(),
                telefoneEditText.text.toString(),
                emailEditText.text.toString(),
                senhaEditText.text.toString()
            )
        }

        voltarButton.setOnClickListener {
            replaceFragment(LoginFragment())
        }
    }

    private fun updateUiWithUser(model: CadastradoUserView) {
        val welcome = getString(R.string.msg_boas_vindas) + model.displayName
        // TODO : initiate successful logged in experience
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, welcome, Toast.LENGTH_LONG).show()
    }

    private fun showCadastroFailed(@StringRes errorString: Int) {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, errorString, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun replaceFragment(fragment : Fragment) {
        requireActivity().supportFragmentManager.beginTransaction().replace(R.id.layout_container, fragment).commit()
    }

}