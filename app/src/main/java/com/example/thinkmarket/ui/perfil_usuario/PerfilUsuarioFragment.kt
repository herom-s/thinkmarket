package com.example.thinkmarket.ui.perfil_usuario

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thinkmarket.R

class PerfilUsuarioFragment : Fragment() {

    companion object {
        fun newInstance() = PerfilUsuarioFragment()
    }

    private lateinit var viewModel: PerfilUsuarioViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_perfil_usuario, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PerfilUsuarioViewModel::class.java)
        // TODO: Use the ViewModel
    }

}