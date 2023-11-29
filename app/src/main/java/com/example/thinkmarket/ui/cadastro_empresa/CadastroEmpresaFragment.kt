package com.example.thinkmarket.ui.cadastro_empresa

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
import androidx.navigation.fragment.findNavController
import com.example.thinkmarket.R
import com.example.thinkmarket.databinding.FragmentCadastroEmpresaBinding

class CadastroEmpresaFragment : Fragment() {

    private lateinit var cadastroEmpresaViewModel: CadastroEmpresaViewModel
    private var _binding: FragmentCadastroEmpresaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCadastroEmpresaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cadastroEmpresaViewModel = ViewModelProvider(this, CadastroEmpresaViewModelFactory())[CadastroEmpresaViewModel::class.java]

        val nomeEditText = binding.txtfNomeEmpresaCadEmpre
        val cnpjEditText = binding.txtfCnpjCadEmpre
        val telefoneEditText = binding.txtfTelefoneCadEmpre
        val emailEditText = binding.txtfEmailCadEmpre
        val senhaEditText = binding.txtfSenhaCadEmpre
        val cadastrarButton = binding.btnCadastroCadEmpre
        val voltarButton = binding.btnVoltarCadEmpre

        cadastroEmpresaViewModel.cadastroFormState.observe(viewLifecycleOwner,
            Observer { cadastroFormState ->
                if (cadastroFormState == null) {
                    return@Observer
                }
                cadastrarButton.isEnabled = cadastroFormState.isDataValid
                cadastroFormState.nomeError?.let {
                    nomeEditText.error = getString(it)
                }
                cadastroFormState.cnpjError?.let {
                    cnpjEditText.error = getString(it)
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

        cadastroEmpresaViewModel.cadastroResult.observe(viewLifecycleOwner,
            Observer { cadastroResult ->
                cadastroResult ?: return@Observer
                cadastroResult.error?.let {
                    showCadastroFailed(it)
                }
                cadastroResult.success.let {
                    updateUiWithUser()
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
                cadastroEmpresaViewModel.cadastroDataChanged(
                    nomeEditText.text.toString(),
                    cnpjEditText.text.toString(),
                    telefoneEditText.text.toString(),
                    emailEditText.text.toString(),
                    senhaEditText.text.toString()
                )
            }
        }
        emailEditText.addTextChangedListener(afterTextChangedListener)
        senhaEditText.addTextChangedListener(afterTextChangedListener)

        cadastrarButton.setOnClickListener {
            cadastroEmpresaViewModel.cadastro(
                nomeEditText.text.toString(),
                cnpjEditText.text.toString(),
                telefoneEditText.text.toString(),
                emailEditText.text.toString(),
                senhaEditText.text.toString()
            )
        }

        voltarButton.setOnClickListener {
            findNavController().navigate(R.id.action_cadastroEmpresaFragment_to_nav_home)
        }
    }

    private fun updateUiWithUser() {
        val sucess = "Cadastro feito com sucesso"
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, sucess, Toast.LENGTH_LONG).show()
    }

    private fun showCadastroFailed(@StringRes errorString: Int) {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, errorString, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}