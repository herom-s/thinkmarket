package com.example.thinkmarket.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.thinkmarket.R
import com.example.thinkmarket.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var toggle: ActionBarDrawerToggle

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel = ViewModelProvider(this, HomeViewModelFactory())[HomeViewModel::class.java]
        super.onCreate(savedInstanceState)

        binding.apply {
            toggle= ActionBarDrawerToggle(activity, drawerLayout, R.string.open, R.string.close)
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()
            activity?.actionBar?.setDisplayHomeAsUpEnabled(true)
            navView.setNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.nav_ordem ->{
                        findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
                    }
                    R.id.nav_empresa ->{
                        findNavController().navigate(R.id.action_homeFragment_to_actEmpresaPerfil)}
                    R.id.nav_servico ->{
                        findNavController().navigate(R.id.action_homeFragment_to_actServicoInfo)}
                    R.id.nav_login ->{
                        findNavController().navigate(R.id.action_homeFragment_to_loginFragment)}
                    R.id.nav_conta ->{
                        findNavController().navigate(R.id.action_homeFragment_to_actDadosUsuario)}
                    R.id.nav_logout ->{
                        homeViewModel.logout()
                    }
                    R.id.nav_admuser ->{
                        findNavController().navigate(R.id.action_homeFragment_to_actUsuarioInfo)}
                    R.id.nav_admempresa ->{
                        findNavController().navigate(R.id.action_homeFragment_to_actEmpresaInfo)}
                    R.id.nav_admservico ->{
                        findNavController().navigate(R.id.action_homeFragment_to_actServicoInfo)}
                    R.id.nav_cadempresa ->{
                        findNavController().navigate(R.id.action_homeFragment_to_cadastroEmpresaFragment)}
                }
                true
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            true
        }
        return super.onOptionsItemSelected(item)
    }
}