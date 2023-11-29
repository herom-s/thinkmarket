package com.example.thinkmarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.thinkmarket.databinding.FragmentLoginBinding

class ActLogin : Fragment() {

    private lateinit var _binding: FragmentLoginBinding


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCadastroLogin.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_cadastroUsuarioFragment)
        }
        binding.btnLoginLogin.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }
        binding.btnLoginLogin.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_actEsqSenha)
        }
    }
}